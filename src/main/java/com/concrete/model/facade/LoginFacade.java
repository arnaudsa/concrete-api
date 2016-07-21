package com.concrete.model.facade;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.LoginAuthenticationException;
import com.concrete.model.exception.UserNotFoundException;
import com.concrete.model.to.LoginTO;
import com.concrete.model.to.UserTO;

public interface LoginFacade {

	/**
	 * Efetura o login
	 * 
	 * @param loginTO
	 * 
	 * @return o Usuario encontrado.
	 * 
	 * @throws UserNotFoundException
	 * 				Caso o e-mail não seja encontrado.
	 * 
	 * @throws LoginAuthenticationException
	 * 				Caso a senha esteja errada.
	 * 
	 * @throws BusinessException
	 * 		Caso os dados de entrada estejam incorretos. 
	 */
	UserTO logar(final LoginTO loginTO) throws UserNotFoundException, LoginAuthenticationException, BusinessException;

}
