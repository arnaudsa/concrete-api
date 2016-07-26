package com.concrete.model.facade;

import static com.concrete.model.constants.MensagemConstants.SESSAO_INVALIDA;
import static com.concrete.model.constants.MensagemConstants.TOKEN_NAO_AUTORIZADO;
import static com.concrete.model.constants.MensagemConstants.USER_NOT_FOUND;
import static com.concrete.model.mock.to.TokenTOMock.TOKEN;

import java.text.MessageFormat;

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
import com.concrete.model.exception.TokenNotFoundException;
import com.concrete.model.facade.impl.PerfilUserFacadeImpl;
import com.concrete.model.mock.enity.UserMock;
import com.concrete.model.mock.to.TokenTOMock;
import com.concrete.model.repository.UserRepository;
import com.concrete.model.security.TokenHelper;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.TokenTO;
import com.concrete.model.to.UserTO;


@RunWith(MockitoJUnitRunner.class)
public class PerfilUserFacadeTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private TokenHelper tokenHelper;

	@InjectMocks
	private final PerfilUserFacade perfilUserFacade = new PerfilUserFacadeImpl();

	private TokenTO tokenTO;

	private Long usuarioID;

	private User userFound;

	@Before
	public void name() {
		tokenTO = new TokenTOMock().createMock();
		usuarioID = 1L;
		userFound = new UserMock().createMock();
	}

	@Test
	public final void testGetPerfilUserNotFound() {

		try {
			final User userFound = null;
			Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(userFound);
			perfilUserFacade.getPerfil(usuarioID, tokenTO);
			Assert.fail();

		} catch (final BusinessException e) {
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(MessageFormat.format(USER_NOT_FOUND, usuarioID), messageError.firstMessage());
		}
	}

	@Test
	public final void testGetPerfilTokenInvalido() {

		try {
			Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(userFound);
			perfilUserFacade.getPerfil(usuarioID, tokenTO);
			Assert.fail();

		} catch (final BusinessException e) {
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(TOKEN_NAO_AUTORIZADO, messageError.firstMessage());
		}
	}

	@Test
	public final void testGetPerfilTokenExpirado() {

		try {
			userFound.setToken(TOKEN);
			Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(userFound);

			final TokenNotFoundException exception = new TokenNotFoundException(new MessageError(SESSAO_INVALIDA));
			Mockito.when(tokenHelper.isTokenExpiration(Mockito.anyString(), Mockito.anyString())).thenThrow(exception);

			perfilUserFacade.getPerfil(usuarioID, tokenTO);
			Assert.fail();

		} catch (final BusinessException e) {
			final MessageError messageError = e.getMessageError();
			Assert.assertEquals(SESSAO_INVALIDA, messageError.firstMessage());
		}
	}


	@Test
	public final void testGetPerfilSucesso() throws BusinessException {

		userFound.setToken(TOKEN);
		Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(userFound);

		final boolean tokenExpiration = false;
		Mockito.when(tokenHelper.isTokenExpiration(Mockito.anyString(), Mockito.anyString())).thenReturn(tokenExpiration);

		final UserTO userTO = perfilUserFacade.getPerfil(usuarioID, tokenTO);
		Assert.assertNotNull(userTO);
	}
}
