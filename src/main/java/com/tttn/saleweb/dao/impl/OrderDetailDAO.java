package com.tttn.saleweb.dao.impl;

import com.tttn.saleweb.dao.IOrderDetailDAO;
import org.springframework.stereotype.Service;

import com.tttn.saleweb.entity.OrderDetail;

@Service
public class OrderDetailDAO extends GeneralDAO<OrderDetail, Integer> implements IOrderDetailDAO {

}
