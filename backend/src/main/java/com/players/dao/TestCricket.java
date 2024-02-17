package com.players.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TestCricket implements Icricket {
	public void display() {
		System.out.println("Test Cricket....");
	}
}
