package com.mygdx.game.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class SQLconnection {
    private static final String DATABASE_URL = "jdbc:postgresql://silly.db.elephantsql.com/zqlujwbj";
    private static final String USERNAME = "zqlujwbj";
    private static final String PASSWORD = "MopidgvMOHvdAiw8DdCv9MK5mUiTAA5_";

    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeath(int stageDied, float time) {
        connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO deathcounter (stage,time) VALUES (" + stageDied + ", " + time + ");");
            statement.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect();
        }
    }
}
