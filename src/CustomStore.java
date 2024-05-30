package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomStore {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("選擇、新增店家");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton addButton = createCustomButton("新增店家");
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(frame, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty()) {
                    JButton newButton = createCustomButton(buttonName);
                    frame.add(newButton);

                    // 刷新 JFrame 以显示新按钮
                    frame.revalidate();
                    frame.repaint();
                } else if (buttonName == null) {
                    // 用户点击了取消，什么都不做
                } else {
                    // 用户输入了空值，显示错误信息
                    JOptionPane.showMessageDialog(frame, "店面名稱不能空白", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 显示 JFrame
        frame.setVisible(true);
    }

    private static JButton createCustomButton(String text) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(150, 50));

        button.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        button.setForeground(Color.WHITE);

        button.setBackground(new Color(59, 89, 182));

        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(59, 89, 182), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(98, 128, 234));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(59, 89, 182));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
