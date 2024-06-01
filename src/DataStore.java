//執行動作，以及放大家共同擁有的物件設定
package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
import javax.swing.*;
import CommonClass.*;
import Pages.*;

public class DataStore {
    public static JFrame MainFrame;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap<>();
    public static Set<String> GoodsName = new HashSet<>();
    public static Map<String, Goods> Goods = new HashMap<>();

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
        //MainFrame.setBounds(0, 0, 300, (int)Double.POSITIVE_INFINITY);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //scroll bar
        JScrollPane scroll = new JScrollPane(cardPanel);       
        scroll.setLayout(new ScrollPaneLayout());                                 
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // 取viewport的大小用來設定
                Dimension size = scroll.getViewport().getSize();
                cardPanel.setPreferredSize(new Dimension(size.width, 8000));
                cardPanel.revalidate();
            }
        });
        scroll.setEnabled(true);
        //DataStore.MainFrame.getContentPane().add(scroll, BorderLayout.CENTER);
        DataStore.MainFrame.add(scroll, BorderLayout.CENTER);
        scroll.setVisible(true);

        // Add initial page's button
        mainMenuPanel = MainMenu.createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "Main Menu");
        mainMenuPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize other panels but don't add them yet
        storePagePanel = StorePage.createStoreMenuPanel();
        goodsPagePanel = GoodsPage.createGoodsPagePanel();
        inventoryPagePanel = InventoryPage.createInventoryPagePanel();
        financeReportPanel = FinancePage.createFinanceReportPanel();

        cardPanel.add(storePagePanel, "Store Menu");
        cardPanel.add(goodsPagePanel, "Goods Page");
        cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardPanel.add(financeReportPanel, "Finance Report");
        
        storePagePanel.setBackground(Color.LIGHT_GRAY);
        goodsPagePanel.setBackground(Color.LIGHT_GRAY);
        inventoryPagePanel.setBackground(Color.LIGHT_GRAY);
        financeReportPanel.setBackground(Color.LIGHT_GRAY);
        
        //MainFrame.add(cardPanel, BorderLayout.CENTER);
        MainFrame.setVisible(true);
        MainFrame.revalidate();
        MainFrame.repaint();
    }

    public static void main(String[] args) {
        new DataStore();
    }

    public static void showMainMenu(String storeName) {
        // cardPanel.add(mainMenuPanel, "Main Menu");
        cardLayout.show(cardPanel, "Main Menu");
    }

    public static void showStoreMenu(String storeName) {
        MainFrame.setTitle(storeName);
        // cardPanel.add(storePagePanel, "Store Menu");
        cardLayout.show(cardPanel, "Store Menu");
    }

    public static void showGoodsPage() {
        // cardPanel.add(goodsPagePanel, "Goods Page");
        cardLayout.show(cardPanel, "Goods Page");
    }

    public static void showInventoryPage() {
        // cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardLayout.show(cardPanel, "Inventory Page");
    }

    public static void showFinanceReportPage() {
        // cardPanel.add(financeReportPanel, "Finance Report");
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

    //設定其他的createCustomButton
}
