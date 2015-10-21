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
public class AuthenticationResource extends ResourceBase {

	/**
	 * Authenticate user, this method will set User informations in session.
	 * @param bean UserBean containing user informations, such as name and password.
	 * @return true if user is allowed, false otherwise.
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Boolean> login(@RequestBody UserBean bean) {
		if (session.getAttribute(User.class.getName()) == null) {
			User user = DAO.getSingle(User.class, "name", bean.getName());
			if (user == null || !user.getPassword().equals(bean.getPassword())) {
				return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
			}
			session.setAttribute(User.class.getName(), user);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/**
	 * Method to invalidate user session.
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout() {
		session.invalidate();
	}
}
