package com.tttn.saleweb.service;

import java.util.List;

import com.tttn.saleweb.entity.Product;

public interface IProductService extends IGeneralService<Product, Integer> {
	List<Product> findByKeywords(String keyWords);
	List<Product> findAllProductByCategory(int id);
	List<Product> findByHot(String key);
	List<Product>  getViewProduct(String name,String id); //  get những sản phẩm da xem thông qua mảng id sp trong Cookie
	List<Product>  getRandomProduct();

}
