//執行動作，以及放大家共同擁有的物件設定
package src;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CommonClass.*;
import Pages.*;

public class DataStore {
    public static JFrame MainFrame;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap<>();

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

        MainFrame.add(scroll, BorderLayout.CENTER);

        // Add initial page's button
        mainMenuPanel = MainMenu.createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "Main Menu");
        mainMenuPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize other panels but don't add them yet
        storePagePanel = StorePage.createStoreMenuPanel();
        goodsPagePanel = null;
        inventoryPagePanel = null;
        financeReportPanel = null;
        inventoryItemPagePanel = null;

        cardPanel.add(storePagePanel, "Store Menu");

        storePagePanel.setBackground(Color.LIGHT_GRAY);

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
        if (Stores.get(storeName).FinancePanelParent == null) {
            Stores.get(storeName).FinancePanelParent = FinancePage.createFinanceReportPanel();
        }
        cardLayout.show(cardPanel, "Store Menu");
    }

    public static void showGoodsPage() {
        // cardPanel.add(goodsPagePanel, "Goods Page");
        if (goodsPagePanel != null) {
            cardPanel.remove(goodsPagePanel);
        }

        goodsPagePanel = Stores.get(MainFrame.getTitle()).GoodsPanelParent;
        goodsPagePanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.add(goodsPagePanel, "Goods Page");
        cardLayout.show(cardPanel, "Goods Page");
    }

    public static Boolean isShowInventoryPage = false;

    public static void showInventoryPage(String command) {
        // cardPanel.add(inventoryPagePanel, "Inventory Page");
        if (inventoryPagePanel != null) {
            cardPanel.remove(inventoryPagePanel);
        }

        inventoryPagePanel = Stores.get(MainFrame.getTitle()).PointsPanelParent;
        inventoryPagePanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardLayout.show(cardPanel, "Inventory Page");

        if (!isShowInventoryPage) {
            isShowInventoryPage = true;
            JPanel panel = Stores.get(MainFrame.getTitle()).PointsPanel;
            // get initial inventory point list
            Database.getInventoryPointList(Stores.get(MainFrame.getTitle()).id,
                    Stores.get(MainFrame.getTitle()).InventoryPointMap);
            for (Map.Entry<String, InventoryPoint> entry : Stores
                    .get(MainFrame.getTitle()).InventoryPointMap.entrySet()) {
                InventoryPoint point = entry.getValue();
                System.out.println(point.name);
                point.ButtonTrigger.put(point.name, createCustomButton(point.name));
                Stores.get(MainFrame.getTitle()).InventoryPointMap.put(point.name, point);
                panel.add(point.ButtonTrigger.get(point.name));
                InventoryPage inventoryPage = new InventoryPage();
                point.ButtonTrigger.get(point.name).addActionListener(inventoryPage);
                point.ButtonTrigger.get(point.name).setActionCommand("Inventory Item Page");

                panel.revalidate();
                panel.repaint();
            }
        }
    }

    public static void showFinanceReportPage() {
        // cardPanel.add(financeReportPanel, "Finance Report");
        if (financeReportPanel != null) {
            cardPanel.remove(financeReportPanel);
        }

        String[] columnNames = { "日期", "項目", "收支" };
        Object[][] data = Stores.get(MainFrame.getTitle()).FinanceTableObject;
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        Stores.get(MainFrame.getTitle()).FinanceTable.setModel(model);
        financeReportPanel = Stores.get(MainFrame.getTitle()).FinancePanelParent;
        financeReportPanel.setBackground(Color.LIGHT_GRAY);
        cardPanel.add(financeReportPanel, "Finance Report");
        cardLayout.show(cardPanel, "Finance Report");
    }

    public static void showInventoryItemPage(String command, String buttonName) {
        // cardPanel.add(financeReportPanel, "Finance Report");
        if (inventoryItemPagePanel != null) {
            cardPanel.remove(inventoryItemPagePanel);
        }

        inventoryItemPagePanel = Stores.get(MainFrame.getTitle()).InventoryPointMap.get(buttonName).PointsPanelParent;
        cardPanel.add(inventoryItemPagePanel, "Inventory Item Page");
        cardLayout.show(cardPanel, "Inventory Item Page");

        JPanel panel = Stores.get(MainFrame.getTitle()).InventoryPointMap.get(buttonName).PointPanel;
        // get initial inventory item list
        Database.getInventoryItemList(Stores.get(MainFrame.getTitle()).id,
                Stores.get(MainFrame.getTitle()).InventoryPointMap.get(buttonName).items);
        for (Map.Entry<String, InventoryItem> entry : Stores
                .get(MainFrame.getTitle()).InventoryPointMap.get(buttonName).items.entrySet()) {
            InventoryItem item = entry.getValue();
            System.out.println(item.name);
        }
    }

    // 最常見的按鈕，表示可以再點進去
    public static JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 200));
        button.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(93, 55, 135));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 129, 184), 5),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(192, 184, 224));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(93, 55, 135));
            }
        });

        return button;
    }

    // 新增按鈕
    public static JButton createAddButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 200));
        button.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(24, 52, 89));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(61, 97, 145), 5),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(63, 101, 150));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(24, 52, 89));
            }
        });

        return button;
    }

    // 返回鍵按鈕，皆自訂寬度
    public static JButton createBackButton(String text, int width) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, 60));
        button.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(217, 180, 190));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212, 138, 158), 5),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(184, 37, 77));
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(217, 180, 190));
                button.setForeground(Color.BLACK);
            }
        });

        return button;
    }

    // 可自訂參數的按鈕
    public static JButton createCustomButton(String text, Dimension size, Color bgColor, Color textColor,
            Color hoverColor, Color borderColor, int borderWidth) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        button.setForeground(textColor);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, borderWidth),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }
}
