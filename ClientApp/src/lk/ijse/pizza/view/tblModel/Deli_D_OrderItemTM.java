package lk.ijse.pizza.view.tblModel;

public class Deli_D_OrderItemTM {
    private String itemCode;
    private String nameNsize;
    private String qty;

    public Deli_D_OrderItemTM() {
    }

    public Deli_D_OrderItemTM(String itemCode, String nameNsize, String qty) {
        this.itemCode = itemCode;
        this.nameNsize = nameNsize;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
        return "Deli_D_OrderItemTM{" +
                "itemCode='" + itemCode + '\'' +
                ", nameNsize='" + nameNsize + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
