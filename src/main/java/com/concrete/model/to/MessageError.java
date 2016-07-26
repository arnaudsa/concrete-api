package com.concrete.model.to;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class MessageError extends TO {

	private static final long serialVersionUID = 1L;

	private List<String> mensagens = new ArrayList<>();

	/**
	 * Construtor Default
	 */
	public MessageError() {
		super();
	}

	public MessageError(final String mensagem) {
		super();
		mensagens.add(mensagem);
	}

	public void addMensagem(final String mensagem) {
		mensagens.add(mensagem);
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(final List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public String firstMessage() {
		String message = StringUtils.EMPTY;
		if (hasError()) {
			message = mensagens.get(NumberUtils.INTEGER_ZERO);
		}
		return message;
	}

	public boolean hasError() {
		return CollectionUtils.isNotEmpty(mensagens);
	}

}
