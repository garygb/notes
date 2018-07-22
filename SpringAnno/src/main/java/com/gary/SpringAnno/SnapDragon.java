package com.gary.SpringAnno;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//注意到直接使用@Component的话，id默认是snapDragon（也就是把首字母小写了）
//也可以显式地指定id
@Component("mySnapDragon")
public class SnapDragon implements MobileProcessor {

	public void process() {
		System.out.println("World's best CPU.");
	}

}
