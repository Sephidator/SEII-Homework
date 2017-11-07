package businesslogicservice.financeblservice;

import businesslogic.blutility.ResultMessage;
import vo.account.AccountQueryVO;
import vo.account.AccountVO;
import vo.bill.financebill.CashBillVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface CashBillBlService {
    public String getID();//返回新的现金费用单单据编号

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);//返回商品列表

    public ArrayList<AccountVO> getAccountList(AccountQueryVO query);//返回账户列表

    public ResultMessage submit(CashBillVO vo);//更新并提交现金费用单，持久化更新涉及的对象的数据

    public ResultMessage saveDraft(CashBillVO vo);//保存单据草稿

}