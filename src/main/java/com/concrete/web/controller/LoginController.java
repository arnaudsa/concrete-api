package com.concrete.web.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.LoginAuthenticationException;
import com.concrete.model.exception.UserNotFoundException;
import com.concrete.model.facade.LoginFacade;
import com.concrete.model.to.LoginTO;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;

@RestController
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginFacade loginFacade;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> logar(@RequestBody final LoginTO loginTO) {

		LOGGER.debug("Iniciando o login de usuario." + loginTO);

		ResponseEntity<?> response;

		try {
			final UserTO userTO = loginFacade.logar(loginTO);
			response = new ResponseEntity<>(userTO, HttpStatus.OK);

		} catch (final UserNotFoundException exception) {
			final MessageError messageError = exception.getMessageError();
			response = new ResponseEntity<>(messageError, NOT_FOUND);
			LOGGER.error(messageError.getMensagens(), exception);

		} catch (final LoginAuthenticationException exception) {			
			final MessageError messageError = exception.getMessageError();
			response = new ResponseEntity<>(messageError, UNAUTHORIZED);
			LOGGER.error(messageError.getMensagens(), exception);

		} catch (final BusinessException exception) {
			final MessageError messageError = exception.getMessageError();
			response = new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
			LOGGER.error(messageError.getMensagens(), exception);
		}

		LOGGER.debug("Fim da autenticação do usuario." + loginTO);

		return response;
	}

}
