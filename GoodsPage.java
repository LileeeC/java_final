import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GoodsPage {
  private static void createAndShowGUI() { // 確保一個漂亮的外觀風格JFrame . setDefaultLookAndFeelDecorated ( true );
    // 建立及設定視窗
    JFrame frame = new JFrame("店面經營系統");
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // frame.setLayout(null);
    frame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // 设置内边距
    

    JLabel label = new JLabel("商品列表");
    label.setBounds(50, 50, 200, 30);
    label.setFont(new Font("Serif", Font.PLAIN, 28));
    frame.getContentPane().add(label);

    ArrayList<Goods> goods = new ArrayList<>();
    for (int i = 0; i < 20; i++)
      goods.add(new Goods());
    int x = 0;
    int y = 0;
    for (int i = 0; i < goods.size(); i++) {
      Goods currentGoods = goods.get(i);

      // 商品名称
      JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
      nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(nameLabel, gbc);
      y++;

      // 商品材料
      for (int j = 0; j < currentGoods.materials.size(); j++) {
        JLabel materialLabel = new JLabel("材料：" + currentGoods.materials.get(j));
        materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridx = x;
        gbc.gridy = y;
        frame.add(materialLabel, gbc);
        y++;
      }

      // 商品数量
      JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
      numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(numberLabel, gbc);
      y++;

      // 商品价格
      JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
      priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(priceLabel, gbc);
      y++;

      // 商品成本
      JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
      costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
      gbc.gridx = x;
      gbc.gridy = y;
      frame.add(costLabel, gbc);
      y++;

      // 如果达到最大行数，换到下一列
      if (y > 10) {
        x++;
        y = 0;
      }
    }

    frame.setVisible(true);
  }

  public static void main(String[] args) { // 顯示應用程式GUI
    javax.swing.SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            createAndShowGUI();
          }
        });
  }
}
