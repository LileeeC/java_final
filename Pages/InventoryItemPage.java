//the look of inventory page
package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CommonClass.Goods;
import CommonClass.InventoryItem;
import src.DataStore;
import src.Database;

public class InventoryItemPage implements ActionListener {
  public static JPanel createInventoryItemPagePanel(String InventoryName) {
    InventoryItemPage inventoryItemPage = new InventoryItemPage();
    JPanel mainPanel = new JPanel(new BorderLayout());

    // Create a new panel for the title and center it
    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel titleLabel = new JLabel("材料與商品庫存");
    titleLabel.setFont(new Font("新宋体", Font.BOLD, 40));
    titlePanel.add(titleLabel);
    titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0)); // Adding padding
    mainPanel.add(titlePanel, BorderLayout.PAGE_START);

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

    // 將主內容面板放在 JScrollPane 中才不會把返回擠下去
    JScrollPane scrollPane = new JScrollPane(panel);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    JButton addInventoryItemButton = src.DataStore.createCustomButton("新增庫存");
    panel.add(addInventoryItemButton);

    // at the bottom
    JButton storeMenuButton = DataStore.createBackButton("返回", 130);
    storeMenuButton.addActionListener(inventoryItemPage);
    storeMenuButton.setActionCommand("Inventory Page");

    JPanel bottomPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanelContainer.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adding padding
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.setPreferredSize(new Dimension(300, 100));
    bottomPanelContainer.add(bottomPanel);

    bottomPanel.add(storeMenuButton);
    mainPanel.add(bottomPanelContainer, BorderLayout.PAGE_END);

    addInventoryItemButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        openInputDialog(InventoryName);
      }
    });

    return mainPanel;
  }

  public static boolean isFloat(String str) {
    try {
      Float.parseFloat(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private static void openInputDialog(String InventoryName) {
    JDialog dialog = new JDialog(DataStore.MainFrame, "設定庫存", true);
    dialog.setSize(300, 150);
    dialog.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

    JLabel ItemName = new JLabel("庫存名稱: "), ItemPrice = new JLabel("庫存成本: "), ItemNumber = new JLabel("庫存數量: ");
    inputPanel.add(ItemName);
    JTextField productNameField = new JTextField();
    inputPanel.add(productNameField);

    inputPanel.add(ItemPrice);
    JTextField productPriceField = new JTextField();
    inputPanel.add(productPriceField);

    inputPanel.add(ItemNumber);
    JTextField productNumberField = new JTextField();
    inputPanel.add(productNumberField);

    JButton confirmButton = new JButton("完成");
    confirmButton.addActionListener(new ActionListener() {
      public String thisDialogName = "";

      @Override
      public void actionPerformed(ActionEvent e) {
        if (productNameField.getText().trim().isEmpty()
            || (DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
                .containsKey(productNameField.getText())
                && DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
                    .get(productNameField.getText()).Data != dialog)) {
          JOptionPane.showMessageDialog(DataStore.MainFrame, "庫存名稱空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
          return;
        } else if (productPriceField.getText().trim().isEmpty()) {
          JOptionPane.showMessageDialog(DataStore.MainFrame, "庫存成本空白", "錯誤", JOptionPane.ERROR_MESSAGE);
          return;
        }

        InventoryItem item;
        JPanel OuterPanel;
        if (thisDialogName == "") {
          item = new InventoryItem();
        } else {
          item = DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
              .get(thisDialogName);
          if (thisDialogName != productNameField.getText()) {
            DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
                .remove(thisDialogName);
            thisDialogName = productNameField.getText();
          }

          OuterPanel = item.display;
          for (Component comp : DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap
              .get(InventoryName).PointPanel.getComponents()) {
            if (comp == OuterPanel) {
              DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).PointPanel
                  .remove(comp);
              break;
            }
          }
        }

        OuterPanel = new JPanel();
        OuterPanel.setPreferredSize(new Dimension(400, 250));
        OuterPanel.setBackground(new Color(173, 216, 230));
        OuterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        OuterPanel.setLayout(new GridBagLayout());

        item.name = productNameField.getText();
        item.singleCost = Float.parseFloat(productPriceField.getText());
        item.quantities = Float.parseFloat(productNumberField.getText());
        item.display = OuterPanel;

        String point_id = DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).id;
        if (thisDialogName == "")
          Database.insertInventoryItem(item.name, item.quantities, item.singleCost, point_id, item);
        else
          Database.updateInventoryItem(item.name, item.quantities, item.singleCost, item.id);
        DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items.put(item.name,
            item);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间距

        Font labelFont = new Font("宋体", Font.BOLD, 15); // 设置字体

        // 材料名稱
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel nameLabel = new JLabel("庫存名稱: " + item.name);
        nameLabel.setFont(labelFont);
        OuterPanel.add(nameLabel, gbc);

        // 材料價格
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JLabel priceLabel = new JLabel("庫存成本: " + item.singleCost);
        priceLabel.setFont(labelFont);
        OuterPanel.add(priceLabel, gbc);

        // 材料剩餘
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JLabel remainingLabel = DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap
            .get(InventoryName).items.get(item.name).remainJLabel;
        if (remainingLabel == null) {
          remainingLabel = new JLabel();
          DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
              .get(item.name).remainJLabel = remainingLabel;
        }
        remainingLabel.setText("剩餘: " + item.quantities);
        remainingLabel.setFont(labelFont);
        OuterPanel.add(remainingLabel, gbc);

        // 修改/查看
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton materialBtn = new JButton("修改");
        materialBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            thisDialogName = item.name;
            productNumberField.setText("" + item.quantities);
            DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).items
                .get(thisDialogName).Data.setVisible(true);
          }
        });
        materialBtn.setFont(labelFont);
        OuterPanel.add(materialBtn, gbc);

        DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).PointPanel
            .add(OuterPanel);
        DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).PointPanel
            .revalidate();
        DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.get(InventoryName).PointPanel.repaint();
        item.Data = dialog;
        dialog.setVisible(false);

        for (Map.Entry<String, Goods> entry : DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList
            .entrySet()) {
          int val = entry.getValue().RemainCalculate(DataStore.Stores.get(DataStore.MainFrame.getTitle()));
          entry.getValue().remainingLabel.setText("剩餘: " + val);
        }

        Object[] newLine = { LocalDate.now(), item.name + "*" + item.quantities,
            "-" + (item.singleCost * item.quantities) };
        DataStore.Stores.get(DataStore.MainFrame.getTitle()).FinanceTableObject = DataStore.Stores
            .get(DataStore.MainFrame.getTitle())
            .addRow(DataStore.Stores.get(DataStore.MainFrame.getTitle()).FinanceTableObject, newLine);
      }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(confirmButton);

    dialog.add(inputPanel, BorderLayout.NORTH);
    dialog.add(buttonPanel, BorderLayout.CENTER);

    dialog.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if ("Store Menu".equals(command)) {
      DataStore.showStoreMenu(DataStore.MainFrame.getTitle());
    } else if ("Inventory Page".equals(command)) {
      DataStore.showInventoryPage(command);
    }
  }
}
