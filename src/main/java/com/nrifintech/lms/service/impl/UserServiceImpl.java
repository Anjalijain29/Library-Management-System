package com.nrifintech.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nrifintech.lms.entity.User;
import com.nrifintech.lms.repository.UserRepository;
import com.nrifintech.lms.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
