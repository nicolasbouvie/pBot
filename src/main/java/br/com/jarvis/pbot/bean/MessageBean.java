package br.com.jarvis.pbot.bean;

import br.com.jarvis.pbot.model.Message;

public class MessageBean implements Bean<Message> {
	private static final long serialVersionUID = -3266401170450582906L;
	
	private Long id;
	private String key;
	private String value;
	private boolean query;

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
	public boolean isQuery() {
		return query;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}
	
	public Message toEntity() {
		Message m = new Message();
		m.setId(id);
		m.setKey(key);
		m.setValue(value);
		m.setQuery(query);
		return m;
	}
}
