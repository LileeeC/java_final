package CommonClass;

import java.util.HashMap;
import java.util.Map;

public class InventoryItem {
    public Map<String, Float> quantities;

    InventoryItem() {
        quantities = new HashMap<>();
    }
}
