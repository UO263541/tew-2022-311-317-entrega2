package com.tew.business;

import com.tew.model.User;

public interface LoginService {
	User verify(String login, String password);
	boolean validLogin(String login, String password);
}