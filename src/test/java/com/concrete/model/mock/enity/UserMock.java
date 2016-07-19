/**
 * 
 */
package com.concrete.model.mock.enity;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.entity.User;

/**
 * Mock Object da classe {@link User}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserMock extends MockGenerator<User> {

	public static final String EMAIL = "joao@silva.org";
	public static final Long ID = null;
	public static final String NAME = "João da Silva";
	public static final String PASSWORD = "hunter2";

	@Override
	public User createMock() {

		final User user = new User();
		user.setEmail(EMAIL);
		user.setId(ID);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setPhones(new PhoneMock().createMockList());

		return user;
	}

	@Override
	public void assertMock(final User user) {
		Assert.assertNotNull(user);
		Assert.assertEquals(EMAIL, user.getEmail());
		Assert.assertEquals(NAME, user.getName());
		Assert.assertEquals(PASSWORD, user.getPassword());
	}

}
