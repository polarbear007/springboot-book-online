package cn.itcast.resource.download;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import cn.itcast.entity.Author;
import cn.itcast.entity.BookCategory;
import cn.itcast.entity.BookInfo;
import cn.itcast.entity.Chapter;
import cn.itcast.service.AuthorService;
import cn.itcast.service.BookCategoryService;
import cn.itcast.service.BookInfoService;
import cn.itcast.service.ChapterService;
import cn.itcast.util.IntegerUtil;

@Component
public class GetResource {
	private String baseUrl = "https://www.readnovel.com";
	
	@Autowired
	private WebClient client;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookCategoryService categoryService;
	
	@Autowired
	private BookInfoService infoService;
	
	@Autowired
	private ChapterService chapterService;
	
	// 获取所有书籍详情页面地址
	public HashSet<String> getBookInfoPagePathsByCatId(Integer... catIds) throws Exception{
		HashSet<String> set = new HashSet<>();
		if(catIds.length > 0) {
			for (Integer catId : catIds) {
				HtmlPage page = client.getPage("https://www.readnovel.com/all?pageNum=1&pageSize=100&gender=2&catId="+catId+"&isFinish=-1&isVip=-1&size=-1&updT=-1&orderBy=0");
				DomNodeList<DomElement> divList = page.getElementsByTagName("div");
				if(divList != null) {
					for (DomElement div : divList) {
						if("book-img".equals(div.getAttribute("class"))) {
							String path = div.getFirstElementChild().getAttribute("href");
							set.add(path);
						}
					}
				}
			}
		}
		return set;
	}
	
	
	// 访问详情页面地址，根据详情页面上面的信息，封装 BookInfo / Author / BookCategory 对象
	// 这些对象如果已经保存了，那么我们就不再保存
	// 如果还没有保存的话，那么我们就 保存到数据库
	
	// 同时，返回这个页面上免费章的全部页面地址，使用 list 集合保存 
	public ArrayList<String> saveBookInfoAndGetChapterPathListBypath(String path) throws Exception{
		// 事先准备好一个 chapterPathList 集合，用于保存一本书全部的章节地址
		ArrayList<String> chapterPathList = new ArrayList<>();
		
		// 事先创建好要封装的 BookInfo 对象
		BookInfo info = new BookInfo();
		// 默认是免费，如果我们找到了需要付费的章节，那么再把 info 改成付费
		info.setIsFree(2);
		
		// 事先创建好要封装的BookInfo 空对象， 因为前面的 set 集合已经保证不会
		HtmlPage page = client.getPage(baseUrl + path);
		DomNodeList<DomElement> divList = page.getElementsByTagName("div");
		if(divList != null && divList.size() > 0) {
			for (DomElement div : divList) {
				if("book-information cf".equals(div.getAttribute("class"))) {
					DomNodeList<HtmlElement> infoDivList = div.getElementsByTagName("div");
					for (DomElement infoDiv : infoDivList) {
						// 获取到图书封面图片地址
						if("book-img".equals(infoDiv.getAttribute("class"))) {
							// 获取并保存图片地址
							String bookCoverPath = infoDiv.getElementsByTagName("img").get(0).getAttribute("src");
							info.setBookCoverPath(bookCoverPath);
						}
						
						// 获取图书的相关信息
						if("book-info".equals(infoDiv.getAttribute("class"))) {
							HtmlElement element = infoDiv.getElementsByTagName("h1").get(0);
							// 获取书名
							String bookName = element.getElementsByTagName("em").get(0).getTextContent();
							info.setBookName(bookName);
							// 获取作者名
							String authorName = element.getElementsByTagName("a").get(0).getTextContent().replaceAll("著$", "").trim();
							// 根据作者名去数据库查询，如果存在我们不处理，如果不存在，我们就封装一个 author 对象，并保存
							Author author = authorService.getAuthorByName(authorName);
							if(author == null) {
								author = new Author();
								author.setAuthorName(authorName);
								authorService.addAuthor(author);
							}
							// 最后我们保存一下 BookInfo 的 authorId
							info.setAuthorId(author.getAuthorId());
							
							
							// 获取图书的状态信息 / 类别信息 / 介绍信息
							DomNodeList<HtmlElement> pList = infoDiv.getElementsByTagName("p");
							if(pList != null && pList.size() > 0) {
								for (DomElement p : pList) {
									if("tag-box".equals(p.getAttribute("class"))) {
										DomNodeList<HtmlElement> iList = p.getElementsByTagName("i");
										// 不想判断了，直接拿第一个 i 标签
										String bookState = iList.get(0).getTextContent();
										if("已完结".equals(bookState)) {
											info.setBookState(2);
										}else if("连载中".equals(bookState)) {
											info.setBookState(1);
										}
										
										// 不想判断了，直接拿倒数第二个 i 标签
										String categoryName = iList.get(iList.getLength() - 2).getTextContent();
										BookCategory category = categoryService.getCategoryByName(categoryName);
										// 如果 category 为 null，说明数据库里面没有对应的类别，我们就添加
										if(category == null) {
											category = new BookCategory();
											category.setCategoryName(categoryName);
											categoryService.addCategory(category);
										}
										// 最后设置一下 BookInfo 的 categoryId
										info.setCategoryId(category.getCategoryId());
									}
									
									// 保存图书介绍
									if("intro".equals(p.getAttribute("class"))) {
										String bookIntroduce = p.getTextContent();
										info.setBookIntroduce(bookIntroduce);
									}
								}
							}
						}
					}
				}
				
				// 获取书籍对应的章节地址集合
				// 我们只获取免费的章节
				if("volume".equals(div.getAttribute("class"))) {
					String isFree = div.getElementsByTagName("span").get(0).getAttribute("class");
					if("free".equals(isFree)) {
						// 如果是免费的章节的话，那么我们就要使用一个  ArrayList 集合，把这些章节地址保存起来
						// 注意： 章节是有顺序的，所以我们不能使用 set 集合来保存了。
						DomNodeList<HtmlElement> liList = div.getElementsByTagName("li");
						if(liList != null && liList.size() > 0) {
							for (DomElement li : liList) {
								String chapterPath = "https:" + li.getElementsByTagName("a").get(0).getAttribute("href");
								chapterPathList.add(chapterPath);
							}
						}
					}else if("vip".equals(isFree)) {
						info.setIsFree(1);
					}
				}
			}
		}
		
		// 最后，我们再判断一下有没有得到有用的章节信息，如果有的话，我们就保存这个 BookInfo 对象
		if(chapterPathList.size() > 0) {
			// 保存章节信息以前，我们最好查一下，这本书有没有保存过，虽然我们前面使用 一个 set 集合保证没有重复
			// 但是如果你采集是分很多次进行的，那么set 集合并没有什么用
			BookInfo bookInfo = infoService.getBookInfoByName(info.getBookName());
			// 如果数据库中没有此书，我们再去保存数据库
			if(bookInfo == null) {
				infoService.addBookInfo(info);
			}
		}
		return chapterPathList;
	}
	
