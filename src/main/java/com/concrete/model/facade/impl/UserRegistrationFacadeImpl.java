/**
 * 
 */
package com.concrete.model.facade.impl;

import static com.concrete.model.constants.MensagemConstants.DADOS_INVALIDOS;
import static com.concrete.model.constants.MensagemConstants.DDD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.EMAIL_JA_EXISTENTE;
import static com.concrete.model.constants.MensagemConstants.EMAIL_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.NOME_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.PASSWORD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.SUBJECT_EMAIL_USER_REGISTRATION;
import static com.concrete.model.constants.MensagemConstants.SUBJECT_TOKEN_USER;
import static com.concrete.model.constants.MensagemConstants.TEXT_EMAIL_USER_REGISTRATION;

import java.text.MessageFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.concrete.model.constants.MensagemConstants;
import com.concrete.model.converter.UserConverter;
import com.concrete.model.entity.User;
import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.UserAlreadyRegisteredException;
import com.concrete.model.exception.UserRegistrationException;
import com.concrete.model.facade.UserRegistrationFacade;
import com.concrete.model.repository.PhoneRepository;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.security.Cryptography;
import com.concrete.model.security.TokenHelper;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.PhoneTO;
import com.concrete.model.to.UserTO;
import com.concrete.model.util.DateUtil;
import com.concrete.model.util.Email;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
@Service
public class UserRegistrationFacadeImpl implements UserRegistrationFacade {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private Email email;

	@Autowired
	private Cryptography cryptography;

	@Autowired
	private TokenHelper tokenHelper;

	@Override
	public void validate(final UserTO userTO) throws UserRegistrationException, UserAlreadyRegisteredException {

		final MessageError messageError = new MessageError();

		if (userTO == null) {
			messageError.addMensagem(DADOS_INVALIDOS);
			throw new UserRegistrationException(messageError);
		}

		if (StringUtils.isBlank(userTO.getName())) {
			messageError.addMensagem(NOME_NAO_INFORMADO);
		}

		if (StringUtils.isBlank(userTO.getEmail())) {
			messageError.addMensagem(EMAIL_NAO_INFORMADO);
		}

		if (StringUtils.isBlank(userTO.getPassword())) {
			messageError.addMensagem(PASSWORD_NAO_INFORMADO);
		}

		validatePhone(userTO, messageError);
		existingEmail(userTO);

		if (messageError.hasError()) {
			throw new UserRegistrationException(messageError);
		}
	}

	/**
	 * Valida os dados de telefone
	 * 
	 * @param userTO
	 *            userTO
	 * 
	 * @param messageError
	 *            messageError
	 */
	private void validatePhone(final UserTO userTO, final MessageError messageError) {

		for (final PhoneTO phone : userTO.getPhones()) {

			if (StringUtils.isBlank(phone.getDdd())) {
				messageError.addMensagem(DDD_NAO_INFORMADO);
			}

			if (StringUtils.isBlank(phone.getNumber())) {
				messageError.addMensagem(MensagemConstants.TELEFONE_NAO_INFORMADO);
			}
		}
	}

	/**
	 * @param userTO
	 * @throws UserAlreadyRegisteredException
	 *             Caso o e-mail já exista na base de dados
	 */
	private void existingEmail(final UserTO userTO) throws UserAlreadyRegisteredException {
		final String email = userTO.getEmail();
		final User userFound = userRepository.findByEmail(email);
		if (userFound != null) {
			throw new UserAlreadyRegisteredException(new MessageError(EMAIL_JA_EXISTENTE));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(final UserTO userTO) throws UserRegistrationException {

		final Calendar sysdate = Calendar.getInstance();

		final String password = userTO.getPassword();
		final String passwordEncrypt = cryptography.encrypt(password);

		final String token = createToken(userTO);

		final User userEntity = UserConverter.toEntity(userTO);
		userEntity.setCreated(sysdate);
		userEntity.setModified(sysdate);
		userEntity.setLastLogin(sysdate);
		userEntity.setToken(token);
		userEntity.setPassword(passwordEncrypt);

		userRepository.save(userEntity);
		phoneRepository.save(userEntity.getPhones());

		final String sysDateStr = DateUtil.toStr(sysdate);
		userTO.setCreated(sysDateStr);
		userTO.setModified(sysDateStr);
		userTO.setLastLogin(sysDateStr);
		userTO.setToken(token);
		userTO.setId(String.valueOf(userEntity.getId()));
	}


	/**
	 * Cria o token
	 * 
	 * @param userTO
	 * @return String do token
	 */
	private String createToken(final UserTO userTO) {
		final String user = userTO.getName();
		final String password = userTO.getPassword();
		return tokenHelper.createToken(user, password, SUBJECT_TOKEN_USER);
	}

	@Override
	public void sendEmail(final UserTO userTO) throws UserRegistrationException {

		try {
			final String to = userTO.getEmail();
			final String subject = SUBJECT_EMAIL_USER_REGISTRATION;
			final String textMessaPattern = TEXT_EMAIL_USER_REGISTRATION;
			final String textMessage = MessageFormat.format(textMessaPattern, userTO.getName(), to);

			email.send(to, subject, textMessage);

		} catch (final BusinessException e) {
			throw new UserRegistrationException(e);
		}
	}

}
