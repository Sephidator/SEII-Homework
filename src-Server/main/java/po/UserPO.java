package po;

//帐号对象
public class UserPO {
    //帐号身份
    private int category;
    //员工年龄
    private int age;
    //是否为最高权限
    private boolean top;
    //员工姓名
    private String name;
    //帐号名称
    private String ID;
    //帐号密码
    private String passport;
    //帐号通知消息
    private String[] notice;

    public UserPO(int category, int age, boolean top, String name, String ID, String passport, String[] notice) {
        this.category = category;
        this.age = age;
        this.top = top;
        this.name = name;
        this.ID = ID;
        this.passport = passport;
        this.notice = notice;
    }

    public int getCategory() {
        return category;
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

    public String getPassport() {
        return passport;
    }

    public String[] getNotice() {
        return notice;
    }
}
