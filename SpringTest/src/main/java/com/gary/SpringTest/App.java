package com.gary.SpringTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//当你创建这个context的对象的时候，会从spring.xml文件中载入所有的配置信息
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	
        Vehicle obj = (Vehicle)context.getBean("car");
        obj.drive();

        Tyre t = (Tyre)context.getBean("tyre");
        System.out.println(t);
    }
}
