//the look of finance page
package Pages;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.DataStore;

public class FinancePage implements ActionListener {
    public static JPanel createFinanceReportPanel() {
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
        panel.add(new JLabel("財務報表頁面"));

        FinancePage financePage = new FinancePage();

        JButton storeMenuButton = DataStore.createCustomButton("葉志嘉說返回上一頁");
        storeMenuButton.addActionListener(financePage);
        storeMenuButton.setActionCommand("Store Menu");
        panel.add(storeMenuButton);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(DataStore.MainFrame.getTitle());
        }
    }
}
