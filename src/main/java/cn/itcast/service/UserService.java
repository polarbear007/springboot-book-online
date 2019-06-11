package cn.itcast.service;

import cn.itcast.entity.User;

public interface UserService {
	User loginByUsernameOrEmailOrPhone(User user);
	Integer registerUserByEmail(User user);
}
