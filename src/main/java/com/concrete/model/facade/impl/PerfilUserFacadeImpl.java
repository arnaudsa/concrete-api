package com.concrete.model.facade.impl;

import static com.concrete.model.constants.MensagemConstants.TOKEN_NAO_AUTORIZADO;
import static com.concrete.model.constants.MensagemConstants.USER_NOT_FOUND;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concrete.model.converter.UserConverter;
import com.concrete.model.entity.User;
import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.TokenNotFoundException;
import com.concrete.model.exception.UserNotFoundException;
import com.concrete.model.facade.PerfilUserFacade;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.security.TokenHelper;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.TokenTO;
import com.concrete.model.to.UserTO;

@Service
public class PerfilUserFacadeImpl implements PerfilUserFacade {

	private static final Logger LOGGER = Logger.getLogger(PerfilUserFacadeImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenHelper tokenHelper;

	@Override
	public UserTO getPerfil(final Long id, final TokenTO tokenTO) throws BusinessException {

		final User userFound = userRepository.findOne(id);
		if (userFound == null) {
			final String mensagem = MessageFormat.format(USER_NOT_FOUND, id);
			LOGGER.info(mensagem);
			throw new UserNotFoundException(new MessageError(mensagem));	
		}

		final boolean tokenInvalido = !StringUtils.equals(userFound.getToken(), tokenTO.getToken());
		if (tokenInvalido) {
			LOGGER.info(TOKEN_NAO_AUTORIZADO);
			throw new TokenNotFoundException(new MessageError(TOKEN_NAO_AUTORIZADO));
		}

		tokenHelper.isTokenExpiration(tokenTO.getToken(), userFound.getPassword());

		return UserConverter.toTO(userFound);

	}

}
