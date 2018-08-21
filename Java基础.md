### Object Cloning

    public class Cloning {
    
    	public static void main(String[] args) throws CloneNotSupportedException {
    		/**
    		 * 1. Shallow
    		 * 2. Deep
    		 * 3. Clone
    		 */
    		Abc obj = new Abc();
    		obj.i = 5;
    		obj.j = 6;
    		System.out.println(obj);
    		
    		Abc obj1 = obj.clone();
    	}
    
    }
    
    class Abc {
    	int i;
    	int j;
    	@Override
    	public String toString() {
    		return "Abc [i=" + i + ", j=" + j + "]";
    	}
    	@Override
    	protected Abc clone() throws CloneNotSupportedException {
    		// TODO Auto-generated method stub
    		return (Abc) super.clone();
    	}
    
    }

运行后会发现：

    Exception in thread "main" java.lang.CloneNotSupportedException: com.gary.cloning.Abc
    	at java.lang.Object.clone(Native Method)
    	at com.gary.cloning.Abc.clone(Cloning.java:31)
    	at com.gary.cloning.Cloning.main(Cloning.java:16)

这是因为出于安全的原因，JVM不允许你创建一个类，默认是可以被克隆的，要想实现这个功能，必须实现Cloneable接口。（注意这里Cloneable是一个Marker Interface，即在没有声明任何方法，marker Interface主要被用作permission）

实现原理如下：

    // P is a Marker Interface
    if (obj instanceof P) {
    	//Permission allowed
        obj.show();
    } else {
        //Do something that shows permission denied.
        ...
    }

常见的Marker Interface有：Serializable， Remote等。



