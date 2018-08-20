#Collection and Generics in Java

1. collection --> a concept
2. Collection --> Interface
3. Collections --> Class

Generics

    package com.gary.collection;
    
    import java.util.ArrayList;
    
    // 我只希望加入Number
    class Container<T extends Number> {
    	private T value;
    	
    	public T getValue() {
    		return value;
    	}
    
    	public void setValue(T value) {
    		this.value = value;
    	}
    
    	public void show() {
    		System.out.println(value.getClass().getName());
    	}
    	
    	public void demo(ArrayList<? super T> obj) {
    		
    	}
    }
    
    
    public class GenericsDemo {
    
    	public static void main(String[] args) {
    		
    		Container<Number> obj = new Container<>();
    		obj.setValue(9.2);
    		obj.show();
    		obj.demo(new ArrayList<Object>());
    	}
    }



    		List<Integer> values = new ArrayList<>();
    		
    		values.add(3);
    		values.add(4);
    		values.add(2);
    		
    		// 通过使用Generics来实现Type Safety
    		
    		// Collection接口本身不支持在某个位置插入元素
    //		values.add(1, 5); -- Wrong!(此处value的类型是Collection)
    		// 1. 使用迭代器
    //		
    //		Iterator i = values.iterator();
    //		
    //		while (i.hasNext()) {
    //			System.out.println(i.next());
    //		}
    		
    		// sort静态方法仅仅支持List
    		// List : 
    		// 1. add values in between
    		// 2. sort elements
    		Collections.sort(values);
    		
    //		values.remove(4);
    //		System.out.println(values.remove(4));
    //		System.out.println(values.remove(4));
    		
    		// 2. ForEach循环
    //		for (int i : values) {
    //			System.out.println(i);
    //		}	
    		
    		// 3. forEach方法
    		values.forEach(System.out::println); // Stream API .. Lambda Expression

Vector和ArrayList的区别：

1. size超出了capacity的时候，Vector会把capacity扩大100%, 而ArrayList会把capacity扩大50%.
2. Vector是线程安全的（会被线程影响到的方法都有synchronized关键字），而ArrayList不是。
3. 由于Vector是线程安全的，所以速度慢；ArrayList不是，所以速度快。

一般情况下，我们使用ArrayList的机会要大于Vector（在Project里面尽量使用ArrayList）。



如何输出List里面的重复元素

关键：利用Set具有元素的唯一性这个原理。

通过foreach循环遍历List里面的每个元素，每取出一个元素，就把它add进一个Set里，并检查返回值。

如果发现返回值为false，说明这个value已经在Set里面原来就有了，那么说明这个元素是List里重复的。

注： 对于Set，重复元素插不进去（成功插入返回true，失败返回false）；对于Map，重复的key会被插入，对应的value会被更新，返回值为之前这个key对于的value。

使用比较器Comparator接口来比较对象

1. 基本类型：

    		// compare the last bit
    		// 返回1代表交换他们
    		// 返回-1代表不要交换
    		Comparator<Integer> c = new Comparator<Integer>() {
    
    			@Override
    			public int compare(Integer o1, Integer o2) {
    				
    				return o1%10 > o2%10 ? 1 : -1;
    			}
    			
    		};
    		
    		Collections.sort(values, c);

1. 对象：

    public class DemoComparator {
    
    	public static void main(String[] args) {
    		List<Student> stus = new ArrayList<>();
    		stus.add(new Student(1, 59));
    		stus.add(new Student(2, 69));
    		stus.add(new Student(3, 79));
    		stus.add(new Student(4, 49));
    		stus.add(new Student(5, 39));
    		
    		Collections.sort(stus, 
    				(s1, s2) -> s1.grade > s2.grade ? 1 
    						: s1.grade < s2.grade ? -1 : 0);
    		
    		for (Student s : stus) {
    			System.out.println(s.toString());
    		}
    	}
    
    }
    
    class Student {
    	int sno;
    	int grade;
    	
    	public Student(int sno, int grade) {
    		super();
    		this.sno = sno;
    		this.grade = grade;
    	}
    
    	@Override
    	public String toString() {
    		return "Student [sno=" + sno + ", grade=" + grade + "]";
    	}
    }

实现Comparable接口来获得比较的能力

实现了Comparable接口的对象知道该如何sort自己。

注意：实现了Comparable接口，则需要实现compareTo方法，使用实现了Comparator接口的比较器，则需要实现compare方法。

我们可以把比较器理解成是一个第三方，它要来比较两个对象，因此compare方法的参数有两个。

而实现了Comparable接口的对象具有了比较的能力，是他自己和别的对象进行比较，因此参数只有一个。

实现了Comparable接口的Student类如下：

    class Student implements Comparable<Student> {
    	int sno;
    	int grade;
    	
    	public Student(int sno, int grade) {
    		super();
    		this.sno = sno;
    		this.grade = grade;
    	}
    
    	@Override
    	public String toString() {
    		return "Student [sno=" + sno + ", grade=" + grade + "]";
    	}
    
    	@Override
    	public int compareTo(Student s) {
    		return this.grade - s.grade;
    	}
    }

此时，排序函数可以直接使用：

    Collections.sort(values);

而不需要传入比较器c。

何时使用Comparable，何时使用Comparator？

如果这个是你自己实现的类，而且你想要拿它作比较，那么最好实现Comparable接口。

如果你使用的是內建的类，那么只能使用Comparator，因为你不好去修改别人写的类。



Map

注意：map和set都不支持多个key相同的case。

1. HashMap 非线程安全的，速度慢，可以支持key或value为null的情况
2. HashTable 线程安全的，速度快，不能支持key或value为null的情况
   （Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好）
3. LinkedHashMap 保持插入的次序
4. TreeHashMap 有序的

    		Map<String, String> map = new HashMap<>();
    		map.put("myName", "Gary");
    		map.put("actor", "John");
    		map.put("ceo", "Marisa");
    		
    		// 打印map的三种方式
    		// 1. 直接使用Map的toString函数
    		System.out.println(map);
    //		System.out.println(map.get("myName"));
    		
    		// 2. 通过Map提供的keySet方法来获得key的Set，
    		// 然后通过Map的getValue方法来获得key对应的value
    		Set<String> keys = map.keySet();
    		
    		for (String key : keys) {
    			System.out.println(key + " " + map.get(key));
    		}
    		
    		Map v = new Hashtable();
    		
    		// 3. 通过Map的entrySet方法来获得每个Entry(一个键值对叫做一个Map)
    		// 每个Entry的类型是Map提供的接口Map.Entry<K,V>指定的
    		Set<Map.Entry<String, String>> values = map.entrySet();
    		for(Map.Entry<String, String> e : values) {
    			System.out.println(e.getKey() + " : " + e.getValue());
    		}



Set

1. HashSet 通过foreach循环输出的value是无序的
2. TreeSet 通过foreach循环输出的value是有序的(一般从小到大)

    		Set<Integer> values = new HashSet<>();
    		values.add(10);
    		values.add(5);
    		System.out.println(values.add(6));
    		values.add(9);
    		values.add(233);
    		values.add(7);
    		values.add(687);
    		System.out.println(values.add(6));
    		
    		for (int i : values) {
    			System.out.println(i);
    		}
    		System.out.println(values);