	// 根据章节地址，封装 Chapter 对象，然后保存到数据库
	// 这里其实还是有一个重复保存的问题，我打算在数据库设置  chapterNum / bookId 组合唯一索引
	// 然后爬取数据的时候就根据  这两个列的值来看一下是否已经保存此章节
	// 如果保存了，那么我们 就不再保存了
	// ===> 其实最稳妥办法是使用人家的 chapterId 作为主键。 但是这样只能爬取一个网站的数据。
	public boolean saveChapterBypath(String path) throws Exception {
		// 拿到路径以后，我们先把根据path 把原始的章节 id 取出来， rest 风格，最后一个路径参数即是
		String id = null;
		String[] strArr = path.split("/");
		if(strArr != null && strArr.length > 0) {
			id = "chapter-" + strArr[strArr.length - 1];
		}
		
		if(id != null) {
			// 事先创建一个 Chapter 对象
			Chapter chapter = new Chapter();
			
			HtmlPage page = client.getPage(path);
			// 获取章节对应的书名
			// 我们需要根据书名去数据库查询对应的  bookId 
			// 如果没有获取到对应的 bookId ，那么就要抛异常。
			String bookName = page.getElementById("bookImg").getTextContent().trim();
			BookInfo info = infoService.getBookInfoByName(bookName);
			if(info == null) {
				throw new RuntimeException("此章节对应的书不存在！！");
			}
			chapter.setBookId(info.getBookId());
			
			DomElement element = page.getElementById(id);
			DomNodeList<HtmlElement> divList = element.getElementsByTagName("div");
			if(divList != null && divList.size() > 0) {
				for (HtmlElement div : divList) {
					// 获取章节的一些相关信息
					if("text-head".equals(div.getAttribute("class"))) {
						String chapterTitle = div.getElementsByAttribute("h3", "class", "j_chapterName").get(0).getTextContent().trim();
						chapter.setChapterTitle(chapterTitle);
						
						// 我们暂时根据这个章节标题来截取出对应的 chapterNum 来
						Integer chapterNum = null;
						
						Pattern p = Pattern.compile("第(.+)章");
						Matcher m = p.matcher(chapterTitle);
						if(m.find()) {
							String chapterNumStr = m.group(1).trim();
							try {
								chapterNum = Integer.parseInt(chapterNumStr);
							}catch(Exception e) {
								//System.out.println("数字转换失败，因为章节可能是中文数字");
								chapterNum = IntegerUtil.getIntegerByNumberStr(chapterNumStr);
							}
						}
						chapter.setChapterNum(chapterNum);
						
						String updateTime = div.getElementsByAttribute("span", "class", "j_updateTime").get(0).getTextContent().trim();
						Date lastUpdateTime = null;
						try {
							lastUpdateTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse(updateTime);
						}catch(Exception e) {
							System.out.println("日期解析失败，但是不做处理");
						}
						chapter.setLastUpdateTime(lastUpdateTime);
					}
					
					// 获取章节内容
					if("read-content j_readContent".equals(div.getAttribute("class"))) {
						String chapterContent = div.getTextContent();
						chapter.setChapterContent(chapterContent);
					}
				}
			}
			
			// 最后我们要先看一下这一章存不存在，如果存在，就不保存了
			// 但是如果前面我们没有根据 title 解析出对应的 chapterNum 的话，这里又会报错
			// 如果是这种情况，我们只好容忍重复了
			Chapter existChapter = null;
			if(chapter.getChapterNum() != null) {
				existChapter = chapterService.getChapterByChapterNumAndBookId(chapter.getChapterNum(), chapter.getBookId());
			}
			
			if(existChapter == null) {
				//System.out.println(chapter);
				Integer result = chapterService.addChapter(chapter);
				if(result == 1) {
					return true;
				}
			}
		}
		return false;
	}
}
