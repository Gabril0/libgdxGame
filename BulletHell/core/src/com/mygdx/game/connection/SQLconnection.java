package com.mygdx.game.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class SQLconnection {
    //I know this is on github but please don't change the table, thank you
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

    public float getLowerTime() {
        connect();
        float lowerTime = Float.MAX_VALUE;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(time) FROM deathcounter WHERE stage = 4");

            if (resultSet.next()) {
                lowerTime = resultSet.getFloat(1);
            }

            resultSet.close();
            statement.close();
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            disconnect();
        }

        return lowerTime;
    }

}
