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


### 练习：使用传统的方式对List排序和定制输出

```
@FunctionalInterface
interface Condition {
	boolean test(Person p);
}

public class Unit1ExerciseSolutionJava7 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39)
				);
		
		// Step 1: Sort list by last name
		Collections.sort(people, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				// compareTo() 比较字典顺序
				return o1.getLastName().compareTo(o2.getLastName());
			}
			
		});
		// Step 2: Create a method that prints all elements in the list
		System.out.println("Printing all persons:");
		printAll(people);
		
		System.out.println("---");
		System.out.println("Printing persons with first name with 'C':");
		// Step 3: Create a method that prints all people that have first name beginning with C
		printConditionally(people, new Condition() {
			
			@Override
			public boolean test(Person p) {
				if (p.getFirstName().startsWith("C")) {
					return true;
				}
				return false;
			}
		});
	}

	private static void printAll(List<Person> people) {
		for (Person p : people) {
			System.out.println(p);
		}
	}

	private static void printConditionally(List<Person> people, Condition condition) {
		for (Person p : people) {
			if (condition.test(p)) {
				System.out.println(p);
			}
		}
	}

}
```

### 将练习改成Lambda表达式


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

于是：

```
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Charles", "Dickens", 60), 
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51), 
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));

		// Step 1: Sort list by last name
		Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		// Step 2: Create a method that prints all elements in the list
		System.out.println("Printing all persons:");
//		printAll(people);
		//printAll可以看成是printConditionally的特例
		printConditionally(people, p -> true, p -> System.out.println(p));

		System.out.println("---");
		System.out.println("Printing persons with first name with 'C':");
		// Step 3: Create a method that prints all people that have first name beginning
		// with C
		// 可以定制行为（输出）
		printConditionally(people, p -> p.getFirstName().startsWith("C"), p -> System.out.println(p.getFirstName()));
	}

//	private static void printAll(List<Person> people) {
//		for (Person p : people) {
//			System.out.println(p);
//		}
//	}

	// passing the behavior(print)
	// using the predefined Interface
	private static void printConditionally(List<Person> people, Predicate<Person> condition, Consumer<Person> consumer) {
		for (Person p : people) {
			if (condition.test(p)) {
//				System.out.println(p);
				consumer.accept(p);
			}
		}
	}
```



## Unit 2: More Lambda Features

### 异常处理

看以下这个情况：

```
	public static void main(String[] args) {
		int[] someNumbers = {1, 2, 3, 4};
		int key = 2;
		
		process(someNumbers, key, (i, k) -> System.out.print(i/k+" "));
		
	}

	// for each element in the array, apply the behavior using key
	private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		for (int i = 0; i < someNumbers.length; i++) {

		}
	}
```

我们有一个函数process，功能是获得一个数组someNumbers，获得一个数字key，获得一个行为，我们需要对数组的每个Entry都apply这个行为（使用key为参数）。在main函数里面我们传入了这个行为，比如说对数组的每个entry都除以key。

可是key可能为0，当key为0的时候，我们的accept函数就会抛出异常，那么我们该在哪里捕捉这个异常呢？

如上面写的，我们在`consumer.accept(someNumbers[i], key);`后面直接捕捉这个异常：

```
try {
	consumer.accept(someNumbers[i], key);
} catch(ArithmeticException e) {
	// Handle the exception
	e.printStackTrace();
}
```

但是这样我们又不知道这个行为是什么，到底会抛出什么异常，因此直接在accept后面写大量的catch块是不太合适的。

第二种方式是把异常处理逻辑写在lambda表达式里面，但是这样写非常地不优雅：

```
		process(someNumbers, key, (i, k) -> {
			try {
				System.out.print(i/k+" ");
			} catch(ArithmeticException e) {
				System.out.println("An Arithmetic exception happened.");
			}
		});
```

解决方法是：

写一个lambda的包裹函数，传入一个lambda，返回一个同样类型的lambda，并在这个包裹函数里面进行异常处理：

```
// 之后可以把这个wrapper函数写成泛型的，这样更通用
private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {

		return (i, k) -> {
			try {
				consumer.accept(i, k);
			} catch (ArithmeticException e) {
				System.out.println("An Arithmetic exception happened.");
			}
		};

	}
```

调用的时候只要再加一层即可：

```
process(someNumbers, key, wrapperLambda((i, k) -> System.out.print(i / k + " ")));
```

### Closures

闭包是引用了自由变量的函数（或对象）。

如下，我们在创建匿名类的时候，我们之间使用了类外面的变量（这个变量的作用域包括了这个匿名类），在Java 8 以前，我们需要声明这个变量是final的，在Java 8 中，我们不需要声明（这个变量b叫做effectively final），但是编译器还是会帮你做检查，我相信你不会去改变b的值，但是我还是会监视你，如果你做了错误的事情，我还是会抓到你。

在将这个匿名类作为参数传入doProcess方法的时候，b此时的值会被frozen，然后被传进doProcess。

```
public class ClosuresExample {

	public static void main(String[] args) {
		
		int a = 10;
		int b = 20;
		
		doProcess(a, new Process() {
			
			@Override
			public void process(int i) {
				System.out.println(i + b);
			}
		});
	}
	
	public static void doProcess(int i, Process p) {
		p.process(i);
	}
}

interface Process {
	void process(int i);
}
```

在lambda表达式里面也是一样：

```
doProcess(a, (int i) -> System.out.println(i + b));
```

b is coming from closure(using variable in the scope).

