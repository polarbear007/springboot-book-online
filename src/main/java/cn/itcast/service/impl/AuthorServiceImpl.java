package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.entity.Author;
import cn.itcast.entity.AuthorExample;
import cn.itcast.mapper.AuthorMapper;
import cn.itcast.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorMapper authorMapper;

	@Override
	@Transactional
	public Integer addAuthor(Author author) {
		return authorMapper.insertSelective(author);
	}
	
	@Override
	public Author getAuthorById(Integer authorId) {
		return authorMapper.selectByPrimaryKey(authorId);
	}
	
	@Override
	@Transactional
	public Integer updateAuthor(Author author) {
		if(author.getAuthorId() == null) {
			throw new RuntimeException("authorId 不能为空！");
		}
		return authorMapper.updateByPrimaryKeySelective(author);
	}

	@Override
	public Integer deleteAuthor(Integer authorId) {
		if(authorId == null) {
			throw new RuntimeException("authorId 不能为空！");
		}
		return authorMapper.deleteByPrimaryKey(authorId);
	}

	@Override
	public Author getAuthorByName(String name) {
		AuthorExample example = new AuthorExample();
		example.createCriteria().andAuthorNameEqualTo(name);
		List<Author> list = authorMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
