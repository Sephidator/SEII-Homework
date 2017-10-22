package businesslogicservice.goodssortblservice;

import vo.GoodsSortVO;
import businesslogic.blutility.ResultMessage;
import java.util.ArrayList;

public interface GoodsSortBlService {



    public ResultMessage insertGoodsSort(String ID,String sortName,String fatherID,String note);

    public ResultMessage deleteGoodsSort(String ID);

    public ResultMessage updateGoodsSort(String ID,String sortName,String fatherID,String note);

    public ResultMessage detailGoodsSort(String ID);

    public String getID(String fatherID);
}
