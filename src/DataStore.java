package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import CommonClass.*;
import Pages.*;

public class DataStore implements ActionListener{
    public static JFrame MainFrame;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap();

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel mainMenuPanel;
    private JPanel goodsPagePanel;
    private JPanel inventoryPagePanel;
    private JPanel financeReportPanel;

    private DataStore() {
        MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MainFrame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add initial page's button
        mainMenuPanel = createMainMenuPanel();
        cardPanel.add(mainMenuPanel, "Main Menu");

        // Initialize other panels but don't add them yet
        goodsPagePanel = createGoodsPagePanel();
        inventoryPagePanel = createInventoryPagePanel();
        financeReportPanel = createFinanceReportPanel();

        MainFrame.add(cardPanel, BorderLayout.CENTER);
        MainFrame.setVisible(true);
        MainFrame.revalidate();
        MainFrame.repaint();
    }

    public static void main(String[] args) {
        new DataStore();
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 150, 50));

        JButton addStoreButton = createCustomButton("新增店家");
        panel.add(addStoreButton);

        addStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonName = JOptionPane.showInputDialog(MainFrame, "輸入店家名稱: ");

                if (buttonName != null && !buttonName.trim().isEmpty() && !StoresName.contains(buttonName)) {
                    Store store = new Store();
                    store.ButtonTrigger = createCustomButton(buttonName);
                    store.StoreName = buttonName;
                    StoresName.add(store.StoreName);
                    Stores.put(buttonName, store);
                    panel.add(store.ButtonTrigger);
                    store.ButtonTrigger.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton clickedButton = (JButton) e.getSource();
                            showStoreMenu(clickedButton.getText());
                        }
                    });
                    panel.revalidate();
                    panel.repaint();
                } else if (buttonName != null) {
                    JOptionPane.showMessageDialog(MainFrame, "店面名稱不能空白或重複", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    private JPanel createGoodsPagePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("商品列表");
        label.setFont(new Font("Serif", Font.PLAIN, 28));
        panel.add(label, gbc);

        ArrayList<Goods> goods = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            goods.add(new Goods());

        int x = 0;
        int y = 1; // 从第二行开始，因为第一行有标题
        for (Goods currentGoods : goods) {
            addGoodsToPanel(panel, gbc, currentGoods, x, y);
            y += 5; // 每个商品占用五行

            if (y > 10) { // 如果达到最大行数，换到下一列
                x++;
                y = 1; // 重置为第二行
            }
        }

        return panel;
    }

    private void addGoodsToPanel(JPanel panel, GridBagConstraints gbc, Goods currentGoods, int x, int y) {
        JLabel nameLabel = new JLabel("商品名稱：" + currentGoods.name);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(nameLabel, gbc);

        for (Material material : currentGoods.materials) {
            JLabel materialLabel = new JLabel("材料：" + material.toString()); // 或使用 material.name
            materialLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            gbc.gridy = ++y;
            panel.add(materialLabel, gbc);
        }

        JLabel numberLabel = new JLabel("數量：" + currentGoods.number);
        numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridy = ++y;
        panel.add(numberLabel, gbc);

        JLabel priceLabel = new JLabel("價格：" + currentGoods.price);
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridy = ++y;
        panel.add(priceLabel, gbc);

        JLabel costLabel = new JLabel("成本：" + currentGoods.cost);
        costLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        gbc.gridy = ++y;
        panel.add(costLabel, gbc);
    }

    private JPanel createInventoryPagePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("材料庫存頁面"));
        return panel;
    }

    private JPanel createFinanceReportPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("財務報表頁面"));
        return panel;
    }

    private JPanel createStoreMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton goodsPageButton = createCustomButton("剩餘商品");
        goodsPageButton.addActionListener(this);
        goodsPageButton.setActionCommand("Goods Page");

        JButton inventoryPageButton = createCustomButton("材料庫存");
        inventoryPageButton.addActionListener(this);
        inventoryPageButton.setActionCommand("Inventory Page");

        JButton financeReportButton = createCustomButton("財務報表");
        financeReportButton.addActionListener(this);
        financeReportButton.setActionCommand("Finance Report");

        panel.add(goodsPageButton);
        panel.add(inventoryPageButton);
        panel.add(financeReportButton);

        return panel;
    }

    private void showStoreMenu(String storeName) {
        MainFrame.setTitle(storeName);
        cardPanel.add(createStoreMenuPanel(), "Store Menu");
        cardLayout.show(cardPanel, "Store Menu");
    }

    private JButton createCustomButton(String text) {
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

    private void showGoodsPage() {
        cardPanel.add(goodsPagePanel, "Goods Page");
        cardLayout.show(cardPanel, "Goods Page");
    }

    private void showInventoryPage() {
        cardPanel.add(inventoryPagePanel, "Inventory Page");
        cardLayout.show(cardPanel, "Inventory Page");
    }

    private void showFinanceReportPage() {
        cardPanel.add(financeReportPanel, "Finance Report");
        cardLayout.show(cardPanel, "Finance Report");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("Goods Page".equals(command)) {
            showGoodsPage();
        } else if ("Inventory Page".equals(command)) {
            showInventoryPage();
        } else if ("Finance Report".equals(command)) {
            showFinanceReportPage();
        }
    }
}
