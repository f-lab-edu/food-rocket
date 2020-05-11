package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.Category;
import com.hoon.foodrocket.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }

    public void registerCategory(Category resource) {
        categoryMapper.registerCategory(resource);
    }
}
