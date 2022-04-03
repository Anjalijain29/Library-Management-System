package com.nrifintech.lms.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

public class RegisterRequest {
		@NotNull
		@Size(min = 3, max = 20, message = "First Name size should be in between 3 to 20")
		String firstName;
		@NotNull
		@Size(min = 3, max = 20, message = "LastName size should be in between 3 to 20")
		String lastName;
		
		@NotNull
		@NumberFormat
		@Size(min = 10, max = 10, message = "Phone number should be of size 10")
		@Pattern(regexp = "^[0-9]*$", message = "only numbers are allowed")
		String phone;
		
		@NotNull
		@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain a number, a uppercase, a lowercase letter and a special charecter. Min length 8")
		String password;
		String confirmPassword;
		public String getFirstName() {
			return firstName;
		}
		
		public RegisterRequest() {
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		@Override
		public String toString() {
			return "LoginRequest [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
					+ ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
		}
		
}
