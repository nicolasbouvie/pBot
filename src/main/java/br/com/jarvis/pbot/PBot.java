package br.com.jarvis.pbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PBot {
	public static void main(String[] args) {
        SpringApplication.run(PBot.class, args);
	}

	@RequestMapping("/")
	public String index() {
		return "&rho;Bot";
	}
}
