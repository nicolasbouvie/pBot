package br.com.jarvis.pbot.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jarvis.pbot.bean.Message;

@RestController
@RequestMapping("/message")
public class MessageResource {

	@RequestMapping(value="/{userId}/{key}", method=RequestMethod.GET)
	public Message get(@PathVariable Long userId, @PathVariable String key) {
		//TODO find message from key under user with id=userId
		return new Message();
	}
	
	@RequestMapping(value="/{key}/{value}", method={RequestMethod.POST, RequestMethod.PUT})
	public void save(@PathVariable String key, @PathVariable String value) {
		//TODO escape key and value, then persist in DB.
	}

	@RequestMapping(value="/{key}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String key) {
		//TODO delete message with key from DB.
	}
}
