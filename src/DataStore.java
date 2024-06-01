//執行動作，以及放大家共同擁有的物件設定
package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import CommonClass.*;
import Pages.*;

public class DataStore{
    public static JFrame MainFrame;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap<>();

    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static JPanel mainMenuPanel;
    private static JPanel goodsPagePanel;
    private static JPanel inventoryPagePanel;
    private static JPanel financeReportPanel;
    private static JPanel storePagePanel;

    private DataStore() {
        MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainFrame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add initial page's button
        mainMenuPanel = MainMenu.createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "Main Menu");
        mainMenuPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize other panels but don't add them yet
        goodsPagePanel = GoodsPage.createGoodsPagePanel();
        inventoryPagePanel = InventoryPage.createInventoryPagePanel();
        financeReportPanel = FinancePage.createFinanceReportPanel();
        storePagePanel = StorePage.createStoreMenuPanel();

        MainFrame.add(cardPanel, BorderLayout.CENTER);
        MainFrame.setVisible(true);
        MainFrame.revalidate();
        MainFrame.repaint();
    }

    public static void main(String[] args) {
        new DataStore();
    }

    public static void showMainMenu(String storeName) {
        cardPanel.add(mainMenuPanel, "Main Menu");
        cardLayout.show(cardPanel, "Main Menu");
    }

    public static void showStoreMenu(String storeName) {
        MainFrame.setTitle(storeName);
        cardPanel.add(storePagePanel, "Store Menu");
        cardLayout.show(cardPanel, "Store Menu");
    }

    public static void showGoodsPage() {
        cardPanel.add(goodsPagePanel, "Goods Page");
        cardLayout.show(cardPanel, "Goods Page");
    }

    public static void showInventoryPage() {
        cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardLayout.show(cardPanel, "Inventory Page");
    }

    public static void showFinanceReportPage() {
        cardPanel.add(financeReportPanel, "Finance Report");
        cardLayout.show(cardPanel, "Finance Report");
    }

    public static JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 200));
        button.setFont(new Font("Microsoft YaHei", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(85, 74, 130));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 129, 184), 5),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

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
}
