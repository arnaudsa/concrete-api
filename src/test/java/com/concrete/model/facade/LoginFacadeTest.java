package com.concrete.model.facade;

import static com.concrete.model.constants.MensagemConstants.DADOS_INVALIDOS;
import static com.concrete.model.constants.MensagemConstants.EMAIL_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.PASSWORD_NAO_INFORMADO;
import static com.concrete.model.constants.MensagemConstants.USER_OR_PASSWORD_INVALID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.concrete.model.entity.User;
import com.concrete.model.exception.BusinessException;
import com.concrete.model.facade.impl.LoginFacadeImpl;
import com.concrete.model.mock.enity.UserMock;
import com.concrete.model.mock.to.LoginTOMock;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.security.Cryptography;
import com.concrete.model.security.TokenHelper;
import com.concrete.model.to.LoginTO;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class LoginFacadeTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private Cryptography cryptography;

	@Mock
	private TokenHelper tokenHelper;

	@InjectMocks
	private final LoginFacade loginFacade = new LoginFacadeImpl();

	private LoginTO loginTO;

	@Before
	public void before() {
		loginTO = new LoginTOMock().createMock();
	}

	@Test
	public final void testLogarDadosInvalidos() {

		try {
			loginFacade.logar(null);

		} catch (final BusinessException e) {			
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(DADOS_INVALIDOS, messageError.firstMessage());

		}
	}

	@Test
	public final void testLogarEmailNaoInformado() {

		try {
			loginTO.setEmail(null);
			loginFacade.logar(loginTO);

		} catch (final BusinessException e) {			
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(EMAIL_NAO_INFORMADO, messageError.firstMessage());

		}
	}

	@Test
	public final void testLogarPasswordNaoInformado() {

		try {
			loginTO.setPassword(null);
			loginFacade.logar(loginTO);

		} catch (final BusinessException e) {			
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(PASSWORD_NAO_INFORMADO, messageError.firstMessage());

		}
	}

	@Test
	public final void testLogarUserNotFound() {

		try {
			final User userFound = null;
			Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userFound);
			loginFacade.logar(loginTO);

		} catch (final BusinessException e) {			
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(USER_OR_PASSWORD_INVALID, messageError.firstMessage());

		}
	}

	@Test
	public final void testLogarPasswordInvalid() {

		try {
			final User userFound = new UserMock().createMock();
			userFound.setPassword("password");

			Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userFound);
			loginFacade.logar(loginTO);

		} catch (final BusinessException e) {			
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(USER_OR_PASSWORD_INVALID, messageError.firstMessage());

		}
	}

	@Test
	public final void testLogarSucesso() throws BusinessException {

		final String password = "password";
		final User userFound = new UserMock().createMock();
		userFound.setPassword(password);

		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userFound);
		Mockito.when(cryptography.encrypt(Mockito.anyString())).thenReturn(password);

		final UserTO userTO = loginFacade.logar(loginTO);
		Assert.assertNotNull(userTO);
		Assert.assertEquals(password, userTO.getPassword());
	}

}
