package com.neuedu.test;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcMethod {

    private static Connection conn;
    private static final String URL="jdbc:mysql://127.0.0.1:3306/jdbc?useUnicode=true&characterEncoding=utf8";
    private static final String USER="root";
    private static final String PSW="123";
    private static PreparedStatement pstm;
    private static ResultSet rSet;
    // 加载驱动
    static {
        try {
            new Driver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 连接
    public static Connection getConn(){
        try {
            conn= DriverManager.getConnection(URL,USER,PSW);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }




    //  增删改方法
    public static int executeUpdate(String sql,Object...objects){
        conn=getConn();
        int result=0;
        try {
            pstm=conn.prepareStatement(sql);
            for (int i=0;i<objects.length;i++){
                pstm.setObject(i+1,objects[i]);
            }
            result=pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,pstm,conn);
        }
        return result;
    }

    //  查询全部封装
    public static <T> List<T> executeQuery(String sql,RowMap<T> rowMap,Object... objects){
        List<T> list=new ArrayList<>();
        conn=getConn();
        try {
            pstm=conn.prepareStatement(sql);
            for (int i=0;i<objects.length;i++){
                pstm.setObject(i+1,objects[i]);
            }
            rSet=pstm.executeQuery();
            //将 结果集转换成对象T
            while (rSet.next()){
                T t=rowMap.rowMap(rSet);
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rSet,pstm,conn);
            return list;
        }
    }

    // 特定查询
    public static <T> T QueryOne(String sql,RowMap<T> rowMap,Object... objects){
        T t=null;
        conn=getConn();
        try {
            pstm=conn.prepareStatement(sql);
            for (int i=0;i<objects.length;i++){
                pstm.setObject(i+1,objects[i]);
            }
            rSet=pstm.executeQuery();
            //将 结果集转换成对象T
            while (rSet.next()){
                t=rowMap.rowMap(rSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rSet,pstm,conn);
            return t;
        }
    }

    //关流
    public static void close(ResultSet rSet,PreparedStatement pstm,Connection conn){
        if (rSet!=null){
            try {
                rSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm!=null){
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


