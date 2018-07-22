package com.gary.singletonDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

enum B {	
	INSTANCE;

	int i;
	public void show() {
		System.out.println(i);
	}
}


class A {
	private static A obj;
	
	private A() {
		System.out.println("A has been created.");
	}
	
//	//synchronized 表示任何时间只能有一个线程能运行这个方法
//	// 但有一个缺点，就是太慢了（使用synchronized会使程序运行时间增加100倍！）
//	public static A getInstance() {  
//		if (obj == null) {
//			obj = new A();
//		}
//		return obj;
//	}
	
	public static A getInstance() {  // Double Checked Locking
		if (obj == null) {
			synchronized (A.class) {
				if (obj == null) {
					obj = new A();
				}
			}
		}
		return obj;
	}
}

class Creation implements Runnable {

	@Override
	public void run() {
		A a = A.getInstance();
	}
	
}

public class SingletonDemo {
	
	public static void main(String[] args) {
//		A obj = A.getInstance();
//		A obj2 = A.getInstance();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				A obj1 = A.getInstance();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				A obj2 = A.getInstance();
			}
		});
	
//		Creation c = new Creation();
		
//		Thread t1 = new Thread(c);
//		Thread t2 = new Thread(c);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Done!");
		
		B obj1 = B.INSTANCE;
		obj1.i = 5;
		obj1.show();
		
		B obj2 = B.INSTANCE;
		obj2.i = 6;
		obj2.show();
		
		Collection values = new ArrayList();
		values.add(5);
		values.add(9.8);
		System.out.println(values);
		
		Iterator it = values.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
