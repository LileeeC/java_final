//attribute of goods
package CommonClass;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Goods {
  public String id;
  public String store_id;
  public String name;
  public ArrayList<Material> materials;
  public int number;
  public int price;
  public float cost;
  public JButton ButtonTrigger;
  public Dialog Data;
  public JPanel display;
  public JLabel remainingLabel;

  public Goods() {
    name = "商品";
    materials = new ArrayList<Material>();
    number = 0;
    price = 0;
    cost = 0;
    Data = null;
    display = null;
  }

  Goods(String name, ArrayList<Material> materials, int number, int price, int cost) {
    this.name = name;
    this.materials = materials;
    this.number = number;
    this.price = price;
    this.cost = cost;
  }

  public int RemainCalculate(Store store) {
    Map<String, Float> materialTotals = new HashMap<>();
    for (Map.Entry<String, InventoryPoint> entry : store.InventoryPointMap.entrySet()) {
      InventoryPoint point = entry.getValue();
      for (Map.Entry<String, InventoryItem> itemEntry : point.items.entrySet()) {
        String itemName = itemEntry.getKey();
        InventoryItem item = itemEntry.getValue();
        materialTotals.put(itemName, materialTotals.getOrDefault(itemName, 0f) + item.quantities);
      }
    }

    int minGoods = (int) Float.MAX_VALUE;
    for (Material material : materials) {
      float totalAvailable = materialTotals.getOrDefault(material.name, 0f);
      int possibleGoods = (int) (totalAvailable / material.number);
      minGoods = Math.min(minGoods, possibleGoods);
    }

    return minGoods;
  }
}