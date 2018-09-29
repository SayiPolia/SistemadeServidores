package dao;

import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DbConnection {
static String db="servidoresdb";
static String login="root";
static String password="holamundo";
static String url="jdbc:mysql://localhost/"+db;
Connection conn=null;

public DbConnection() {
    try {
       //obtenemos el driver para mysql
       Class.forName("com.mysql.jdbc.Driver");
       //obtenemos una conexion con los parametros especificados anteriormente 
       conn = (Connection) DriverManager.getConnection(url, login, password);
       // Si conn no es nulo, significa que pidimos conectarnos
       if (conn != null) {
          System.out.println("Connecting database [" + conn + "] OK");
       }
    } catch (SQLException e) // Excepcion ocurrida por la conexion 
    {
       System.out.println("Excepcion conexion: " + e.getMessage());         
    } catch (ClassNotFoundException e) // Excepcion ocurrida por no encontrar el driver
    {
       System.out.println("Excepcion driver: " + e.getMessage());         
    }
 }
public Connection getConnection() {      
    return conn;
 }
//Quitamos de memoria la conexion
public void disconnect() {
   System.out.println("Closing database: [" + conn + "] OK");
   if (conn != null) {
      try {
         // System.out.println("Desconectado de " + bd + " OK");
         conn.close();
      	} catch (SQLException e) {
         System.out.println(e);
      	}
   }
}
}
