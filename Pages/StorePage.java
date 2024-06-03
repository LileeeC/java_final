//the look of store page
package Pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.*;

public class StorePage implements ActionListener {
    public static JPanel createStoreMenuPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a new panel for the top
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)) /*{
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
        }*/;
        JLabel titleLabel = new JLabel("店家管理");
        titleLabel.setFont(new Font("新宋体", Font.BOLD, 40));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0)); // Adding padding
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        StorePage storePage = new StorePage();

        // Create a center panel with a specific width
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 130, 160));
        centerPanel.setBorder(new EmptyBorder(10, 50, 0, 50)); // 上, 左, 下, 右邊距
        centerPanel.setPreferredSize(new Dimension(600, 0)); // 設定 centerPanel 的寬度

        JButton goodsPageButton = DataStore.createCustomButton("商品列表");
        goodsPageButton.addActionListener(storePage);
        goodsPageButton.setActionCommand("Goods Page");

        JButton inventoryPageButton = DataStore.createCustomButton("材料庫存");
        inventoryPageButton.addActionListener(storePage);
        inventoryPageButton.setActionCommand("Inventory Page");

        JButton financeReportButton = DataStore.createCustomButton("財務報表");
        financeReportButton.addActionListener(storePage);
        financeReportButton.setActionCommand("Finance Report");

        centerPanel.add(goodsPageButton);
        centerPanel.add(inventoryPageButton);
        centerPanel.add(financeReportButton);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //at the bottom
        JButton mainMenuButton = DataStore.createBackButton("返回店家總覽", 160);
        mainMenuButton.addActionListener(storePage);
        mainMenuButton.setActionCommand("Main Menu");

        JPanel bottomPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanelContainer.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adding padding
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(300, 100));
        bottomPanelContainer.add(bottomPanel);

        bottomPanel.add(mainMenuButton);
        mainPanel.add(bottomPanelContainer, BorderLayout.PAGE_END);

        
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Goods Page".equals(command)) {
            DataStore.showGoodsPage();
        } else if ("Inventory Page".equals(command)) {
            DataStore.showInventoryPage(command);
        } else if ("Finance Report".equals(command)) {
            DataStore.showFinanceReportPage();
        } else if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(command);
        } else if ("Main Menu".equals(command)) {
            DataStore.showMainMenu(command);
        }
    }
}
