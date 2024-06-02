//attributes of store
package CommonClass;

import javax.swing.JButton;
import javax.swing.JPanel;
import Pages.GoodsPage;

public class Store {
    public JPanel StorePanel = new JPanel();
    public Goods[] GoodsList;
    public String name;
    public JButton ButtonTrigger;

    public Store() {
        StorePanel = GoodsPage.createGoodsPagePanel();
    }
}
