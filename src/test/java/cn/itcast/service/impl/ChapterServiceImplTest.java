package cn.itcast.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.entity.Chapter;
import cn.itcast.service.ChapterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChapterServiceImplTest {
	@Autowired
	private ChapterService chapterService;
	
	@Test
	public void testAddChapter() {
		Chapter chapter = new Chapter();
		chapter.setBookId(1);
		chapter.setChapterTitle("章节标题1");
		chapter.setChapterContent("这是测试的数据....");
		chapterService.addChapter(chapter);
	}

	@Test
	public void testGetChapterById() {
		Chapter chapter = chapterService.getChapterById(1);
		System.out.println(chapter.getChapterContent());
	}

	@Test
	public void testUpdateChapterById() {
		Chapter chapter = chapterService.getChapterById(1);
		chapter.setChapterContent("哈哈哈");
		chapter.setChapterTitle("这是修改以后的标题");
		chapter.setChapterNum(2);
		chapterService.updateChapterById(chapter);
	}

	@Test
	public void testDeleteChapterById() {
		chapterService.deleteChapterById(2);
	}

}
