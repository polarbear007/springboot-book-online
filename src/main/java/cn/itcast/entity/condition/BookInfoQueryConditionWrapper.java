package cn.itcast.entity.condition;

import java.util.Calendar;
import java.util.Date;

import cn.itcast.util.ConditionUtils;

public class BookInfoQueryConditionWrapper {
	// 上面都可以是等值条件，很好处理
	private Integer pageSize = 10;
	private Integer currentPage = 1;
	private String keyword;
	private Integer categoryId;
	// 1表示连载中， 2表示完结
	private Integer bookState;
	// 1表示付费， 2表示免费
	private Integer isFree;
	// 排序条件
	// 前端传过来一个整数值
	// 我们可以根据这个整数值通过  ConditionUtils.getOrderByConditionMap().get(key)
	// 拿到对应的排序字段值。
	// 我们直接在这个类里面放一个快捷的方法：  getOrderBy()
	private Integer orderByKey;
	// 长度范围条件
	private LengthRange lengthRange;
	// 更新时间条件
	// 我们可以根据这个整数，加上当前的日期
	// 计算出更新时间条件。比如说参数为3 ，当前日期为 2019-1-5  ，那么得到的结果将是  2019-1-2
	// 我们直接在这个类时面放一个快捷的方法：  getDate()
	private Integer updateWithinDays;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getBookState() {
		return bookState;
	}
	public void setBookState(Integer bookState) {
		this.bookState = bookState;
	}
	public Integer getIsFree() {
		return isFree;
	}
	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}
	public Integer getOrderByKey() {
		return orderByKey;
	}
	public void setOrderByKey(Integer orderByKey) {
		this.orderByKey = orderByKey;
	}
	public LengthRange getLengthRange() {
		return lengthRange;
	}
	public void setLengthRange(LengthRange lengthRange) {
		this.lengthRange = lengthRange;
	}
	public Integer getUpdateWithinDays() {
		return updateWithinDays;
	}
	public void setUpdateWithinDays(Integer updateWithinDays) {
		this.updateWithinDays = updateWithinDays;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	// 根据参数，返回对应的日期
	// 【注意】 如果我们想要在  mybatis 的映射文件中调用 getDate() 方法，那么可以直接使用  date 
	//       因为 myatbis 其实是使用 ognl 表达式实现对象导航取值的，我们写 date 表达式，
	//       背后调用的是  getDate() 函数
	public Date getDate() {
		if(this.updateWithinDays != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, this.updateWithinDays * -1);
			return calendar.getTime();
		}else {
			return null;
		}
	}
	
	// 如果我们有设置排序参数，那么就可以通过 getOrderBy() 方法返回对应的排序条件
	public String  getOrderBy() {
		if(this.orderByKey != null) {
			return ConditionUtils.getOrderByConditionMap().get(this.orderByKey);
		}else {
			return null;
		}
	}
	@Override
	public String toString() {
		return "BookInfoQueryConditionWrapper [pageSize=" + pageSize + ", currentPage=" + currentPage + ", keyword="
				+ keyword + ", categoryId=" + categoryId + ", bookState=" + bookState + ", isFree=" + isFree
				+ ", orderByKey=" + orderByKey + ", lengthRange=" + lengthRange + ", updateWithinDays="
				+ updateWithinDays + "]";
	}
	
	
}
