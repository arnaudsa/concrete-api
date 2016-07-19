/**
 * 
 */
package com.concrete.web.controller;

import java.net.URI;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.UserAlreadyRegisteredException;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;
import com.concrete.model.transacao.UserRegistrationTransacao;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserRegistrationTransacao userRegistrationTransacao;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> save(@RequestBody final UserTO userTO) {

		LOGGER.debug("Iniciando o cadastro de usuario.");
		ResponseEntity<?> response;

		try {
			userRegistrationTransacao.registration(userTO);

			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(getLocation(userTO.getId()));
			response = new ResponseEntity<UserTO>(userTO, httpHeaders, HttpStatus.CREATED);

		} catch (final UserAlreadyRegisteredException exception) {
			final MessageError messageError = exception.getMessageError();
			response = new ResponseEntity<>(messageError, HttpStatus.CONFLICT);

		} catch (final BusinessException exception) {
			final MessageError messageError = exception.getMessageError();
			response = new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
		}

		LOGGER.debug("Fim do cadastro de usuario.");

		return response;
	}

	private URI getLocation(final String id) {
		final ServletUriComponentsBuilder request = ServletUriComponentsBuilder.fromCurrentRequest();
		final UriComponentsBuilder path = request.path("/{id}");
		return path.buildAndExpand(id).toUri();
	}
}
