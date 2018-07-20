# Java多线程

制造线程的两种方式：

1. 让你的类继承Thread
2. 让你的类实现Runable接口（主要原因是Java不支持多继承，所以当你的类需要继承别的类的时候，此时只能通过实现Runable接口）

####继承Thread

```
class Mythread extends Thread {
    int[] values = {6, 5, 1, 7, 8};
     //必须命名为run才能被start正确调用（重写run方法是你的责任）
    public void run() {
        for (int i = 0; i < 5; i++) {
            values[i] = values[i] * 2;
        }
    }
}
```

调用：

```
Thread t1 = new MyThread();
t1.start(); //启动进程（start内部会自动调用run方法）
```

#### 实现Runnable接口

> 让线程休眠50ms:（会抛出异常，需要被try catch块包裹）
>
> Thread.sleep(50); 
>
> 注意：sleep是一个静态方法，在哪个线程里面发起就是哪个线程睡觉。
>
> 和t2.stop()不同，这个是让别的线程终止。

```
class Hi implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Hi");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Hello implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Hello");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadDemo {
	public static void main(String args[]) {
		Runnable hi = new Hi();
		Runnable hello = new Hello();
		
		Thread t1 = new Thread(hi); //创建一个新的Thread，传入一个实现了Runnable的类
		Thread t2 = new Thread(hello);
		
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
```

由于我们只用到了Hello一次,使用匿名类即可，无需特地创建类Hi和Hello:

```
Runnable hi = new Hi();
```

改成：

```
		Runnable hello = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("Hello");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
```

但是由于这个Runnable类是一个Functional Interface，因此可以改成lambda表达式形式：

**Tips:删除掉code that can be imaged**

 new Runnable() {	@Override		public void run

是可以被想出来的，因为这个接口只有一个方法，那必定是实现这个方法，而且接口类型肯定也是之前声明的引用类型Runnable。

```
		Runnable hello = ()-> {
				for (int i = 0; i < 5; i++) {
					System.out.println("Hello");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
```

甚至可以更简洁，因为我们要把实现的匿名类作为参数传入Thread()，因此可以直接写成：

```
Thread t1 = new Thread(()-> {
				for (int i = 0; i < 5; i++) {
					System.out.println("Hello");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}); //创建一个新的Thread，传入一个实现了Runnable的类
```

### join方法

```
//join的用处：会等待t1线程结束之后，才会返回，此时本线程（main）才会继续向下执行
t1.join();
//Bye 会在线程t1结束之后才会打印
System.out.println("Bye");
```

### isAlive方法

```
//使用isAlive方法来检查线程是否还处于running state
System.out.println(t1.isAlive());
```

###查看和设置线程名

```
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		t1.setName("Hi Thread");
		t2.setName("Hello Thread");
		System.out.println(t1.getName());
		System.out.println(t2.getName());
```

可以看到线程名默认是Thread-0 Thread-1 ...

可以通过setNmame来修改。

好处是在你有很多线程的时候方便管理。

或者在创建的时候就给出名字：

```
Thread t2 = new Thread(hello, "Hello Thread");
```

### 优先级

线程的优先级： 1-10

1----最低的优先级

10--最高的优先级

5---普通优先级

可以通过getPriority方法来查看：

```
t1.getPriority()
```

可以通过来setPriority方法设置优先级：

```
t1.setPriority()
```

由于记数字难度大，可以直接使用Thread类提供的常量：

```
t2.setPriority(Thread.MAX_PRIORITY);
```

使用`Thread.currentThread()`来引用当前的线程对象