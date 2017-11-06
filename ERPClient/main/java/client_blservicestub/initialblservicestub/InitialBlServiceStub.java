package client_blservicestub.initialblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.initialblservice.InitialBlService;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsSortVO;
import vo.goods.GoodsVO;
import vo.initial.InitialQueryVO;
import vo.initial.InitialVO;
import vo.user.UserVO;

import java.util.ArrayList;

public class InitialBlServiceStub implements InitialBlService{
    @Override
    public int getYear() {
        return 2017;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        GoodsVO goodsVO = new GoodsVO("Goods-20171106-00001","灯",null,"大",10,10,11,9,11,100,"",true);
        ArrayList<GoodsVO> goodsVOArrayList = new ArrayList<GoodsVO>();
        goodsVOArrayList.add(goodsVO);
        return goodsVOArrayList;
    }

    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {
        ArrayList<ClientVO> clientVOArrayList = new ArrayList<ClientVO>();
        UserVO userVO = new UserVO("财务人员",20,true,"王宁","Finance-20171106-00001","1234",true);
        ClientVO clientVO = new ClientVO("Client-20171106-00001","进货商",5,"张三","15900000000","Nanjing University",
                "210023","wangnig@vip.com",1000,0,1000,userVO);
        clientVOArrayList.add(clientVO);
        return clientVOArrayList;
    }

    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        AccountVO accountVO = new AccountVO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        accountVOArrayList.add(accountVO);
        return accountVOArrayList;
    }

    @Override
    public ResultMessage establishInitial(InitialVO initial) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<InitialVO> getInitial(InitialQueryVO query) {
        //GoodsVO
        GoodsVO goodsVO = new GoodsVO("Goods-20171106-00001","灯",null,"大",10,10,11,9,11,100,"",true);
        ArrayList<GoodsVO> goodsVOArrayList = new ArrayList<GoodsVO>();
        goodsVOArrayList.add(goodsVO);

        //ClientVO
        ArrayList<ClientVO> clientVOArrayList = new ArrayList<ClientVO>();
        UserVO userVO = new UserVO("财务人员",20,true,"王宁","Finance-20171106-00001","1234",true);
        ClientVO clientVO = new ClientVO("Client-20171106-00001","进货商",5,"张三","15900000000","Nanjing University",
                "210023","wangnig@vip.com",1000,0,1000,userVO);
        clientVOArrayList.add(clientVO);

        //AccountVO
        AccountVO accountVO = new AccountVO("Account-20171106-00001","6212262402011111111","公司甲帐",10000);
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        accountVOArrayList.add(accountVO);

        //GoodsSortVO
        //String ID;商品分类编号String name;商品分类名称GoodsSortVO father;商品分类父类ArrayList<GoodsSortVO> children该商品分类的子分类
        //ArrayList<GoodsVO> goodsName;商品分类下的商品名称String comment;商品分类备注boolean visible = true;商品分类是否存在
        GoodsSortVO goodsSortVO = new GoodsSortVO("GoodsSort-20171106-00001","灯",null,new ArrayList<GoodsSortVO>(),goodsVOArrayList,"",true);
        ArrayList<GoodsSortVO> goodsSortVOArrayList = new ArrayList<GoodsSortVO>();
        goodsSortVOArrayList.add(goodsSortVO);

        //return
        InitialVO initialVO = new InitialVO(2017,goodsVOArrayList,goodsSortVOArrayList,clientVOArrayList,accountVOArrayList);
        ArrayList<InitialVO> initialVOArrayList = new ArrayList<InitialVO>();
        initialVOArrayList.add(initialVO);
        return initialVOArrayList;
    }
}
