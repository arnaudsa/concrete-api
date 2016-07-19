/**
 * 
 */
package com.concrete.model.transacao;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 18/07/2016
 *
 */
public interface UserRegistrationTransacao extends Transacao<UserTO> {

	UserTO registration(UserTO userTO) throws BusinessException;

}
