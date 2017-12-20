package main.java.runner;

import main.java.data.accountdata.AccountData;
import main.java.data.clientdata.ClientData;
import main.java.data.financedata.CashBillData;
import main.java.data.financedata.PaymentBillData;
import main.java.data.financedata.ReceiptBillData;
import main.java.data.goodsdata.GoodsData;
import main.java.data.goodssortdata.GoodsSortData;
import main.java.data.initialdata.InitialData;
import main.java.data.inventorydata.InventoryGiftBillData;
import main.java.data.inventorydata.InventoryLossOverBillData;
import main.java.data.logdata.LogData;
import main.java.data.messagedata.MessageData;
import main.java.data.promotiondata.PromotionData;
import main.java.data.purchasedata.PurchaseRefundBillData;
import main.java.data.purchasedata.PurchaseTradeBillData;
import main.java.data.saledata.SaleRefundBillData;
import main.java.data.saledata.SaleTradeBillData;
import main.java.data.userdata.UserData;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteHelper {
    public RemoteHelper() throws RemoteException {
        LocateRegistry.createRegistry(7200);
        init();
    }

    private void init() throws RemoteException {
        new AccountData();
        new ClientData();
        new CashBillData();
        new PaymentBillData();
        new ReceiptBillData();
        new GoodsData();
        new GoodsSortData();
        new InitialData();
        new InventoryGiftBillData();
        new InventoryLossOverBillData();
        new LogData();
        new MessageData();
        new PromotionData();
        new PurchaseRefundBillData();
        new PurchaseTradeBillData();
        new SaleRefundBillData();
        new SaleTradeBillData();
        new UserData();
    }
}
