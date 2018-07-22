package com.gary.SpringAnno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//用来specify this is a configuration file(class)

@Configuration
//Scan for the qualified Beans
@ComponentScan(basePackages="com.gary.SpringAnno")
public class AppConfig {

	// 1. 使用bean tag来生产对象
	
//	@Bean
//	public Samsung getPhone() {
//		return new Samsung();
//	}
//	
//	//检查方法的返回值类型，发现是需要的，就“生产”一个出来
//	@Bean
//	public MobileProcessor getProcessor() {
//		return new SnapDragon();
//	}
	
	// 2. 不使用Bean tag来生产对象
	
}
