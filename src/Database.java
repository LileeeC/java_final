package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CommonClass.Store;

public class Database {
  static String url = "jdbc:mysql://localhost:3306/store_management_system";
  static String user = "root";
  static String password = "javafinalproject";

  static Connection conn = null;
  static PreparedStatement pstmt = null;
  static ResultSet rs = null;

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
        // JOptionPane.showMessageDialog(DataStore.MainFrame, "新增成功", sql,
        // JOptionPane.ERROR_MESSAGE);
      } else {
        System.out.println("新增失敗！");
        // JOptionPane.showMessageDialog(DataStore.MainFrame, "新增失敗", sql,
        // JOptionPane.ERROR_MESSAGE);
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

  public static void getStoreList() {
    try {
      // connect
      conn = DriverManager.getConnection(url, user, password);

      String sql = "SELECT * FROM store_management_system.store;";

      pstmt = conn.prepareStatement(sql);

      // execute
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        Database.insertStore(name);
        Store store = new Store();
        store.ButtonTrigger = DataStore.createCustomButton(name);
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
}