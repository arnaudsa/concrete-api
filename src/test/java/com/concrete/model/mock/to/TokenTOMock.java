package com.concrete.model.mock.to;

import org.junit.Assert;

import com.concrete.mock.MockGenerator;
import com.concrete.model.to.TokenTO;

public class TokenTOMock extends MockGenerator<TokenTO> {

	public static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE0Njk0OTAyOTIsInN1YiI6IlRva2VuIGRlIFVzdcOhcmlvIiwiaXNzIjoiQXJuYXVkIFNhbnRhbmEgQWx2ZXMiLCJleHAiOjE0Njk0OTIwOTJ9.jyMSj7z26Nnio_wZfkHKlmcAbGTPPuQMqCvTUoNGxhPxB_sv8ejZ0fb_fADu3mszaOty7i6SQNQNI-YgXIy8ag";

	@Override
	public TokenTO createMock() {
		final TokenTO tokenTO = new TokenTO();
		tokenTO.setToken(TOKEN);

		return tokenTO;
	}

	@Override
	public void assertMock(final TokenTO tokenTO) {
		Assert.assertNotNull(tokenTO);
		Assert.assertEquals(TOKEN, tokenTO.getToken());

	}

}
