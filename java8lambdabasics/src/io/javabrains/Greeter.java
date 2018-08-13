package io.javabrains;

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
		// Can be used as the type of variable
		//MyLambda myLambdaFunction = () -> System.out.println("Hello World.");
		// 对比一下，你会发现MyLambda刚好和Greeting接口一样!
		// 注意了： Functional Interface只能有一个方法，这个方法必须和Lambda表达式具有相同的签名
		// 你可以理解这个lambda表达式的类型刚好是Greeting，也可以理解成lambda表达式刚好实现了Functional Interface的仅有的一个方法
		Greeting lambdaGreeting = () -> System.out.println("Hello World.");
		Greeting innerClassGreeting = new Greeting() {
			
			@Override
			public void perform() {
				System.out.println("Hello World.");
			}
		};
		innerClassGreeting.perform();
		lambdaGreeting.perform();
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
