package br.com.jarvis.pbot.resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jarvis.pbot.bean.User;

@RestController
@RequestMapping("/user")
public class UserResource {
	
	@RequestMapping(value={"/create", "/update"}, method=RequestMethod.POST)
	public void save(@RequestBody User user) {
		//TODO
		if (user.getId() == null) {
			create(user);
		} else {
			update(user);
		}
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void delete() {
		//TODO destroy current user
	}
	
	private void create(User user) {
		//TODO
	}

	private void update(User user) {
		//TODO
	}
}
