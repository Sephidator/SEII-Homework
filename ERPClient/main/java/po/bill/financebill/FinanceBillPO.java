package po.bill.financebill;

import po.bill.BillPO;

public class FinanceBillPO extends BillPO {
    protected double total;// 财务账单的总金额

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
