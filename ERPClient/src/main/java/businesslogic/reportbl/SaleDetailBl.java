package main.java.businesslogic.reportbl;

import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.reportblservice.SaleDetailBlService;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.report.SaleDetailQueryVO;
import main.java.vo.report.SaleRecordVO;

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
     * 实现说明：通过筛选条件拿到合适的SaleTradeBill，再拿到属性saleList，以及时间，打包成ArrayList传回
     */
    public ArrayList<SaleRecordVO> getSaleRecordList(SaleDetailQueryVO query)throws Exception {
        /*制作SaleTradeBillQueryVO*/
        //TODO 差一个client属性
        SaleTradeBillQueryVO saleTradeBillQueryVO = new SaleTradeBillQueryVO(query.start, query.end, query.goodsName,query.client,query.salesman);

        SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
        //TODO 需要提供一个查找函数
        ArrayList<SaleTradeBillVO> saleTradeBillVOS = saleTradeBillTool.findsByReport(saleTradeBillQueryVO);


        ArrayList<SaleRecordVO> saleRecordVOS = new ArrayList<>();
        return saleRecordVOS;
    }
}
