package com.concrete.model.security;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class Cryptography {

	/**
	 * 
	 * @param value
	 *            value
	 * 
	 * @return String encryptada
	 */
	public String encrypt(final String value) {
		return DigestUtils.sha512Hex(value.getBytes(StandardCharsets.UTF_8));
	}

}
