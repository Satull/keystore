package de.viadee.spring.boot.security.example.baseauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class Public {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hallo";
	}
}
