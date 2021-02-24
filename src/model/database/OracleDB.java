package model.database;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleDB implements IDB{

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return null;
    }
}
