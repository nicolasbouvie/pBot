package br.com.jarvis.pbot.resource;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jarvis.pbot.model.User;

public abstract class ResourceBase {
	@Autowired
	protected HttpSession session;
	
	protected boolean isAuthenticated() {
		return getUser() != null;
	}
	
	protected User getUser() {
		return (User) session.getAttribute(User.class.getName());
	}
}
