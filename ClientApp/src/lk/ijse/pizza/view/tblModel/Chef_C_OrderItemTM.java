package lk.ijse.pizza.view.tblModel;

public class Chef_C_OrderItemTM {

    private String ItemCode;
    private String nameNsize;
    String qty;

    public Chef_C_OrderItemTM() {
    }

    public Chef_C_OrderItemTM(String itemCode, String nameNsize, String qty) {
        ItemCode = itemCode;
        this.nameNsize = nameNsize;
        this.qty = qty;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getNameNsize() {
        return nameNsize;
    }

    public void setNameNsize(String nameNsize) {
        this.nameNsize = nameNsize;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Chef_C_OrderItemTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", nameNsize='" + nameNsize + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
