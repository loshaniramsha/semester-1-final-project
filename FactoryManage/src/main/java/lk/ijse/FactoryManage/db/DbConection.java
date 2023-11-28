package lk.ijse.FactoryManage.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConection {
    private static DbConection dbConection;
    private Connection connection;
    private DbConection() throws Exception {
    connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/fms","root","loshani@123");
    }
    public static DbConection getInstance() throws Exception {
        if(dbConection==null){
            dbConection=new DbConection();
        }
        return dbConection;
    }
    public Connection getConnection() {
        return connection;
    }

    }

