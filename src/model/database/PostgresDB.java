package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
Class is helping to connect to to the database
*/


public class PostgresDB implements IDB {

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:postgresql://localhost:5432/Assignment4";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionURL, "postgres", "123456");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
