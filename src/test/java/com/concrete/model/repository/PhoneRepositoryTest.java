package com.concrete.model.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.concrete.SpringTestInit;
import com.concrete.model.entity.Phone;
import com.concrete.model.entity.User;
import com.concrete.model.mock.enity.PhoneMock;
import com.concrete.model.mock.enity.UserMock;

public class PhoneRepositoryTest extends SpringTestInit {

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private UserRepository userRepository;

	private Phone phone;
	private User user;

	@Before
	public void before() {
		phone = new PhoneMock().createMock();
		user = new UserMock().createMock();
		user.addPhone(phone);
		phone.setUser(user);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testFindAll() {
		userRepository.save(user);
		phoneRepository.save(phone);
		final List<Phone> list = phoneRepository.findAll();
		Assert.assertNotNull(list);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testSaveAndFlush() {
		userRepository.save(user);
		phoneRepository.save(phone);
		Assert.assertNotNull(phone.getId());
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public final void testFindOne() {
		userRepository.save(user);
		phoneRepository.save(phone);

		final Long id = phone.getId();
		Assert.assertNotNull(id);

		final Phone phoneFound = phoneRepository.findOne(id);
		Assert.assertNotNull(phoneFound);
		Assert.assertEquals(id, phoneFound.getId());
	}

}
