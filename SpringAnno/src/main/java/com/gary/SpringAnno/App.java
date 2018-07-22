package com.gary.SpringAnno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
//Spring Core Annotations
public class App 
{
    public static void main( String[] args )
    {
    	//create a class who will provide me the configuration
        ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
    	
        //只有在配置类里面提供有返回这个类的方法之后，才能正常使用getBean方法
    	Samsung s7 = factory.getBean(Samsung.class);
        s7.config();
        
        
    }
}
