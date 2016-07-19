/**
 * 
 */
package com.concrete.model.transacao.impl;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.to.TO;
import com.concrete.model.transacao.Transacao;
import com.concrete.model.transacao.passo.Passo;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public abstract class AbstractTransacao<T extends TO> implements Transacao<T> {

	/** Constante LOG. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractTransacao.class);

	/**
	 * Lista de passos que será executada na transacao.
	 * 
	 * @return List<Passo>
	 */
	protected abstract List<Passo<T>> getPassos();

	/**
	 * Nome da transacao que está sendo executada
	 * 
	 * @return Nome da Transacao
	 */
	protected abstract String getTransactionName();

	@Transactional(propagation = Propagation.REQUIRED)
	protected void execute(final T to) throws BusinessException {

		final String transactionName = getTransactionName();
		final List<Passo<T>> passos = getPassos();

		if (CollectionUtils.isEmpty(passos)) {
			throw new BusinessException(
					MessageFormat.format("Nenhum passo encontrado para a transacao {0} ", transactionName));
		}

		LOGGER.debug("Executando a transação: " + transactionName);

		executePassos(passos, to);

	}

	/**
	 * Executa os passos da Transação
	 * 
	 * @param passos
	 *            passos
	 * 
	 * @param to
	 *            to
	 * 
	 * @throws BusinessException
	 *             Caso aconteca algum erro ao executar algum passo.
	 */
	private void executePassos(final List<Passo<T>> passos, final T to) throws BusinessException {

		for (final Passo<T> passo : passos) {

			final String passoName = passo.getClass().getName();
			LOGGER.debug("Executando o passo da classe: " + passoName);

			try {
				passo.execute(to);

				LOGGER.debug(passoName + " executado com sucesso.");

			} catch (final BusinessException e) {
				final String pattern = "Erro ao executar o passo {0}  - Detalhe: [ {1} ]";
				final String stackTrace = ExceptionUtils.getStackTrace(e);
				LOGGER.error(MessageFormat.format(pattern, passoName, stackTrace));

				throw e;
			}
		}
	}

}
