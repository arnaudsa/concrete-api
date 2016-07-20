package com.concrete.model.security;

import static com.concrete.model.constants.MensagemConstants.SUBJECT_TOKEN_USER;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.concrete.SpringTestInit;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.UserTO;

public class TokenHelperTest extends SpringTestInit {

	@Autowired
	private TokenHelper tokenHelper;

	@Test
	public final void testCreateToken() {

		final UserTO userTO = new UserTOMock().createMock();
		final String user = userTO.getName();
		final String password = userTO.getPassword();

		final String token = tokenHelper.createToken(user, password, SUBJECT_TOKEN_USER);
		Assert.assertNotNull(token);
	}

}
