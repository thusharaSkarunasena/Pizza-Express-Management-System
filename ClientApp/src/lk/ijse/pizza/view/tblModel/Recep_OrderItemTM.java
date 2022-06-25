package lk.ijse.pizza.view.tblModel;

public class Recep_OrderItemTM {

    private String itemCode;
    private String qty;
    private String total;

    public Recep_OrderItemTM() {
    }

    public Recep_OrderItemTM(String itemCode, String qty, String total) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Recep_OrderItemTM{" +
                "itemCode='" + itemCode + '\'' +
                ", qty='" + qty + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
