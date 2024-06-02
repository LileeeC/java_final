//attributes of store
package CommonClass;

import javax.swing.JPanel;
import Pages.GoodsPage;

public class Store {
    public JPanel StorePanel = new JPanel();
    public Goods[] GoodsList;

    public Store() {
        StorePanel = GoodsPage.createGoodsPagePanel();
    }
}
