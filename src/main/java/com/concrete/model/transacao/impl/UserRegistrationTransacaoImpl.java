/**
 * 
 */
package com.concrete.model.transacao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.to.UserTO;
import com.concrete.model.transacao.UserRegistrationTransacao;
import com.concrete.model.transacao.passo.Passo;
import com.concrete.model.transacao.passo.UserRegistrationEmailPasso;
import com.concrete.model.transacao.passo.UserRegistrationSavePasso;
import com.concrete.model.transacao.passo.UserRegistrationValidatePasso;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
@Component
public class UserRegistrationTransacaoImpl extends AbstractTransacao<UserTO> implements UserRegistrationTransacao {

	@Autowired
	private UserRegistrationValidatePasso validatePasso;

	@Autowired
	private UserRegistrationSavePasso savePasso;

	@Autowired
	private UserRegistrationEmailPasso emailPasso;

	@Override
	public UserTO registration(final UserTO userTO) throws BusinessException {

		super.execute(userTO);

		return userTO;
	}

	@Override
	protected List<Passo<UserTO>> getPassos() {

		final List<Passo<UserTO>> passos = new ArrayList<Passo<UserTO>>();
		passos.add(validatePasso);
		passos.add(savePasso);
		passos.add(emailPasso);

		return passos;
	}

	@Override
	protected String getTransactionName() {
		return "UserRegistrationTransacao";
	}

}
