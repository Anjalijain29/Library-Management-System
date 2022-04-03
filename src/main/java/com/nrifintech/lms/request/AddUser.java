package com.nrifintech.lms.request;

import com.nrifintech.lms.entity.User;

public class AddUser {
	public static User makeUser(RegisterRequest req) {
		String firstName = req.getFirstName();
		String lastName = req.getLastName();
		String phone = req.getPhone();
		String password = req.getPassword();
		User user = new User(firstName, lastName, phone, password);
		return user;
	}
}
