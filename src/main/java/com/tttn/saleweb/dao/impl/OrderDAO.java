package com.tttn.saleweb.dao.impl;

import java.util.List;

import com.tttn.saleweb.dao.IOrderDAO;
import com.tttn.saleweb.dao.IOrderDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tttn.saleweb.entity.Customer;
import com.tttn.saleweb.entity.Order;

@Repository
public class OrderDAO extends GeneralDAO<Order, Integer> implements IOrderDAO {

	@Autowired
	private IOrderDetailDAO dao;

	@Override
	public List<Order> findByUser(Customer user) {
		String sql = "FROM Order o WHERE o.customer.id=?0 ORDER BY o.orderDate DESC";
		return this.getResultList(sql, user.getId());
	}

}
