package main.java.businesslogic.reportbl;

import main.java.businesslogicservice.reportblservice.BusinessConditionBlService;
import main.java.vo.report.BusinessConditionQueryVO;

import java.util.ArrayList;

public class BusinessConditionBl implements BusinessConditionBlService {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:07
     * @para: [query] 包括起始时间
     * @function:
     * 大作业说明：查看经营情况表（统计显示一段时间内的经营收支状况和利润。
     * 经营收入显示为折让后，并显示出折让了多少。显示信息：
     * 1. 收入类：销售收入、商品类收入（商品报溢收入 成本调价收入 进货退货差价 代金券与实际收款差额收入）。
     * 收入类显示折让后总收入，并显示折让了多少。
     * 2. 支出类：销售成本、商品类支出（商品报损 商品赠出）。支出类显示总支出。
     * 3. 利润：折让后总收入-总支出。）
     */
    public ArrayList<Double> getCondition(BusinessConditionQueryVO query)throws Exception {
        return null;
    }
}
