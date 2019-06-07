package cn.itcast.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.entity.BookCategory;
import cn.itcast.service.BookCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCategoryServiceImplTest {
	@Autowired
	private BookCategoryService categoryService;
	
	@Test
	public void testAddCategory() {
		BookCategory category = new BookCategory();
		category.setCategoryName("现代言情");
		categoryService.addCategory(category);
	}
	
	@Test
	public void testGetCategoryById() {
		BookCategory category = categoryService.getCategoryById(1);
		System.out.println(category.getCategoryName());
	}
	
	@Test
	public void testUpdateCategory() {
		BookCategory category = categoryService.getCategoryById(1);
		category.setCategoryName("古代言情");
		categoryService.updateCategory(category);
	}

	@Test
	public void testDeleteCategory() {
		categoryService.deleteCategory(1);
	}
	
	/**
	 * 	添加一些测试数据
	 */
	@Test
	public void addCategoryForTest() {
		BookCategory category = new BookCategory();
		category.setCategoryId(1);
		category.setCategoryName("现代言情");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(2);
		category.setCategoryName("古代言情");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(3);
		category.setCategoryName("浪漫青春");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(4);
		category.setCategoryName("玄幻言情");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(5);
		category.setCategoryName("仙侠奇缘");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(6);
		category.setCategoryName("悬疑灵异");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(7);
		category.setCategoryName("科幻空间");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(8);
		category.setCategoryName("游戏竞技");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(9);
		category.setCategoryName("短篇小说");
		categoryService.addCategory(category);
		category = new BookCategory();
		category.setCategoryId(10);
		category.setCategoryName("轻小说");
		categoryService.addCategory(category);
	}
}
