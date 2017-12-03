package main.java.businesslogic.accountbl;

import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.po.account.AccountPO;
import main.java.po.account.AccountQueryPO;
import main.java.vo.account.AccountQueryVO;
import main.java.vo.account.AccountVO;

import java.util.ArrayList;

public class AccountBl implements AccountBlService,AccountTool{

    /**
     * @version: 1
     * @date: 2017.11.25 18:15
     * @para: [query] 包含待查询信息的账户查询对象
     * @function: 将AccountQueryVO转为AccountQueryPO，调用AccountDatdaService.find服务
     *             得到ArrayList<AccountPO>以后转成ArrayList<AccountVO>，返回ArrayList<AccountVO>
     */
    @Override
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) {
        /*AccountQueryVO转为AccountQueryPO*/
        AccountQueryPO accountQueryPO = query.getAccountQueryPO();

        /*调用AccountDatdaService.find服务*/
        //AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //ArrayList<AccountPO> accoutPOArralist = accountDataService.find(accountQueryPO);

        /*ArrayList<AccountPO>转成ArrayList<AccountVO>*/
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        //for(AccountPO accoutPO : accoutPOArralist){
        //    accountVOArrayList.add(new AccountVO(accoutPO));
        //}

        /*返回ArrayList<AccountVO>*/
        return accountVOArrayList;
    }

    /**
     * @version: 1
     * @date: 2017.11.25 18:23
     * @para: [account] 修改完成的账户对象，用于覆盖数据库中该账户数据
     * @function: 将AccountVO转成AccountPO，并调用AccountDataService.update服务
     */
    @Override
    public void editAccount(AccountVO account){
        /*AccountVO转成AccountPO*/
        AccountPO accountPO = account.getAccountPO();

        /*调用AccountDataService.update*/
       // AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //accountDataService.update(accountPO);
    }


    /**
     * @version: 1
     * @date: 2017.11.25 18:29
     * @para:  [account]新账户对象
     * @function: 将AccountVO转成AccountPO，并调用AccountDataService.insert服务
     */
    @Override
    public void addAccount(AccountVO account)  {
        /*AccountVO转成AccountPO*/
        AccountPO accountPO = account.getAccountPO();

        /*调用AccountDataService.insert*/
       // AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //accountDataService.insert(accountPO);
    }

    /**
     * @version: 1
     * @date: 2017.11.25 18:30
     * @para:  [account]包含已存在的账户的信息的对象
     * @function: 调用AccountDataService.delete服务
     */
    @Override
    public void deleteAccount(String accountID) {

        /*调用AccountDataService.delete*/
        //AccountDataService accountDataService = (AccountDataService)Naming.lookup("rmi://localhost:");
        //accountDataService.delete(accountID);
    }
}
