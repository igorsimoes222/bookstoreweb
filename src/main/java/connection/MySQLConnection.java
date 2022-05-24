/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * gerencia a conexao com a base de dados. possui instruçoes
 *
 * @author devsys-a
 */
public class MySQLConnection {
    // define string de conexao com o banco

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30:3306/ils_bookstore";

    private static final String USER = "igor";
    private static final String PASS = "21262804";

    /*
cria conexao com o banco de dados MySql 
*@return
*
     */
    public static Connection getConnection() {

        try {
            
            Class.forName (DRIVER);
            return DriverManager.getConnection (URL, USER, PASS);
        } catch (SQLException ex) {

            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erro na conexao com BD. Verifique!");
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erro na carga do driver do BD. Verifoque!");
        }

    }

    public static void closeConnection(Connection conn)  {
        try {
            if (conn != null) {

                conn.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * fecha a conexao com db
     *
     * @param conn
     * @param stmt
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt)  {
        closeConnection(conn);
        try {

            if (stmt != null) {
                stmt.close();
            }//fim if

        } catch (SQLException ex) {

            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }//fim catch

    }

    public static void closeConnection(Connection conn,
            PreparedStatement stmt,
            ResultSet rs) throws SQLException {
        closeConnection(conn, stmt);
        try {

            if (rs != null) {
 
                rs.close();

            }//fim if

        } catch (SQLException ex) {

            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
