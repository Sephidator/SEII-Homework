package businesslogicservice.financeblservice;

import java.util.ArrayList;
import vo.CashBillVO;
import po.UserPO; //TODO

/**
 * �ֽ���õ��Ĳ鿴���½��Լ��޸ģ����
 * Created by wangn on 2017.10.19.
 */
public interface CashBillBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*�����ֽ���õ��ı�ţ���ʽΪXJFYD-yyyyMMdd-xxxxx*/
    public String getCashBillID(CashBillVO vo);

    /*�½��ֽ���õ����ɹ��ı䵥��״̬������true*/
    public ResultMessage newCashBill(String DocID, ArrayList<String> bankAccount,ArrayList<String> itemList,
                                     String total, String operator);
    /*�鿴�ֽ���õ���������Ϣ��list�������ΰ���������Ϣ*/
    public CashBillVO showCashBill(String DocID);

    /*�޸��ֽ���õ����ɹ��ı䵥��״̬������true*/
    public ResultMessage mockCashBill(String DocID, ArrayList<String> bankAccount,ArrayList<String> itemList,
                                      String total, String operator);

    /*�ı䵥��״̬���ɹ�����true*/
    public ResultMessage mockCashBillStatus(String DocID);

    /*�Զ������ܶ�*/
    public double getCashBillTotal(ArrayList<Double> list);

    /*��ȡ����Ա*/
    public UserPO getOperator();

    /*��壬�ɹ�����true*/
    public ResultMessage reverseCashBill(CashBillVO vo);

    /*���ư�ť���֣���������½���ť*/
    public ResultMessage showButton(String DocID);

    /*�ϴ�����,�ɹ�����true*/
    public ResultMessage submitDoc(CashBillVO vo);

    /*���浥��,�ɹ�����true*/
    public ResultMessage saveDoc(CashBillVO vo);
}

