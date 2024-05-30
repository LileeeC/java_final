package src;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class DataStore {
    public static JFrame CustomStorePage;
    public static JFrame StorePage;
    public static Set<String> StoresName = new HashSet<>();

    public static void main(String[] args) {
        CustomStore.main(args);
    }

    public static class Store {

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
