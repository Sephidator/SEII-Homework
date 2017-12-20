package main.java.businesslogic.inventorybl;

import main.java.businesslogic.purchasebl.PurchaseRefundBillBl;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.businesslogic.purchasebl.PurchaseTradeBillBl;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.businesslogic.salebl.SaleRefundBillBl;
import main.java.businesslogic.salebl.SaleRefundBillTool;
import main.java.businesslogic.salebl.SaleTradBillBl;
import main.java.businesslogic.salebl.SaleTradeBillTool;
import main.java.businesslogicservice.inventoryblservice.InventoryCheckBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;
import main.java.vo.bill.salebill.SaleRefundBillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.goods.GoodsItemVO;
import main.java.vo.goods.InventoryCheckItemVO;

import java.util.ArrayList;
import java.util.Date;

public class InventoryCheckBl implements InventoryCheckBlService {
    /**
     * @version: 1
     * @date:
     * @param: [start][end] 分别包含待查询信息的时间区间
     * @function: 返回ArrayList<InventoryCheckItemVO>的库存查看列表
     */
    @Override
    public ArrayList<InventoryCheckItemVO> getInventoryCheck(Date start, Date end) throws Exception {
        ArrayList<InventoryCheckItemVO> inventoryCheckItemVOS = new ArrayList<>();

        ArrayList<GoodsItemVO> purchaseRefundBillGoodsItemVOS = new ArrayList<>();
        ArrayList<GoodsItemVO> purchaseTradeBillGoodsItemVOS = new ArrayList<>();
        ArrayList<GoodsItemVO> saleRefundBillGoodsItemVOS = new ArrayList<>();
        ArrayList<GoodsItemVO> saleTradeBillGoodsItemVOS = new ArrayList<>();

        ArrayList<PurchaseRefundBillVO> purchaseRefundBillVOS;
        ArrayList<PurchaseTradeBillVO> purchaseTradeBillVOS;
        ArrayList<SaleRefundBillVO> saleRefundBillVOS;
        ArrayList<SaleTradeBillVO> saleTradeBillVOS;

        BillQueryVO billQueryVO = new BillQueryVO(null, start, end, null, null, null);

        //寻找相应单据列表
        PurchaseRefundBillTool purchaseRefundBillTool = new PurchaseRefundBillBl();
        purchaseRefundBillVOS = purchaseRefundBillTool.getPurchaseRefundBillList(billQueryVO);

        PurchaseTradeBillTool purchaseTradeBillTool = new PurchaseTradeBillBl();
        purchaseTradeBillVOS = purchaseTradeBillTool.getPurchaseTradeBillList(billQueryVO);

        SaleRefundBillTool saleRefundBillTool = new SaleRefundBillBl();
        saleRefundBillVOS = saleRefundBillTool.getSaleRefundBillList(billQueryVO);

        SaleTradeBillTool saleTradeBillTool = new SaleTradBillBl();
        saleTradeBillVOS = saleTradeBillTool.getSaleTradeBillList(billQueryVO);

        //寻找相应单据的ArrayList<GoodsItemVO>
        for (PurchaseRefundBillVO purchaseRefundBillVO : purchaseRefundBillVOS) {
            for (GoodsItemVO goodsItemVO : purchaseRefundBillVO.getPurchaseList()) {
                purchaseRefundBillGoodsItemVOS.add(goodsItemVO);
            }
        }
        for (PurchaseTradeBillVO purchaseTradeBillVO : purchaseTradeBillVOS) {
            for (GoodsItemVO goodsItemVO : purchaseTradeBillVO.getPurchaseList()) {
                purchaseTradeBillGoodsItemVOS.add(goodsItemVO);
            }
        }
        for (SaleRefundBillVO saleRefundBillVO : saleRefundBillVOS) {
            for (GoodsItemVO goodsItemVO : saleRefundBillVO.getSaleList()) {
                saleRefundBillGoodsItemVOS.add(goodsItemVO);
            }
        }
        for (SaleTradeBillVO saleTradeBillVO : saleTradeBillVOS) {
            for (GoodsItemVO goodsItemVO : saleTradeBillVO.getSaleList()) {
                saleTradeBillGoodsItemVOS.add(goodsItemVO);
            }
        }

        //填入ArrayList<InventoryCheckItemVO> inventoryCheckItemVOS
        for (GoodsItemVO goodsItemVO : purchaseRefundBillGoodsItemVOS) {
            int judge = 0;
            for (InventoryCheckItemVO inventoryCheckItemVO : inventoryCheckItemVOS) {
                if (inventoryCheckItemVO.goods.getID().equals(goodsItemVO.goods.getID())) {
                    judge = 1;
                    inventoryCheckItemVO.purchaseNumber = inventoryCheckItemVO.purchaseNumber - goodsItemVO.number;
                    inventoryCheckItemVO.purchaseAmount = inventoryCheckItemVO.purchaseAmount - goodsItemVO.price * goodsItemVO.number;
                    break;
                }
            }
            if (judge == 0) {
                inventoryCheckItemVOS.add(new InventoryCheckItemVO(goodsItemVO.goods, 0 - goodsItemVO.number, 0 - goodsItemVO.number * goodsItemVO.price, 0, 0));
            }
        }

        for (GoodsItemVO goodsItemVO : purchaseTradeBillGoodsItemVOS) {
            int judge = 0;
            for (InventoryCheckItemVO inventoryCheckItemVO : inventoryCheckItemVOS) {
                if (inventoryCheckItemVO.goods.getID().equals(goodsItemVO.goods.getID())) {
                    judge = 1;
                    inventoryCheckItemVO.purchaseNumber = inventoryCheckItemVO.purchaseNumber + goodsItemVO.number;
                    inventoryCheckItemVO.purchaseAmount = inventoryCheckItemVO.purchaseAmount + goodsItemVO.price * goodsItemVO.number;
                    break;
                }
            }
            if (judge == 0) {
                inventoryCheckItemVOS.add(new InventoryCheckItemVO(goodsItemVO.goods, goodsItemVO.number, goodsItemVO.number * goodsItemVO.price, 0, 0));
            }
        }

        for (GoodsItemVO goodsItemVO : saleRefundBillGoodsItemVOS) {
            int judge = 0;
            for (InventoryCheckItemVO inventoryCheckItemVO : inventoryCheckItemVOS) {
                if (inventoryCheckItemVO.goods.getID().equals(goodsItemVO.goods.getID())) {
                    judge = 1;
                    inventoryCheckItemVO.saleNumber = inventoryCheckItemVO.saleNumber - goodsItemVO.number;
                    inventoryCheckItemVO.saleAmount = inventoryCheckItemVO.saleAmount - goodsItemVO.price * goodsItemVO.number;
                    break;
                }
            }
            if (judge == 0) {
                inventoryCheckItemVOS.add(new InventoryCheckItemVO(goodsItemVO.goods, 0, 0, 0 - goodsItemVO.number, 0 - goodsItemVO.price * goodsItemVO.number));
            }
        }

        for (GoodsItemVO goodsItemVO : saleTradeBillGoodsItemVOS) {
            int judge = 0;
            for (InventoryCheckItemVO inventoryCheckItemVO : inventoryCheckItemVOS) {
                if (inventoryCheckItemVO.goods.getID().equals(goodsItemVO.goods.getID())) {
                    judge = 1;
                    inventoryCheckItemVO.saleNumber = inventoryCheckItemVO.saleNumber + goodsItemVO.number;
                    inventoryCheckItemVO.saleAmount = inventoryCheckItemVO.saleAmount + goodsItemVO.price * goodsItemVO.number;
                    break;
                }
            }
            if (judge == 0) {
                inventoryCheckItemVOS.add(new InventoryCheckItemVO(goodsItemVO.goods, 0, 0, goodsItemVO.number, goodsItemVO.price * goodsItemVO.number));
            }
        }

        return inventoryCheckItemVOS;
    }
}
