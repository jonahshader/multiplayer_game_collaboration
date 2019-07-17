package com.compilation.game.managers;

import java.sql.*;
import java.util.ArrayList;

public class SqlManager {

    Connection connection;
    String connectionUrl = "jdbc:sqlserver://den1.mssql7.gear.host;"
                                + "database=gameproject;"
                                + "user=gameproject;"
                                + "password=Nn264Buo-kf?;"
                                + "encrypt=true;"
                                + "trustServerCertificate=true;"
                                + "loginTimeout=30;";

    public boolean testConnection(){
        try{
            connection = DriverManager.getConnection(connectionUrl);
            connection.close();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public <T> T getSingleValue(String pSql){

        try{
            ResultSet resultSet = null;
            connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(pSql);

            resultSet.next();
            return (T) resultSet.getObject(0);
        }catch (SQLException e){
            return null;
        }

    }

    public <T> ArrayList<T> getSingleRow(String pSql){

        try{
            ResultSet resultSet = null;
            connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(pSql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            resultSet.next();
            ArrayList<T> returnResults = new ArrayList<T>();
            for(int x = 0; x < metaData.getColumnCount(); x++){
                returnResults.add((T) resultSet.getObject(0));
            }
            return returnResults;
        }catch (SQLException e){
            return null;
        }

    }

    public boolean executeSql(String pSql){
        try{
            connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
            statement.executeQuery(pSql);

            return true;
        }catch (SQLException e){
            return false;
        }
    }

}
