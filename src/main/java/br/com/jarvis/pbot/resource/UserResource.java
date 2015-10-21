package br.com.jarvis.pbot.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jarvis.pbot.bean.UserBean;
import br.com.jarvis.pbot.model.DAO;
import br.com.jarvis.pbot.model.User;

@RestController
@RequestMapping("/user")
public class UserResource extends ResourceBase {
	
	/**
	 * Create a new user in the system.
	 * @param user UserBean with user informations, such as name and password.
	 * @return Status code HttpStatus.CREATED if user created successfully, HttpStatus.BAD_REQUEST otherwise.
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<UserBean> create(@RequestBody UserBean user) {
		User single = DAO.getSingle(User.class, "name", user.getName());
		if (single != null) {
			return new ResponseEntity<UserBean>(user, HttpStatus.BAD_REQUEST);
		}
		User entity = user.toEntity();
		entity.setId(null);
		entity.add();
		return new ResponseEntity<UserBean>(entity.toBean(), HttpStatus.CREATED);
	}
	
	/**
	 * Update user informations
	 * @param user UserBean with user informations, such as name and password.
	 * @return Status code HttpStatus.OK if user updated successfully, 
	 * HttpStatus.FORBIDDEN if user not authenticated, HttpStatus.BAD_REQUEST otherwise.
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<UserBean> update(@RequestBody UserBean user) {
		if (!isAuthenticated()) {
			return new ResponseEntity<UserBean>(user, HttpStatus.FORBIDDEN);
		}
		User sUser = super.getUser();
		if (!sUser.getName().equals(user.getName()) 
				&& DAO.getSingle(User.class, "name", user.getName()) != null) {
			return new ResponseEntity<UserBean>(user, HttpStatus.BAD_REQUEST);
		}
		User entity = DAO.getByID(User.class, sUser.getId());
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		entity.update();
		return new ResponseEntity<UserBean>(entity.toBean(), HttpStatus.OK);
	}
	
	/**
	 * Remove current authenticated user from system.
	 * @return HttpStatus.FORBIDDEN if user not authenticated, HttpStatus.OK if user successfully deleted.
	 */
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete() {
		if (isAuthenticated()) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		User sUser = super.getUser();
		DAO.getByID(User.class, sUser.getId()).delete();
		session.removeAttribute(User.class.getName());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
