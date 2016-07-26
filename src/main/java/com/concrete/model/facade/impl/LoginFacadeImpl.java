package com.concrete.model.facade.impl;

import static com.concrete.model.constants.MensagemConstants.DADOS_INVALIDOS;
import static com.concrete.model.constants.MensagemConstants.EMAIL_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.PASSWORD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.SUBJECT_TOKEN_USER;
import static com.concrete.model.constants.MensagemConstants.USER_NOT_FOUND;
import static com.concrete.model.constants.MensagemConstants.USER_OR_PASSWORD_INVALID;

import java.text.MessageFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.model.converter.UserConverter;
import com.concrete.model.entity.User;
import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.LoginAuthenticationException;
import com.concrete.model.exception.UserNotFoundException;
import com.concrete.model.exception.UserRegistrationException;
import com.concrete.model.facade.LoginFacade;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.security.Cryptography;
import com.concrete.model.security.TokenHelper;
import com.concrete.model.to.LoginTO;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;

@Service
public class LoginFacadeImpl implements LoginFacade {

	private static final Logger LOGGER = Logger.getLogger(LoginFacadeImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Cryptography cryptography;

	@Autowired
	private TokenHelper tokenHelper;

	@Override
	public UserTO logar(final LoginTO loginTO) throws BusinessException  {

		isValidLogin(loginTO);

		final String email = loginTO.getEmail();
		final User userFound = userRepository.findByEmail(email);
		if (userFound == null) {
			LOGGER.info(MessageFormat.format(USER_NOT_FOUND, email));
			throw new UserNotFoundException(new MessageError(USER_OR_PASSWORD_INVALID));	
		}

		final String encryptPassword = cryptography.encrypt(loginTO.getPassword());
		final boolean passwordInvalido = ! userFound.getPassword().equals(encryptPassword);

		if (passwordInvalido) {
			LOGGER.info(USER_OR_PASSWORD_INVALID);
			throw new LoginAuthenticationException(new MessageError(USER_OR_PASSWORD_INVALID));
		}

		final Calendar agora = Calendar.getInstance();

		final String token = tokenHelper.createToken(userFound.getName(), userFound.getPassword(), SUBJECT_TOKEN_USER);
		userFound.setLastLogin(agora);
		userFound.setModified(agora);
		userFound.setToken(token);
		userRepository.saveAndFlush(userFound);

		return UserConverter.toTO(userFound);
	}


	/**
	 * Valida os dados de Login
	 * 
	 * @param loginTO
	 * 
	 * @throws BusinessException
	 * 		Caso os parametros de entrada estejam invalidos.
	 */
	private void isValidLogin(final LoginTO loginTO) throws BusinessException {

		final MessageError messageError = new MessageError();

		if (loginTO == null) {
			messageError.addMensagem(DADOS_INVALIDOS);
			throw new BusinessException(messageError);
		}

		if (StringUtils.isBlank(loginTO.getEmail())) {
			messageError.addMensagem(EMAIL_NAO_INFORMADO);
		}

		if (StringUtils.isBlank(loginTO.getPassword())) {
			messageError.addMensagem(PASSWORD_NAO_INFORMADO);
		}

		if (messageError.hasError()) {
			throw new UserRegistrationException(messageError);
		}
	}
}
