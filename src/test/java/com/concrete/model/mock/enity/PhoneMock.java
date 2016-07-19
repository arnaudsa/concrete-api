/**
 * 
 */
package com.concrete.model.mock.enity;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.entity.Phone;

/**
 * Mock Object da classe {@link Phone}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class PhoneMock extends MockGenerator<Phone> {

	private static final Long ID = null;
	private static final Short DDD = 21;
	private static final Long NUMBER = 987654321L;

	@Override
	public Phone createMock() {

		final Phone phone = new Phone();
		phone.setId(ID);
		phone.setDdd(DDD);
		phone.setNumber(NUMBER);

		return phone;
	}

	@Override
	public void assertMock(final Phone phone) {
		Assert.assertNotNull(phone);
		Assert.assertEquals(ID, phone.getId());
		Assert.assertEquals(DDD, phone.getDdd());
		Assert.assertEquals(NUMBER, phone.getNumber());
	}

}
