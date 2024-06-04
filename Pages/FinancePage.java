//the look of finance page
package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.Data;

import src.DataStore;

public class FinancePage implements ActionListener {
    public static JPanel createFinanceReportPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a new panel for the title and center it
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("財務報表頁面");
        titleLabel.setFont(new Font("新宋体", Font.BOLD, 40));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(30, 0, 20, 0)); // Adding padding
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        FinancePage financePage = new FinancePage();

        // center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 50, 0, 50)); // 上, 左, 下, 右邊距
        centerPanel.setPreferredSize(new Dimension(600, 0)); // 設定 centerPanel 的寬度

        // Adding table
        String[] columnNames = { "日期", "項目", "收支" };
        Object[][] data = DataStore.Stores.get(DataStore.MainFrame.getTitle()).FinanceTableObject;
        DefaultTableModel model = new DefaultTableModel(data, columnNames);   
        JTable table = DataStore.Stores.get(DataStore.MainFrame.getTitle()).FinanceTable;
        if (table == null) {
            table = new JTable(model);
            DataStore.Stores.get(DataStore.MainFrame.getTitle()).FinanceTable = table;
        }

        // Set table font
        Font tableFont = new Font("Serif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.setRowHeight(50); // Set row height to ensure text fits

        // Set header font
        JTableHeader tableHeader = table.getTableHeader();
        Font headerFont = new Font("Serif", Font.BOLD, 20);
        tableHeader.setFont(headerFont);

        // Set table size
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setMinimumSize(new Dimension(300, 100));
        table.setName("當日報表");
        table.setBackground(Color.DARK_GRAY);
        table.setForeground(Color.WHITE);

        table.setIntercellSpacing(new Dimension(30, 0));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // at the bottom
        JButton storeMenuButton = DataStore.createBackButton("返回", 130);
        storeMenuButton.addActionListener(financePage);
        storeMenuButton.setActionCommand("Store Menu");

        JPanel bottomPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanelContainer.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adding padding
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(300, 100));
        bottomPanelContainer.add(bottomPanel);

        bottomPanel.add(storeMenuButton);
        mainPanel.add(bottomPanelContainer, BorderLayout.PAGE_END);

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Store Menu".equals(command)) {
            DataStore.showStoreMenu(DataStore.MainFrame.getTitle());
        }
    }
}
