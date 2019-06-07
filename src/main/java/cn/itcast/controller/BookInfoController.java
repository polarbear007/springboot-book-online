package cn.itcast.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.itcast.entity.BookCategory;
import cn.itcast.entity.BookInfo;
import cn.itcast.entity.Chapter;
import cn.itcast.entity.condition.BookInfoQueryConditionWrapper;
import cn.itcast.entity.condition.IsFreeWrapper;
import cn.itcast.entity.condition.LengthRange;
import cn.itcast.entity.condition.StateWrapper;
import cn.itcast.entity.condition.UpdateTimeWrapper;
import cn.itcast.service.BookCategoryService;
import cn.itcast.service.BookInfoService;
import cn.itcast.service.ChapterService;

@RestController
@CrossOrigin(origins= {"http://localhost:8080"})
public class BookInfoController {
	@Autowired
	private BookInfoService infoService;
	
	@Autowired
	private BookCategoryService categoryService;
	
	@Autowired
	private ChapterService chapterService;
	
	/**
	 * 分类查询页面需要加载的一些基本参
	 * @return
	 */
	@GetMapping("/getPrimaryInfo")
	public HashMap<String, Object> getPrimaryInfo(){

		// 创建一个 map 集合
		HashMap<String, Object> infoMap = new HashMap<>();
		
		// 放进全部的类别信息  categoryList
		List<BookCategory> categoryList = categoryService.getCategories();
		infoMap.put("categoryList", categoryList);
		
		// 放进全部的 长度条件  lengthRangeList
		LengthRange l1 = new LengthRange(null, 50000, "5万以下");
		LengthRange l2 = new LengthRange(50001, 100000, "5万-10万");
		LengthRange l3 = new LengthRange(100001, 150000, "10万-15万");
		LengthRange l4 = new LengthRange(150001, 250000, "15万-25万");
		LengthRange l5 = new LengthRange(250001, null, "25万以上");
		ArrayList<LengthRange> lengthRangeList = new ArrayList<LengthRange>();
		lengthRangeList.add(l1);
		lengthRangeList.add(l2);
		lengthRangeList.add(l3);
		lengthRangeList.add(l4);
		lengthRangeList.add(l5);
		infoMap.put("lengthRangeList", lengthRangeList);
		
		// 放进全部的 状态条件  bookStateList
		// 【说明】 因为我们偷懒，没有像 category 那样另外建一张表，所以这里直接使用约定的方式，不查数据库
		StateWrapper state1 = new StateWrapper(1, "连载中");
		StateWrapper state2 = new StateWrapper(2, "已完结");
		ArrayList<StateWrapper> bookStateList = new ArrayList<>();
		bookStateList.add(state1);
		bookStateList.add(state2);
		infoMap.put("bookStateList", bookStateList);
		
		// 放进全部的 是否免费条件   isFreeList
		IsFreeWrapper isFree1 = new IsFreeWrapper(1, "付费");
		IsFreeWrapper isFree2 = new IsFreeWrapper(2, "免费");
		ArrayList<IsFreeWrapper> isFreeList = new ArrayList<IsFreeWrapper>();
		isFreeList.add(isFree1);
		isFreeList.add(isFree2);
		infoMap.put("isFreeList", isFreeList);
		
		// 放进全部的更新时间条件  updateWithinDaysList
		UpdateTimeWrapper wrapper1 = new UpdateTimeWrapper(365, "一年内");
		UpdateTimeWrapper wrapper2 = new UpdateTimeWrapper(730, "两年内");
		UpdateTimeWrapper wrapper3 = new UpdateTimeWrapper(1095, "三年内");
		ArrayList<UpdateTimeWrapper> updateWithinDaysList = new ArrayList<>();
		updateWithinDaysList.add(wrapper1);
		updateWithinDaysList.add(wrapper2);
		updateWithinDaysList.add(wrapper3);
		infoMap.put("updateWithinDaysList", updateWithinDaysList);
		// 最终返回这个集合
		return infoMap;
	}
	
	@PostMapping("/getBookInfoByConditions")
	public PageInfo<BookInfo> getBookInfoByConditions(@RequestBody BookInfoQueryConditionWrapper wrapper){
		System.out.println(wrapper);
		return PageInfo.of(infoService.getBookInfoWithAuthorAndCategoryByWrapper(wrapper));
	}
	
	@GetMapping("/getBookInfoByKeyword")
	public PageInfo<BookInfo> getBookInfoByKeyword(BookInfoQueryConditionWrapper wrapper){
		System.out.println(wrapper);
		PageInfo<BookInfo> page = PageInfo.of(infoService.getBookInfoWithAuthorAndCategoryByKeyword(wrapper));
		System.out.println(page);
		return page;
	}
	
	@GetMapping("/getBookInfoAndChapterListByBookId")
	public HashMap<String, Object> getBookInfoAndChapterListByBookId(Integer bookId){
		HashMap<String, Object> map = new HashMap<String, Object>();
		BookInfo bookInfo = infoService.getBookInfoWithhAuthorAndCategoryByBookId(bookId);
		if(bookInfo == null) {
			throw new RuntimeException("您查询的书籍不存在！！");
		}
		map.put("bookInfo", bookInfo);
		List<Chapter> chapterList = chapterService.getChaptersByBookId(bookId);
		map.put("chapterList", chapterList);
		return map;
	}
	
	@GetMapping("/getChapterContentByChapterId")
	public String getChapterContentByChapterId(Integer chapterId) {
		return chapterService.getChapterContentByChapterId(chapterId);
	}
}
