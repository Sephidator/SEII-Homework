package main.java.vo.user;

public class UserVO {

    private String type; //帐号身份

    private int age;//员工年龄

    private boolean top=false;//是否为最高权限

    private String name;//员工姓名

    private String ID;//帐号名称

    private String password;//帐号密码

    private boolean visible=true;// 用户是否存在

    public UserVO() {

    }

    public UserVO(String type, int age, boolean top, String name, String ID, String password, boolean visible) {
        this.type = type;
        this.age = age;
        this.top = top;
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.visible = visible;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public boolean isTop() {
        return top;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
