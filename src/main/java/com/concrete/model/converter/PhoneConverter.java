/**
 * 
 */
package com.concrete.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.concrete.model.entity.Phone;
import com.concrete.model.entity.User;
import com.concrete.model.to.PhoneTO;
import com.concrete.model.to.UserTO;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class PhoneConverter {

	/**
	 * Construtor Default
	 */
	private PhoneConverter() {

	}

	/**
	 * Converte o TO {@link UserTO} para a entidade {@link Phone}
	 * 
	 * @param to
	 *            to
	 * 
	 * @return Uma lista da entidade List<Phone>
	 */
	public static List<Phone> toEntity(final UserTO to) {

		final List<Phone> listEntity = new ArrayList<Phone>();
		if (to == null) {
			return listEntity;
		}

		for (final PhoneTO phoneTO : to.getPhones()) {

			final String dddStr = phoneTO.getDdd().replace("(", "").replace(")", "").trim();
			final String numberStr = phoneTO.getNumber().replace("-", "").trim();

			if (NumberUtils.isNumber(dddStr) && NumberUtils.isNumber(numberStr)) {
				final Phone entity = new Phone();				
				entity.setDdd(Short.valueOf(dddStr));
				entity.setNumber(Long.valueOf(numberStr));

				listEntity.add(entity);
			}
		}

		return listEntity;
	}


	/**
	 * Converte a enitity {@link User} para {@link List<PhoneTO>}
	 * 
	 * @param entity
	 * @return {@link List<PhoneTO>}
	 */
	public static List<PhoneTO> toTO(final User entity) {

		final List<PhoneTO> listTO= new ArrayList<>();
		if (entity == null) {
			return listTO;
		}

		for (final Phone phone : entity.getPhones()) {

			final PhoneTO phoneTO = new PhoneTO();
			phoneTO.setDdd(String.valueOf(phone.getDdd()));
			phoneTO.setNumber(String.valueOf(phone.getNumber()));

			listTO.add(phoneTO);
		}

		return listTO;
	}

}
