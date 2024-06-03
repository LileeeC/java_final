//attributes of inventory points
package CommonClass;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InventoryPoint {
    public String name;
    //Sting代表材料名稱，不同名稱對應不同InventoryItem(材料庫存)
    public Map<String, InventoryItem> items = new HashMap<>();
    //打開不同庫存的觸發Button
    public Map<String, JButton> ButtonTrigger = new HashMap<>();
    public JPanel PointPanel;

    public InventoryPoint() {
        
    }
}