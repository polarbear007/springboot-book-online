package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.BookInfo;
import cn.itcast.entity.condition.BookInfoQueryConditionWrapper;

public interface BookInfoService {
	Integer addBookInfo(BookInfo info);
	Integer deleteBookInfoById(Integer bookId);
	Integer updateBookInfoById(BookInfo info);
	List<BookInfo> getBookInfoWithAuthorAndCategoryByKeyword(BookInfoQueryConditionWrapper conditionWrapper);
	List<BookInfo> getBookInfoWithAuthorAndCategoryByWrapper(BookInfoQueryConditionWrapper wrapper);
	BookInfo getBookInfoByName(String name);
	BookInfo getBookInfoWithhAuthorAndCategoryByBookId(Integer bookId);
}
