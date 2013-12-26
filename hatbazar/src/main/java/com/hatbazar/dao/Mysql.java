package com.hatbazar.dao;

/**
 * Created with IntelliJ IDEA.
 * User: bsejawal
 * Date: 6/19/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
import java.sql.*;

public class Mysql {
    private static final String DB_PORT="3306"; // database port no eg mysql port : 3306
    private static final String DB_NAME="hatbazar"; // database name
    private static final String DB_URL="jdbc:mysql://localhost:"+DB_PORT+"/"+DB_NAME;
    private static final String DB_USER="root";// Database username
    private static final String DB_PASSWORD="root"; // Database password
    private static final String DB_DRIVER="com.mysql.jdbc.Driver";

    protected static Connection conn;
    public Mysql(){
        try{
            connect();
        }
        catch(Exception e){
            System.err.println("Error while calling connect method ::"+e.getMessage());
        }
    }

    protected static boolean connect() throws InstantiationException, IllegalAccessException{
        try{
            Class.forName(DB_DRIVER).newInstance();
        }
        catch(ClassNotFoundException e){
            System.err.print("Error while loading Driver :"+DB_DRIVER+" "+e.getMessage());
        }
        try{
            conn =DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        catch(SQLException e){
            System.err.println("Error while conneting Database DB_URL:"+DB_URL+" DB_USER:"+DB_USER+" DB_PASSWORD:"+DB_PASSWORD+" ERROR ::::"+e.getMessage());
        }
        return conn != null;
    }

    public static ResultSet find(PreparedStatement preparedStatement) throws SQLException, IllegalAccessException, InstantiationException {
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error while Executing query :"+e.getMessage());
        }
        return resultSet != null ? resultSet : null;
    }
    public static boolean affect(PreparedStatement preparedStatement) throws SQLException, IllegalAccessException, InstantiationException {
        int result = 0;
        try{
            result=preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("Error while executeUpdate "+e.getMessage());
        }finally {
            preparedStatement.clearParameters();
            close();
        }
        return result==0? false :true;
    }
    public static void close() throws SQLException{
        if(conn != null) conn.close();
    }
}