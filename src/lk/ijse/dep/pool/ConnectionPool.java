/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.dep.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slash
 */
public class ConnectionPool {

    public ArrayList<Connection> connectionPool = new ArrayList<>();

    public ArrayList<Connection> consumerPool = new ArrayList<>();

    private void pooldetails() throws ClassNotFoundException, SQLException {
        int initial = 5;
        String classname = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/test";
        String uname = "root";
        String pass = "";

        Class.forName(classname);

        Connection connection = DriverManager.getConnection(url, uname, pass);

        for (int x = 0; x < initial; x++) {
            connectionPool.add(connection);
        }
    }

    public ConnectionPool() throws ClassNotFoundException, SQLException {
        pooldetails();
    }
    
    public synchronized Connection getConnection(){
        
        if(connectionPool.size()!=0){
            Connection connection = connectionPool.get(0);
            
            consumerPool.add(connection);
            connectionPool.remove(connection);
            return connection;
        }else{
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
            return getConnection();
        }
        
    }
    
    public synchronized boolean removeConnection(){
        
        if(consumerPool.size()!=0){
            Connection connection = consumerPool.get(0);
            connectionPool.add(connection);
            consumerPool.remove(connection);
            notifyAll();
            return true;
        }else{
            return false;
        }
        
        
    }
}
