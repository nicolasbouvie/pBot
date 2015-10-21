package br.com.jarvis.pbot.bean;

import br.com.jarvis.pbot.model.User;

public class UserBean implements Bean<User> {
	private static final long serialVersionUID = 5531220733482849309L;

	private Long id;
	private String name;
	private String password;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public User toEntity() {
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setPassword(password);
		return u;
	}
}
