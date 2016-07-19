package com.concrete.model.transacao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.UserTO;
import com.concrete.model.transacao.UserRegistrationTransacao;
import com.concrete.model.transacao.passo.UserRegistrationEmailPasso;
import com.concrete.model.transacao.passo.UserRegistrationSavePasso;
import com.concrete.model.transacao.passo.UserRegistrationValidatePasso;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTransacaoImplTest extends UserRegistrationTransacaoImpl {

	@Mock
	private UserRegistrationValidatePasso validatePasso;

	@Mock
	private UserRegistrationSavePasso savePasso;

	@Mock
	private UserRegistrationEmailPasso emailPasso;

	@InjectMocks
	private final UserRegistrationTransacao userRegistrationTransacao = new UserRegistrationTransacaoImpl();

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public final void testGetPassos() throws BusinessException {

		userRegistrationTransacao.registration(userTO);
		Mockito.verify(validatePasso).execute(userTO);
		Mockito.verify(savePasso).execute(userTO);
		Mockito.verify(emailPasso).execute(userTO);
	}

}
