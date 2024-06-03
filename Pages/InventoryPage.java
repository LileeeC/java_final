//the look of inventory page
package Pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import CommonClass.InventoryPoint;
import src.DataStore;

public class InventoryPage implements ActionListener {
    public static JPanel createInventoryPagePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create a new panel for the title and center it
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("材料庫存");
        titleLabel.setFont(new Font("新宋体", Font.BOLD, 40));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0)); // Adding padding
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        //中間內容的panel
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

        InventoryPage inventoryPage = new InventoryPage();

        JButton addInventoryPointButton = src.DataStore.createAddButton("新增庫存點");
        panel.add(addInventoryPointButton);

        addInventoryPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入庫存點名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty()
                        && !DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap
                                .containsKey(buttonName)) {
                    InventoryPoint point = new InventoryPoint(buttonName);
                    point.ButtonTrigger.put(point.name, DataStore.createCustomButton(buttonName));
                    DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.put(buttonName, point);
                    panel.add(point.ButtonTrigger.get(point.name));
                    point.ButtonTrigger.get(point.name).addActionListener(inventoryPage);
                    point.ButtonTrigger.get(point.name).setActionCommand("Inventory Item Page");

                    panel.revalidate();
                    panel.repaint();
                } else if (buttonName != null) {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "庫存點名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // 將主內容面板放在 JScrollPane 中才不會把返回擠下去
        JScrollPane scrollPane = new JScrollPane(panel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        //at the bottom
        JButton storeMenuButton = DataStore.createBackButton("返回", 130);
        storeMenuButton.addActionListener(inventoryPage);
        storeMenuButton.setActionCommand("Store Menu");

        JPanel bottomPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanelContainer.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adding padding
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(300, 100));
        bottomPanelContainer.add(bottomPanel);

        bottomPanel.add(storeMenuButton);
        mainPanel.add(bottomPanelContainer, BorderLayout.PAGE_END);

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(DataStore.MainFrame.getTitle());
        } else if ("Inventory Item Page".equals(command)) {
            DataStore.showInventoryItemPage(command, ((JButton) e.getSource()).getText());
        }
    }
}
