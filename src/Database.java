package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import CommonClass.InventoryItem;
import CommonClass.InventoryPoint;
import CommonClass.Store;

public class Database {
  static String url = "jdbc:mysql://localhost:3306/store_management_system";
  static String user = "root";
  static String password = "javafinalproject";

  static Connection conn = null;
  static PreparedStatement pstmt = null;
  static ResultSet rs = null;

  // create a new store
  public static void insertStore(String name) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "INSERT INTO store (name) VALUES (?)";

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, name);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("新增成功！");
      } else {
        System.out.println("新增失敗！");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // get store id
  public static String getStoreId(Store store) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT id FROM store_management_system.store WHERE name = ?";

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, store.name);

      // execute
      rs = pstmt.executeQuery();

      if (rs.next()) {
        String id = rs.getString("id");
        return id;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return "";
  }

  // get all the stores
  public static void getStoreList() {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT * FROM store_management_system.store;";

      pstmt = conn.prepareStatement(sql);

      // execute
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String id = rs.getString("id");
        String name = rs.getString("name");
        Store store = new Store();
        store.ButtonTrigger = DataStore.createCustomButton(name);
        store.id = id;
        store.name = name;
        DataStore.StoresName.add(store.name);
        DataStore.Stores.put(name, store);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // create a new inventory point
  public static void insertInventoryPoint(String name, String store_id, InventoryPoint point) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "INSERT INTO inventory_point (name, store_id) VALUES (?,?)";

      pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      pstmt.setString(1, name);
      pstmt.setString(2, store_id);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("新增成功！");

        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            String id = Long.toString(generatedKeys.getLong(1));
            point.id = id;
          }
        }
      } else {
        System.out.println("新增失敗！");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // get all the inventory point
  public static void getInventoryPointList(String store_id, Map<String, InventoryPoint> InventoryPointMap) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT * FROM store_management_system.inventory_point WHERE store_id = ?;";

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, store_id);

      // execute
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        InventoryPoint point = new InventoryPoint(name);
        point.id = rs.getString("id");
        point.store_id = rs.getString("store_id");
        point.ButtonTrigger.put(point.name, DataStore.createCustomButton(name));
        DataStore.Stores.get(DataStore.MainFrame.getTitle()).InventoryPointMap.put(name, point);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // create a new inventory item
  public static void insertInventoryItem(String name, float number, float cost, String point_id, InventoryItem item) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "INSERT INTO inventory_item (name, number, cost, point_id) VALUES (?, ?, ?, ?)";

      // Create the prepared statement with RETURN_GENERATED_KEYS
      pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      pstmt.setString(1, name);
      pstmt.setDouble(2, number);
      pstmt.setDouble(3, cost);
      pstmt.setString(4, point_id);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("新增成功！");

        // Retrieve the generated keys
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            String id = Long.toString(generatedKeys.getLong(1));
            item.id = id;
          }
        }
      } else {
        System.out.println("新增失敗！");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // update inventory item
  public static void updateInventoryItem(String name, float number, float cost, String id) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "UPDATE inventory_item SET name = ?, number = ?, cost = ? WHERE id = ?";

      // Create the prepared statement
      pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      pstmt.setString(1, name);
      pstmt.setDouble(2, number);
      pstmt.setDouble(3, cost);
      pstmt.setString(4, id);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("更新成功！");
      } else {
        System.out.println("更新失敗！");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  // get all the inventory item
  public static void getInventoryItemList(String point_id, Map<String, InventoryItem> InventoryItemMap) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT * FROM store_management_system.inventory_item WHERE point_id = ?;";

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, point_id);

      // execute
      rs = pstmt.executeQuery();

      while (rs.next()) {
        InventoryItem item = new InventoryItem();
        JPanel OuterPanel;
        OuterPanel = new JPanel();
        OuterPanel.setPreferredSize(new Dimension(400, 250));
        OuterPanel.setBackground(new Color(173, 216, 230));
        OuterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        OuterPanel.setLayout(new GridBagLayout());

        item.id = rs.getString("id");
        item.point_id = point_id;
        item.name = rs.getString("name");
        item.singleCost = rs.getFloat("cost");
        item.quantities = rs.getFloat("number");
        item.display = OuterPanel;

        InventoryItemMap.put(item.name, item);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}