package com.concrete.model.facade;

import com.concrete.model.exception.UserAlreadyRegisteredException;
import com.concrete.model.exception.UserRegistrationException;
import com.concrete.model.to.UserTO;

/**
 * Facade de Cadastro de Usuario
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public interface UserRegistrationFacade {

	/**
	 * Valida os dados de Cadastro
	 * 
	 * @param userTO
	 *            userTO
	 * 
	 * @throws UserRegistrationException
	 *             Caso aconteca algum erro na validação
	 * 
	 * @throws UserAlreadyRegisteredException
	 *             Caso o e-mail já esteje cadastrado na base de dados
	 */
	void validate(final UserTO userTO) throws UserRegistrationException, UserAlreadyRegisteredException;

	/**
	 * Persiste os dados do Cadastro.
	 * 
	 * @param userTO
	 *            userTO
	 * 
	 * @throws UserRegistrationException
	 *             Caso aconteça algum erro ao persistir o cadastro
	 */
	void save(final UserTO userTO) throws UserRegistrationException;

	/**
	 * Envia o email
	 * 
	 * @param userTO
	 *            userTO
	 * 
	 * @throws UserRegistrationException
	 */
	void sendEmail(final UserTO userTO) throws UserRegistrationException;
}
