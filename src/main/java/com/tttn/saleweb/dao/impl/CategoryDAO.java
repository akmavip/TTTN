package com.tttn.saleweb.dao.impl;

import com.tttn.saleweb.dao.ICategoryDAO;
import org.springframework.stereotype.Repository;

import com.tttn.saleweb.entity.Category;

@Repository
public class CategoryDAO extends GeneralDAO<Category, Integer> implements ICategoryDAO {

}
