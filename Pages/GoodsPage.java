//the look of goods page
package Pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CommonClass.Goods;
import CommonClass.Material;
import CommonClass.Store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import src.*;

public class GoodsPage implements ActionListener{
  public static JPanel createGoodsPagePanel() {
    GoodsPage goodsPage = new GoodsPage();
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 60, 100));
    //panel.setPreferredSize(new Dimension(400, 500));

    

    //back button
    JButton storeMenuButton = DataStore.createCustomButton("返回");
    storeMenuButton.addActionListener(goodsPage);
    storeMenuButton.setActionCommand("Store Menu");
    panel.add(storeMenuButton);

    //addgoodsbutton
    JButton addGoodsButton = src.DataStore.createCustomButton("新增商品");
    panel.add(addGoodsButton);

    addGoodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入商品名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !DataStore.GoodsName.contains(buttonName)) {
                    Goods goods = new Goods();
                    goods.ButtonTrigger = DataStore.createCustomButton(buttonName);
                    goods.name = buttonName;
                    DataStore.GoodsName.add(goods.name);
                    DataStore.Goods.put(buttonName, goods);
                    panel.add(goods.ButtonTrigger);
                    goods.ButtonTrigger.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton clickedButton = (JButton) e.getSource();
                            DataStore.showStoreMenu(clickedButton.getText());
                        }
                    });
                    panel.revalidate();
                    panel.repaint();
                } else if (buttonName != null) {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "商品名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });    

  //   JPanel panel = new JPanel(new GridBagLayout());

  //   GridBagConstraints gbc = new GridBagConstraints();
  //   gbc.insets = new Insets(10, 10, 10, 10);

  //   JLabel label = new JLabel("商品列表");
  //   label.setFont(new Font("Serif", Font.PLAIN, 28));
  //   panel.add(label, gbc);

  //   ArrayList<Goods> goods = new ArrayList<>();
  //   for (int i = 0; i < 20; i++)
  //     goods.add(new Goods());

  //   int x = 0;
  //   int y = 1; // 从第二行开始，因为第一行有标题
  //   for (Goods currentGoods : goods) {
  //     addGoodsToPanel(panel, gbc, currentGoods, x, y);
  //     y += 5; // 每个商品占用五行
  //     if (y > 10) {
  //       x++;
  //       y = 1; // 重置为第二行
  //     }
  //   }

    return panel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();

      if ("Store Menu".equals(command)) {
          DataStore.showStoreMenu(command);
      } else if("Main Menu".equals(command)){
          DataStore.showMainMenu(command);
      }
  }
  // public static void addGoodsToPanel(JPanel panel, GridBagConstraints gbc, Goods currentGoods, int x, int y) {
  //   JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
  //   nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
  //   gbc.gridx = x;
  //   gbc.gridy = y;
  //   panel.add(nameLabel, gbc);

  //   for (Material material : currentGoods.materials) {
  //     JLabel materialLabel = new JLabel("材料：" + material.toString()); // 或使用 material.name
  //     materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
  //     gbc.gridy = ++y;
  //     panel.add(materialLabel, gbc);
  //   }

  //   JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
  //   numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
  //   gbc.gridy = ++y;
  //   panel.add(numberLabel, gbc);

  //   JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
  //   priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
  //   gbc.gridy = ++y;
  //   panel.add(priceLabel, gbc);

  //   JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
  //   costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
  //   gbc.gridy = ++y;
  //   panel.add(costLabel, gbc);
  // }
}
