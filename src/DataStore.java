package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import CommonClass.*;

public class DataStore {
    public static JFrame CustomStorePage;
    public static JFrame StorePage;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap();

    private DataStore() {
        StorePage = new JFrame();
        StorePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StorePage.setExtendedState(JFrame.MAXIMIZED_BOTH);

        StorePage.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton addButton = createCustomButton("剩餘商品");
        StorePage.add(addButton);
        addButton = createCustomButton("材料庫存");
        StorePage.add(addButton);
        addButton = createCustomButton("財務報表");
        StorePage.add(addButton);
        
        StorePage.revalidate();
        StorePage.repaint();

        StorePage.setVisible(false);
        CustomStore.main(null);
    }

    public static void main(String[] args) {
        new DataStore();
    }

    public static class Store {
        public String StoreName;
        public JButton ButtonTrigger;
        public Goods[] GoodsList;
    }

    public static JButton createCustomButton(String text) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(300, 200));

        button.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        button.setForeground(Color.WHITE);

        button.setBackground(new Color(85, 74, 130));

        button.setBorderPainted(true);
        button.setFocusPainted(true);

        // 設定按鈕的邊框為複合邊框，外層為藍色實線邊框，內層為空邊框
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 129, 184), 5),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        // 為按鈕添加一個鼠標事件監聽器，當鼠標進入按鈕區域時改變背景色
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(192, 184, 224));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(85, 74, 130));
            }
        });

        return button;
    }
}
