/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop.service;

import java.sql.*;

/**
 *
 * @author USUARIO
 */
public class MysqlConnection {
    private String bdName="vet";
    private String username="root";
    private String password="my-secret-pw";
    private String url="jdbc:mysql://localhost:3306/"+bdName+"?"+
                        "useUnicode=true&"+
                        "useJDBCCompliantTimezoneShift=true&"+
                        "useLegacyDatetimeCode=false&"+
                        "serverimezone=UTC";
    private Connection conn=null;
    public MysqlConnection(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn=DriverManager.getConnection(url, username, password);
           if(conn!=null){
                System.err.println("Connected successfully to "+bdName);
           }
           System.out.println(conn);
        } catch(ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: "+ e.getMessage());
        } catch(SQLException e) {
            System.err.println("SQLException: "+ e.getMessage());
        }
    }
    public Connection getConnection(){
        return conn;
    }
}
