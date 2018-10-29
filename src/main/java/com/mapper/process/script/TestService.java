package com.mapper.process.script;

import org.springframework.stereotype.Service;

@Service
public class TestService implements IScript{
	
	public String greetings(String word) {
		System.out.println("greetings======="+word);
		return word;
	}
}
