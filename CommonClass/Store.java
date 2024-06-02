//attributes of store
package CommonClass;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import Pages.GoodsPage;
import Pages.InventoryPage;

public class Store {
    public JPanel StorePanel = new JPanel(), PointsPanel = new JPanel();
    public Set<String> AllGoodsName = new HashSet<>();
    public Map<String, Goods> GoodsList = new HashMap<>();
    public String name;
    public JButton ButtonTrigger;
    //String代表庫存點名稱，不同名稱對應不同InventoryPoint(庫存點)
    public Map<String, InventoryPoint> InventoryPointMap = new HashMap<>();

    public Store() {
        StorePanel = GoodsPage.createGoodsPagePanel();
        PointsPanel = InventoryPage.createInventoryPagePanel();
    }
}