### IO

    public class FileDemo {
    
    	public static void main(String[] args) {
    		DataOutputStream dos = null;
    		DataInputStream dis = null;
    		
    		try {
    			dos = new DataOutputStream(
    					new BufferedOutputStream(
    							new FileOutputStream("a.txt")));
    			dos.writeUTF("Demo content.");
    			dos.flush();
    
    			dis = new DataInputStream(
    					new FileInputStream("a.txt"));
    			String s = dis.readUTF();
    			System.out.println(s);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			try {
    				dos.close();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    }

注意到以上硬编码了文件名a.txt，我们可以使用：

    File f = new File("a.txt");

来产生一个File的对象，然后我们可以直接传入这个f就好了， 不用每次都传入“a.txt”,像这样：

    dis = new DataInputStream(
    					new FileInputStream(f));

### Properties File

将配置写在Properties文件中。（当然还可以写在XML中）

每个Properties文件中都有一组key=value

1. store data

    public class App {
    
    	public static void main(String[] args) throws IOException {
    		Properties p = new Properties();
    		OutputStream os = new FileOutputStream("dataConfig.properties");
    		
    		//设置property
    		// 注意这里'\'需要被转义
    		p.setProperty("url", "localhost:3306\\myDb");
    		p.setProperty("uname", "Gary");
    		p.setProperty("pass", "0000");
    		
    		// 将这个Properties对象p中的所有属性都保存到output stream指定的流中
    		// 这里是dataConfig.properties这个文件
    		// 设置output stream 和 comments
    		p.store(os, "comments");
    	}
    }

1. fetch value

    		Properties p = new Properties();
    		File file = new File("dataConfig.properties");
    		FileInputStream fis = new FileInputStream(file);
    		
    		// load properties file
    		// 将dataConfig.properties文件中的所有属性都加载到p这个Properties对象中
    		p.load(fis);
    		// 通过Properties的getProperty方法来获得value，需要指定key
    		System.out.println(p.getProperty("uname"));
    		
    		// 列出所有的Properties
    		p.list(System.out);

注意三处的不同之处：

设置时指定的value：

    p.setProperty("url", "localhost:3306\\myDb");

dataConfig.properties文件中的内容：

    #comments
    #Thu Aug 09 17:46:23 GMT+08:00 2018
    uname=Gary
    url=localhost\:3306\\myDb
    pass=0000

控制台输出的结果：

    Gary
    localhost:3306\myDb
    -- listing properties --
    uname=Gary
    url=localhost:3306\myDb
    pass=0000

### Serialization

序列化对象，以数据格式保存：

    public class SerialDemo {
    
    	private static ObjectInputStream ois = null;
    	private static ObjectOutputStream oos = null;
    
    	public static void main(String[] args) {
    		Save obj = new Save();
    		obj.i = 4;
    		
    		File f = new File("Obj.txt");
    		
    		try {
    			oos = new ObjectOutputStream(
    					new FileOutputStream(f));
    			oos.writeObject(obj);


​    			
    			ois = new ObjectInputStream(
    					new FileInputStream(f));
    			Save obj1 = (Save) ois.readObject();
    			System.out.println(obj1.i);
    		} catch (IOException e) {
    			e.printStackTrace();
    		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			try {
    				oos.close();
    				ois.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    
    	}
    
    }
    
    // 出于安全性的考量，Java不允许直接保存对象
    // 想要实现writeObject，必须实现Serializable接口(Marker Interface)
    class Save implements Serializable {
    	/**
    	 * 
    	 */
    	private static final long serialVersionUID = 8445701397706283104L;
    
    	int i;
    
    }

但是以数据的方式保存对象状态有一个缺点，也就是如果class structure改变了，那么再次从序列化的对象文件中读取数据的时候就会错误。

解决的办法就是以XML的方式来保存对象状态（优点是别的机器，别的技术也能读取，没有兼容性的问题）：

使用XMLEncoder来帮我们完成这个工作：

    public class SerializeXML {
    
    	private static XMLEncoder x;
    
    	public static void main(String[] args) {
    		Student st1 = new Student();
    		st1.setRollno(101);
    		st1.setName("Gary");
    		
    		Student st2 = new Student();
    		st2.setRollno(102);
    		st2.setName("Marry");
    		
    		List<Student> students = new ArrayList<>();
    		students.add(st1);
    		students.add(st2);
    		
    		College c = new College();
    		c.setStudents(students);
    		
    		// Serialize the College object
    		try {
    			x = new XMLEncoder(
    					new BufferedOutputStream(
    							new FileOutputStream("myCollege.xml")));
    			x.writeObject(c);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} finally {
    			x.close();
    		}
    	}
    	
    }

College类：

    public class College {
    
    	private List<Student> students;
    
    	public List<Student> getStudents() {
    		return students;
    	}
    
    	public void setStudents(List<Student> students) {
    		this.students = students;
    	}
    
    }

Student类：

    public class Student {
    
    	private int rollno;
    	private String name;
    	
    	public int getRollno() {
    		return rollno;
    	}
    	public void setRollno(int rollno) {
    		this.rollno = rollno;
    	}
    	public String getName() {
    		return name;
    	}
    	public void setName(String name) {
    		this.name = name;
    	}
    	
    	@Override
    	public String toString() {
    		return "Student [rollno=" + rollno + ", name=" + name + "]";
    	}
    }

运行之后，保存在myCollege.xml里面的内容为：

    <?xml version="1.0" encoding="UTF-8"?>
    <java version="1.8.0_45" class="java.beans.XMLDecoder">
     <object class="com.gary.file.model.College">
      <void property="students">
       <object class="java.util.ArrayList">
        <void method="add">
         <object class="com.gary.file.model.Student">
          <void property="name">
           <string>Gary</string>
          </void>
          <void property="rollno">
           <int>101</int>
          </void>
         </object>
        </void>
        <void method="add">
         <object class="com.gary.file.model.Student">
          <void property="name">
           <string>Marry</string>
          </void>
          <void property="rollno">
           <int>102</int>
          </void>
         </object>
        </void>
       </object>
      </void>
     </object>
    </java>

使用XMLDecoder来完成反序列化：

    public class DeserializeXML {
    
    	private static XMLDecoder x;
    
    	public static void main(String[] args) {
    		try {
    			x = new XMLDecoder(
    					new BufferedInputStream(
    							new FileInputStream("myCollege.xml")));
    			College c = (College) x.readObject();
    			
    			List<Student> s = c.getStudents();
    			for (Student val : s) {
    				System.out.println(val);
    			}
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} finally {
    			x.close();
    		}
    		
    	}
    }

### javap命令

用来反编译.class文件，只能获得对象的结构，方法内部逻辑的实现无法获得。

    D:\eclipse-workspace-new\FileDemo\bin\com\gary\file>javap Save.class
    Compiled from "SerialDemo.java"
    class com.gary.file.Save implements java.io.Serializable {
      int i;
      com.gary.file.Save();
    }
    
    D:\eclipse-workspace-new\FileDemo\bin\com\gary\file>javap java.lang.object
    错误: 找不到类: java.lang.object
    
    D:\eclipse-workspace-new\FileDemo\bin\com\gary\file>javap java.lang.Object
    Compiled from "Object.java"
    public class java.lang.Object {
      public java.lang.Object();
      public final native java.lang.Class<?> getClass();
      public native int hashCode();
      public boolean equals(java.lang.Object);
      protected native java.lang.Object clone() throws java.lang.CloneNotSupportedEx
    ception;
      public java.lang.String toString();
      public final native void notify();
      public final native void notifyAll();
      public final native void wait(long) throws java.lang.InterruptedException;
      public final void wait(long, int) throws java.lang.InterruptedException;
      public final void wait() throws java.lang.InterruptedException;
      protected void finalize() throws java.lang.Throwable;
      static {};
    }

使用Reflection API调用私有方法

    public class ReflectionDemo {
    
    	private void privateMethod() {
    		System.out.println("hello");
    	}
    	
    	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    		Class<?> c = Class.forName("com.gary.reflection.ReflectionDemo");
    		ReflectionDemo r = (ReflectionDemo) c.newInstance();
    		// name of method, argument types.
    		Method m = c.getDeclaredMethod("privateMethod", null);
    		m.setAccessible(true);
    		// object, arguments
    		m.invoke(r, null);
    	}
    }

注意到使用这种方法其实不仅能调用该类的私有方法，其实还能调用其他类的私有方法，那么私有的意义何在？

其实，使用反射来调用方法主要是用于debugging。

### interface的种类

1. normal

   里面声明了多个方法

2. single abstract method (sam interface, Functional Interface)

   里面只声明了一个方法

3. marker interface

   里面没有声明任何方法

- interface里的方法默认是public abstract的，因此无需显式地声明。

### “==”和“equals()”方法比较字符串的异同

equals 方法是 java.lang.Object 类的方法。

有两种用法说明：

（1）对于字符串变量来说，使用“==”和“equals()”方法比较字符串时，其比较方法不同。

“==”比较两个变量本身的值，即两个对象在内存中的首地址。

“equals()”比较字符串中所包含的内容是否相同。

比如：

String s1,s2,s3 = "abc", s4 ="abc" ;

s1 = new String("abc");

s2 = new String("abc");

 那么：

s1==s2   是 false      //两个变量的内存地址不一样，也就是说它们指向的对象不 一样，

故不相等。

s1.equals(s2) 是 true    //两个变量的所包含的内容是abc，故相等。

注意（1）：

如果：          StringBuffer s1 = new StringBuffer("a");
                      StringBuffer s2 = new StringBuffer("a");
                      

结果：           s1.equals(s2)  //是false

解释：StringBuffer类中没有重新定义equals这个方法，因此这个方法就来自Object类，
 而Object类中的equals方法是用来比较“地址”的，所以等于false.

注意（2）：

对于s3和s4来说，有一点不一样要引起注意，由于s3和s4是两个字符

串常量所生成的变量，其中所存放的内存地址是相等的，

所以s3==s4是true（即使没有s3=s4这样一个赋值语句）

（2）对于非字符串变量来说，"=="和"equals"方法的作用是相同的都是用来比较其

对象在堆内存的首地址，即用来比较两个引用变量是否指向同一个对象。

比如：

```
class A {
	A obj1 = new  A();
  	A obj2 = new  A();
}
```

那么：obj1==obj2是false

```
obj1.equals(obj2)是false
```

但是如加上这样一句：obj1=obj2;

那么  obj1==obj2  是true

```
obj1.equals(obj2) 是true
```

总之：equals方法对于字符串来说是比较内容的，而对于非字符串来说是比较

其指向的对象是否相同的。

```
== 比较符也是比较指向的对象是否相同的也就是对象在对内存中的的首地址。
```

String类中重新定义了equals这个方法，而且比较的是值，而不是地址。所以是true。

### eclipse操作

Ctrl + shift + C  用来注释/取消注释

ctrl + shift + O 用来快速导入包

alt + 左箭头  后退一步

alt + 右箭头 前进一步

ctrl + shift + T 查找类

