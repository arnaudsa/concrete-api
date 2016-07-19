package com.concrete.model.transacao.passo;

import static com.concrete.model.constants.MensagemConstants.EMAIL_NAO_INFORMADO;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.exception.UserRegistrationException;
import com.concrete.model.facade.UserRegistrationFacade;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.MessageError;
import com.concrete.model.to.UserTO;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationValidatePassoTest {

	@Mock
	private UserRegistrationFacade userRegistrationFacade;

	@InjectMocks
	private UserRegistrationValidatePasso userRegistrationValidatePasso;

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public final void testExecuteSucesso() throws BusinessException {
		userRegistrationValidatePasso.execute(userTO);
		Mockito.verify(userRegistrationFacade).validate(userTO);
	}

	@Test
	public final void testExecuteErro() {
		try {
			userTO.setEmail(null);

			final MessageError messageError = new MessageError(EMAIL_NAO_INFORMADO);
			final UserRegistrationException exception = new UserRegistrationException(messageError);

			Mockito.doThrow(exception).when(userRegistrationFacade).validate(userTO);

			userRegistrationValidatePasso.execute(userTO);

			Assert.fail();

		} catch (final BusinessException exception) {
			final MessageError messageError = exception.getMessageError();
			Assert.assertEquals(EMAIL_NAO_INFORMADO, messageError.getMensagens().get(NumberUtils.INTEGER_ZERO));
		}
	}

}
