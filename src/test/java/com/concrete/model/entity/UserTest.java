/**
 * 
 */
package com.concrete.model.entity;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.concrete.model.mock.enity.PhoneMock;

/**
 * Teste da Entity {@link User}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserTest {

	private static final String EMAIL = "joao@silva.org";
	private static final Long ID = null;
	private static final String NAME = "João da Silva";
	private static final String PASSWORD = "hunter2";
	private static final List<Phone> PHONES = new PhoneMock().createMockList();
	private User user;

	@Before
	public void before() {
		user = new User();
		user.setEmail(EMAIL);
		user.setId(ID);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setPhones(PHONES);
	}

	@Test
	public final void testGetId() {
		Assert.assertEquals(ID, user.getId());
	}

	@Test
	public final void testSetId() {
		final Long userID = 123L;
		user.setId(userID);
		Assert.assertEquals(userID, user.getId());
	}

	@Test
	public final void testGetName() {
		Assert.assertEquals(NAME, user.getName());
	}

	@Test
	public final void testSetName() {
		final String nameUser = "Arnaud";
		user.setName(nameUser);
		Assert.assertEquals(nameUser, user.getName());
	}

	@Test
	public final void testGetEmail() {
		Assert.assertEquals(EMAIL, user.getEmail());
	}

	@Test
	public final void testSetEmail() {
		final String emailUser = "arnaud@gmail.com";
		user.setEmail(emailUser);
		Assert.assertEquals(emailUser, user.getEmail());
	}

	@Test
	public final void testGetPassword() {
		Assert.assertEquals(PASSWORD, user.getPassword());
	}

	@Test
	public final void testSetPassword() {
		final String passwordUser = "abc123456@";
		user.setPassword(passwordUser);
		Assert.assertEquals(passwordUser, user.getPassword());
	}

	@Test
	public final void testGetPhones() {
		Assert.assertEquals(PHONES, user.getPhones());
	}

	/**
	 * Test method for
	 * {@link com.concrete.model.entity.User#setPhones(java.util.List)}.
	 */
	@Test
	public final void testSetPhones() {
		final List<Phone> listPhones = new PhoneMock().createMockList(2);
		user.setPhones(listPhones);
		Assert.assertEquals(listPhones, user.getPhones());
	}

}
