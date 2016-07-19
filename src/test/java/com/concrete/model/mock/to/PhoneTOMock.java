/**
 * 
 */
package com.concrete.model.mock.to;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.to.PhoneTO;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class PhoneTOMock extends MockGenerator<PhoneTO> {

	private static final String DDD = "21";
	private static final String NUMBER = "987654321";

	@Override
	public PhoneTO createMock() {

		final PhoneTO phone = new PhoneTO();
		phone.setDdd(DDD);
		phone.setNumber(NUMBER);

		return phone;
	}

	@Override
	public void assertMock(final PhoneTO phone) {
		Assert.assertNotNull(phone);
		Assert.assertEquals(DDD, phone.getDdd());
		Assert.assertEquals(NUMBER, phone.getNumber());
	}

}
