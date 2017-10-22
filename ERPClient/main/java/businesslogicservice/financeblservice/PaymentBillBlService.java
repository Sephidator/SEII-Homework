package businesslogicservice.financeblservice;

import java.util.ArrayList;
import vo.PaymentBillVO;
import po.UserPO;//TODO

/**
 * ����Ĳ鿴���½��Լ��޸ģ����
 * Created by wangn on 2017.10.19.
 */
public interface PaymentBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*���ظ���ı�ţ���ʽΪFKD-yyyyMMdd-xxxxx*/
    public String getPaymentBillID(PaymentBillVO vo);

    /*�½�������ɹ��ı䵥��״̬������true*/
    public ResultMessage newPaymentBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*�鿴�����������Ϣ��list�������ΰ���������Ϣ*/
    public PaymentBillVO showPaymentBill(String DocID);

    /*�޸ĸ�����ɹ��ı䵥��״̬������true*/
    public ResultMessage mockPaymentBill(String DocID, ArrayList<String> client,ArrayList<String> transList, String total, String operator);

    /*�ı䵥��״̬���ɹ�����true*/
    public ResultMessage mockPaymentBillStatus(String DocID);

    /*�Զ������ܶ�*/
    public double getPaymentBillTotal(ArrayList<Double> list);

    /*��ȡ����Ա*/
    public UserPO getOperator();

    /*��壬�ɹ�����true*/
    public ResultMessage reversePaymentBill(PaymentBillVO vo);

    /*���ư�ť���֣���������½���ť*/
    public ResultMessage showButton(String DocID);

    /*�ϴ�����,�ɹ�����true*/
    public ResultMessage submitDoc(PaymentBillVO vo);

    /*���浥��,�ɹ�����true*/
    public ResultMessage saveDoc(PaymentBillVO vo);
}

