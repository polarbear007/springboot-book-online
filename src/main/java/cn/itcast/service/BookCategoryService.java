package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.BookCategory;

public interface BookCategoryService {
	Integer addCategory(BookCategory category);
	BookCategory getCategoryById(Integer categoryId);
	Integer updateCategory(BookCategory category);
	Integer deleteCategory(Integer categoryId);
	BookCategory getCategoryByName(String name);
	List<BookCategory> getCategories();
}
