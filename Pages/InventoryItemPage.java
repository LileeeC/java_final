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

import CommonClass.InventoryItem;
import src.DataStore;

public class InventoryItemPage implements ActionListener {
  public static JPanel createInventoryItemPagePanel() {
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

    InventoryItemPage inventoryItemPage = new InventoryItemPage();

    JButton storeInventoryItemButton = DataStore.createCustomButton("返回");
    storeInventoryItemButton.addActionListener(inventoryItemPage);
    storeInventoryItemButton.setActionCommand("Inventory Page");
    panel.add(storeInventoryItemButton);

    JButton addInventoryItemButton = src.DataStore.createCustomButton("新增庫存");
    panel.add(addInventoryItemButton);

    addInventoryItemButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入庫存名稱: ");

        if (buttonName != null && !buttonName.trim().isEmpty()
            && !DataStore.InventoryItemName.contains(buttonName)) {
          InventoryItem item = new InventoryItem();
          item.ButtonTrigger = DataStore.createCustomButton(buttonName);
          item.name = buttonName;
          DataStore.InventoryItemName.add(item.name);
          DataStore.InventoryItem.put(buttonName, item);

          String quantities = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入庫存數量: ");
          while (!(quantities != null && !quantities.trim().isEmpty()
              && isFloat(quantities))) {
            JOptionPane.showMessageDialog(DataStore.MainFrame, "庫存名稱不能空白或非數字", "錯誤", JOptionPane.ERROR_MESSAGE);
            quantities = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入庫存數量: ");
          }
          item.quantities = Float.parseFloat(quantities);

          panel.add(item.ButtonTrigger);

          panel.revalidate();
          panel.repaint();
        } else if (buttonName != null) {
          JOptionPane.showMessageDialog(DataStore.MainFrame, "庫存名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
        }

      }
    });

    return panel;
  }

  public static boolean isFloat(String str) {
    try {
      Float.parseFloat(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if ("Inventory Page".equals(command)) {
      DataStore.showInventoryPage(command);
    }
  }
}
