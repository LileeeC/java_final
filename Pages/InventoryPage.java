//the look of inventory page
package Pages;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CommonClass.InventoryPoint;
import src.DataStore;

public class InventoryPage implements ActionListener {
    public static JPanel createInventoryPagePanel() {
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
        // panel.add(new JLabel("材料庫存頁面"));

        InventoryPage inventoryPage = new InventoryPage();

        JButton storeMenuButton = DataStore.createBackButton("返回");
        storeMenuButton.addActionListener(inventoryPage);
        storeMenuButton.setActionCommand("Store Menu");
        panel.add(storeMenuButton);

        JButton addInventoryPointButton = src.DataStore.createAddButton("新增庫存點");
        panel.add(addInventoryPointButton);

        addInventoryPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入庫存點名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty()
                        && !DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap
                                .containsKey(buttonName)) {
                    InventoryPoint point = new InventoryPoint();
                    point.name = buttonName;
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
        return panel;
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
