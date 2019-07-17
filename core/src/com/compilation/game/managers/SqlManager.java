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
            boolean success = statement.execute(pSql);

            if(!success){
                connection.close();
                return null;
            }

            resultSet = statement.getResultSet();

            resultSet.next();
            var Output = (T) resultSet.getObject(1);
            connection.close();
            return Output;

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
            for(int x = 1; x <= metaData.getColumnCount(); x++){
                returnResults.add((T) resultSet.getObject(x));
            }

            connection.close();
            return returnResults;
        }catch (SQLException e){
            return null;
        }

    }

    public boolean executeSql(String pSql){
        try{
            connection = DriverManager.getConnection(connectionUrl);

            Statement statement = connection.createStatement();
            boolean success = statement.execute(pSql);
            connection.close();
            return success;
        }catch (SQLException e){
            return false;
        }
    }

}
