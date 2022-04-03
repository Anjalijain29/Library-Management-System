package com.nrifintech.lms.service;

import java.util.List;

import com.nrifintech.lms.entity.User;


public interface UserService {

	User findByPhone(String phone);
	User save(User user);
}
