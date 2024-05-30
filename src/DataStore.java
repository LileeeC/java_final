package src;

import java.awt.Button;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DataStore {
    public static JFrame CustomStorePage;
    public static JFrame StorePage;
    public static Set<String> StoresName = new HashSet<>();
    public static Map<String, Store> Stores = new HashMap();

    private DataStore() {
        StorePage = new JFrame();
        StorePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StorePage.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    public static class Goods {
        public Material[] materials;
        public int prices;
    }

    public static class Material {
        public String name;
        public float number;
    }
}
