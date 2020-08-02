package sample;

import java.sql.*;

public class DatabaseConnection {
    private String databaseName = "//User.accdb";
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    public DatabaseConnection() {
        connectToDB();
    }

    public DatabaseConnection(String databaseName) {
        setDatabaseName(databaseName);
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public Statement getStatement() {
        return statement;
    }

    public void connectToDB() {
        try {
            System.out.println("\nConnecting to Database : " + databaseName + "\n");
            connection = DriverManager.getConnection("jdbc:ucanaccess:" + databaseName);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public ResultSet getData(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    connection.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Retrieving data .........\n");
        return this.resultSet;
    }
    public void storeData(String query) {
        try {
            Statement statement = null;
            connectToDB();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data stored successfully .........\n");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateData(String query) {
        try {
            Statement statement = null;
            connectToDB();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated successfully .........\n");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteData(String query) {
        try {
            Statement statement = null;
            connectToDB();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data deleted successfully .........\n");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
