package com.concrete.model.transacao.passo;

import org.apache.log4j.Logger;

import com.concrete.model.to.TO;

/**
 * Classe abstrata de passo da Transacao.
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public abstract class AbstractPasso<T extends TO> implements Passo<T> {

	/** Constante LOG. */
	protected static final Logger LOG = Logger.getLogger(AbstractPasso.class);

	/**
	 * 
	 * @return
	 */
	public static Logger getLog() {
		return LOG;
	}

}
