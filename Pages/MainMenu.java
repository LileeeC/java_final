//the look of main menu
package Pages;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import CommonClass.Store;
import src.*;

public class MainMenu {
    public static JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 150, 100)) {
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
                        if (rowWidth + compSize.width + 150 > parentWidth) {
                            width = Math.max(width, rowWidth);
                            height += rowHeight + 100; // 10 is the vertical gap
                            rowWidth = compSize.width;
                            rowHeight = compSize.height;
                        } else {
                            rowWidth += compSize.width + 150; // 10 is the horizontal gap
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

        JButton addStoreButton = src.DataStore.createCustomButton("新增店家");
        panel.add(addStoreButton);

        addStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(DataStore.MainFrame, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !DataStore.StoresName.contains(buttonName)) {
                    Database.insertStore(buttonName);
                    Store store = new Store();
                    JButton ButtonTrigger = DataStore.createCustomButton(buttonName);
                    store.ButtonTrigger = ButtonTrigger;
                    DataStore.StoresName.add(buttonName);
                    DataStore.Stores.put(buttonName, store);
                    panel.add(ButtonTrigger);
                    ButtonTrigger.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton clickedButton = (JButton) e.getSource();
                            DataStore.showStoreMenu(clickedButton.getText());
                            DataStore.Stores.put(buttonName, new Store());
                        }
                    });
                    panel.revalidate();
                    panel.repaint();
                } else if (buttonName != null) {
                    JOptionPane.showMessageDialog(DataStore.MainFrame, "店面名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // get initial store list
        Database.getStoreList();
        for (Map.Entry<String, Store> entry : DataStore.Stores.entrySet()) {
            Store store = entry.getValue();
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
        }

        return panel;
    }
}
