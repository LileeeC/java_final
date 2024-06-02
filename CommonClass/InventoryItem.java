//attributes of inventory items
package CommonClass;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class InventoryItem {
    public float quantities;
    public JButton ButtonTrigger;
    public String name;

    public InventoryItem() {
        quantities = (float) 0;
    }
}
