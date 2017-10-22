package businesslogicservice.financeblservice;

import java.util.ArrayList;
import vo.ReceiptBillVO;
import po.UserPO;//TODO


/**
 * �տ�Ĳ鿴���½��Լ��޸ģ����
 * Created by wangn on 2017.10.19.
 */
public interface ReceiptBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*�����տ�ı��*/
    public String getReceiptBillID(ReceiptBillVO vo);

    /*�½��տ���ɹ��ı䵥��״̬������true*/
    public ResultMessage newReceiptBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*�鿴�տ��������Ϣ��list�������ΰ���������Ϣ*/
    public ReceiptBillVO showReceiptBill(String DocID);

    /*�޸��տ���ɹ��ı䵥��״̬������true*/
    public ResultMessage mockReceiptBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*�ı䵥��״̬���ɹ�����true*/
    public ResultMessage mockReceiptBillStatus(String DocID);

    /*�Զ������ܶ�*/
    public double getReceiptBillTotal(ArrayList<Double> list);

    /*��ȡ����Ա*/
    public UserPO getOperator();

    /*��壬�ɹ�����true*/
    public ResultMessage reverseReceiptBill(ReceiptBillVO vo);

    /*���ư�ť���֣���������½���ť*/
    public ResultMessage showButton(String DocID);

    /*�ϴ�����,�ɹ�����true*/
    public ResultMessage submitDoc(ReceiptBillVO vo);

    /*���浥��,�ɹ�����true*/
    public ResultMessage saveDoc(ReceiptBillVO vo);
}
