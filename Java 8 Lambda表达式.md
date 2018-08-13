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

