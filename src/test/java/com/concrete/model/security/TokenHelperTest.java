package com.concrete.model.security;

import static com.concrete.model.constants.MensagemConstants.SUBJECT_TOKEN_USER;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.concrete.SpringTestInit;
import com.concrete.model.exception.TokenNotFoundException;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.UserTO;

public class TokenHelperTest extends SpringTestInit {

	@Autowired
	private TokenHelper tokenHelper;


	private UserTO userTO;

	@Before
	public void name() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public void testCreateToken() {
		final String user = userTO.getName();
		final String password = userTO.getPassword();

		final String token = tokenHelper.createToken(user, password, SUBJECT_TOKEN_USER);
		Assert.assertNotNull(token);
	}

	@Test
	public void testTokenExpirado() throws TokenNotFoundException {

		final String user = userTO.getName();
		final String password = userTO.getPassword();

		final String token = tokenHelper.createToken(user, password, SUBJECT_TOKEN_USER);

		final boolean expiration = tokenHelper.isTokenExpiration(token, password);
		Assert.assertFalse(expiration);

	}
}
