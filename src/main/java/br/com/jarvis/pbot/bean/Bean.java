package br.com.jarvis.pbot.bean;

import java.io.Serializable;

public interface Bean<T> extends Serializable {
	T toEntity();
}
