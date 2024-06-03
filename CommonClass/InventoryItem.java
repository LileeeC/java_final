//attributes of inventory items
package CommonClass;

import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryItem {
    public float quantities = 0;
    public JButton ButtonTrigger;
    public String name = "";
    public float singleCost = 0;
    public Dialog Data = null;
    public JPanel display = null;
    public JLabel remainJLabel = null;

    public InventoryItem() {
        quantities = (float) 0;
    }
}
