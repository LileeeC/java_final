//the look of store page
package Pages;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.*;

public class StorePage implements ActionListener {
    public static JPanel createStoreMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 60, 100)) {
            @Override
            public Dimension getPreferredSize() {
                Container parent = getParent();
                if (parent != null && parent.getWidth() > 0) {
                    int parentWidth = parent.getWidth();
                    int width = 0;
                    int height = 0;
                    int rowWidth = 0;
                    int rowHeight = 0;

                    for (Component comp : getComponents()) {
                        Dimension compSize = comp.getPreferredSize();
                        if (rowWidth + compSize.width + 60 > parentWidth) {
                            width = Math.max(width, rowWidth);
                            height += rowHeight + 100; // 10 is the vertical gap
                            rowWidth = compSize.width;
                            rowHeight = compSize.height;
                        } else {
                            rowWidth += compSize.width + 60; // 10 is the horizontal gap
                            rowHeight = Math.max(rowHeight, compSize.height + 100);
                        }
                    }

                    width = Math.max(width, rowWidth);
                    height += rowHeight;
                    return new Dimension(width, height);
                }
                return super.getPreferredSize();
            }
        };
        StorePage storePage = new StorePage();

        JButton mainMenuButton = DataStore.createCustomButton("主選單");
        mainMenuButton.addActionListener(storePage);
        mainMenuButton.setActionCommand("Main Menu");

        JButton goodsPageButton = DataStore.createCustomButton("商品列表");
        goodsPageButton.addActionListener(storePage);
        goodsPageButton.setActionCommand("Goods Page");

        JButton inventoryPageButton = DataStore.createCustomButton("材料庫存");
        inventoryPageButton.addActionListener(storePage);
        inventoryPageButton.setActionCommand("Inventory Page");

        JButton financeReportButton = DataStore.createCustomButton("財務報表");
        financeReportButton.addActionListener(storePage);
        financeReportButton.setActionCommand("Finance Report");

        panel.add(mainMenuButton);
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
        } else if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(command);
        } else if ("Main Menu".equals(command)) {
            DataStore.showMainMenu(command);
        }
    }
}
