package cn.itcast.entity;

public class User {
    private Integer uid;

    private String username;

    private String password;

    private String phone;

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
}