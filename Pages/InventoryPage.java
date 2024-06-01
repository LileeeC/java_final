package Pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryPage {
    public static JPanel createInventoryPagePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("材料庫存頁面"));
        return panel;
    }
}
