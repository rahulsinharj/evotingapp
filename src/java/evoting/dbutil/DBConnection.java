/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author satyam dada
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//RahulSinhaRj:1521/xe","e_voting_db","evoting");
            System.out.println("Connection Done");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Connection not done","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"Class not found","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
            System.out.println("connection closed");
        }
    
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Connection not closed;","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
}
