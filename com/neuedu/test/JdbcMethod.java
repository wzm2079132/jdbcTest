package com.neuedu.test;

import com.mysql.jdbc.Driver;

import java.sql.SQLException;

public class JdbcMethod {
    static {
        try {
            new Driver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void executeUpdate(){
        
    }

}
