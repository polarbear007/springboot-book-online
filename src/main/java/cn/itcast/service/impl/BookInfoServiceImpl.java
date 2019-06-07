package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.itcast.entity.Author;
import cn.itcast.entity.BookCategory;
import cn.itcast.entity.BookInfo;
import cn.itcast.entity.BookInfoExample;
import cn.itcast.entity.condition.BookInfoQueryConditionWrapper;
import cn.itcast.mapper.AuthorMapper;
import cn.itcast.mapper.BookCategoryMapper;
import cn.itcast.mapper.BookInfoMapper;
import cn.itcast.service.BookInfoService;

@Service
public class BookInfoServiceImpl implements BookInfoService {
	@Autowired
	private BookInfoMapper infoMapper;
	
	// 我们直接引入 AuthorMapper， 当然你也可以引入 AuthorService
	@Autowired
	private AuthorMapper authorMapper;
	
	// 我们直接引入 BookCategoryMapper， 当然你也可以引入 BookCategoryService
	@Autowired
	private BookCategoryMapper categoryMapper;
	
	@Override
	@Transactional
	public Integer addBookInfo(BookInfo info) {
		// 判断一下是否有指定关联的  categoryId
		if(info.getCategoryId() == null) {
			throw new RuntimeException("没有指定书籍的类别！");
		}
		// 判断一下是否存在指定的 authorId
		if(info.getAuthorId() == null) {
			throw new RuntimeException("没有指定书籍的作者！");
		}
		// 看一下 categoryId 是否有对应的记录
		BookCategory category = categoryMapper.selectByPrimaryKey(info.getCategoryId());
		if(category == null) {
			throw new RuntimeException("指定的类别不存在！");
		}
		// 看一下 authorId 是否有对应的记录
		Author author = authorMapper.selectByPrimaryKey(info.getAuthorId());
		if(author == null) {
			throw new RuntimeException("指定的作者不存在！");
		}
		
		// 如果前面都没有抛异常，我们就直接插入数据
		return infoMapper.insertSelective(info);
	}

	@Override
	@Transactional
	public Integer deleteBookInfoById(Integer bookId) {
		if(bookId == null) {
			throw new RuntimeException("bookId 不能为空！");
		}
		return infoMapper.deleteByPrimaryKey(bookId);
	}

	@Override
	@Transactional
	public Integer updateBookInfoById(BookInfo info) {
		if(info.getBookId() == null) {
			throw new RuntimeException("bookId 不能为空！");
		}
		return infoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public List<BookInfo> getBookInfoWithAuthorAndCategoryByKeyword(BookInfoQueryConditionWrapper conditionWrapper) {
		String keyword = conditionWrapper.getKeyword();
		if(keyword != null && keyword.trim() != "") {
			keyword = "%" + keyword + "%";
		}else {
			throw new RuntimeException("关键字不能为空！");
		}
		// currentPage 和 pageSize 有对应的默认值
		// orderBy 可能会是 null ，但是没关系，如果是 null ，pageHelper 并不会帮我们添加 order by 子句
		PageHelper.startPage(conditionWrapper.getCurrentPage(), conditionWrapper.getPageSize(), conditionWrapper.getOrderBy());
		return infoMapper.getBookInfoWithAuthorAndCategoryByKeyword(keyword);
	}

	@Override
	public List<BookInfo> getBookInfoWithAuthorAndCategoryByWrapper(BookInfoQueryConditionWrapper wrapper) {
		// 分页参数、排序参数在这里设置
		PageHelper.startPage(wrapper.getCurrentPage(), wrapper.getPageSize(), wrapper.getOrderBy());
		return infoMapper.getBookInfoWithAuthorAndCategoryByWrapper(wrapper);
	}

	@Override
	public BookInfo getBookInfoByName(String name) {
		BookInfoExample example = new BookInfoExample();
		example.createCriteria().andBookNameEqualTo(name);
		List<BookInfo> list = infoMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return  list.get(0);
		}
		return null;
	}

	@Override
	public BookInfo getBookInfoWithhAuthorAndCategoryByBookId(Integer bookId) {
		return infoMapper.getBookInfoWithhAuthorAndCategoryByBookId(bookId);
	}
	
}
