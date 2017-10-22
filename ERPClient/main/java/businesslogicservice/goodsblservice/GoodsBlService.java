package businesslogicservice.goodsblservice;

import vo.GoodsVO;
import businesslogic.blutility.ResultMessage;
import java.util.ArrayList;

public interface GoodsBlService {



    public ResultMessage insertGoods(String ID, String name,String sortID, String model, int number,
                                     double cost, double retail, double latestCost,
                                     double latestRetail, String comment);

    public String getID();

    public ResultMessage deleteGoods(String ID);

    public ResultMessage updateGoods(String ID, String name,String sortID, String model, int number,
                                     double cost, double retail, double latestCost,
                                     double latestRetail, String comment);

    public ResultMessage detailGoods(String ID);

    public ResultMessage setAlarmNum(String ID,int alarmNum);


}
