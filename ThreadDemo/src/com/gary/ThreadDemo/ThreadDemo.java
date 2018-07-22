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
		//��һ�ַ�����
		//��������ֻ�õ���Helloһ��,ʹ�������༴��
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
		

		
		//Ϊ���ʹ�������ʹ��lambda���ʽ
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
		
		//join���ô�����ȴ�t1�߳̽���֮�󣬲Ż᷵�أ���ʱ���̣߳�main���Ż��������ִ��
		t1.join();
		
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		t1.setName("Hi Thread");
		System.out.println(t1.getName());
		System.out.println(t2.getName());
		
		
		System.out.println(t1.getPriority());
		t2.setPriority(Thread.MAX_PRIORITY);
		System.out.println(t2.getPriority());
		
		//ʹ��isAlive����������߳��Ƿ񻹴���running state
		System.out.println(t1.isAlive());
		System.out.println(t2.isAlive());
		t2.join();
		//Bye �����߳�t1 ��t2������֮��Ż��ӡ
		System.out.println("Bye");
	}
}
