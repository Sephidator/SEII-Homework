package main.java.po.client;

import main.java.po.PO;

public class ClientPO extends PO {
    private String category; // 客户类别：进货商、销售商
    private int level; // 客户级别：1-5（vip）
    private String name; // 客户姓名
    private String phone; // 客户电话
    private String address; // 客户地址
    private String post; // 客户邮编
    private String email; // 客户电子邮箱
    private double receivable; // 客户应收
    private double payable; // 客户应付
    private double receivableLimit; // 客户应收额度
    private String salesmanID; // 默认业务员

    public ClientPO() {
        category = "";
        level = 0;
        name = "";
        phone = "";
        address = "";
        post = "";
        email = "";
        receivable = 0;
        payable = 0;
        receivableLimit = 0;
        salesmanID = "";
    }

    public ClientPO(String category, int level, String name,
                    String phone, String address, String post, String email,
                    double receivable, double payable, double receivableLimit, String salesmanID) {
        this.category = category;
        this.level = level;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.post = post;
        this.email = email;
        this.receivable = receivable;
        this.payable = payable;
        this.receivableLimit = receivableLimit;
        this.salesmanID = salesmanID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getReceivable() {
        return receivable;
    }

    public void setReceivable(double receivable) {
        this.receivable = receivable;
    }

    public double getPayable() {
        return payable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public double getReceivableLimit() {
        return receivableLimit;
    }

    public void setReceivableLimit(double receivableLimit) {
        this.receivableLimit = receivableLimit;
    }

    public String getSalesmanID() {
        return salesmanID;
    }

    public void setSalesmanID(String salesmanID) {
        this.salesmanID = salesmanID;
    }
}
