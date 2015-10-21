package br.com.jarvis.pbot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.jarvis.pbot.bean.UserBean;

@Entity
public class User extends DAO<UserBean> implements Serializable {
	private static final long serialVersionUID = 6032860102804414088L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Message> messages;

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
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public UserBean toBean() {
		UserBean u = new UserBean();
		u.setId(id);
		u.setName(name);
		u.setPassword(password);
		return u;
	}
}
