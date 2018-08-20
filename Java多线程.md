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



### 一个常见的错误

```
class Simple {
    int a = 1, b = 2;
    void synchonized to() { a = 3; b = 4;}
    void fro() {println("a=" + a + ",b=" + b);}
}
```

注意到对于同步方法来说，相当于：

```
 void to() {
    synchonized(this) {
   		a = 3; b = 4;
	}
 }
```

相当于：

```
lock s;
evaluate a=3; b=4;
unlock s;
(s is a instance of Simple)
```

在这种情况下，如果有两个线程分别调用了to()和fro()（假设调用的是同一个对象的两个两个方法），我们可以发现即使执行了to()，该函数获取了锁，但是由于fro()函数不需要获得锁就可以进入执行，因此还是可能出现interleaving的情况。

同步只在所有人都checking the lock 的情况下才管用！ 

以下写法才正确：

```
class Simple {
    int a = 1, b = 2;
    void synchonized to() { a = 3; b = 4;}
    void synchonized fro() {println("a=" + a + ",b=" + b);}
}
```

即使没有synchronization，一个变量也只应该有被某个进行写入的值，也就是说，写入值的过程应该是原子的，但是Java由于效率的原因，其double的值却不是原子的，也就是说，线程1想对a写入3.14，线程2想对a写入2.78，最后写入的值可能不是3.14也不是2.78，反而是一个"out of air value"，比如说3.78. 这在一般的atomic type都不会发生（Java保证了它们的写都是原子的），但是有一个例外，那就是double（出于性能的的考虑）。

因此，我们在使用double的时候，如果可能出现多个线程访问这个变量，则必须要对它声明volatile，这样才能保证不出现上述的异常情况。

### 线程同步

    package com.gary.thread;
    
    class Q {
    	private int num;
    	// false-空 full-满
    	boolean valueSet = false;
    	
    	// 当我们使用了wait和notify，我们需要让方法是synchronized
    	synchronized public void put(int num) {
    		
    		// 如果valueSet里面已经是满的，阻塞
    		while(valueSet) {
    			try {
    				wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    		
    		System.out.println("Put: " + num);
    		this.num = num;
    		// 放入了值,需要让valueSet变full
    		valueSet = true;
    		// 通知Consumer线程
    		notify();
    	}
    	
    	synchronized public void get() {
    		// 如果valueSet里面没有放入东西，阻塞
    		while(!valueSet) {
    			try {
    				wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    		System.out.println("Get: " + num);
    		// 消费掉了,需要让valueSet变空
    		valueSet = false;
    		// 通知producer线程
    		notify();
    	}
    }
    
    class Producer implements Runnable {
    
    	Q q;
    	
    	public Producer(Q q) {
    		super();
    		this.q = q;
    		Thread t = new Thread(this, "Producer");
    		t.start();
    	}
    	
    	@Override
    	public void run() {
    		int i = 0;
    		while(true) {
    			q.put(i++);
    			try {
    				// 可以看到当生产者生产得很慢的时候，消费者也只能等着
    				Thread.sleep(5000);
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}	
    }
    
    class Consumer implements Runnable {
    
    	Q q;
    	
    	public Consumer(Q q) {
    		super();
    		this.q = q;
    		Thread t = new Thread(this, "Consumer");
    		t.start();
    	}
    	
    	@Override
    	public void run() {
    		int i = 0;
    		while(true) {
    			q.get();
    			try {
    				Thread.sleep(1000);
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}	
    }
    
    public class InterThread {
    	public static void main(String[] args) {
    		Q q = new Q();
    		new Producer(q);
    		new Consumer(q);
    	}
    }


