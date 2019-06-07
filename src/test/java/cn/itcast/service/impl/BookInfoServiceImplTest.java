package cn.itcast.service.impl;


import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.entity.BookInfo;
import cn.itcast.entity.condition.BookInfoQueryConditionWrapper;
import cn.itcast.entity.condition.LengthRange;
import cn.itcast.service.BookInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookInfoServiceImplTest {
	@Autowired
	private BookInfoService infoService;
	
	@Test
	public void testAddBookInfo() {
		BookInfo info = new BookInfo();
		info.setBookName("平凡的世界1");
		info.setBookState(1);
		info.setIsFree(1);
		info.setAuthorId(1);
		info.setCategoryId(1);
		infoService.addBookInfo(info);
	}
	
	@Test
	public void testUpdateBookInfoById() {
		BookInfo info = new BookInfo();
		info.setBookId(1);
		info.setIsFree(2);
		infoService.updateBookInfoById(info);
	}

	@Test
	public void testDeleteBookInfoById() {
		infoService.deleteBookInfoById(1);
	}
	
	/**
	 * 	添加一些测试数据
	 */
	@Test
	public void addBookInfoForTest() {
		for (int i = 0; i < 1000; i++) {
			BookInfo info = new BookInfo();
			info.setBookId(i + 1);
			info.setBookName("平凡的世界" + i);
			info.setBookIntroduce("这是测试数据....");
			// 长度在 1 - 250万 之间生成一个随机数据
			//info.setBookLength(new Random().nextInt(2500000) + 1);
			// 书籍状态： 1 表示连载， 2表示完结
			info.setBookState(new Random().nextInt(2) + 1);
			// 是否免费： 1表示收费，2表示免费
			info.setIsFree(new Random().nextInt(2) + 1);
			// 这里只为测试，全部设置成一个作者
			info.setAuthorId(1);
			// 设置类别，取值在 1-10 之间
			info.setCategoryId(new Random().nextInt(10) + 1);
			// 设置更新时间
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, (new Random().nextInt(60)) * (-1));
			info.setLastUpdateTime(calendar.getTime());
			infoService.addBookInfo(info);
		}
	}
	
	@Test
	public void testGetBookInfoWithAuthorAndCategoryByKeyword() {
		BookInfoQueryConditionWrapper wrapper = new BookInfoQueryConditionWrapper();
		wrapper.setKeyword("4");
		wrapper.setCurrentPage(2);
//		wrapper.setOrderByKey(1);
		List<BookInfo> list = infoService.getBookInfoWithAuthorAndCategoryByKeyword(wrapper);
		if(list != null) {
			for (BookInfo bookInfo : list) {
				System.out.println(bookInfo);
			}
		}
	}
	
	@Test
	public void testGetBookInfoWithAuthorAndCategoryByWrapper() {
		BookInfoQueryConditionWrapper wrapper = new BookInfoQueryConditionWrapper();
		wrapper.setCategoryId(2);
		wrapper.setIsFree(2);
		wrapper.setBookState(2);
		wrapper.setLengthRange(new LengthRange(300000, 500000));
		wrapper.setUpdateWithinDays(30);
		List<BookInfo> list = infoService.getBookInfoWithAuthorAndCategoryByWrapper(wrapper);
		if(list != null) {
			for (BookInfo bookInfo : list) {
				System.out.println(bookInfo);
			}
		}
	}
	
	@Test
	public void testGetBookInfoByBookId() {
		BookInfo info = infoService.getBookInfoWithhAuthorAndCategoryByBookId(1);
		System.out.println(info);
	}
}
