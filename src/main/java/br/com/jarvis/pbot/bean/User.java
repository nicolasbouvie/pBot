package br.com.jarvis.pbot.bean;

import java.io.Serializable;

//TODO
public class User implements Serializable {
	private static final long serialVersionUID = 5531220733482849309L;
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
