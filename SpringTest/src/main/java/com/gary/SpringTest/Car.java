package com.gary.SpringTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {
	
	@Autowired
	private Tyre t;
	
	public void drive() {
		System.out.println("car " + t);
	}
}
