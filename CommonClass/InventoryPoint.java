//attributes of inventory points
package CommonClass;

import java.util.*;

import javax.swing.JButton;

public class InventoryPoint {
    public String name;
    //Sting代表location名稱，不同名稱對應不同InventoryItem(庫存)
    public Map<String, ArrayList<InventoryItem>> location;
    //打開不同庫存的觸發Button
    public Map<String, JButton> ButtonTrigger;

    public InventoryPoint() {
        location = new HashMap<>();
    }
}