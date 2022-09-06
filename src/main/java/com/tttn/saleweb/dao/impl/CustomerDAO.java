package com.tttn.saleweb.dao.impl;

import java.util.List;

import com.tttn.saleweb.dao.ICustomerDAO;
import org.springframework.stereotype.Repository;

import com.tttn.saleweb.entity.Customer;

@Repository
public class CustomerDAO extends GeneralDAO<Customer, String> implements ICustomerDAO {

	@Override
	public List<Customer> findByRoles(boolean roles) {
		String sql = "From Customer c WHERE c.admin =?0";
		return this.getResultList(sql, roles);
	}

}
