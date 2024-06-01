//the look of finance page
package Pages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.DataStore;

public class FinancePage implements ActionListener {
    public static JPanel createFinanceReportPanel() {
        JPanel panel = new JPanel();
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
            DataStore.showStoreMenu(command);
        }
    }
}
