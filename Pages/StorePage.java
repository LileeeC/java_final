package Pages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.xml.crypto.Data;

import src.*;

public class StorePage implements ActionListener {
    public static JPanel createStoreMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        StorePage storePage = new StorePage();

        JButton goodsPageButton = DataStore.createCustomButton("剩餘商品");
        goodsPageButton.addActionListener(storePage);
        goodsPageButton.setActionCommand("Goods Page");

        JButton inventoryPageButton = DataStore.createCustomButton("材料庫存");
        inventoryPageButton.addActionListener(storePage);
        inventoryPageButton.setActionCommand("Inventory Page");

        JButton financeReportButton = DataStore.createCustomButton("財務報表");
        financeReportButton.addActionListener(storePage);
        financeReportButton.setActionCommand("Finance Report");

        panel.add(goodsPageButton);
        panel.add(inventoryPageButton);
        panel.add(financeReportButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Goods Page".equals(command)) {
            DataStore.showGoodsPage();
        } else if ("Inventory Page".equals(command)) {
            DataStore.showInventoryPage();
        } else if ("Finance Report".equals(command)) {
            DataStore.showFinanceReportPage();
        }
    }
}