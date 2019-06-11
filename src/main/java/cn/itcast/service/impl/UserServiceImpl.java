package cn.itcast.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.entity.User;
import cn.itcast.entity.UserExample;
import cn.itcast.mapper.UserMapper;
import cn.itcast.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User loginByUsernameOrEmailOrPhone(User user) {
		// 非空校验都由 jsr303 事先校验好，这里只校验 username 是用户名还是邮箱还是电话
		// 先判断是不是 email，如果是，按 email 查询
		// 带有 @ 就算是邮箱了，用户名不可以带有 @ 字符
		if(user.getUsername().matches("^\\w+@\\w+(\\.\\w+)+$")) {
			return loginByUsername(user);
		}
		// 再判断是不是 电话，如果是，按电话查询
		// 直接简单的 11 位数字便算是手机了， 用户名不可以全部是数字
		if(user.getUsername().matches("^\\d{11}$")) {
			return loginByPhone(user);
		}
		// 如果前面两个都不是的话，那么就校验是否符合用户名规则
		// 符合，按用户名查找； 不符合，直接扔异常
		// 用户名规则： 不少于8位，不长于20位，大小写字母数字下划线组合，不可以全部是数字，不可以全部是字母或下划线
		if(user.getUsername().matches("(?!\\d+$)(?![a-zA-Z_]+$)\\w{8,20}$")) {
			return loginByUsername(user);
		}else {
			// 其实这里也可以使用 jsr303 避免，但是暂时就先这样写吧
			throw new RuntimeException("非法参数!");
		}
	}
	
	public User loginByUsername(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public User loginByEmail(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public User loginByPhone(User user) {
		UserExample example = new UserExample();
		example.createCriteria().andPhoneEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public Integer registerUserByEmail(User user) {
		Integer byUsername = countByUsername(user.getUsername());
		if(byUsername > 0) {
			throw new RuntimeException("用户名已经存在！");
		}
		Integer byEmail = countByEmail(user.getEmail());
		if(byEmail > 0) {
			throw new RuntimeException("邮箱已经存在！");
		}
		
		// 暂时我们也不搞什么帐号激活了，先注册的时候就默认激活
		user.setState(2);
		
		// 前面检查都没有重复以后，我们还需要生成一个随机字符串，用来激活帐号
		// 帐号状态设置成  1 表示未激活
		
		return userMapper.insertSelective(user);
	}
	
	public Integer countByUsername(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		return userMapper.countByExample(example);
	}
	
	public Integer countByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		return userMapper.countByExample(example);
	}
	
	public Integer countByPhone(String phone) {
		UserExample example = new UserExample();
		example.createCriteria().andPhoneEqualTo(phone);
		return userMapper.countByExample(example);
	}
}
