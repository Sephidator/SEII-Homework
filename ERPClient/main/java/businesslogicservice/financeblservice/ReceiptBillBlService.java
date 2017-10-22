package businesslogicservice.financeblservice;

import java.util.ArrayList;
import vo.ReceiptBillVO;
import po.UserPO;//TODO


/**
 * 收款单的查看、新建以及修改，红冲
 * Created by wangn on 2017.10.19.
 */
public interface ReceiptBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*返回收款单的编号*/
    public String getReceiptBillID(ReceiptBillVO vo);

    /*新建收款单，成功改变单据状态，返回true*/
    public ResultMessage newReceiptBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*查看收款单，返回信息，list里面依次包含所有信息*/
    public ReceiptBillVO showReceiptBill(String DocID);

    /*修改收款单，成功改变单据状态，返回true*/
    public ResultMessage mockReceiptBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*改变单据状态，成功返回true*/
    public ResultMessage mockReceiptBillStatus(String DocID);

    /*自动计算总额*/
    public double getReceiptBillTotal(ArrayList<Double> list);

    /*获取操作员*/
    public UserPO getOperator();

    /*红冲，成功返回true*/
    public ResultMessage reverseReceiptBill(ReceiptBillVO vo);

    /*控制按钮出现，比如红冲和新建按钮*/
    public ResultMessage showButton(String DocID);

    /*上传单据,成功返回true*/
    public ResultMessage submitDoc(ReceiptBillVO vo);

    /*保存单据,成功返回true*/
    public ResultMessage saveDoc(ReceiptBillVO vo);
}
