//the look of inventory page
package Pages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.DataStore;

public class InventoryPage implements ActionListener {
    public static JPanel createInventoryPagePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 60, 100));
        panel.add(new JLabel("材料庫存頁面"));

        InventoryPage inventoryPage = new InventoryPage();

        JButton storeMenuButton = DataStore.createCustomButton("葉志嘉說返回上一頁");
        storeMenuButton.addActionListener(inventoryPage);
        storeMenuButton.setActionCommand("Store Menu");
        panel.add(storeMenuButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(command);
        }
    }
}
