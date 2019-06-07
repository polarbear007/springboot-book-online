package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.entity.BookCategory;
import cn.itcast.entity.BookCategoryExample;
import cn.itcast.mapper.BookCategoryMapper;
import cn.itcast.service.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
	@Autowired
	private BookCategoryMapper categoryMapper;
	
	/**
	 *	添加书籍分类信息（可以不指定 id）
	 */
	@Override
	@Transactional
	public Integer addCategory(BookCategory category) {
		return categoryMapper.insertSelective(category);
	}

	/**
	 * 	删除书籍分类信息
	 */
	@Override
	@Transactional
	public Integer deleteCategory(Integer categoryId) {
		return categoryMapper.deleteByPrimaryKey(categoryId);
	}

	/**
	 * 	根据主键去更新分类信息
	 */
	@Override
	@Transactional
	public Integer updateCategory(BookCategory category) {
		if(category.getCategoryId() == null) {
			throw new RuntimeException("categoryId 不能为空！");
		}
		return categoryMapper.updateByPrimaryKeySelective(category);
	}

	/**
	 * 	根据主键 id 去查询分类信息
	 */
	@Override
	public BookCategory getCategoryById(Integer categoryId) {
		if(categoryId == null) {
			throw new RuntimeException("categoryId 不能为空！");
		}
		return categoryMapper.selectByPrimaryKey(categoryId);
	}

	@Override
	public BookCategory getCategoryByName(String name) {
		BookCategoryExample example = new BookCategoryExample();
		example.createCriteria().andCategoryNameEqualTo(name);
		List<BookCategory> list = categoryMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BookCategory> getCategories() {
		BookCategoryExample example = new BookCategoryExample();
		return categoryMapper.selectByExample(example);
	}
}
