# Java中protected访问权限

1. protected 访问控制符能被用于方法和成员变量。

 2. 声明为protected的方法和成员变量能被同一个包里的所有类所访问，就像默认修饰符package一样。

 3. 能被该类的子类所访问，子类可以和父类不在一个包中。

    这样，当你想让一个类中的某个方法或成员变量在包中都可见，而且其子类也能访问（子类有可能和父类不在同一个包中）但又不想让所有类都可以访问该类时，就可以用protected修饰符。

    > 可访问性：  public > protected > package >private

    **注意：**

 4. **But a subclass in another package can access the protected members in the super-class via only the references of subclass or its subclasses. A subclass in the same package doesn’t have this restriction. This ensures that classes from other packages are accessing only the members that are part of their inheritance hierarchy.**

    **但是位于与父类不同包中的一个子类，能够使用其父类中protected成员的途径只能是，使用子类（或者是子类的子类）的引用。子类和父类在同一个包中则没有这个限制。这样就保证了其他包中的类只能访问它们所继承的部分成员。**

下面的例子可以说明上述几点：（特别注意第4点）

1 创建一个父类Bird.java，放在birdpack包中，父类中有一个protected int的成员变量nFeathers：

```
package birdpack;  

public class Bird {  

    protected int nFeathers;  

} 
```

2 创建Bird的一个子类Duck1.java，放在duckpack包中，这个程序说明在子类中直接使用父类的protected变量是可以的，父类的protected权限的成员变量可以被子类继承：

```
package duckpack;  

import birdpack.Bird;  

public class Duck1 extends Bird {  

    public void setn(int duck1n) {  

        nFeathers = duck1n;  

    }  

} 
```

3 创建Bird的一个子类Duck2.java，放在duckpack包中，这个程序说明在子类中通过子类的对象访问父类的protected成员是可以的：

```
package duckpack;  

import birdpack.Bird;  

public class Duck2 extends Bird {  

    public void constructor(int newDuck2) {  

        Duck2 d2 = new Duck2();  

        //在子类中通过子类的对象访问父类中的protected变量  

        d2.nFeathers = newDuck2;  

    }  

} 
```

4 创建Bird的一个子类Duck3.java，放在duckpack包中，这个程序说明在子类中使用父类的对象访问父类的protected成员反而是不行的：

```
package duckpack;  

import birdpack.Bird;  

public class Duck3 extends Bird {  

    public void constructor(int newDuck3) {  

        Bird b = new Bird();  

        //子类中用父类对象反而不能访问父类中的protected变量  

        //b.nFeathers = newDuck3;  

    }  
    
} 
```

5 创建Bird的一个子类Swan.java，放在duckpack包中，这个程序说明在子类中使用其他子类的对象访问父类的protected成员也是不行的：

```
package duckpack;  

import birdpack.Bird;  

public class Swan extends Bird {  

    public void constructor(int swan) {  

            Duck1 d1 = new Duck1();  

            //子类中用另外一个子类的对象也不能访问父类中的protected变量  

            //d1.nFeathers = swan;  
    }  
}  
```

转载这篇文章，主要是为了说明，虽然在java中，父类中protected权限的成员变量可以被子类访问，但是还是有条件的，具体如下：

**1.在子类中直接使用父类的protected变量是可以的，父类的protected权限的成员变量可以被子类继承**

**2.在子类中通过子类的对象访问父类的protected成员是可以的**

**3.在子类中使用父类的对象访问父类的protected成员反而是不行的**

**4.在子类中使用其他子类的对象访问父类的protected成员也是不行的**
