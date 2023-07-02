/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sihombing.jadwal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;
/**
 *
 * @author assaf
 */
public class koneksi {
    String db = "jdbc:mysql://localhost/jadwal";
    String user="root";
    String password="";
    Connection connection = null;
    
    public Connection getConnection(){
       
        try {
            connection = DriverManager.getConnection(db, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    
}
    public void connect(){
        try{
           DriverManager.registerDriver(new Driver());
           connection = DriverManager.getConnection(db,user,password);
           System.out.println("Terhubung");
        }catch (SQLException e){
            System.out.println("Gagal Terhubung");
            e.printStackTrace();
        }
    }
    
    public void dissconect(){
        try{
             if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database");
            }
        }catch (SQLException e){
            System.out.println("Failed to disconnect from the database");
            e.printStackTrace();
        }
    }
}
