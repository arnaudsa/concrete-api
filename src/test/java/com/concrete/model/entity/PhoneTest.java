package com.concrete.model.entity;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.concrete.model.mock.enity.UserMock;

/**
 * Teste da Entity {@link Phone}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class PhoneTest {

	private static final Long ID = null;
	private static final Short DDD = 21;
	private static final Long NUMBER = 987654321L;
	private static final User USER = new UserMock().createMock();

	private Phone phone;

	@Before
	public void before() {
		phone = new Phone();
		phone.setId(ID);
		phone.setDdd(DDD);
		phone.setNumber(NUMBER);
		phone.setUser(USER);
		phone.setUserID(USER.getId());
	}

	@Test
	public final void testGetId() {
		Assert.assertEquals(ID, phone.getId());
	}

	@Test
	public final void testSetId() {
		final Long phoneID = 12346L;
		phone.setId(phoneID);
		Assert.assertEquals(phoneID, phone.getId());
	}

	@Test
	public final void testGetDdd() {
		Assert.assertEquals(DDD, phone.getDdd());
	}

	@Test
	public final void testSetDdd() {
		final Short ddd = 11;
		phone.setDdd(ddd);
		Assert.assertEquals(ddd, phone.getDdd());
	}

	@Test
	public final void testGetNumber() {
		Assert.assertEquals(NUMBER, phone.getNumber());
	}

	@Test
	public final void testSetNumber() {
		final Long numberPhone = 971881018L;
		phone.setNumber(numberPhone);
		Assert.assertEquals(numberPhone, phone.getNumber());
	}

	@Test
	public final void testGetUserID() {
		Assert.assertEquals(USER.getId(), phone.getUserID());
	}

	@Test
	public final void testSetUserID() {
		final Long userID = NumberUtils.LONG_ONE;
		phone.setUserID(userID);
		Assert.assertEquals(userID, phone.getUserID());
	}

	@Test
	public final void testGetUser() {
		Assert.assertEquals(USER, phone.getUser());
	}

	@Test
	public final void testSetUser() {
		final User userMock = new UserMock().createMock();
		userMock.setId(NumberUtils.LONG_ZERO);
		phone.setUser(userMock);
		Assert.assertEquals(userMock, phone.getUser());
	}

}
