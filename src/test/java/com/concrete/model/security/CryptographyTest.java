package com.concrete.model.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.concrete.SpringTestInit;

public class CryptographyTest extends SpringTestInit {

	@Autowired
	private Cryptography cryptography;

	@Test
	public final void testEncrypt() {

		final String value = "abc123";
		final String encrypt = cryptography.encrypt(value);

		Assert.assertNotEquals(value, encrypt);
	}

	@Test
	public final void testConfirmeEncrypt() {

		final String value1 = "abc123";
		final String encrypt1 = cryptography.encrypt(value1);

		final String value2 = "abc123";
		final String encrypt2 = cryptography.encrypt(value2);

		Assert.assertEquals(encrypt1, encrypt2);
	}

}
