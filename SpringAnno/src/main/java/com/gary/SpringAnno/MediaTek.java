package com.gary.SpringAnno;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//使用primary注解可以在多个类符合的情况下先选择这个类生产
@Primary
public class MediaTek implements MobileProcessor {

	public void process() {
		System.out.println("World's 2'nd best CPU.");
		
	}

}
