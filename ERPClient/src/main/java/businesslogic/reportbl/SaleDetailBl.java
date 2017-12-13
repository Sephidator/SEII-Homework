package main.java.businesslogic.reportbl;

import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;
import java.util.Date;

import java.util.ArrayList;

public class SaleDetailBl implements SaleDetailBlService {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:08
     * @para: [query] 
     * @function:
     * @return: [SaleRecordVO]包括 时间、goodsItemVO
     * 大作业说明：查看销售明细表（统计一段时间内商品的销售情况（应该就是查询销售出货单据记录），
     * 筛选条件有：时间区间，商品名，客户，业务员，仓库
     * 显示符合上述条件的商品销售记录
     *
     * 以列表形式显示，列表中包含如下信息：
     * 时间（精确到天），商品名，型号，数量，单价，总额。
     *
     * 实现说明：通过筛选条件拿到合适的SaleTradeBill，打包成ArrayList传回
     */
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query)throws Exception {
        /*制作SaleTradeBillQueryVO*/
        Date start = new Date();Date end = new Date();
        String goodsName = ""; String client = ""; String salesman = "";
        SaleTradeBillQueryVO saleTradeBillQueryVO;
        if(query == null)saleTradeBillQueryVO = null;
        else saleTradeBillQueryVO = new SaleTradeBillQueryVO(query.start, query.end, query.goodsName,query.client,query.salesman);

        SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
        //拿到销售单的单据
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = saleTradeBillTool.findsByReport(saleTradeBillQueryVO);

        //开始组装saleRecordVO
        ArrayList<SaleRecordVO> saleRecordVOS = new ArrayList<>();
        ArrayList<GoodsItemVO> goodsItemVOS = new ArrayList<>();
        for(SaleTradeBillVO saleTradeBillVO : saleTradeBillVOS){
            goodsItemVOS = saleTradeBillVO.getSaleList();
            for(GoodsItemVO goodsItemVO : goodsItemVOS)
                saleRecordVOS.add(new SaleRecordVO(saleTradeBillVO.getTime(), goodsItemVO));
        }
        return saleRecordVOS;
    }
}
