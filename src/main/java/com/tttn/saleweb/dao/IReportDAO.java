package com.tttn.saleweb.dao;

import java.util.List;

public interface IReportDAO {
	List<Object[]> inventory();
	List<Object[]> revenueByProduct(Integer categoryId); 
	List<Object[]> revenueByCategory(); 
	List<Object[]> revenueByCustomer();
	List<Object[]> revenueByYear();
	List<Object[]> revenueByMonth(); 

	
}
