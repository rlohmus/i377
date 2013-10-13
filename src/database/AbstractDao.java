package database;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public abstract class AbstractDao {

    public static final String DB_URL = "jdbc:hsqldb:file:${user.home}/data/rlohmus/db;shutdown=true;";

    private Connection connection;
    protected PreparedStatement prepdStatement;
    protected Statement statement;
    protected ResultSet resultSet;

    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, "sa", "");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeResources() {
        DbUtils.closeQuietly(resultSet);
        DbUtils.closeQuietly(prepdStatement);
        DbUtils.closeQuietly(statement);
        DbUtils.closeQuietly(connection);
    }

    protected void executeQuery(String queryString) {
        try {
             statement = getConnection().createStatement();
             statement.executeUpdate(queryString);
         } catch (Exception e) {
             throw new RuntimeException(e);
         } finally {
             closeResources();
         }
    }

}