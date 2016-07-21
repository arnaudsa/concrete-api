/**
 * 
 */
package com.concrete.model.converter;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.concrete.model.entity.Phone;
import com.concrete.model.entity.User;
import com.concrete.model.facade.UserRegistrationFacade;
import com.concrete.model.facade.impl.UserRegistrationFacadeImpl;
import com.concrete.model.mock.enity.UserMock;
import com.concrete.model.mock.to.UserTOMock;
import com.concrete.model.to.PhoneTO;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public class PhoneConverterTest {

	@InjectMocks
	private final UserRegistrationFacade userRegistrationFacade = new UserRegistrationFacadeImpl();

	private UserTO userTO;

	@Before
	public void before() {
		userTO = new UserTOMock().createMock();
	}

	@Test
	public void testToEntitySucesso() {
		final List<Phone> list = PhoneConverter.toEntity(userTO);
		Assert.assertNotNull(list);
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}

	@Test
	public void testToEntityNulla() {
		final List<Phone> list = PhoneConverter.toEntity(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	public void testToTOSucesso() {
		final User entity = new UserMock().createMock();
		final List<PhoneTO> list = PhoneConverter.toTO(entity);
		Assert.assertNotNull(list);
		Assert.assertTrue(CollectionUtils.isNotEmpty(list));
	}

	@Test
	public void testToTONulo() {
		final List<PhoneTO> list = PhoneConverter.toTO(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

}
