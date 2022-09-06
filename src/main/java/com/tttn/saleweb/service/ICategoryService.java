package com.tttn.saleweb.service;

import java.util.List;

import com.tttn.saleweb.entity.Category;

public interface ICategoryService extends IGeneralService<Category, Integer> {
	List<Category> getRamDomByCategory();
}
