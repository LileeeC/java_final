//attributes of store
package CommonClass;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import Pages.GoodsPage;

public class Store {
    public JPanel StorePanel = new JPanel();
    public Set<String> AllGoodsName = new HashSet<>();
    public Goods[] GoodsList;
    public String name;
    public JButton ButtonTrigger;

    public Store() {
        StorePanel = GoodsPage.createGoodsPagePanel();
    }
}
