package cn.itcast.util;

import java.util.HashMap;

/**
 * 创建这个工具类，是为了方便前台传条件参数时，可以直接传数字
 * 我们后台可以根据数字解析成想要的排序条件
 * 
 * 【说明】其实我们这里也是可以像那些  LengthWrapper 处理，但是那样子会把数据库的字段暴露，所以不太好
 * @author Administrator
 *
 */
public class ConditionUtils {
	private ConditionUtils() {};
	private static HashMap<Integer, String> orderByConditionMap = new HashMap<>();
	static {
		// 设置 orderByConditionMap 的一些初始值
		orderByConditionMap.put(0, "book_id");
		orderByConditionMap.put(1, "book_length asc");
		orderByConditionMap.put(2, "book_length desc");
		orderByConditionMap.put(3, "last_update_time asc");
		orderByConditionMap.put(4, "last_update_time desc");
	}
	public static  HashMap<Integer, String> getOrderByConditionMap() {
		return orderByConditionMap;
	}
}
