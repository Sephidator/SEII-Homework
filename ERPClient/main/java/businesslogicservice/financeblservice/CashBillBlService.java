package businesslogicservice.financeblservice;

import java.util.ArrayList;

import businesslogic.blutility.ResultMessage;
import vo.CashBillVO;
import po.UserPO; //TODO

/**
 * 现金费用单的查看、新建以及修改，红冲
 * Created by wangn on 2017.10.19.
 */
public interface CashBillBlService {

    /*返回现金费用单的编号，格式为XJFYD-yyyyMMdd-xxxxx*/
    public String getCashBillID(CashBillVO vo);

    /*新建现金费用单，成功改变单据状态，返回true*/
    public ResultMessage newCashBill(String DocID, ArrayList<String> bankAccount, ArrayList<String> itemList,
                                     String total, String operator);
    /*查看现金费用单，返回信息，list里面依次包含所有信息*/
    public CashBillVO showCashBill(String DocID);

    /*修改现金费用单，成功改变单据状态，返回true*/
    public ResultMessage mockCashBill(String DocID, ArrayList<String> bankAccount,ArrayList<String> itemList,
                                      String total, String operator);

    /*改变单据状态，成功返回true*/
    public ResultMessage mockCashBillStatus(String DocID);

    /*自动计算总额*/
    public double getCashBillTotal(ArrayList<Double> list);

    /*获取操作员*/
    public UserPO getOperator();

    /*红冲，成功返回true*/
    public ResultMessage reverseCashBill(CashBillVO vo);

    /*控制按钮出现，比如红冲和新建按钮*/
    public ResultMessage showButton(String DocID);

    /*上传单据,成功返回true*/
    public ResultMessage submitDoc(CashBillVO vo);

    /*保存单据,成功返回true*/
    public ResultMessage saveDoc(CashBillVO vo);
}
