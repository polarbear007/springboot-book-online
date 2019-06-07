package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.Chapter;

public interface ChapterService {
	Integer addChapter(Chapter chapter);
	Chapter getChapterById(Integer chapterId);
	Integer updateChapterById(Chapter chapter);
	Integer deleteChapterById(Integer chapterId);
	Chapter getChapterByChapterNumAndBookId(Integer chapterNum , Integer bookId);
	/**
	 * 注意，我们特地设置了一个 chapterNum 列，用来排序，这里查询的时候，一定要用起来
	 * 另外，因为我们的 content列设置 mediumText 类型，所以默认的	QBE 查询是不查这个字段的
	 * 如果我们想要查 content 列的话，需要使用  selectByExampleWithBLOBs
	 * @param bookId
	 * @return
	 */
	List<Chapter> getChaptersByBookId(Integer bookId);
	
	/**
	 * 	根据chapterId 拿对应的章节内容
	 * @param chapterId
	 * @return
	 */
	String getChapterContentByChapterId(Integer chapterId);
}
