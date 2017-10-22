package businesslogicservice.financeblservice;

import java.util.ArrayList;
import vo.PaymentBillVO;
import po.UserPO;//TODO

/**
 * 付款单的查看、新建以及修改，红冲
 * Created by wangn on 2017.10.19.
 */
public interface PaymentBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*返回付款单的编号，格式为FKD-yyyyMMdd-xxxxx*/
    public String getPaymentBillID(PaymentBillVO vo);

    /*新建付款单，成功改变单据状态，返回true*/
    public ResultMessage newPaymentBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*查看付款单，返回信息，list里面依次包含所有信息*/
    public PaymentBillVO showPaymentBill(String DocID);

    /*修改付款单，成功改变单据状态，返回true*/
    public ResultMessage mockPaymentBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*改变单据状态，成功返回true*/
    public ResultMessage mockPaymentBillStatus(String DocID);

    /*自动计算总额*/
    public double getPaymentBillTotal(ArrayList<Double> list);

    /*获取操作员*/
    public UserPO getOperator();

    /*红冲，成功返回true*/
    public ResultMessage reversePaymentBill(PaymentBillVO vo);

    /*控制按钮出现，比如红冲和新建按钮*/
    public ResultMessage showButton(String DocID);

    /*上传单据,成功返回true*/
    public ResultMessage submitDoc(PaymentBillVO vo);

    /*保存单据,成功返回true*/
    public ResultMessage saveDoc(PaymentBillVO vo);
}
