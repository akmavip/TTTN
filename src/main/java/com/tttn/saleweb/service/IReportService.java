package com.tttn.saleweb.service;

import java.util.List;

public interface IReportService {
	List<Object[]> inventory();
	List<Object[]> revenueByCategory(); 
	List<Object[]> revenueByProduct(Integer categoryId);
	List<Object[]> revenueByCustomer();
	List<Object[]> revenueByYear();
	List<Object[]> revenueByMonth(); 
}
