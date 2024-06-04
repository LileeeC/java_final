//attributes of inventory points
package CommonClass;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Pages.InventoryItemPage;

public class InventoryPoint {
    public String id;
    public String name;
    public int store_id;
    // Sting代表材料名稱，不同名稱對應不同InventoryItem(材料庫存)
    public Map<String, InventoryItem> items = new HashMap<>();
    // 打開不同庫存的觸發Button
    public Map<String, JButton> ButtonTrigger = new HashMap<>();
    public JPanel PointPanel, PointsPanelParent;

    public InventoryPoint(String PointName) {
        name = PointName;
        PointsPanelParent = InventoryItemPage.createInventoryItemPagePanel(PointName);
        PointPanel = (JPanel) ((JScrollPane) PointsPanelParent.getComponent(1)).getViewport().getView();
    }
}