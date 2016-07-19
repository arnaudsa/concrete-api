/**
 * 
 */
package com.concrete.model.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.concrete.model.exception.BusinessException;

@Component
public class Email {

	private static final Logger LOGGER = LoggerFactory.getLogger(Email.class);

	@Autowired
	private JavaMailSender mailSender;

	public void send(final String to, final String subject, final String textMessage) throws BusinessException {

		try {
			final MimeMessage message = mailSender.createMimeMessage();
			final MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(textMessage);

			LOGGER.debug("Enviando e-mail para: {}", to);

			mailSender.send(message);

			LOGGER.debug("E-mail enviado com sucesso.");

		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException("Erro ao enviar o e-mail");
		}
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

}
