package com.concrete.model.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import com.concrete.model.util.DateUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {

	private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;


	/**
	 * Create Token 
	 * 
	 * @param user
	 * @param password
	 * @param subject
	 * 
	 * @return O token criado 
	 * 
	 */
	public String createToken(final String user, final String password, final String subject) {

		final Date sysdate = new Date();
		final Date dtExpire = DateUtil.addMinute(sysdate, DateUtil.MINUTES_30);

		final byte[] passwordBytes = DatatypeConverter.parseBase64Binary(password);
		final Key key = new SecretKeySpec(passwordBytes, ALGORITHM.getJcaName());

		return Jwts.builder()
				.setIssuedAt(sysdate)
				.setSubject(subject)
				.setIssuer(user)
				.setExpiration(dtExpire)
				.signWith(ALGORITHM, key).compact();
	}
}
