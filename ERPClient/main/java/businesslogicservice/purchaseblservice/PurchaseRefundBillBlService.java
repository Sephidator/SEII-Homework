package businesslogicservice.purchaseblservice;

public interface PurchaseRefundBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query);
    public ArrayList<ClientVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(PurchaseRefundBillVO bill);
    public ResultMessage saveDraft(PurchaseRefundBillVO bill);

}
