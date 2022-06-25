package lk.ijse.pizza.view.tblModel;

public class Recep_ItemManagementTM {

    private String itemCode;
    private String name;
    private String size;
    private String price;

    public Recep_ItemManagementTM() {
    }

    public Recep_ItemManagementTM(String itemCode, String name, String size, String price) {
        this.itemCode = itemCode;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Recep_ItemManagementTM{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
