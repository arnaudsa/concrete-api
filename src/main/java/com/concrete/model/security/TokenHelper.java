package com.concrete.model.security;

import static com.concrete.model.constants.MensagemConstants.SESSAO_INVALIDA;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.concrete.model.exception.TokenNotFoundException;
import com.concrete.model.to.MessageError;
import com.concrete.model.util.DateUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {

	private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

	private static final Logger LOGGER = Logger.getLogger(TokenHelper.class);

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


	/**
	 * Verifica se o Token foi expirdado.
	 * 
	 * @param token
	 * @return 
	 * 
	 * @return true caso o token tenha sido expirdado.
	 * @throws TokenNotFoundException 
	 */
	public boolean isTokenExpiration(final String token, final String password) throws TokenNotFoundException {

		try {
			final byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(password);
			Jwts.parser().setSigningKey(parseBase64Binary).parseClaimsJws(token).getBody();

			return false;	

		} catch (final ExpiredJwtException e) {
			LOGGER.info(SESSAO_INVALIDA);
			throw new TokenNotFoundException(new MessageError(SESSAO_INVALIDA));
		}
	}
}
