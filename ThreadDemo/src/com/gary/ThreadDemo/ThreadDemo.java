package com.gary.ThreadDemo;

import javax.annotation.Generated;

//class Hi implements Runnable {
//	public void run() {
//		for (int i = 0; i < 5; i++) {
//			System.out.println("Hi");
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
//
//class Hello implements Runnable {
//	public void run() {
//		for (int i = 0; i < 5; i++) {
//			System.out.println("Hello");
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}

public class ThreadDemo {
	
	private int i;
	
	
	public static void main(String args[]) throws InterruptedException {
//		Runnable hi = new Hi();
//		Runnable hello = new Hello();
		//另一种方法：
		//由于我们只用到了Hello一次,使用匿名类即可
//		Runnable hello = new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 5; i++) {
//					System.out.println("Hello");
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		
		

		
		//为降低代码量，使用lambda表达式
		Runnable hello = ()-> {
				for (int i = 0; i < 5; i++) {
					System.out.println("Hello " + Thread.currentThread().getPriority());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		
		Thread t1 = new Thread(()->{ 
			for (int i = 0; i < 5; i++) {
				System.out.println("Hi");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} );
		
		Thread t2 = new Thread(hello, "Hello Thread");
		
		t1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		
		//join的用处：会等待t1线程结束之后，才会返回，此时本线程（main）才会继续向下执行
		t1.join();
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		t1.setName("Hi Thread");
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		
		
		System.out.println(t1.getPriority());
		t2.setPriority(Thread.MAX_PRIORITY);
		System.out.println(t2.getPriority());
		
		//使用isAlive方法来检查线程是否还处于running state
		System.out.println(t1.isAlive());
		System.out.println(t2.isAlive());
		t2.join();
		//Bye 会在线程t1 和t2都结束之后才会打印
		System.out.println("Bye");
	}
}
