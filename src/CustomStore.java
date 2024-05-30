package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomStore {
    private static void createAndShowGUI() {
        DataStore.CustomStorePage = new JFrame("選擇、新增店家");
        DataStore.CustomStorePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DataStore.CustomStorePage.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 主容器面板
        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 30));
        // DataStore.CustomStorePage.setLayout(new FlowLayout(FlowLayout.LEFT, 150,
        // 50));

        JButton addButton = createCustomButton("新增店家");
        DataStore.CustomStorePage.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.CustomStorePage, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !DataStore.StoresName.contains(buttonName)) {
                    DataStore.Store store = new DataStore.Store();
                    store.ButtonTrigger = createCustomButton(buttonName);
                    store.StoreName = buttonName;
                    DataStore.StoresName.add(store.StoreName);
                    DataStore.Stores.put(buttonName, store);
                    DataStore.CustomStorePage.add(store.ButtonTrigger);
                    store.ButtonTrigger.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton clickedButton = (JButton) e.getSource();
                            DataStore.CustomStorePage.setVisible(false);
                            DataStore.StorePage.setTitle(clickedButton.getText());
                            DataStore.StorePage.setVisible(true);
                        }
                    });
                    DataStore.CustomStorePage.revalidate();
                    DataStore.CustomStorePage.repaint();
                } else if (buttonName == null) {
                } else {
                    JOptionPane.showMessageDialog(DataStore.CustomStorePage, "店面名稱不能空白或重複", "錯誤",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        DataStore.CustomStorePage.setVisible(true);
    }

    private static JButton createCustomButton(String text) {
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

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
