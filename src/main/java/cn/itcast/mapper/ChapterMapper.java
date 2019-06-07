package cn.itcast.mapper;

import cn.itcast.entity.Chapter;
import cn.itcast.entity.ChapterExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChapterMapper {
    int countByExample(ChapterExample example);

    int deleteByExample(ChapterExample example);

    int deleteByPrimaryKey(Integer chapterId);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    List<Chapter> selectByExampleWithBLOBs(ChapterExample example);

    List<Chapter> selectByExample(ChapterExample example);

    Chapter selectByPrimaryKey(Integer chapterId);

    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExampleWithBLOBs(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKeyWithBLOBs(Chapter record);

    int updateByPrimaryKey(Chapter record);
    
    Integer getMaxChapterNumByBookId(Integer bookId);
    
    String getChapterContentByChapterId(Integer chapterId);
}