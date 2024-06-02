//the look of goods page
package Pages;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        JButton storeMenuButton = DataStore.createCustomButton("返回");
        storeMenuButton.addActionListener(goodsPage);
        storeMenuButton.setActionCommand("Store Menu");
        panel.add(storeMenuButton);

        // addgoodsbutton
        JButton addGoodsButton = src.DataStore.createCustomButton("新增商品");
        addGoodsButton.addActionListener(goodsPage);
        addGoodsButton.setActionCommand("Add Goods");
        panel.add(addGoodsButton);

        // JPanel panel = new JPanel(new GridBagLayout());

        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.insets = new Insets(10, 10, 10, 10);

        // JLabel label = new JLabel("商品列表");
        // label.setFont(new Font("Serif", Font.PLAIN, 28));
        // panel.add(label, gbc);

        // ArrayList<Goods> goods = new ArrayList<>();
        // for (int i = 0; i < 20; i++)
        // goods.add(new Goods());

        // int x = 0;
        // int y = 1; // 从第二行开始，因为第一行有标题
        // for (Goods currentGoods : goods) {
        // addGoodsToPanel(panel, gbc, currentGoods, x, y);
        // y += 5; // 每个商品占用五行
        // if (y > 10) {
        // x++;
        // y = 1; // 重置为第二行
        // }
        // }

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
                /*
                 * Goods goods = new Goods(productNameField.getText());
                 * for (int i = 0; i < materialPanel.getComponentCount(); i += 2) {
                 * if (materialPanel.getComponent(i) instanceof JTextField
                 * && materialPanel.getComponent(i + 1) instanceof JTextField) {
                 * JTextField materialNameField = (JTextField) materialPanel.getComponent(i);
                 * JTextField materialQuantityField = (JTextField) materialPanel.getComponent(i
                 * + 1);
                 * goods.addMaterial(materialNameField.getText(),
                 * Integer.parseInt(materialQuantityField.getText()));
                 * }
                 * }
                 * goodsList.add(goods);
                 * displayGoods(goods);
                 * dialog.dispose();
                 */
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
            DataStore.showStoreMenu(command);
        } else if ("Main Menu".equals(command)) {
            DataStore.showMainMenu(command);
        } else if ("Add Goods".equals(command)) {
            openInputDialog();
        }
    }
    // public static void addGoodsToPanel(JPanel panel, GridBagConstraints gbc,
    // Goods currentGoods, int x, int y) {
    // JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
    // nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    // gbc.gridx = x;
    // gbc.gridy = y;
    // panel.add(nameLabel, gbc);

    // for (Material material : currentGoods.materials) {
    // JLabel materialLabel = new JLabel("材料：" + material.toString()); // 或使用
    // material.name
    // materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    // gbc.gridy = ++y;
    // panel.add(materialLabel, gbc);
    // }

    // JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
    // numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    // gbc.gridy = ++y;
    // panel.add(numberLabel, gbc);

    // JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
    // priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    // gbc.gridy = ++y;
    // panel.add(priceLabel, gbc);

    // JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
    // costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    // gbc.gridy = ++y;
    // panel.add(costLabel, gbc);
    // }
}
