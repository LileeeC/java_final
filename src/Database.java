package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, name);
      pstmt.setString(2, store_id);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("新增成功！");

        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          String id = rs.getString(1);
          point.id = id;
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

  // create a new inventory item
  public static void insertInventoryItem(String name, int number, float cost, String point_id, InventoryItem item) {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "INSERT INTO inventory_item (name, number, cost, point_id) VALUES (?, ?, ?, ?)";

      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, name);
      pstmt.setInt(2, number);
      pstmt.setDouble(3, cost);
      pstmt.setString(4, point_id);

      // execute
      int rowsAffected = pstmt.executeUpdate();

      // check
      if (rowsAffected > 0) {
        System.out.println("新增成功！");

        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
          String id = rs.getString(1);
          item.id = id;
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
}