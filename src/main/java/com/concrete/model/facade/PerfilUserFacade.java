package com.concrete.model.facade;

import com.concrete.model.exception.BusinessException;
import com.concrete.model.to.TokenTO;
import com.concrete.model.to.UserTO;

public interface PerfilUserFacade {

	UserTO getPerfil(Long id, TokenTO tokenTO) throws BusinessException;

}
