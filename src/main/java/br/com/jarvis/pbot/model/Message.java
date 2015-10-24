package br.com.jarvis.pbot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.jarvis.pbot.bean.MessageBean;

@Entity
public class Message extends DAO<MessageBean> implements Serializable {
	private static final long serialVersionUID = -4611129291161146734L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String key;
	private String value;
	private boolean query;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isQuery() {
		return query;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}

	@Override
	public MessageBean toBean() {
		MessageBean m = new MessageBean();
		m.setId(id);
		m.setKey(key);
		m.setValue(value);
		m.setQuery(query);
		return m;
	}
}
