package com.nrifintech.lms.request;

import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.security.MD5Encoder;

import com.google.common.hash.Hashing;

public class HashPassword {
	public static String hash(String password) {
		String sha = Hashing.md5().hashString(password, StandardCharsets.UTF_8).toString();
		return sha;
	}
}
