package main.java.businesslogicservice.initialblservice;

import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.initial.InitialQueryVO;
import main.java.vo.initial.InitialVO;

import java.util.ArrayList;

public interface InitialBlService {

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;//返回上一年的持久化商品对象

    ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception;//返回上一年的持久化客户对象

    ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception;//返回上一年的持久化账户对象

    String establishInitial(InitialVO initial) throws Exception;//保存期初信息，持久化更新涉及的对象的数据

    ArrayList<InitialVO> getInitial(InitialQueryVO query) throws Exception;//返回期初信息列表

}
