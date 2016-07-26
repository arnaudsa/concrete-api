package com.concrete.web.controller;

import static com.concrete.model.constants.MensagemConstants.TOKEN;
import static com.concrete.model.constants.MensagemConstants.TOKEN_NAO_AUTORIZADO;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.TokenNotFoundException;
import com.concrete.model.exception.UserNotFoundException;
import com.concrete.model.facade.PerfilUserFacade;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.TokenTO;
import com.concrete.model.to.UserTO;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	private static final Logger LOGGER = Logger.getLogger(PerfilController.class);

	@Autowired
	private PerfilUserFacade perfilUserFacade;

	@RequestMapping(value="/{id}", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> save(@RequestBody final TokenTO tokenTO, @PathVariable final Long id, final HttpSession session) throws BusinessException {

		LOGGER.debug("Iniciando o perfil de usuário.");

		try {
			final boolean tokenInvalido = isTokenInvalido(tokenTO, session);
			if (tokenInvalido) {
				final MessageError messageError = new MessageError(TOKEN_NAO_AUTORIZADO);
				return new ResponseEntity<>(messageError, UNAUTHORIZED);
			}

			final UserTO userTO = perfilUserFacade.getPerfil(id, tokenTO);
			return new ResponseEntity<>(userTO, HttpStatus.OK);

		} catch (final UserNotFoundException exception) {
			final MessageError messageError = exception.getMessageError();
			LOGGER.error(messageError.getMensagens(), exception);
			return new ResponseEntity<>(messageError, NOT_FOUND);

		} catch (final TokenNotFoundException exception) {
			final MessageError messageError = exception.getMessageError();
			LOGGER.error(messageError.getMensagens(), exception);
			return new ResponseEntity<>(messageError, UNAUTHORIZED);
		}
	}

	/**
	 * Valida o token
	 * 
	 * @param tokenTO
	 * @param session
	 * @return 
	 */
	private boolean isTokenInvalido(final TokenTO tokenTO, final HttpSession session) {

		if (tokenTO == null || StringUtils.isBlank(tokenTO.getToken())) {
			return true;
		}

		final String tokenSession = (String) session.getAttribute(TOKEN);
		final String tokenTrim = StringUtils.trim(tokenTO.getToken());

		return ! StringUtils.equals(tokenSession, tokenTrim);
	}

}
