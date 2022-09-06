package com.tttn.saleweb.dao;

import java.util.List;
import com.tttn.saleweb.entity.Customer;

public interface ICustomerDAO extends IGeneralDAO<Customer, String> {
	List<Customer> findByRoles(boolean roles);

}
