//執行動作，以及放大家共同擁有的物件設定
package src;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import CommonClass.*;
import Pages.*;

public class DataStore {
    public static JFrame MainFrame;
    public static Set<String> StoresName = new HashSet<>();
    public static Set<String> InventoryPointName = new HashSet<>();
    public static Set<String> InventoryItemName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap<>();
    public static Set<String> GoodsName = new HashSet<>();
    public static Map<String, Goods> Goods = new HashMap<>();
    public static Map<String, InventoryPoint> InventoryPoint = new HashMap<>();
    public static Map<String, InventoryItem> InventoryItem = new HashMap<>();

    private static CardLayout cardLayout;
    public static JPanel cardPanel;
    public static JPanel mainMenuPanel;
    public static JPanel goodsPagePanel;
    public static JPanel inventoryPagePanel;
    public static JPanel financeReportPanel;
    public static JPanel storePagePanel;
    public static JPanel inventoryItemPagePanel;

    private DataStore() {
        MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainFrame.setLayout(new BorderLayout());
        MainFrame.setTitle("商店經營系統");
        // MainFrame.setBounds(0, 0, 300, (int)Double.POSITIVE_INFINITY);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // scroll bar
        JScrollPane scroll = new JScrollPane(cardPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        DataStore.MainFrame.add(scroll, BorderLayout.CENTER);

        // Add initial page's button
        mainMenuPanel = MainMenu.createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "Main Menu");
        mainMenuPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize other panels but don't add them yet
        storePagePanel = StorePage.createStoreMenuPanel();
        goodsPagePanel = null;
        inventoryPagePanel = InventoryPage.createInventoryPagePanel();
        financeReportPanel = FinancePage.createFinanceReportPanel();
        inventoryItemPagePanel = InventoryItemPage.createInventoryItemPagePanel();

        cardPanel.add(storePagePanel, "Store Menu");
        cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardPanel.add(financeReportPanel, "Finance Report");
        cardPanel.add(inventoryItemPagePanel, "Inventory Item Page");

        storePagePanel.setBackground(Color.LIGHT_GRAY);
        inventoryPagePanel.setBackground(Color.LIGHT_GRAY);
        financeReportPanel.setBackground(Color.LIGHT_GRAY);
        inventoryItemPagePanel.setBackground(Color.LIGHT_GRAY);

        // MainFrame.add(cardPanel, BorderLayout.CENTER);
        MainFrame.setVisible(true);
        MainFrame.revalidate();
        MainFrame.repaint();
    }

    public static void main(String[] args) {
        new DataStore();
    }

    public static void showMainMenu(String storeName) {
        // cardPanel.add(mainMenuPanel, "Main Menu");
        MainFrame.setTitle("商店經營系統");
        cardLayout.show(cardPanel, "Main Menu");
    }

    public static void showStoreMenu(String storeName) {
        MainFrame.setTitle(storeName);
        // cardPanel.add(storePagePanel, "Store Menu");
        cardLayout.show(cardPanel, "Store Menu");
    }

    public static void showGoodsPage() {
        // cardPanel.add(goodsPagePanel, "Goods Page");
        if (goodsPagePanel != null) {
            cardPanel.remove(goodsPagePanel);
        }

        goodsPagePanel = Stores.get(MainFrame.getTitle()).StorePanel;
        goodsPagePanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.add(goodsPagePanel, "Goods Page");
        cardLayout.show(cardPanel, "Goods Page");
    }

    public static void showInventoryPage(String command) {
        // cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardLayout.show(cardPanel, "Inventory Page");
    }

    public static void showFinanceReportPage() {
        // cardPanel.add(financeReportPanel, "Finance Report");
        cardLayout.show(cardPanel, "Finance Report");
    }

    public static void showInventoryItemPage(String command) {
        // cardPanel.add(financeReportPanel, "Finance Report");
        cardLayout.show(cardPanel, "Inventory Item Page");
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

    // 設定其他的createCustomButton
}
