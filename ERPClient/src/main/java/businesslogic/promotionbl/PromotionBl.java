package main.java.businesslogic.promotionbl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.data_stub.promotiondataservicestub.PromotionDataServiceStub;
import main.java.datafactory.promotiondatafactory.PromotionDataFactory;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.goods.GoodsQueryPO;
import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionQueryPO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionBl implements PromotionBlService, PromotionTool {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [query] 
     * @function: 
     */
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query)throws Exception {
        /*将PromotionQueryVO转换到PromotionQueryPO*/
        PromotionQueryPO promotionQueryPO = new PromotionQueryPO("","");
        if(query == null)promotionQueryPO = null;
        else promotionQueryPO = query.getPromotionQueryPO();

        /*dataService*/
        PromotionDataFactory promotionDataFactory = new PromotionDataFactory();
        PromotionDataService promotionDataService = promotionDataFactory.getService();

//        /*dataServiceStub*/
//        PromotionDataService promotionDataService = new PromotionDataServiceStub();
        ArrayList<PromotionPO> promotionPOS = promotionDataService.finds(promotionQueryPO);

        ArrayList<PromotionVO> promotionVOS  = new ArrayList<>();//转换至ArrayList<PromotionVO
        for(PromotionPO promotionPO : promotionPOS)
            promotionVOS.add(new PromotionVO(promotionPO));

        return promotionVOS;
    }

    /**
     * @version: 1
     * @date: 2017.12.08 14:51
     * @para: [promotionID]
     * @function:
     */
    @Override
    public PromotionVO find(String promotionID) throws Exception{
        /*dataService*/
        PromotionDataFactory promotionDataFactory = new PromotionDataFactory();
        PromotionDataService promotionDataService = promotionDataFactory.getService();
//        /*dataServiceStub*/
//        PromotionDataService promotionDataService = new PromotionDataServiceStub();
        PromotionPO promotionPO = promotionDataService.find(promotionID);
        return new PromotionVO(promotionPO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public String addPromotion(PromotionVO vo) throws Exception{
        /*将PromotionVO转到PromotionPO*/
        PromotionPO promotionPO = vo.getPromotionPO();
        /*dataService*/
        PromotionDataFactory promotionDataFactory = new PromotionDataFactory();
        PromotionDataService promotionDataService = promotionDataFactory.getService();
//        /*dataServiceStub*/
//        PromotionDataService promotionDataService = new PromotionDataServiceStub();
        String id = promotionDataService.insert(promotionPO);
        return id;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void editPromotion(PromotionVO vo)throws Exception {
        /*将PromotionVO转到PromotionPO*/
        PromotionPO promotionPO = vo.getPromotionPO();
        /*dataService*/
        PromotionDataFactory promotionDataFactory = new PromotionDataFactory();
        PromotionDataService promotionDataService = promotionDataFactory.getService();
//        /*dataServiceStub*/
//        PromotionDataService promotionDataService = new PromotionDataServiceStub();
        promotionDataService.update(promotionPO);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void deletePromotion(String promotionID) throws Exception{
        /*dataService*/
        PromotionDataFactory promotionDataFactory = new PromotionDataFactory();
        PromotionDataService promotionDataService = promotionDataFactory.getService();
//        /*dataServiceStub*/
//        PromotionDataService promotionDataService = new PromotionDataServiceStub();
        promotionDataService.delete(promotionID);
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.12.18 23:37
     * @para: [query]
     * @function: 目的是总经理制定促销策略的时候可以选择用于赠送的商品列表
     */
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {

        GoodsTool goodsTool = new GoodsBl();
        ArrayList<GoodsVO> goodsVOS = goodsTool.getGoodsList(query);
        return goodsVOS;
    }


}
