package businesslogicservice.goodssortblservice;

import businesslogic.blutility.ResultMessage;

public interface GoodsSortBlService {



    public ResultMessage insertGoodsSort(String ID,String sortName,String fatherID,String note);

    public ResultMessage deleteGoodsSort(String ID);

    public ResultMessage updateGoodsSort(String ID,String sortName,String fatherID,String note);

    public ResultMessage detailGoodsSort(String ID);

    public String getID(String fatherID);
}
