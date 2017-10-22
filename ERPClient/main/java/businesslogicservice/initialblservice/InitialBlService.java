package businesslogicservice.initialblservice;

import java.util.ArrayList;
import java.util.Date;
import po.GoodsPO;
import po.ClientPO;
import po.AccountPO;
import vo.AccountVO;
import vo.ClientVO;
import vo.GoodsVO;
import vo.InitialVO;

/**
 * �ڳ� ���� �Լ���Ŀ�Ĳ鿴
 * Created by wangn on 2017.10.19.
 */
public interface InitialBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*������Ʒ�൥����Ϣ����������Ʒ���࣬ĳһ��Ʒ�����ƣ�����ͺţ�����/�ۼۣ�Ĭ��Ϊȥ��ƽ������������ۺ�����ۼ�����*/
    public ArrayList<GoodsPO> getLastYearGoods(Date current);

    /*���ؿͻ��൥����Ϣ���������ͻ����࣬ĳһ���ͻ������ƣ���ϵ��ʽ�ȣ�Ӧ��Ӧ��(֮ǰ����)*/
    public ArrayList<ClientPO> getLastYearClient(Date current);

    /*�����˻��൥����Ϣ�����������ƣ����*/
    public ArrayList<AccountPO> getLastYearAccount(Date current);

    /*�����ڳ���Ϣ���ɹ�����true*/
    public ResultMessage establishInitial(ArrayList<GoodsVO> goods, ArrayList<ClientVO> client, ArrayList<AccountVO> account);

    /*�ṩ�鿴ĳһ����Ŀ�Ĺ���*/
    public ArrayList<InitialVO> getInitial(int year);

    /*���ư�ť�ĳ���*/
    public ResultMessage showButton(int year);
}
