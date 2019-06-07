package cn.itcast.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.entity.Author;
import cn.itcast.service.AuthorService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceImplTest {
	@Autowired
	private AuthorService authorService;
	
	@Test
	public void testAddAuthor() {
		Author author = new Author();
		author.setAuthorName("小明");
		authorService.addAuthor(author);
	}

	@Test
	public void testGetAuthorById() {
		Author author = authorService.getAuthorById(1);
		System.out.println(author.getAuthorName());
	}

	@Test
	public void testUpdateAuthor() {
		Author author = authorService.getAuthorById(1);
		author.setAuthorName("小黑");
		authorService.updateAuthor(author);
	}

	@Test
	public void testDeleteAuthor() {
		authorService.deleteAuthor(1);
	}
	
	/**
	 * 	添加一些测试数据
	 */
	@Test
	public void addAuthorForTest() {
		Author author = new Author();
		author.setAuthorId(1);
		author.setAuthorName("小明");
	}
}
