//the look of goods page
package Pages;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CommonClass.Goods;
import CommonClass.Material;

import java.awt.*;
import src.*;

public class GoodsPage implements ActionListener {
    public static JPanel createGoodsPagePanel() {
        GoodsPage goodsPage = new GoodsPage();
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
        // panel.setPreferredSize(new Dimension(400, 500));

        // back button
        JButton storeMenuButton = DataStore.createBackButton("返回");
        storeMenuButton.addActionListener(goodsPage);
        storeMenuButton.setActionCommand("Store Menu");
        panel.add(storeMenuButton);

        // addgoodsbutton
        JButton addGoodsButton = src.DataStore.createAddButton("新增商品");
        addGoodsButton.addActionListener(goodsPage);
        addGoodsButton.setActionCommand("Add Goods");
        panel.add(addGoodsButton);

        return panel;
    }

    private void openInputDialog() {
        JDialog dialog = new JDialog(DataStore.MainFrame, "Input Product and Materials", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("商品名稱:"));
        JTextField productNameField = new JTextField();
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("商品價格:"));
        JTextField productPriceField = new JTextField();
        inputPanel.add(productPriceField);

        JPanel materialPanel = new JPanel();
        materialPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(materialPanel);

        JButton addMaterialButton = new JButton("新增材料");
        addMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materialPanel.add(new JLabel("材料名稱:"));
                materialPanel.add(new JTextField());

                materialPanel.add(new JLabel("材料數量:"));
                materialPanel.add(new JTextField());

                materialPanel.revalidate();
                materialPanel.repaint();
            }
        });

        JButton confirmButton = new JButton("完成商品設定");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Goods goods = new Goods();
                goods.name = productNameField.getText();
                goods.price = Integer.parseInt(productPriceField.getText());
                JTextField materialNameField, materialNumberField;

                JPanel OuterPanel = new JPanel(), InnerPanel = new JPanel();

                OuterPanel.setPreferredSize(new Dimension(300, 200));
                OuterPanel.setBackground(new Color(173, 216, 230));
                OuterPanel.setLayout(new BorderLayout());
                OuterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                // 创建NorthPanel并居中对齐
                JLabel NameLabel = new JLabel("商品名稱: " + goods.name);
                JPanel NorthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                NorthPanel.add(NameLabel);
                OuterPanel.add(NorthPanel, BorderLayout.NORTH);
                NameLabel.setFont(new Font("宋体", Font.BOLD, 20));
                // 创建SouthPanel并居中对齐
                JLabel PriceLabel = new JLabel("商品價格: " + goods.price);
                JPanel SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                SouthPanel.add(PriceLabel);
                OuterPanel.add(SouthPanel, BorderLayout.SOUTH);
                PriceLabel.setFont(new Font("宋体", Font.BOLD, 20));

                InnerPanel.setLayout(new BoxLayout(InnerPanel, BoxLayout.Y_AXIS));
                JPanel CenterPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间距
                gbc.gridx = 0;
                gbc.gridy = 0;

                for (int i = 0; i < materialPanel.getComponentCount(); i += 4) {
                    Material m = new Material();
                    materialNameField = materialPanel.getComponent(i + 1) instanceof JTextField
                            ? (JTextField) materialPanel.getComponent(i + 1)
                            : null;
                    materialNumberField = materialPanel.getComponent(i + 3) instanceof JTextField
                            ? (JTextField) materialPanel.getComponent(i + 3)
                            : null;
                    m.name = materialNameField.getText();
                    m.number = Float.parseFloat(materialNumberField.getText());
                    goods.materials.add(m);

                    JLabel meterialNameLabel = new JLabel("所需材料: " + m.name),
                            materialNumberJLabel = new JLabel("所需數量: " + m.number);
                    CenterPanel.add(meterialNameLabel, gbc);
                    gbc.gridx++;
                    CenterPanel.add(materialNumberJLabel, gbc);
                    gbc.gridx = 0;
                    gbc.gridy++;
                }

                InnerPanel.add(CenterPanel);
                OuterPanel.add(InnerPanel, BorderLayout.CENTER);
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.add(OuterPanel);
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.revalidate();
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.repaint();
                dialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addMaterialButton);
        buttonPanel.add(confirmButton);

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(DataStore.MainFrame.getTitle());
        } else if ("Add Goods".equals(command)) {
            openInputDialog();
        }
    }
}
