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
		// �Ա�һ�£���ᷢ��MyLambda�պú�Greeting�ӿ�һ��!
		// ע���ˣ� Functional Interfaceֻ����һ��������������������Lambda���ʽ������ͬ��ǩ��
		// �����������lambda���ʽ�����͸պ���Greeting��Ҳ��������lambda���ʽ�պ�ʵ����Functional Interface�Ľ��е�һ������
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
