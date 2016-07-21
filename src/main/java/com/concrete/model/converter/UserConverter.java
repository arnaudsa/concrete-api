/**
 * 
 */
package com.concrete.model.converter;

import java.util.List;

import com.concrete.model.entity.Phone;
import com.concrete.model.entity.User;
import com.concrete.model.to.PhoneTO;
import com.concrete.model.to.UserTO;
import com.concrete.model.util.DateUtil;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserConverter {

	/**
	 * Construtor Default
	 */
	private UserConverter() {

	}

	/**
	 * Converte o TO {@link UserTO} para a entidade {@link User}
	 * 
	 * @param to
	 *            to
	 * 
	 * @return A entidade User
	 */
	public static User toEntity(final UserTO to) {

		if (to == null) {
			return null;
		}

		final List<Phone> phones = PhoneConverter.toEntity(to);

		final User entity = new User();
		entity.setEmail(to.getEmail());
		entity.setName(to.getName());
		entity.setPassword(to.getPassword());
		entity.setPhones(phones);

		for (final Phone phone : phones) {
			phone.setUser(entity);
		}

		return entity;
	}

	/**
	 * Converte a enity {@link User} para a entidade {@link UserTO}
	 * 
	 * @param entity
	 * 
	 * @return {@link UserTO}
	 */
	public static UserTO toTO(final User entity) {

		if (entity == null) {
			return null;
		}

		final UserTO to = new UserTO();
		to.setName(entity.getName());
		to.setEmail(entity.getEmail());
		to.setPassword(entity.getPassword());
		to.setId(String.valueOf(entity.getId()));
		to.setCreated(DateUtil.toStr(entity.getCreated()));
		to.setModified(DateUtil.toStr(entity.getModified()));
		to.setLastLogin(DateUtil.toStr(entity.getLastLogin()));
		to.setToken(entity.getToken());

		final List<PhoneTO> phones = PhoneConverter.toTO(entity);
		to.setPhones(phones);

		return to;
	}
}
