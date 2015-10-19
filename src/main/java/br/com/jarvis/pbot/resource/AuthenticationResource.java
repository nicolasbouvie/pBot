package br.com.jarvis.pbot.resource;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jarvis.pbot.resource.AuthenticationResource;

@RestController
public class AuthenticationResource {

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(HttpSession session) {
		//TODO
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpSession session) {
		//TODO
	}
}
