1. interface的种类

   1. normal

      里面声明了多个方法

   2. single abstract method (sam interface, Functional Interface)

      里面只声明了一个方法

   3. marker interface

      里面没有声明任何方法

2. interface里的方法默认是public abstractd的，因此无需显式地声明。

3. 




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

class A

{

      A obj1   =   new  A();

      A obj2   =   new  A();

}

那么：obj1==obj2是false

            obj1.equals(obj2)是false

 

但是如加上这样一句：obj1=obj2;

那么  obj1==obj2  是true

          obj1.equals(obj2) 是true

 

总之：equals方法对于字符串来说是比较内容的，而对于非字符串来说是比较

其指向的对象是否相同的。

             == 比较符也是比较指向的对象是否相同的也就是对象在对内存中的的首地址。

  
String类中重新定义了equals这个方法，而且比较的是值，而不是地址。所以是true。



Ctrl + shift + C  用来注释/取消注释

ctrl + shift + O 用来快速导入包

alt + 左箭头  后退一步

alt + 右箭头 前进一步

ctrl + shift + T 查找类

