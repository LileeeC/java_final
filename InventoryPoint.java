import java.util.*;

public class InventoryPoint{
    public int id;
    public String location;
    public ArrayList<InventoryItem> items;

    InventoryPoint() {
        id = 0;
        location = "";
        items = new ArrayList<InventoryItem>();
    }
    
    InventoryPoint(int id, String location, ArrayList<InventoryItem> items) {
        this.id = id;
        this.location = location;
        this.items = items;
    }
}