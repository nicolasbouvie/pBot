package br.com.jarvis.pbot.bean;

import br.com.jarvis.pbot.model.Message;

public class MessageBean implements Bean<Message> {
	private static final long serialVersionUID = -3266401170450582906L;
	
	private Long id;
	private String key;
	private String value;

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
	
	public Message toEntity() {
		Message m = new Message();
		m.setId(id);
		m.setKey(key);
		m.setValue(value);
		return m;
	}
}
