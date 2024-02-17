package com.players.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("odi")
public class OdiCricket implements Icricket {
	public void display() {
		System.out.println("ODI Cricket");
	}
}
