package com.concrete.model.to;

public class LoginTO extends TO {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;


	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("LoginTO [email=").append(email);
		builder.append(", password=").append(password);
		builder.append("]");
		return builder.toString();
	}


}
