/**
 * 
 */
package com.concrete.model.converter;

import java.util.List;

import com.concrete.model.entity.Phone;
import com.concrete.model.entity.User;
import com.concrete.model.to.UserTO;

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

}
