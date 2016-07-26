/**
 * 
 */
package com.concrete.model.repository;

import com.concrete.model.entity.User;

/**
 * Reposity da Entity {@link User}
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public interface UserRepository extends MyRepository<User, Long> {

	User findByEmail(final String email);

	User findByToken(final String token);
}
