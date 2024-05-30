import java.util.ArrayList;

public class Goods {
  public ArrayList<String> materials;
  public int number;
  public int price;
  public int cost;

  Goods() {
    materials = new ArrayList<>();
    number = 0;
    price = 0;
    cost = 0;
  }

  Goods(ArrayList<String> materials, int number, int price, int cost) {
    this.materials = materials;
    this.number = number;
    this.price = price;
    this.cost = cost;
  }
}
