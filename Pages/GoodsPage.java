//the look of goods page
package Pages;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

        JLabel GoodsName = new JLabel("商品名稱:"), GoodsPrice = new JLabel("商品價格:");
        inputPanel.add(GoodsName);
        JTextField productNameField = new JTextField();
        inputPanel.add(productNameField);
        inputPanel.add(GoodsPrice);
        JTextField productPriceField = new JTextField();
        inputPanel.add(productPriceField);

        JPanel materialPanel = new JPanel();
        materialPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(materialPanel);
        materialPanel.add(new JLabel("材料名稱:"));
        materialPanel.add(new JTextField());

        materialPanel.add(new JLabel("材料數量:"));
        materialPanel.add(new JTextField());

        materialPanel.revalidate();
        materialPanel.repaint();
        
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

        JButton removeMaterialButton = new JButton("移除材料");
        removeMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(materialPanel.getComponentCount() < 8)
                {
                    return;
                }

                for(int i = 0; i < 4; i++)
                {
                    materialPanel.remove(materialPanel.getComponent(materialPanel.getComponentCount() - 1));
                }
                materialPanel.revalidate();
                materialPanel.repaint();
            }
        });

        JButton confirmButton = new JButton("完成商品設定");
        confirmButton.addActionListener(new ActionListener() {
            public String thisDialogName = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(thisDialogName);
                if(productNameField.getText().trim().isEmpty() || (DataStore.Stores.get(DataStore.MainFrame.getTitle()).AllGoodsName.contains(productNameField.getText()) && DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList.get(productNameField.getText()).Data != dialog))
                {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "商品名稱空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(productPriceField.getText().trim().isEmpty())
                {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "商品價格空白", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Goods goods;
                JPanel OuterPanel;
                if(thisDialogName == "")
                {
                    goods = new Goods();
                    goods.name = productNameField.getText();
                    goods.price = Integer.parseInt(productPriceField.getText());
                }
                else
                {
                    goods = DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList.get(thisDialogName);
                    if(thisDialogName != productNameField.getText())
                    {
                        DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList.remove(thisDialogName);
                        DataStore.Stores.get(DataStore.MainFrame.getTitle()).AllGoodsName.remove(thisDialogName);
                        thisDialogName = productNameField.getText();
                    }

                    OuterPanel = goods.display;
                    for (Component comp : DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.getComponents()) {
                        if (comp == OuterPanel) {
                            DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.remove(comp);
                            break;
                        }
                    }
                }

                OuterPanel = new JPanel();
                OuterPanel.setPreferredSize(new Dimension(400, 250));
                OuterPanel.setBackground(new Color(173, 216, 230));
                OuterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                OuterPanel.setLayout(new GridBagLayout());
                
                goods.name = productNameField.getText();
                goods.price = Integer.parseInt(productPriceField.getText());
                goods.display = OuterPanel;

                DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList.put(goods.name, goods);
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).AllGoodsName.add(goods.name);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间距

                Font labelFont = new Font("宋体", Font.BOLD, 15); // 设置字体
                
                goods.materials.clear();
                for (int i = 0; i < materialPanel.getComponentCount(); i += 4) {
                    Material m = new Material();
                    JTextField materialNameField = materialPanel.getComponent(i + 1) instanceof JTextField
                            ? (JTextField) materialPanel.getComponent(i + 1)
                            : null;

                    JTextField materialNumberField = materialPanel.getComponent(i + 3) instanceof JTextField
                            ? (JTextField) materialPanel.getComponent(i + 3)
                            : null;

                    if(materialNameField.getText().trim().isEmpty())
                    {
                        JOptionPane.showMessageDialog(DataStore.MainFrame, "材料名稱未輸入", "錯誤", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    m.name = materialNameField.getText();

                    if(materialNumberField.getText().trim().isEmpty())
                    {
                        JOptionPane.showMessageDialog(DataStore.MainFrame, "材料數量未輸入", "錯誤", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    m.number = Float.parseFloat(materialNumberField.getText());

                    goods.materials.add(m);
                }

                // 商品名稱
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                JLabel nameLabel = new JLabel("商品名稱: " + goods.name);
                nameLabel.setFont(labelFont);
                OuterPanel.add(nameLabel, gbc);

                // 商品價格
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                JLabel priceLabel = new JLabel("商品價格: " + goods.price);
                priceLabel.setFont(labelFont);
                OuterPanel.add(priceLabel, gbc);

                // 剩餘
                gbc.gridx = 0;
                gbc.gridy++;
                JLabel remainingLabel = new JLabel("剩餘: " + goods.RemainCalculate(DataStore.Stores.get(DataStore.MainFrame.getTitle())));
                remainingLabel.setFont(labelFont);
                OuterPanel.add(remainingLabel, gbc);

                // 賣出數量選擇
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 1;
                JLabel quantityLabel = new JLabel("賣出數量選擇: ");
                quantityLabel.setFont(labelFont);
                OuterPanel.add(quantityLabel, gbc);

                gbc.gridy++;
                JTextField quantityField = new JTextField(15);
                OuterPanel.add(quantityField, gbc);

                // 賣出
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                JButton soldBtn = new JButton("賣出");
                soldBtn.setFont(labelFont);
                OuterPanel.add(soldBtn, gbc);

                // 修改/查看
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                JButton materialBtn = new JButton("修改/查看");
                materialBtn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        thisDialogName = goods.name;
                        DataStore.Stores.get(DataStore.MainFrame.getTitle()).GoodsList.get(goods.name).Data.setVisible(true);
                    }
                });
                materialBtn.setFont(labelFont);
                OuterPanel.add(materialBtn, gbc);

                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.add(OuterPanel);
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.revalidate();
                DataStore.Stores.get(DataStore.MainFrame.getTitle()).StorePanel.repaint();
                goods.Data = dialog;
                dialog.setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addMaterialButton);
        buttonPanel.add(removeMaterialButton);
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
