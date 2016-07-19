/**
 * 
 */
package com.concrete.model.facade;

import static com.concrete.model.constants.MensagemConstants.DADOS_INVALIDOS;
import static com.concrete.model.constants.MensagemConstants.DDD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.EMAIL_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.NOME_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.PASSWORD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.TELEFONE_NAO_INFORMADO;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

import com.concrete.model.entity.User;
import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.UserRegistrationException;
import com.concrete.model.facade.impl.UserRegistrationFacadeImpl;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.repository.PhoneRepository;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;
import com.concrete.model.util.Email;

/**
 * Classe de teste de {@link UserRegistrationFacadeImpl}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationFacadeTest {

	private final int INTEGER_ZERO = NumberUtils.INTEGER_ZERO.intValue();

	@Mock
	private UserRepository userRepository;

	@Mock
	private PhoneRepository phoneRepository;

	@Mock
	private Email email;

	@Mock
	private JavaMailSender mailSender;

	@InjectMocks
	private final UserRegistrationFacade userRegistrationFacade = new UserRegistrationFacadeImpl();

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public final void testValidateSucesso() throws BusinessException {
		userRegistrationFacade.validate(userTO);
	}

	@Test
	public final void testValidateDadosInvalidos() {
		try {
			userRegistrationFacade.validate(null);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(DADOS_INVALIDOS, getOneErro(exception));
		}
	}

	@Test
	public final void testValidateNameInvalido() {
		try {
			userTO.setName(null);
			userRegistrationFacade.validate(userTO);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(NOME_NAO_INFORMADO, getOneErro(exception));
		}
	}

	@Test
	public final void testValidateEmailInvalido() {
		try {
			userTO.setEmail(null);
			userRegistrationFacade.validate(userTO);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(EMAIL_NAO_INFORMADO, getOneErro(exception));
		}
	}

	@Test
	public final void testValidatePasswordInvalido() {
		try {
			userTO.setPassword("    ");
			userRegistrationFacade.validate(userTO);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(PASSWORD_NAO_INFORMADO, getOneErro(exception));
		}
	}

	@Test
	public final void testValidateDDDInvalido() {
		try {
			userTO.getPhones().get(INTEGER_ZERO).setDdd("  ");
			userRegistrationFacade.validate(userTO);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(DDD_NAO_INFORMADO, getOneErro(exception));
		}
	}

	@Test
	public final void testValidateNumberPhoneInvalido() {
		try {
			userTO.getPhones().get(INTEGER_ZERO).setNumber(" ");
			userRegistrationFacade.validate(userTO);
			Assert.fail();

		} catch (final BusinessException exception) {
			Assert.assertEquals(TELEFONE_NAO_INFORMADO, getOneErro(exception));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testSave() throws UserRegistrationException {

		userRegistrationFacade.save(userTO);
		Mockito.verify(userRepository).save(Mockito.any(User.class));
		Mockito.verify(phoneRepository).save(Mockito.any(List.class));
	}

	@Test
	public void testSendEmailSucesso() throws BusinessException {
		email.setMailSender(mailSender);
		userRegistrationFacade.sendEmail(userTO);
		Mockito.verify(email).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Captura o primeiro item da lista de erros
	 * 
	 * @param exception
	 *            exception
	 * 
	 * @return String de erro.
	 */
	private String getOneErro(final BusinessException exception) {
		final MessageError messageError = exception.getMessageError();
		return messageError.getMensagens().get(INTEGER_ZERO);
	}
}
