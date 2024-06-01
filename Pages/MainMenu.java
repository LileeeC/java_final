//the look of main menu
package Pages;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CommonClass.Store;
import src.*;

public class MainMenu {
    public static JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 150, 100));

        JButton addStoreButton = src.DataStore.createCustomButton("新增店家");
        panel.add(addStoreButton);

        addStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !DataStore.StoresName.contains(buttonName)) {
                    Store store = new Store();
                    store.ButtonTrigger = DataStore.createCustomButton(buttonName);
                    store.StoreName = buttonName;
                    DataStore.StoresName.add(store.StoreName);
                    DataStore.Stores.put(buttonName, store);
                    panel.add(store.ButtonTrigger);
                    store.ButtonTrigger.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton clickedButton = (JButton) e.getSource();
                            DataStore.showStoreMenu(clickedButton.getText());
                        }
                    });
                    panel.revalidate();
                    panel.repaint();
                } else if (buttonName != null) {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "店面名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }
}
