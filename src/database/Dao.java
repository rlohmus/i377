package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Unit;

public class Dao extends AbstractDao {
   
   public boolean deleteAll() throws SQLException {
      try {
         statement = getConnection().createStatement();
         resultSet = statement.executeQuery("DELETE FROM unit");
      }
      finally {
         closeResources();
      }
      return true;
   }
   
   public boolean deleteUnit(int id) throws SQLException {
      try {
         statement = getConnection().createStatement();
         resultSet = statement.executeQuery("DELETE FROM unit WHERE id = " + id);
      }
      finally {
         closeResources();
      }
      return true;
   }
   
   public boolean addUnit(String name, String code) throws SQLException {
      try {
         statement = getConnection().createStatement();
         resultSet = statement.executeQuery("INSERT INTO unit VALUES (NEXT VALUE FOR seq1), '" + name + "', '" + code + "'");
      }
      finally {
         closeResources();
      }
      return true;
   }
   
   public List<Unit> search(String keyword) throws SQLException{
      List<Unit> units = new ArrayList<Unit>();
      try {
         statement = getConnection().createStatement();
         resultSet = statement.executeQuery("SELECT * FROM unit WHERE LCASE(name) LIKE '%" + keyword.toLowerCase() + "%'");
         while(resultSet.next()) {
            Unit unit = new Unit();
            unit.setId(resultSet.getInt("id"));
            unit.setName(resultSet.getString("name"));
            unit.setCode(resultSet.getString("code"));
            units.add(unit);
         }
      }
      finally {
         closeResources();
      }
      return units;
   }
   
   public List<Unit> findAllUnits() throws SQLException {
      List<Unit> units = new ArrayList<Unit>();
      try {
         statement = getConnection().createStatement();
         resultSet = statement.executeQuery("SELECT * FROM unit");
         while(resultSet.next()) {
            Unit unit = new Unit();
            unit.setId(Integer.parseInt(resultSet.getString("id")));
            unit.setName(resultSet.getString("name"));
            unit.setCode(resultSet.getString("code"));
            units.add(unit);
         }
      } 
      finally {
         closeResources();
      }
      return units;
   }

}