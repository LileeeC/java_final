//attributes of store
package CommonClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Pages.*;

public class Store {
    public JPanel GoodsPanel = new JPanel(), PointsPanel = new JPanel(), GoodsPanelParent = null, PointsPanelParent = null, FinancePanelParent = null;
    public JTable FinanceTable = null;
    public Set<String> AllGoodsName = new HashSet<>();
    public Map<String, Goods> GoodsList = new HashMap<>();
    public String name;
    public JButton ButtonTrigger;
    // String代表庫存點名稱，不同名稱對應不同InventoryPoint(庫存點)
    public Map<String, InventoryPoint> InventoryPointMap = new HashMap<>();
    public Object FinanceTableObject[][] = {};

    public Store() {
        GoodsPanelParent = GoodsPage.createGoodsPagePanel();
        PointsPanelParent = InventoryPage.createInventoryPagePanel();
        GoodsPanel = (JPanel)((JScrollPane)GoodsPanelParent.getComponent(1)).getViewport().getView();
        PointsPanel = (JPanel)((JScrollPane)PointsPanelParent.getComponent(1)).getViewport().getView();
    }

    public Object[][] addRow(Object[][] original, Object[] newRow) {
        Object[][] newArray = new Object[original.length + 1][];
        
        for (int i = 0; i < original.length; i++) {
            newArray[i] = original[i];
        }
        
        newArray[original.length] = newRow;
        
        return newArray;
    }
}
