package com.tttn.saleweb.service.impl;

import java.util.List;

import com.tttn.saleweb.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tttn.saleweb.dao.ICustomerDAO;
import com.tttn.saleweb.entity.Customer;
import com.tttn.saleweb.service.IAccountService;
import com.tttn.saleweb.service.IHttpService;

@Service
public class AccountService extends GeneralService<Customer, String> implements IAccountService {

	@Autowired
	private IMailService mailer;

	@Autowired
	private ICustomerDAO dao;

	@Autowired
	private IHttpService http;

	@Override
	public Customer findById(String id) {
		return dao.findById(id);
	}

	@Override
	public void updateUser(Customer user) {
		dao.update(user);
	}

	@Override
	public List<Customer> findByRoles(boolean roles) {
		return dao.findByRoles(roles);
	}

	@Override
	public boolean sendActivedUser(Customer user) {
		String to = user.getEmail();
		String subject = "Welcome to WEB SALES ";
		String url = http.getCurrentUrl().replace("register", "activate/" + http.encode(user.getId()));
		String body = "<a href='" + url + "'>Click to activate your account!</a>";
		return mailer.send(to, subject, body);
	}

}
