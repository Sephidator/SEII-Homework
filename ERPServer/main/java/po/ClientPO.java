package po;

import java.io.Serializable;

/**
 * 客户PO类
 * */
public class ClientPO implements Serializable {
    private String ID; // 客户编号
    private String category; // 客户类别：进货商、销售商
    private int level; // 客户级别：1-5（vip）
    private String name; // 客户姓名
    private String phone; // 客户电话
    private String address; // 客户地址
    private String post; // 客户邮编
    private String email; // 客户电子邮箱
    private double receivable; // 客户应收
    private double payable; // 客户应付
    private double receivableLimit;// 客户应收额度
    private String salesmanID; // 默认业务员

    /**
     * 设置客户的所有信息
     */
    public ClientPO(String ID, String category, int level, String name,
                    String phone, String address, String post, String email,
                    double receivable, double payable, double receivableLimit, String salesmanID) {
        this.ID = ID;
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

    public String getID(){
        return ID;
    }

    public String getCategory(){
        return category;
    }

    public int getLevel(){
        return level;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

    public String getPost(){
        return post;
    }

    public String getEmail(){
        return email;
    }

    public double getReceivable(){
        return receivable;
    }

    public double getPayable(){
        return payable;
    }

    public double getReceivableLimit(){
        return receivableLimit;
    }

    public String getSalesmanID(){
        return salesmanID;
    }
}
