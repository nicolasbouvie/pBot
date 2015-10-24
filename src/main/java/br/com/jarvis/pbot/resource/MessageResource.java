package br.com.jarvis.pbot.resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jarvis.pbot.bean.MessageBean;
import br.com.jarvis.pbot.model.Aula;
import br.com.jarvis.pbot.model.DAO;
import br.com.jarvis.pbot.model.Message;

@RestController
@RequestMapping("/message")
public class MessageResource extends ResourceBase {
	private static final Message DEFAULT_MESSAGE = new Message();
	static {
		DEFAULT_MESSAGE.setValue("Command key not found, please inform a valid command!");
	}

	/**
	 * Get a message from user {userId} with key {key}
	 * @param userId User that message belongs to
	 * @param key Command to search
	 * @return MessageBean with the command result or DEFAULT_MESSAGE when message not found.
	 */
	@RequestMapping(value="/{userId}/{key}", method={RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<MessageBean> get(@PathVariable Long userId, @PathVariable String key, @RequestBody(required=false) String token) {
		Message message = findMessage(userId, key.toLowerCase());
		if (message == null) {
			return new ResponseEntity<MessageBean>(DEFAULT_MESSAGE.toBean(), HttpStatus.OK);
		}
		MessageBean bean = message.toBean();
		if (bean.isQuery()) {
			if (token == null) {
				return new ResponseEntity<MessageBean>(HttpStatus.UNAUTHORIZED);
			}
			//123abcd
			//321abcd
			//abcd123

			SQLQuery query = DAO.getSession().createSQLQuery(message.getValue());
			query.setString("token", token);
			String result = (String) query.uniqueResult();
			if (result == null) {
				return new ResponseEntity<MessageBean>(HttpStatus.BAD_REQUEST);
			}
			bean.setValue(result);
		}
		return new ResponseEntity<MessageBean>(bean, HttpStatus.OK);
	}

	/**
	 * Create/Update a command to authenticated user.
	 * @param key Command key.
	 * @param value Value issued when command executed.
	 * @return String informing if message was saved successfully or not.
	 */
	@RequestMapping(value="/{key}", method=RequestMethod.POST)
	public ResponseEntity<String> save(@PathVariable String key, @RequestBody String value) {
		if (!isAuthenticated()) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		Message message = findMessage(getUser().getId(), key.toLowerCase());
		
		boolean update = message != null;
		if (!update) {
			message = new Message();
		}
		message.setKey(key.toLowerCase());
		message.setUser(getUser());
		message.setValue(value.trim());
		message.setQuery(value.trim().toUpperCase().startsWith("SELECT"));
		if (message.isQuery() && !value.contains(":token")) {
			return new ResponseEntity<String>("Query messages should bound :token parameter to it, otherwise it can't be parsed by the client", 
					HttpStatus.BAD_REQUEST);
		}
		if (update) {
			message.update();
		} else {
			message.add();
		}
		
		return new ResponseEntity<String>("Message key " + key + " - " + (update ? "updated" : "created"), update ? HttpStatus.OK : HttpStatus.CREATED);
	}

	/**
	 * Remove a message from authenticated user.
	 * @param key Command key to be removed
	 * @return String informing if message was removed.
	 */
	@RequestMapping(value="/{key}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable String key) {
		if (!isAuthenticated()) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		Message message = findMessage(getUser().getId(), key.toLowerCase());
		if (message == null) {
			return new ResponseEntity<String>("key " + key.toLowerCase() + " not found!", HttpStatus.BAD_REQUEST);
		}
		message.delete();
		return new ResponseEntity<String>("key " + key.toLowerCase() + " removed!", HttpStatus.OK);
	}
	
	private Message findMessage(Long userId, String key) {
		Query query = DAO.getSession().createQuery("from Message where user.id = :userId and key = :key");
		query.setParameter("userId", userId);
		query.setParameter("key", key.toLowerCase());
		Message message = (Message) query.uniqueResult();
		return message;
	}
}
