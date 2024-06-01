//attribute of goods
package CommonClass;

import java.util.ArrayList;

public class Goods {
  public String name;
  public ArrayList<Material> materials;
  public int number;
  public int price;
  public int cost;

  public Goods() {
    name = "商品";
    materials = new ArrayList<Material>();
    number = 0;
    price = 0;
    cost = 0;
  }

  Goods(String name, ArrayList<Material> materials, int number, int price, int cost) {
    this.name = name;
    this.materials = materials;
    this.number = number;
    this.price = price;
    this.cost = cost;
  }
}