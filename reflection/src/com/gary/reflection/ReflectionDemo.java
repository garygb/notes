package com.gary.reflection;

import java.lang.reflect.Method;

public class ReflectionDemo {

	public static void main(String[] args) throws Exception  {
		
		// 1. new
		Test obj = new Test();
//		obj.show();
		
		// 2. clone
		
		// 3. De-serialization
		
		// 4. Dependency injection
		
		
		Class c = Class.forName("com.gary.reflection.Test");
		System.out.println(c.getSuperclass());
		System.out.println(c.isInterface());
		
		Test t = (Test)c.newInstance();
//		t.show();
		
		//调用类对象的getDeclaredMethod函数，获得方法类，传入
		Method m = c.getDeclaredMethod("show", null);
		m.setAccessible(true);
		m.invoke(t, null);
	}

}
