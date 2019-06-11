package cn.itcast.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import cn.itcast.entity.validation.UserLoginValidation;
import cn.itcast.entity.validation.UserRegisterValidation;

public class User {
    private Integer uid;
    
    @NotBlank(message="用户名不能为空 ", groups= {UserRegisterValidation.class})
    @NotBlank(message="帐号不能为空 ", groups= {UserLoginValidation.class})
    // 注册的时候， username 就是username 本身，只需要校验用户名格式
    @Pattern(regexp="(?!\\d+$)(?![a-zA-Z_]+$)\\w{8,20}$", message="用户名格式有误", groups= {UserRegisterValidation.class})
    // 登陆的时候， username 可以是 username / phone / email 三个中的任意一个，所以校验规则要有所变化
    @Pattern(regexp="(^\\w+@\\w+(\\.\\w+)+$)|(^\\d{11}$)|((?!\\d+$)(?![a-zA-Z_]+$)\\w{8,20}$)", message="帐号格式有误", groups= {UserLoginValidation.class})
    private String username;

    @NotBlank(message="密码不能为空 ", groups= {UserLoginValidation.class, UserRegisterValidation.class})
    @Size(min=8, max=15, message="密码的长度范围在8-15位",  groups= {UserLoginValidation.class, UserRegisterValidation.class})
    private String password;

    //@Pattern(regexp="^\\d{11}$", message="电话格式有误",  groups= {UserRegisterValidation.class})
    private String phone;
    
    @Email(message="邮箱格式有误",  groups= {UserRegisterValidation.class})
    private String email;

    private Integer state;

    private String aventorPath;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAventorPath() {
        return aventorPath;
    }

    public void setAventorPath(String aventorPath) {
        this.aventorPath = aventorPath == null ? null : aventorPath.trim();
    }

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", state=" + state + ", aventorPath=" + aventorPath + "]";
	}
}