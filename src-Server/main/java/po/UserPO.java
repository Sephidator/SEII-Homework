package po;

//帐号对象
public class UserPO {
    //帐号ID
    private int id;
    //员工年龄
    private int age;
    //是否为最高权限
    private boolean top;
    //员工姓名
    private String name;
    //帐号名称
    private String user;
    //帐号密码
    private String passport;
    //帐号通知消息
    private String[] notice;

    public UserPO(int id, int age, boolean top, String name, String user, String passport, String[] notice) {
        this.id = id;
        this.age = age;
        this.top = top;
        this.name = name;
        this.user = user;
        this.passport = passport;
        this.notice = notice;
    }

    public int getId() {
        return id;
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

    public String getUser() {
        return user;
    }

    public String getPassport() {
        return passport;
    }

    public String[] getNotice() {
        return notice;
    }
}
