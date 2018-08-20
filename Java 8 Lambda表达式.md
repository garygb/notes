# Java 8 Lambda表达式

将一个行为当做参数传入方法有两种方式：

1. OOP的处理方法，创建一个含有这个方法的对象，然后将这个对象传入，之后在方法内部调用这个这个对象的相应方法。
2. 传入Lambda表达式：Lambda表达式是一个方法，这个方法可以被赋值给一个变量，就如同数据可以被赋值给变量一样！

如下：

```
public class Greeter {

	// passing bahavior in OOP
	public void greet(Greeting greeting) {
//		System.out.println("Hello world.");
		greeting.perform();
	}
	
	public static void main(String[] args) {
		Greeter greeter = new Greeter();
		Greeting helloWorldGreeting = new HelloWorldGreeting();
		greeter.greet(helloWorldGreeting);
		
		// IDEA: Interface contains the declaration of function
		// Can be used as the TYPE of variable
		//MyLambda myLambdaFunction = () -> System.out.println("Hello World.");
		// 对比一下，你会发现MyLambda刚好和Greeting接口一样!
		// 注意了： Functional Interface只能有一个方法，这个方法必须和Lambda表达式具有相同的签名
		// 你可以理解这个lambda表达式的类型刚好是Greeting，也可以理解成lambda表达式刚好实现了Functional Interface的仅有的一个方法
		Greeting lambdaGreeting = () -> System.out.println("Hello World.");
	}

}

// Interface.
// One method.
// Same signature as the lambda expression I'm trying to declare.
// Use this interface as the TYPE of the variable "myLambdaFunction"
interface MyLambda {
	// name can be anything
	void foo();
}
```

Greeting接口：

```
public interface Greeting {
	void perform();
}
```

He'llWorldGreeting类实现了Greeting接口：

```
public class HelloWorldGreeting implements Greeting {

	@Override
	public void perform() {
		System.out.println("Hello World.");
	}

}
```

那么：

(创建了一个实现了Greeting接口的类)

```
Greeting helloWorldGreeting = new HelloWorldGreeting();
```

与

(创建了一个Lambda表达式，这个表达式实现了Greeting接口的唯一声明的方法)

```
Greeting lambdaGreeting = () -> System.out.println("Hello World.");
```

有什么相同点和不同点呢？

他们都有相同的Type。

执行：

```
helloWorldGreeting.perform();
lambdaGreeting.perform();
```

可以发现，console的输出：

```
Hello World.
Hello World.
```

注意！这就是我们执行Lambda表达式的方法：通过调用接口的方法。就像这是一个类的实例。

当我们实现一个接口，通过仅仅实现接口的方法，而不是通过一个类来实现。

实际上，lambda表达式大致约等于匿名内部类的语法糖(还是有不同之处，之后会讨论)：

```
Greeting lambdaGreeting = () -> System.out.println("Hello World.");
```

等同于：

```
Greeting innerClassGreeting = new Greeting() {

	@Override
	public void perform() {
		System.out.println("Hello World.");
	}
};
```

Type Inference

    	public static void main(String[] args) {
    		// 由于声明了Type为StringLengthLambda，编译器会自动做类型检查
    		// 因此就不需要显式地在lambda表达式里面表明(String s)了，只要(s)就行（只有一个参数，括号也可以省）
    
    		printLambda(s -> s.length());
    	}
    	
    	public static void printLambda(StringLengthLambda l) {
    		System.out.println(l.getLength("Hello Lambda"));
    	}
    	
    	interface StringLengthLambda {
    		int getLength(String s);
    	}

为什么使用Interface来作为Type？ 最重要的元素是后向兼容。比如说你想使用库函数，并向其中传入你写的lambda表达式，那么你必须要重写库函数来实现“识别”你写的lambda表达式。使用Interface，那么之前可以接受Functional Interface的地方，你可以使用lambda表达式来进行传参。

### 函数式接口

在Java 8中，我们可以在Interface里面声明方法的实现。

Functional Interface是Java 8仅仅有一个抽象方法的接口。

你可以有一个接口，这个里面有三个方法，其中两个已经又了实现，只有一个没有实现，他也是Functional Interface。

函数式接口是接口的一种属性，你完全可能在这个接口中新增加了一个抽象方法，使得原来是函数式接口的接口变成了非函数式接口。

但是这和你创建一个接口给别人用来创建类实现这个接口不同，如果你希望把这个接口用作别人传入lambda表达式的Type的话，你是不希望别人对你的接口进行修改的，因为一旦对这个接口进行修改（比如说加入了一个新的方法），之后便无法传入Lambda表达式了。

对于这种问题，我们可以使用一个注解：

    @FunctionalInterface

来强制编译器对这个接口的抽象方法数量进行检查。





注意到我们的代码里面，创建Condition这个接口比较麻烦，我们在函数中使用的lambda表达式既没有使用到这个接口的名字，也没有用到这个接口的方法名，也就是说，我们的接口的名字和方法的名字是可以任意取的，只要其signature正确就行。

于是，我们可以把这些常用的signature用一个库记录下来，然后之后只要用库中的函数接口来声明我们的lambda表达式的类型就行了。

`java.util.function`包含了一些out-of-box interfaces.

我们可以把这些out-of-box interface 看成是Java提供给我们的可用的基本函数类型。

如：

```
interface Condition(Person) {
    boolean test(Person p);
}
```

这个接口，我们完全可以用库中的：

https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html#test-T-（default 代表已经声明了函数体）

```
public interface Predicate<T> {
	···
	boolean	test(T t); //abstract method
}
```

来代替。声明的时候：

```
private static void printCondintionally(List<Person> people, Predicate<Person> predicate) {
    ...
}
```

注意到，我们传入lambda表达式的时候，类型系统会检查他们的signature，而不会检查类名字和方法名字，所以这样是完全可以的。