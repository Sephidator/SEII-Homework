package vo;
//import UserPO

public class FinanceBillVO extends BillVO {

    protected double total;// 财务账单的总金额

    protected String operator;//操作员

    protected String comment;//财务单据的备注

    public double getTotal() {
        return total;
    }

    public String getOperator() {
        return operator;
    }

    public String getComment() {
        return comment;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "[总额=" + this.total + ", 操作员=" + this.operator +", 备注=" + this.comment +"]";
    }
}
