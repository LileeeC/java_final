package src;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import CommonClass.*;

public class DataStore {
    public static JFrame CustomStorePage;
    public static JFrame StorePage;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap();

    private DataStore() {
        StorePage = new JFrame();
        StorePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StorePage.setExtendedState(JFrame.MAXIMIZED_BOTH);

        StorePage.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton addButton = CustomStore.createCustomButton("剩餘商品");
        StorePage.add(addButton);
        addButton = CustomStore.createCustomButton("材料庫存");
        StorePage.add(addButton);
        addButton = CustomStore.createCustomButton("財務報表");
        StorePage.add(addButton);
        
        StorePage.revalidate();
        StorePage.repaint();

        StorePage.setVisible(false);
        CustomStore.main(null);
    }

    public static void main(String[] args) {
        new DataStore();
    }

    public static class Store {
        public String StoreName;
        public JButton ButtonTrigger;
        public Goods[] GoodsList;
    }
}
