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

        JButton addButton = DataStore.createCustomButton("新增店家");
        DataStore.CustomStorePage.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.CustomStorePage, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !DataStore.StoresName.contains(buttonName)) {
                    DataStore.Store store = new DataStore.Store();
                    store.ButtonTrigger = DataStore.createCustomButton(buttonName);
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

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
