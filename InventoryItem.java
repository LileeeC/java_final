public class InventoryItem{
    public int id;
    public String name;
    public int quantity;

    InventoryItem(){
        id = 0;
        name = "";
        quantity = 0;
    }

    InventoryItem(int id, String name, int quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
}
