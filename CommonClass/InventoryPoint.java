//attributes of inventory points
package CommonClass;

import java.util.*;

import javax.swing.JButton;

public class InventoryPoint {
    public Map<String, InventoryItem> location;
    public String name;
    public JButton ButtonTrigger;

    public InventoryPoint() {
        location = new HashMap<>();
    }
}