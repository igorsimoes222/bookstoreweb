/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreweb.model.dao;

import bookstoreweb.model.bean.Book;
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
public class BookDAO {

    private static final String SQL_INSERT = "INSERT INTO book("
            + "titulo, autor, preco) "
            + "VALUES (?, ?, ?)";
 
    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    
    private static final String SQL_SELECT_ID = "SELECT * FROM book "
            + "WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE book SET "
            + "titulo = ?, autor = ?, preco = ? where id = ?";

    private static final String SQL_DELETE = "DELETE FROM book  WHERE id = ?";

    /**
     * insere um usario na base de dados
     *
     * @param b
     */
    public void create(Book b) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, b.getTitulo());
            stmt.setString(2, b.getAutor());
            stmt.setDouble(3, b.getPreco());

//Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, "Inclusao: {0}", auxRetorno);

        } catch (SQLException ex) {

            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,ex);

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
    public List<Book> getResults() {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book p = null;
        List<Book> listaProduto = null;

        try {
            //PREPARA A STRING DE select E EXECUTA A QUERY
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            listaProduto = new ArrayList<>();

            while (rs.next()) {

                p = new Book();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setAutor(rs.getString("autor"));
                p.setPreco(rs.getDouble("preco"));
                listaProduto.add(p);
            }

        } catch (SQLException ex) {

            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return listaProduto;
    }

    /**
     * retorna um produto da tabela produto.
     *
     * @param id Identificaçao do produto
     * @return
     */
    public Book getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book p = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {

                p = new Book();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setAutor(rs.getString("autor"));
                p.setPreco(rs.getDouble("preco"));

            }

        } catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            //encerra a conexao com o banco eo statement

            MySQLConnection.closeConnection(conn, stmt);

        }
        return p;
    }

    /**
     *
     * @param p
     *
     */
    public void update(Book p) {

        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(4, p.getId());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(2, p.getAutor());
            stmt.setString(1, p.getTitulo());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);
        } catch (SQLException sQLException) {

            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
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
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, null,
                    "delete: " + auxRetorno);

        } catch (SQLException sQLException) {

            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);

        } finally {

            MySQLConnection.closeConnection(conn, stmt);

        }
    }

}
