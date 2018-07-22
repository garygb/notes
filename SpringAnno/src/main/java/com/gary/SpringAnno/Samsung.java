package com.gary.SpringAnno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//若想让这个对象默认成为一个Bean，做法是在这个类之前加上一个 @Component 的标签
//然后在配置类里面加上一个extra annotation，@ComponentScan(basePackages="你需要查找Bean的完整包名，多个包的话使用逗号隔开")
@Component
public class Samsung {
	
	//如果没有的话，会自动让工厂生产出来
	//如果有多个类符合的话，就会出现confusion，如这里的MediaTek和SnapDragon都实现了MobileProcessor类，工厂不知道造哪个出来
	@Autowired
	//当然，也可以使用Qualifier来显式指定需要装配的对象
	@Qualifier("mySnapDragon")
	MobileProcessor cpu;
	
	public MobileProcessor getCpu() {
		return cpu;
	}

	public void setCpu(MobileProcessor cpu) {
		this.cpu = cpu;
	}

	public void config() {
		System.out.println("Octa Core, 4GB RAM");
		cpu.process();
	}
}
