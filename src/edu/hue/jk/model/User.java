package edu.hue.jk.model;
public class User {
    private String username;
    private String password;
    // 用户类型:normal 普通用户；admin：管理员
    public enum Type {normal, admin};
    private Type type;
    private boolean login = false;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isLogin() {
        return login;
    }
    public void setLogin(boolean login) {
        this.login = login;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
