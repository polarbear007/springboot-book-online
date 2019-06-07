package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.entity.BookInfo;
import cn.itcast.entity.Chapter;
import cn.itcast.entity.ChapterExample;
import cn.itcast.mapper.BookInfoMapper;
import cn.itcast.mapper.ChapterMapper;
import cn.itcast.service.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService{
	@Autowired
	private ChapterMapper chapterMapper;
	
	@Autowired
	private BookInfoMapper infoMapper;

	@Override
	@Transactional
	public Integer addChapter(Chapter chapter) {
		// 添加之前，我们应该先校验一下  chapterTitle / bookId / chapterContent
		// 都不能为空。  其实这些校验可以都放到 controller 层，使用 hibernate-validator 校验
		//  所以这里就不再校验了
		
		// 首先，我们要保证章节对应的书是存在的
		BookInfo info = infoMapper.selectByPrimaryKey(chapter.getBookId());
		if(info == null) {
			throw new RuntimeException("bookId 不存在！");
		}
		
		// 下面的三个参数，都由后台自动来生成，防止出错
		// 添加前，我们先获取当前的时间
		if(chapter.getLastUpdateTime() == null) {
			chapter.setLastUpdateTime(new Date());
		}
		// 根据 content 获取长度
		Integer length = chapter.getChapterContent().length();
		chapter.setChapterLength(length);
		
		// 如果我们没有指定 chapterNum ，那么就会自动设置一个; 如果指定了，那么就按指定的来就行了。
		if(chapter.getChapterNum() == null) {
			// 先根据 bookId 获取最大的 chapterNum 
			Integer maxChapterNum = chapterMapper.getMaxChapterNumByBookId(chapter.getBookId());
			// 如果没有找到对应的 bookId 的话，说明还没有关于本书的章节信息，也就是这是插入的第一章
			if(maxChapterNum != null) {
				chapter.setChapterNum(maxChapterNum + 1); 
			}else {
				chapter.setChapterNum(1);
			}
		}
		
		// 然后我们就添加章节
		int result = chapterMapper.insertSelective(chapter);
		
		// 判断 result ，如果 resutl == 1 说明插入成功
		// 我们就需要再更新一下 book_info 表的  length 和 update_time
		if(result == 1) {
			info.setLastUpdateTime(chapter.getLastUpdateTime());
			info.setBookLength(info.getBookLength() + length);
			
			int result2 = infoMapper.updateByPrimaryKeySelective(info);
			// 如果更新失败的话，那么我们直接抛异常回滚
			if(result2 != 1) {
				throw new RuntimeException("t_book_info 表更新失败");
			}
		}
		
		return result;
	}

	@Override
	public Chapter getChapterById(Integer chapterId) {
		return chapterMapper.selectByPrimaryKey(chapterId);
	}

	@Override
	@Transactional
	public Integer updateChapterById(Chapter chapter) {
		if(chapter.getChapterId() == null) {
			throw new RuntimeException("chapterId 不能为空！");
		}
		
		// 根据chapterId 先获取原来的 chapter 对象，如果为空，则报错
		Chapter oldChapter = chapterMapper.selectByPrimaryKey(chapter.getChapterId());
		if(oldChapter == null) {
			throw new RuntimeException("要修改的章节不存在！");
		}
		
		// 根据 oldChapter 找到对应的  bookInfo 对象
		// 一般我们需要设置 外键关系，防止这种问题，这里也判断一下
		BookInfo info = infoMapper.selectByPrimaryKey(oldChapter.getBookId());
		if(info == null) {
			throw new RuntimeException("系统错误！！！");
		}
		
		// 把 bookId 设置成 null ，防止错误修改
		chapter.setBookId(null);
		
		// 这是维护更新时间和长度
		Date date = new Date();
		chapter.setLastUpdateTime(date);
		Integer length = chapter.getChapterContent().length();
		chapter.setChapterLength(length);
		
		int result = chapterMapper.updateByPrimaryKeySelective(chapter);
		if(result == 1) {
			info.setLastUpdateTime(date);
			info.setBookLength(info.getBookLength() + length - oldChapter.getChapterLength());
			
			int result2 = infoMapper.updateByPrimaryKeySelective(info);
			// 如果更新失败的话，那么我们直接抛异常回滚
			if(result2 != 1) {
				throw new RuntimeException("t_book_info 表更新失败");
			}
		}
		
		return result;
	}

	@Override
	public Integer deleteChapterById(Integer chapterId) {
		// 删除之前，我们应该先查询一下这个章节是否存在 
		Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
		if(chapter == null) {
			throw new RuntimeException("要删除的章节不存在！");
		}
		// 根据 chapter 找到对应的  bookInfo 对象
		// 一般我们需要设置 外键关系，防止这种问题，这里也判断一下
		BookInfo info = infoMapper.selectByPrimaryKey(chapter.getBookId());
		if(info == null) {
			throw new RuntimeException("系统错误！！！");
		}
		
		int result = chapterMapper.deleteByPrimaryKey(chapterId);
		if(result == 1) {
			// 删除章节的话，我们就不算更新时间了
			// 按理说我们还得去查最新的一章，然后同步到 t_book_info 表去，这里偷个懒
			info.setBookLength(info.getBookLength() - chapter.getChapterLength());
			
			int result2 = infoMapper.updateByPrimaryKeySelective(info);
			// 如果更新失败的话，那么我们直接抛异常回滚
			if(result2 != 1) {
				throw new RuntimeException("t_book_info 表更新失败");
			}
		}
		return result;
	}

	@Override
	public Chapter getChapterByChapterNumAndBookId(Integer chapterNum, Integer bookId) {
		ChapterExample example = new ChapterExample();
		example.createCriteria().andBookIdEqualTo(bookId).andChapterNumEqualTo(chapterNum);
		List<Chapter> list = chapterMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Chapter> getChaptersByBookId(Integer bookId) {
		ChapterExample example = new ChapterExample();
		example.setOrderByClause("chapter_num");
		example.createCriteria().andBookIdEqualTo(bookId);
		return chapterMapper.selectByExample(example);
	}

	@Override
	public String getChapterContentByChapterId(Integer chapterId) {
		return chapterMapper.getChapterContentByChapterId(chapterId);
	}
}
