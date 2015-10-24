package br.com.jarvis.pbot.bean;

import br.com.jarvis.pbot.model.Aula;

public class AulaBean implements Bean<Aula> {
	private static final long serialVersionUID = 2588787752191648344L;
	private Long id;
	private String token;
	private String proxima;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getProxima() {
		return proxima;
	}
	public void setProxima(String proxima) {
		this.proxima = proxima;
	}

	@Override
	public Aula toEntity() {
		Aula a = new Aula();
		a.setId(id);
		a.setToken(token);
		a.setProxima(proxima);
		return a;
	}
}
