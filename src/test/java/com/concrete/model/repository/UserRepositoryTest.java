package com.concrete.model.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.concrete.SpringTestInit;
import com.concrete.model.entity.User;
import com.concrete.model.mock.enity.UserMock;

public class UserRepositoryTest extends SpringTestInit {

	@Autowired
	private UserRepository userRepositoy;
	private User user;

	@Before
	public void before() {
		user = new UserMock().createMock();
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testFindAll() {
		userRepositoy.save(user);
		final List<User> list = userRepositoy.findAll();
		Assert.assertNotNull(list);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testSaveAndFlush() {
		user.setName("Arnaud");
		userRepositoy.saveAndFlush(user);
		Assert.assertNotNull(user.getId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testGetOne() {

		userRepositoy.saveAndFlush(user);

		final Long id = user.getId();
		Assert.assertNotNull(id);

		final User userFound = userRepositoy.findOne(id);
		Assert.assertNotNull(userFound);
		Assert.assertEquals(id, userFound.getId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public void testFindByEmail() {

		userRepositoy.saveAndFlush(user);

		final User userFound = userRepositoy.findByEmail(UserMock.EMAIL);
		Assert.assertNotNull(userFound);
	}
}
