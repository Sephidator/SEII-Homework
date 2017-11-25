package main.java.vo.bill;

import java.util.Date;

public class BillQueryVO {
    public String ID; //单据编号
    public String state; //单据状态
    public Date start; //起始时间
    public Date end; //结束时间
    public String type; //单据类型
    public String operator;//操作员
    public String salesman;//业务员
    public String client;// 客户
    public String goodsName;// 商品
}
