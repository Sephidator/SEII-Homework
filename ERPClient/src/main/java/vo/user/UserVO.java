package main.java.vo.user;

import main.java.po.user.UserPO;
import main.java.vo.VO;

public class UserVO extends VO{

    private String name; //员工姓名

    private String type; //员工身份

    private String jobName; //员工工号

    private String password; //帐号密码

    private int age; //员工年龄

    private boolean top; //员工是否为最高权限

    private boolean login; //员工是否登录

    public UserVO() {

    }

    public UserVO(String name, String type, String jobName, String password, int age, boolean top) {
        this.name = name;
        this.type = type;
        this.jobName = jobName;
        this.password = password;
        this.age = age;
        this.top = top;
    }

    public UserPO getUserPO(){
        UserPO userPO=new UserPO();
        userPO.setID(this.ID);
        userPO.setVisible(this.visible);
        userPO.setName(this.name);
        userPO.setType(this.type);
        userPO.setJobName(this.jobName);
        userPO.setPassword(this.password);
        userPO.setAge(this.age);
        userPO.setTop(this.top);
        userPO.setLogin(this.login);

        return userPO;
    }

    public UserVO(UserPO userPO){
        this.ID=userPO.getID();
        this.visible=userPO.isVisible();
        this.name=userPO.getName();
        this.type=userPO.getType();
        this.jobName=userPO.getJobName();
        this.password=userPO.getPassword();
        this.age=userPO.getAge();
        this.top=userPO.isTop();
        this.login=userPO.isLogin();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String userName) {
        this.jobName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
