/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreweb.model.dao;

import bookstoreweb.model.bean.User;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author devsys-a
 */
public class UserDAO {

    private static final String SQL_INSERT = "INSERT INTO user("
            + "email, password, fullname) "
            + "VALUES (?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user"
            + "WHERE id = ?";

    private static final String SQL_SELECT = "SELECT fullname FROM user "
            + "WHERE email = ? AND password = ?";

    private static final String SQL_UPDATE = "UPDATE UserSET "
            + "email = ?, password = ?, fullname = ? where id = ?";

    private static final String SQL_DELETE = "DELETE FROM User WHERE id = ?";
    

    /**
     * insere um usario na base de dados
     *
     * @param u
     */
    public void create(User u) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());

//Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Inclusao: {0}", auxRetorno);

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            MySQLConnection.closeConnection(conn, stmt);

        }

    }

    /**
     * retorno todas os registro de tabela produto
     *
     * @return
     * @returno
     */
    public List<User> getResults() {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        List<User> listaProduto = null;

        try {
            //PREPARA A STRING DE select E EXECUTA A QUERY
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            listaProduto = new ArrayList<>();

            while (rs.next()) {

                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                listaProduto.add(u);
            }

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return listaProduto;
    }

    /**
     * retorna um produto da tabela produto.
     *
     * @param id Identificaçao do produto
     * @return
     */
    public User getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {

                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));

            }

        } catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //encerra a conexao com o banco eo statement

            MySQLConnection.closeConnection(conn, stmt);

        }
        return u;
    }

    /**
     *
     * @param u
     *
     */
    public void update(User u) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(4, u.getId());
            stmt.setString(3, u.getFullname());
            stmt.setString(2, u.getPassword());
            stmt.setString(1, u.getEmail());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);
        } catch (SQLException sQLException) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);

        } finally {

            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            int auxRetorno = stmt.executeUpdate();
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, null,
                    "delete: " + auxRetorno);

        } catch (SQLException sQLException) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);

        } finally {

            MySQLConnection.closeConnection(conn, stmt);

        }
    }

    public User checkLogin(String email, String password) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {

            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setFullname(rs.getString("fullname"));
                u.setEmail(email);

            }
        } catch (SQLException aQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, aQLException);

        }finally{
        
        MySQLConnection.closeConnection(conn,stmt);
        
        }
        return u;
    }

}
