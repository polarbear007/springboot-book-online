package cn.itcast.mapper;

import cn.itcast.entity.BookInfo;
import cn.itcast.entity.BookInfoExample;
import cn.itcast.entity.condition.BookInfoQueryConditionWrapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookInfoMapper {
    int countByExample(BookInfoExample example);

    int deleteByExample(BookInfoExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    List<BookInfo> selectByExampleWithBLOBs(BookInfoExample example);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") BookInfo record, @Param("example") BookInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BookInfo record, @Param("example") BookInfoExample example);

    int updateByExample(@Param("record") BookInfo record, @Param("example") BookInfoExample example);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKeyWithBLOBs(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
    
    List<BookInfo> getBookInfoWithAuthorAndCategoryByKeyword(@Param("keyword") String keyword);
    List<BookInfo> getBookInfoWithAuthorAndCategoryByWrapper(BookInfoQueryConditionWrapper wrapper);
    
    BookInfo getBookInfoWithhAuthorAndCategoryByBookId(Integer bookId);
}