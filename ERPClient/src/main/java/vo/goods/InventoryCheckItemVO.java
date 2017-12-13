package main.java.vo.goods;



public class InventoryCheckItemVO {
    public GoodsVO goods;
    public int purchaseNumber;
    public double purchaseAmount;
    public int saleNumber;
    public double saleAmount;


    public InventoryCheckItemVO(GoodsVO goodsVO, int purchaseNumber, double purchaseAmount, int saleNumber, double saleAmount){
        this.goods=goodsVO;
        this.purchaseNumber=purchaseNumber;
        this.purchaseAmount=purchaseAmount;
        this.saleNumber=saleNumber;
        this.saleAmount=saleAmount;
    }
    public InventoryCheckItemVO(GoodsVO goodsVO){
        this.goods=goodsVO;
        this.purchaseNumber=0;
        this.purchaseAmount=0;
        this.saleNumber=0;
        this.saleAmount=0;
    }
}
