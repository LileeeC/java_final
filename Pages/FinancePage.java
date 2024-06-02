//the look of finance page
package Pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import src.DataStore;

public class FinancePage implements ActionListener {
    public static JPanel createFinanceReportPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a new panel for the title and center it
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("財務報表頁面");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(20, 0, 10, 0)); // Adding padding
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        FinancePage financePage = new FinancePage();

        // Create a new panel for the bottom part
        JPanel bottomPanelContainer = new JPanel(new BorderLayout());
        // bottomPanelContainer.setLayout(new BoxLayout(bottomPanelContainer, BoxLayout.Y_AXIS));
        bottomPanelContainer.setBorder(new EmptyBorder(10, 0, 10, 0)); // Adding padding

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setPreferredSize(new Dimension(300, 60)); // 設定 bottomPanel 的大小
        
        JButton storeMenuButton = DataStore.createBackButton("返回");
        storeMenuButton.addActionListener(financePage);
        storeMenuButton.setActionCommand("Store Menu");
        bottomPanel.add(storeMenuButton);
        bottomPanelContainer.add(bottomPanel);

        mainPanel.add(bottomPanelContainer, BorderLayout.PAGE_END);

        // Create a center panel with a specific width
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 50, 0, 50)); // 上, 左, 下, 右邊距
        centerPanel.setPreferredSize(new Dimension(600, 0)); // 設定 centerPanel 的寬度

        // Adding table
        String[] columnNames = {"日期", "項目", "收支"};
        Object[][] data = {
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"},
            {"20240602", "珍珠", "1000"},
            {"20240602", "珍珠", "-500"},
            {"20240602", "仙草", "1500"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        
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
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

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
