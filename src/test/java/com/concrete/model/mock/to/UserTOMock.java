/**
 * 
 */
package com.concrete.model.mock.to;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserTOMock extends MockGenerator<UserTO> {

	private static final String EMAIL = "joao@silva.org";
	private static final String NAME = "João da Silva";
	private static final String PASSWORD = "hunter2";

	@Override
	public UserTO createMock() {

		final UserTO user = new UserTO();
		user.setEmail(EMAIL);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setPhones(new PhoneTOMock().createMockList());

		return user;
	}

	@Override
	public void assertMock(final UserTO user) {
		Assert.assertNotNull(user);
		Assert.assertEquals(EMAIL, user.getEmail());
		Assert.assertEquals(NAME, user.getName());
		Assert.assertEquals(PASSWORD, user.getPassword());
	}

}
