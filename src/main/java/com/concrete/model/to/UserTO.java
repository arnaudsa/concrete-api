/**
 * 
 */
package com.concrete.model.to;

import java.util.ArrayList;
import java.util.List;

/**
 * Transfer Object de Usuario.
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public class UserTO extends TO {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	private String password;
	private String created;
	private String modified;
	private String lastLogin;
	private String token;
	private List<PhoneTO> phones = new ArrayList<PhoneTO>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

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

	public String getCreated() {
		return created;
	}

	public void setCreated(final String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(final String modified) {
		this.modified = modified;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(final String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public List<PhoneTO> getPhones() {
		return phones;
	}

	public void setPhones(final List<PhoneTO> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("UserTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", created=");
		builder.append(created);
		builder.append(", modified=");
		builder.append(modified);
		builder.append(", lastLogin=");
		builder.append(lastLogin);
		builder.append(", token=");
		builder.append(token);
		builder.append(", phones=");
		builder.append(phones);
		builder.append("]");
		return builder.toString();
	}


}
