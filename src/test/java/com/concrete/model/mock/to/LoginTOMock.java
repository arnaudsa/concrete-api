/**
 * 
 */
package com.concrete.model.mock.to;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.to.LoginTO;

/**
 * @author Arnaud Santana Alves
 * @since 21/07/2016
 *
 */
public class LoginTOMock extends MockGenerator<LoginTO> {

	private static final String EMAIL = "joao@silva.org";
	private static final String PASSWORD = "hunter2";

	@Override
	public LoginTO createMock() {
		final LoginTO loginTO = new LoginTO();
		loginTO.setEmail(EMAIL);
		loginTO.setPassword(PASSWORD);

		return loginTO;
	}

	@Override
	public void assertMock(final LoginTO loginTO) {
		Assert.assertNotNull(loginTO);
		Assert.assertEquals(EMAIL, loginTO.getEmail());
		Assert.assertEquals(PASSWORD, loginTO.getPassword());
	}

}
