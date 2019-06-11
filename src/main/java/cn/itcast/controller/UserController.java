package cn.itcast.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.entity.Message;
import cn.itcast.entity.User;
import cn.itcast.entity.validation.UserLoginValidation;
import cn.itcast.entity.validation.UserRegisterValidation;
import cn.itcast.service.UserService;

@RestController
@CrossOrigin(origins= {"http://localhost:8080"})
public class UserController {
	private HashMap<String, Object> tokenMap = new HashMap<>();
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Message login(@Validated(UserLoginValidation.class) @RequestBody User user, BindingResult bindingResult) {
		Message message = new Message();
		if(bindingResult.hasFieldErrors()) {
			// 一旦有参数校验失败，我们直接给 message 的自定义状态码设置成2,然后把所有的错误对象放到 Message 对象中返回
			message.setStatus(2);
			message.setMsg("参数校验失败！");
			message.getData().put("fieldErrors", bindingResult.getFieldErrors());
			return message;
		}
		
		// 如果前面的参数校验没有什么问题，那么我们就去查询数据库
		User existUser = userService.loginByUsernameOrEmailOrPhone(user);
		// 如果返回的结果不为 null ，说明校验成功
		if(existUser != null) {
			// 如果校验成功，我们把状态码设置成 1
			message.setStatus(1);
			message.setMsg("身份校验成功");
			
			// 校验成功以后，我们需要生成一个  token 字符串
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			// 把这个 token 保存到message 对象中
			message.getData().put("auth_token", token);
			// 另外，后台也需要以这个 token 为  key ，保存一些这个用户相关的一些数据
			tokenMap.put(token, existUser);
		}else {
			// 如果校验失败的话，那么我们就把状态码设置成 0
			message.setStatus(0);
			message.setMsg("身份校验失败！");
		}
		System.out.println(message);
		return message;
	}
	
	@PostMapping("/registerByEmail")
	public Message register(@Validated(UserRegisterValidation.class) @RequestBody User user, BindingResult bindingResult) {
		Message message = new Message();
		if(bindingResult.hasFieldErrors()) {
			// 一旦有参数校验失败，我们直接给 message 的自定义状态码设置成 2,然后把所有的错误对象放到 Message 对象中返回
			message.setStatus(2);
			message.setMsg("参数校验失败！");
			message.getData().put("fieldErrors", bindingResult.getFieldErrors());
			return message;
		}
		
		// 如果前面的校验没有什么问题，那么我们这里就调用 service 方法进行注册了
		Integer result = null;
		try {
			result = userService.registerUserByEmail(user);
		}catch(RuntimeException e) {
			// 注册过程中，如果出现用户名、邮箱，会抛异常，最好是自定义异常
			// 但是我们为了省事，直接使用 runtimeException
			// 如果出现异常，我们把状态设置成 3 , 然后把异常信息放在 message 中
			message.setStatus(3);
			message.setMsg(e.getMessage());
			return message;
		}
		
		if(result > 0 ) {
			message.setStatus(4);
			message.setMsg("注册成功！");
		}else {
			message.setStatus(5);
			message.setMsg("注册失败,请稍后再试！");
		}
		return message;
	}
	
	@GetMapping("/validateToken")
	public Message validateToken(HttpServletRequest request) {
		Message message = new Message();
		String token = request.getHeader("auth_token");
		Object user = tokenMap.get(token);
		if(user != null) {
			// 状态码为 10， 表示 token 校验有效
			message.setStatus(10);
			message.setMsg("token 校验成功");
			message.getData().put("user", user);
		}else {
			// 状态码为 11， 表示  token 校验无效，一般是因为过期
			message.setStatus(11);
			message.setMsg("token 过期");
		}
		System.out.println(message);
		return message;
	}
}
