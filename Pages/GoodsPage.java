package Pages;

import javax.swing.*;

import CommonClass.Goods;
import CommonClass.Material;

import java.awt.*;
import java.util.ArrayList;

public class GoodsPage {
  public static JFrame frame;

  public static JPanel createGoodsPagePanel() {
    JPanel panel = new JPanel(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    JLabel label = new JLabel("商品列表");
    label.setFont(new Font("Serif", Font.PLAIN, 28));
    panel.add(label, gbc);

    ArrayList<Goods> goods = new ArrayList<>();
    for (int i = 0; i < 20; i++)
      goods.add(new Goods());

    int x = 0;
    int y = 1; // 从第二行开始，因为第一行有标题
    for (Goods currentGoods : goods) {
      addGoodsToPanel(panel, gbc, currentGoods, x, y);
      y += 5; // 每个商品占用五行

      // goods name
      JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
      nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(nameLabel, gbc);
      y++;

      // goods materials
      for (int j = 0; j < currentGoods.materials.size(); j++) {
        JLabel materialLabel = new JLabel(
            "材料：" + currentGoods.materials.get(j).name + " 所需數量：" + currentGoods.materials.get(j).number);
        materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridx = x;
        gbc.gridy = y;
        frame.add(materialLabel, gbc);
        y++;
      }

      // goods number
      JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
      numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(numberLabel, gbc);
      y++;

      // goods price
      JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
      priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(priceLabel, gbc);
      y++;

      // goods cost
      JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
      costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(costLabel, gbc);
      y++;

      if (y > 10) {
        x++;
        y = 1; // 重置为第二行
      }
    }

    return panel;
  }

  public static void addGoodsToPanel(JPanel panel, GridBagConstraints gbc, Goods currentGoods, int x, int y) {
    JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
    nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    gbc.gridx = x;
    gbc.gridy = y;
    panel.add(nameLabel, gbc);

    for (Material material : currentGoods.materials) {
      JLabel materialLabel = new JLabel("材料：" + material.toString()); // 或使用 material.name
      materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridy = ++y;
      panel.add(materialLabel, gbc);
    }

    JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
    numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    gbc.gridy = ++y;
    panel.add(numberLabel, gbc);

    JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
    priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    gbc.gridy = ++y;
    panel.add(priceLabel, gbc);

    JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
    costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
    gbc.gridy = ++y;
    panel.add(costLabel, gbc);
  }
}