传入的lambda表达式有可能会在未来被调用，那么这个lambda表达式里面包含的自由变量会在传入Lambda表达式的时候被冰冻，之后，如果b被改变了，调用的时候还是会用原来的b值。

### this

说明了lambda表达式并不是匿名内部类的语法糖。

我们知道匿名内部类里面使用this，这个this会指向这个匿名内部类。

lambda表达式并不会override这个this关键字，在lambda表达式里面的this（就是这个lambda表达所在的类的this）和在外面使用是一样的，并不会有一个“匿名类”，让这个this指向lambda表达式实现的这个匿名类。

## Unit 3: Method References and Collections

方法引用是Java 8的另一种使用lambda表达式的方法。

如果lambda表达式实际上做的事情就是透传，如下，`() -> printMessage()`做的事情就是未接受任何参数，并执行了printMessage()方法，在这种时候我们可以使用方法引用。

使用方法引用：

在原来是填写lambda表达式的地方：

1. 给出需要调用方法所在的类名（静态方法）或者对象名（非静态方法）
2. 加上::
3. 加上方法名

如下：

```
public class MethodReferenceExample1 {

	public static void main(String[] args) {
		
		// MethodReferenceExample1::printMessage === () -> printMessage()
		Thread t = new Thread(MethodReferenceExample1::printMessage);
		t.start();
	}

	public static void printMessage() {
		System.out.println("Hello");
	}
}
```

举一个有参数的例子：

```
// Lambda表达式
printConditionally(people, p -> true, p -> System.out.println(p));
// Method Reference(方法引用)
printConditionally(people, p -> true, System.out::println);
```

我们注意到，而在声明printConditionally方法的时候，声明了第三个参数的接口类型，其需要实现的方法是需要一个Person类型的参数，然后什么也不返回。此时倘若我们想写的lambda表达式的确是仅仅把传入的参数直接传给System.out对象的println方法，而且传入lambda表达式的参数和传入println的参数是完全一样的，那么我们也可以直接使用方法引用来实现。

总结一下上面两种lambda表达式可以被替换为方法引用的情况(以及第三种方法）：

1. () -> method()
2. p -> method(p)
3. (p, q) ->method(p, q)

### Iteration

1. External Iterator

   你自己来控制Iteration process。

   ```
   		List<Person> people = Arrays.asList(
   				new Person("Charles", "Dickens", 60),
   				new Person("Lewis", "Carroll", 42),
   				new Person("Thomas", "Carlyle", 51),
   				new Person("Charlotte", "Bronte", 45),
   				new Person("Matthew", "Arnold", 39)
   				);
   		System.out.println("Using for loop");
   		for (int i = 0; i < people.size(); i++) {
   			System.out.println(people.get(i));
   		}
   		System.out.println("Using for each loop");
   		for (Person p : people) {
   			System.out.println(p);
   ```

   

2. Internal Iterator

   将你对List的控制别的某个别的人。

   在Java 8中，每个Collection都有一个新的方法，叫做forEach：

   传入behavior，该循环会自动为你做遍历的事情。

   ```
   System.out.println("Using lambda for each loop");
   // forEach方法接受一个Consumer<? super Person>的参数
   people.forEach(p -> System.out.println(p));
   
   System.out.println("Using lambda for each loop");
   // 由于lambda表达式是一个pass through，因此可以改成方法引用
   people.forEach(System.out::println);
   ```

   使用forEach(Internal Iterator)的好处是，我们知道使用External Iterator，遍历的工作是完全串行的，如果不方便并发执行，而使用Internal Iterator的话，我们只要告诉编译器我们需要遍历这个List，具体该怎么遍历这个我们不需要告诉编译器，这就为它之后的parallel processing提供了可能。

### Streams

```
public class StreamsExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39)
				);
		
		// 将people这个对象放到流水线上
		// 第一位流水线工人：对于出现在流水线上的每个元素，检查它是否符合predicate的要求
		// 第二位流水线工人：对于出现在流水线上的每个元素，输出它的FirstName
		// 每个流水线上的工人都有机会改变stream
		// 最后的结果是每个流水线上功能改变的总和
		people.stream()
		.filter(p -> p.getLastName().startsWith("C")) // 如果判断为false，则该元素会从流水线上移除
		.forEach(p -> System.out.println(p.getFirstName()));
	}

}
```

stream有三个部分：

1. source：提供传送带上的elements。对于上述的代码，source是people。
2. all the operations that need to be performed on the stream. 对于上述代码，为filter方法。
3. terminal operation.对于上述的代码，为forEach方法。

另一个例子：

```
long count = people.stream()
// 这也并不会启动这个流水线
.filter(p -> p.getLastName().startsWith("C"))
// 记录流水线上元素的个数,这会启动这个流水线
.count();

System.out.println(count);
```

使用stream的好处之一是它为parallel processing提供的可能，我们对于一个流，可以将它切割成多个较小的子流，从而可以使用multicore来处理，就像增加了新的流水线。在代码和逻辑层面，完全没有变化，但是底层实现变了，从而可以利用多核的能力。对于上面的例子：

```
long count = people.parallelStream()
// 这也并不会启动这个流水线
.filter(p -> p.getLastName().startsWith("C"))
// 记录流水线上元素的个数,这会启动这个流水线
.count();
```

### 总结：Why Lambdas?

- Enables functional programming
- Readable and concise code
- Easier-to-use APIs and libraries(e.g. Collection API)
- Enables support for parallel processing

### 总结：In This Course

- Understanding lambdas
- Using lambdas
- Functional Interfaces
- Method References
- Collections improvements
