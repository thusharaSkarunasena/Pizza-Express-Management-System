package lk.ijse.pizza.view.tblModel;

public class Admin_ViewOrderDetailTM {

    private String itemCode;
    private String nameNsize;
    private String qty;

    public Admin_ViewOrderDetailTM() {
    }

    public Admin_ViewOrderDetailTM(String itemCode, String nameNsize, String qty) {
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
        return "Admin_ViewOrderDetailTM{" +
                "itemCode='" + itemCode + '\'' +
                ", nameNsize='" + nameNsize + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
