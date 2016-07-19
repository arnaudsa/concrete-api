/**
 * 
 */
package com.concrete.model.transacao.passo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.facade.UserRegistrationFacade;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
@Component
public class UserRegistrationEmailPasso extends AbstractPasso<UserTO> {

	@Autowired
	private UserRegistrationFacade userRegistrationFacade;

	@Override
	public void execute(final UserTO to) throws BusinessException {
		userRegistrationFacade.sendEmail(to);
	}

}
