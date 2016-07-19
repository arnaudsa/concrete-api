package com.concrete.model.transacao.passo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.facade.UserRegistrationFacade;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationEmailPassoTest {

	@Mock
	private UserRegistrationFacade userRegistrationFacade;

	@InjectMocks
	private UserRegistrationEmailPasso userRegistrationEmailPasso;

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public final void test() throws BusinessException {
		userRegistrationEmailPasso.execute(userTO);
		Mockito.verify(userRegistrationFacade).sendEmail(userTO);

	}

}
