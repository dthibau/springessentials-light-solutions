package org.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {
	@Autowired
	HelloProperties props;
	
	@Value("${app.random}")
	String uuid;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		log.debug("Hello " + name);
		return props.getGreeting() + name;
	}
	
	@RequestMapping("/uuid")
	public String uuid() {
		return uuid;
	}
}