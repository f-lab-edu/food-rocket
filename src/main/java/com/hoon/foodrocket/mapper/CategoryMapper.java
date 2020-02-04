package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getCategories();

    void registerCategory(Category category);
}
