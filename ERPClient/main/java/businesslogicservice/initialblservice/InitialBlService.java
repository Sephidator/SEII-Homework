package businesslogicservice.initialblservice;

import businesslogic.blutility.ResultMessage;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;
import vo.initial.InitialQueryVO;
import vo.initial.InitialVO;

import java.util.ArrayList;

public interface InitialBlService {

    public int getYear();//返回期初年份,用于新建

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);//返回上一年的持久化商品对象

    public ArrayList<ClientVO> getClientList(ClientQueryVO query);//返回上一年的持久化客户对象

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query);//返回上一年的持久化账户对象

    public ResultMessage establishInitial(InitialVO initial);//保存期初信息，持久化更新涉及的对象的数据

    public ArrayList<InitialVO> getInitial(InitialQueryVO query);//返回期初信息列表

}
