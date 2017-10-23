package po;

import java.io.Serializable;

//帐号对象
public class UserPO implements Serializable{
    //帐号身份
    private int type;
    //员工年龄
    private int age;
    //是否为最高权限
    private boolean top;
    //员工姓名
    private String name;
    //帐号名称
    private String ID;
    //帐号密码
    private String password;
    //帐号通知消息
    private String[] notice;

    public UserPO(int type, int age, boolean top, String name, String ID, String password, String[] notice) {
        this.type = type;
        this.age = age;
        this.top = top;
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.notice = notice;
    }

    public int getType() {
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

    public String[] getNotice() {
        return notice;
    }
}
