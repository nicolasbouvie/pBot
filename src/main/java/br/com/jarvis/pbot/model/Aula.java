package br.com.jarvis.pbot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.jarvis.pbot.bean.AulaBean;

@Entity
public class Aula extends DAO<AulaBean> implements Serializable {
	private static final long serialVersionUID = -7822689029233026931L;

	@Id
	@GeneratedValue
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
	public AulaBean toBean() {
		AulaBean a = new AulaBean();
		a.setId(id);
		a.setProxima(proxima);
		a.setToken(token);
		return a;
	}
}
