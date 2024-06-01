//the look of finance page
package Pages;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinancePage {
    public static JPanel createFinanceReportPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("財務報表頁面"));
        return panel;
    }
}
