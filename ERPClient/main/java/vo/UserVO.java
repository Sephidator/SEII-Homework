package vo;

public class UserVO {
    //帐号身份
    public int type;
    //员工年龄
    public int age;
    //是否为最高权限
    public boolean top;
    //员工姓名
    public String name;
    //帐号名称
    public String ID;
    //帐号密码
    public String passport;
    //帐号通知消息
    public String[] notice;

    public UserVO() {

    }

    public UserVO(int type, int age, boolean top, String name, String ID, String passport, String[] notice) {
        this.type = type;
        this.age = age;
        this.top = top;
        this.name = name;
        this.ID = ID;
        this.passport = passport;
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "[ID=" + ID + "]";
    }
}
