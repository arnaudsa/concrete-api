/**
 * 
 */
package com.concrete.model.to;

/**
 * Transfer Object de Phone.
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class PhoneTO extends TO {

	private static final long serialVersionUID = 1L;

	private String ddd;
	private String number;

	public String getDdd() {
		return ddd;
	}

	public void setDdd(final String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PhoneTO [ddd=").append(ddd);
		builder.append(", number=").append(number);
		builder.append("]");
		return builder.toString();
	}


}
