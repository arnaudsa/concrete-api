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

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
