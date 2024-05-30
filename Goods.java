import java.util.ArrayList;
import java.util.Arrays;

public class Goods {
  public String name;
  public ArrayList<String> materials;
  public int number;
  public int price;
  public int cost;

  Goods() {
    name = "商品";
    materials = new ArrayList<>(Arrays.asList("蘋果", "香蕉"));
    number = 0;
    price = 0;
    cost = 0;
  }

  Goods(String name, ArrayList<String> materials, int number, int price, int cost) {
    this.name = name;
    this.materials = materials;
    this.number = number;
    this.price = price;
    this.cost = cost;
  }
}
