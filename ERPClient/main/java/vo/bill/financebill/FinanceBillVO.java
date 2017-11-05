package vo.bill.financebill;
//import UserPO

import vo.bill.BillVO;

public class FinanceBillVO extends BillVO {
    protected double total;// 财务账单的总金额

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
