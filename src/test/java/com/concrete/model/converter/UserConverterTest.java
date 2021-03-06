/**
 * 
 */
package com.concrete.model.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.concrete.model.entity.User;
import com.concrete.model.mock.enity.UserMock;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public class UserConverterTest {

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public final void testToEntitySucesso() {
		final User entity = UserConverter.toEntity(userTO);
		Assert.assertNotNull(entity);
		Assert.assertEquals(userTO.getEmail(), entity.getEmail());
	}

	@Test
	public final void testToEntityNulla() {
		final User entity = UserConverter.toEntity(null);
		Assert.assertNull(entity);
	}

	@Test
	public final void testToTOSucesso() {
		final User entity = new UserMock().createMock();
		final UserTO to = UserConverter.toTO(entity);		
		Assert.assertNotNull(to);
		Assert.assertEquals(entity.getEmail(), to.getEmail());
	}

	@Test
	public final void testToTONula() {
		final UserTO to = UserConverter.toTO(null);		
		Assert.assertNull(to);		
	}

}
