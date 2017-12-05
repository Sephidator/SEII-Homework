package main.java.vo.client;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.po.client.ClientPO;
import main.java.vo.VO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

/**
 * 客户VO类
 * */
public class ClientVO extends VO{
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
    private UserVO salesman; // 默认业务员

    public ClientVO() {

    }

    public ClientVO(String category, int level, String name,String phone, String address, String post, String email,double receivable, double payable, double receivableLimit, UserVO salesman) {
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
        this.salesman = salesman;
    }

    public ClientPO getClientPO(){
        ClientPO clientPO=new ClientPO();
        clientPO.setID(this.ID);
        clientPO.setVisible(this.visible);
        clientPO.setCategory(this.category);
        clientPO.setLevel(this.level);
        clientPO.setName(this.name);
        clientPO.setPhone(this.phone);
        clientPO.setAddress(this.address);
        clientPO.setPost(this.post);
        clientPO.setEmail(this.email);
        clientPO.setReceivable(this.receivable);
        clientPO.setPayable(this.payable);
        clientPO.setReceivableLimit(this.receivableLimit);

        clientPO.setSalesmanID(this.salesman.getID());

        return clientPO;
    }

    public ClientVO(ClientPO clientPO)throws Exception{
        this.ID=clientPO.getID();
        this.visible=clientPO.isVisible();
        this.category=clientPO.getCategory();
        this.level=clientPO.getLevel();
        this.name=clientPO.getName();
        this.phone=clientPO.getPhone();
        this.address=clientPO.getAddress();
        this.post=clientPO.getPost();
        this.email=clientPO.getEmail();
        this.receivable=clientPO.getReceivable();
        this.payable=clientPO.getPayable();
        this.receivableLimit=clientPO.getReceivableLimit();

        UserTool userTool=new UserBl();
        this.salesman=userTool.find(clientPO.getSalesmanID());
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

    public UserVO getSalesman(){
        return salesman;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReceivable(double receivable) {
        this.receivable = receivable;
    }

    public void setPayable(double payable) {
        this.payable = payable;
    }

    public void setReceivableLimit(double receivableLimit) {
        this.receivableLimit = receivableLimit;
    }

    public void setSalesman(UserVO salesman) {
        this.salesman = salesman;
    }

}
