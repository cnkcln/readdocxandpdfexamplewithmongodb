package com.osi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/javainuse")
public class HelloController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return " Hello World";
	}
}