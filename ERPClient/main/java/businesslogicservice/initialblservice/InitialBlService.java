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
 * 期初 建账 以及账目的查看
 * Created by wangn on 2017.10.19.
 */
public interface InitialBlService {

    /******************************/
    public enum ResultMessage{True, False, Success};
    /*****************************/

    /*返回商品类单据信息，包括：商品分类，某一商品的名称，类别，型号，进价/售价（默认为去年平均），最近进价和最近售价留空*/
    public ArrayList<GoodsPO> getLastYearGoods(Date current);

    /*返回客户类单据信息，包括：客户分类，某一个客户的名称，联系方式等，应收应付(之前遗留)*/
    public ArrayList<ClientPO> getLastYearClient(Date current);

    /*返回账户类单据信息，包括：名称，余额*/
    public ArrayList<AccountPO> getLastYearAccount(Date current);

    /*建立期初信息，成功返回true*/
    public ResultMessage establishInitial(ArrayList<GoodsVO> goods, ArrayList<ClientVO> client, ArrayList<AccountVO> account);

    /*提供查看某一年账目的功能*/
    public ArrayList<InitialVO> getInitial(int year);

    /*控制按钮的呈现*/
    public ResultMessage showButton(int year);
}
