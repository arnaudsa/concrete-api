package com.concrete.model.transacao.passo;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.to.TO;

/**
 * Interface de passos de uma Transacao.
 * 
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public interface Passo<T extends TO> {

	/**
	 * Executa um determinado passo da transacao
	 * 
	 * @param to
	 *            dados de entrada para executar passo
	 * 
	 * @throws BusinessException
	 *             erro ao executar passo
	 */
	void execute(T to) throws BusinessException;
}
