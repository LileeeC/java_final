//attributes of inventory items
package CommonClass;

import javax.swing.JButton;

public class InventoryItem {
    public float quantities;
    public JButton ButtonTrigger;
    public String name;
    public float singleCost;

    public InventoryItem() {
        quantities = (float) 0;
    }
}
