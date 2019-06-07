package cn.itcast.resource.download;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itcast.resource.download.mapper.ErrorPathMapper;
import cn.itcast.resource.download.mapper.FinishPathMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetResourceTest {
	@Autowired
	private GetResource getResource;
	
	@Autowired
	private ErrorPathMapper errorMapper;
	
	@Autowired
	private FinishPathMapper finishMapper;

	@Test
	public void testGetBookInfoPagePathsByCatId() throws Exception {
		HashSet<String> set = getResource.getBookInfoPagePathsByCatId(30020, 30013, 30031, 30001, 30008, 30036, 30042, 30050, 30083, 30055);
		System.out.println(set.size());
		for (String path : set) {
			System.out.println(path);
		}
	}

	@Test
	public void testSaveBookInfoBypath() throws Exception {
		ArrayList<String> chapterPathlist = getResource.saveBookInfoAndGetChapterPathListBypath("/book/4818982104534803");
		System.out.println(chapterPathlist.size());
		for (String path : chapterPathlist) {
			System.out.println(path);
		}
	}

	@Test
	public void testSaveChapterBypath() throws Exception {
		getResource.saveChapterBypath("https://www.readnovel.com/chapter/9743170703944503/27204274349476268");
	}
	
	@Test
	public void testSaveOneBookWithAllChapters() throws Exception {
		// 开始爬数据之前，我们先从数据库读取全部的错误路径名单（详情页面）
		// 如果我们要访问的详情页面出现在这个集合中，那么直接跳过
		// 另外，如果我们访问到一个新的错误路径，也要同步到此集合，然后还要保存到数据库持久化
		Set<String> errorPaths = errorMapper.getErrorPaths();
		if(errorPaths == null) {
			errorPaths = new HashSet<>();
		}
		
		// 同样的道理，我们先从数据库读取全部已经保存的详情页面路径
		// 省得下次再遍历的时候，还得获取全部的章节路径，封装好 chapter 对象以后，又发现保存不了
		Set<String> finishPaths = finishMapper.getFinishPaths();
		if(finishPaths == null) {
			finishPaths = new HashSet<>();
		}
		
		HashSet<String> set = getResource.getBookInfoPagePathsByCatId(30020, 30013, 30031, 30001, 30008, 30036, 30042, 30050, 30083, 30055);
		System.out.println("路径收集完毕，总共：" + set.size());
		
		if(set != null && set.size() > 0) {
			for (String bookInfoPath : set) {
				System.out.println("当前访问的路径是： " + bookInfoPath);
				ArrayList<String> chapterPathlist = null;
				
				// 开始收集前，我们先看看维护的那两个集合中有没有此路径
				// 如果有的话，就直接跳过即可。
				if(errorPaths.contains(bookInfoPath)) {
					System.out.println("已经跳过错误路径： " + bookInfoPath);
					continue;
				}
				if(finishPaths.contains(bookInfoPath)) {
					System.out.println("此路径的书籍信息已经收集完毕，跳过： " + bookInfoPath);
					continue;
				}
				
				try {
					chapterPathlist = getResource.saveBookInfoAndGetChapterPathListBypath(bookInfoPath);
				}catch(Exception e) {
					// 打开详情页面的时候，可能会出现无法访问的情况，所以这里try 一下，免得一个链接解析不了
					// 就全部停下来。
					// 如果出现解析不了的问题，我们可以把这条路径保存到 errorPaths 集合，同时保存到数据库
					errorPaths.add(bookInfoPath);
					
					// 一般来说，已经保存过的路径前面已经过滤了，这里也没有并发，所以一般不会有重复保存问题
					try {
						Integer result = errorMapper.saveErrorPath(bookInfoPath);
						if(result == 1) {
							System.out.println("错误路径保存成功");
						}
					} catch (Exception e2) {
						System.out.println("错误路径保存失败，这里并不处理，只是怕因为保存失败而导致程序停止");
					}
				}
				
				if(chapterPathlist != null && chapterPathlist.size() > 0) {
					for (String path : chapterPathlist) {
						getResource.saveChapterBypath(path);
					}
					
					// 这里我们把已经完成收集的路径保存起来
					finishPaths.add(bookInfoPath);
					
					// 保存到数据库
					finishMapper.saveFinishPath(bookInfoPath);
					System.out.println("成功保存此书：" + bookInfoPath);
				}
			}
		}
	}
	
}
