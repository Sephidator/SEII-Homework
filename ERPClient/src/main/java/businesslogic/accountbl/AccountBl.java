package main.java.businesslogic.accountbl;

import main.java.businesslogicservice.accountblservice.AccountBlService;
import main.java.data_stub.accountdataservicestub.AccountDataServiceStub;
import main.java.dataservice.accountdataservice.AccountDataService;
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
    public ArrayList<AccountVO> getAccountList(AccountQueryVO query) throws Exception{
        /*AccountQueryVO转为AccountQueryPO*/
        AccountQueryPO accountQueryPO = new AccountQueryPO("","");
        if(query != null)accountQueryPO = query.getAccountQueryPO();
        else accountQueryPO = null;

        /*调用AccountDatdaService.find服务*/
        //AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //ArrayList<AccountPO> accoutPOS = accountDataService.finds(accountQueryPO);

        /*调用dataservice的桩*/
        AccountDataService accountDataServiceStub = new AccountDataServiceStub();
        ArrayList<AccountPO> accountPOS = accountDataServiceStub.finds(accountQueryPO);

        /*ArrayList<AccountPO>转成ArrayList<AccountVO>*/
        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        for(AccountPO accountPO : accountPOS){
            accountVOArrayList.add(new AccountVO(accountPO));
        }

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
    public void editAccount(AccountVO account) throws Exception{
        /*AccountVO转成AccountPO*/
        AccountPO accountPO = account.getAccountPO();

        /*调用AccountDataService.update*/
       // AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //accountDataService.update(accountPO);

        /*调用dataservice的桩*/
        AccountDataService accountDataService = new AccountDataServiceStub();
        accountDataService.update(accountPO);
    }

    /**
     * @version: 1
     * @date: 2017.12.08 0:06
     * @para:  [accountID]待查询的ID，find用于系统查询
     * @function: 给其它类使用的findByID方法
     */
    @Override
    public AccountVO find(String accountID) throws Exception {
        /*调用AccountDataService.find*/
//        AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
//        AccountPO accountPO = accountDataService.find(accountID);
//        AccountVO accountVO = new AccountVO(accountPO);

        /*调用dataservice的桩*/
        AccountDataService accountDataService = new AccountDataServiceStub();
        AccountPO accountPO = accountDataService.find(accountID);
        AccountVO accountVO = new AccountVO(accountPO);

        return accountVO;
    }


    /**
     * @version: 1
     * @date: 2017.11.25 18:29
     * @para:  [account]新账户对象
     * @function: 将AccountVO转成AccountPO，并调用AccountDataService.insert服务
     */
    @Override
    public String addAccount(AccountVO account)   throws Exception{
        /*AccountVO转成AccountPO*/
        AccountPO accountPO = account.getAccountPO();

        /*调用AccountDataService.insert*/
       // AccountDataService accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:");
        //String id = accountDataService.insert(accountPO);

        /*调用AccountDataService桩*/
        AccountDataService accountDataService = new AccountDataServiceStub();
        String id = accountDataService.insert(accountPO);

        return id;
    }

    /**
     * @version: 1
     * @date: 2017.11.25 18:30
     * @para:  [account]包含已存在的账户的信息的对象
     * @function: 调用AccountDataService.delete服务
     */
    @Override
    public void deleteAccount(String accountID)  throws Exception{

        /*调用AccountDataService.delete*/
        //AccountDataService accountDataService = (AccountDataService)Naming.lookup("rmi://localhost:");
        //accountDataService.delete(accountID);

        /*调用AccountDataService桩*/
        AccountDataService accountDataService = new AccountDataServiceStub();
        accountDataService.delete(accountID);
    }
}
