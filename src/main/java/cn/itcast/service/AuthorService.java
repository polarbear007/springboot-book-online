package cn.itcast.service;

import cn.itcast.entity.Author;

public interface AuthorService {
	Integer addAuthor(Author author);
	
	Integer deleteAuthor(Integer authorId);
	
	Integer updateAuthor(Author author);
	
	Author getAuthorById(Integer authorId);
	
	Author getAuthorByName(String name);
}
