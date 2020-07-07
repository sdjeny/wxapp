package org.sdjen.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/") // wxapp
@SpringBootApplication
public class MainTest {

	public static void main(String[] args) {
		SpringApplication.run(MainTest.class, args);
	}

	@RequestMapping("/.well-known/acme-challenge/UNK5IwMkU437fkNXjaBA6hDc0qHXwkdGtZj_Hr6cmao")
	@ResponseBody
	private String test() {
		return "UNK5IwMkU437fkNXjaBA6hDc0qHXwkdGtZj_Hr6cmao.lN46OSErBxeuCDse6mB4R1TkvZVElAeTkCpa71HcMvE";
	}

	@RequestMapping("/")
	@ResponseBody
	private String hello() {
		return "hello";
	}
}
