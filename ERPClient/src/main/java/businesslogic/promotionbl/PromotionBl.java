package main.java.businesslogic.promotionbl;

import main.java.businesslogic.goodsbl.GoodsBl;
import main.java.businesslogic.goodsbl.GoodsTool;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.datafactory.promotiondatafactory.PromotionDataFactory;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.*;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.*;

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
        PromotionQueryPO promotionQueryPO = null;
        if(query != null)
            promotionQueryPO = query.getPromotionQueryPO();

        /*dataService*/
        PromotionDataService promotionDataService = PromotionDataFactory.getService();
        ArrayList<PromotionPO> promotionPOS = promotionDataService.finds(promotionQueryPO);

        ArrayList<PromotionVO> promotionVOS  = new ArrayList<>();//转换至ArrayList<PromotionVO
        for(PromotionPO promotionPO : promotionPOS){
            promotionVOS.add(getPromotionVO(promotionPO));
        }

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
        if(promotionID.equals("")){
            PromotionPO promotionPO=new PromotionPO();
            return new PromotionVO(promotionPO);
        }
        else{
            /*dataService*/
            PromotionDataService promotionDataService = PromotionDataFactory.getService();
            PromotionPO promotionPO = promotionDataService.find(promotionID);
            return getPromotionVO(promotionPO);
        }
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
        PromotionDataService promotionDataService = PromotionDataFactory.getService();
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
        PromotionDataService promotionDataService = PromotionDataFactory.getService();
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
        PromotionDataService promotionDataService = PromotionDataFactory.getService();
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

    /**
     * @version: 1
     * @date: 2017.12.18 23:37
     * @para: [query]
     * @function: 为了解决王宁没有根据促销策略类型来生成对应的VO所产生的问题
     */
    private PromotionVO getPromotionVO(PromotionPO promotionPO) throws Exception{
        PromotionVO promotionVO=new PromotionVO();
        if(promotionPO.getType().equals("客户促销策略")){
            PromotionClientPO promotionClientPO=(PromotionClientPO)promotionPO;
            promotionVO=new PromotionClientVO(promotionClientPO);
        }
        else if(promotionPO.getType().equals("商品促销策略")){
            PromotionGoodsPO promotionGoodsPO=(PromotionGoodsPO)promotionPO;
            promotionVO=new PromotionGoodsVO(promotionGoodsPO);
        }
        else if(promotionPO.getType().equals("总价促销策略")){
            PromotionTotalPO promotionTotalPO=(PromotionTotalPO)promotionPO;
            promotionVO=new PromotionTotalVO(promotionTotalPO);
        }
        return promotionVO;
    }
}
