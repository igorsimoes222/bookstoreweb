/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreweb.controller;

import bookstoreweb.model.bean.User;
import bookstoreweb.model.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author devsys-a
 */
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();
        Logger.getLogger(UserServlet.class.getName()).log(Level.INFO, "path solicitado: {0}", action);

        try {
            switch (action) {

                case "/bsuser/new":
                    showNewUserForm(request, response);
                    break;

                case "/bsuser/insert":
                    insertUserAction(request, response);
                    break;

                case "/bsuser/edit":
                    showEditUserForm(request, response);
                    break;

                case "/bsuser/update":
                    updateUserAction(request, response);
                    break;
                
                    
                case "/bsuser/delete":
                    deleteUserAction(request, response);
                    break;

                case "/bsuser/list":
                default:
                    listUser(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Exibe a lista completa de livros
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        UserDAO bookDAO = new UserDAO();
        List<User> listaUser = bookDAO.getResults();

        Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Total de registro: {0}", listaUser.size());

        request.setAttribute("listaUser", listaUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserList.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserForm.jsp");
        dispatcher.forward(request, response);

    }

    private void insertUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        UserDAO bookDAO = new UserDAO();
        User novoUser = new User();
        novoUser.setEmail(request.getParameter("formemail"));
        novoUser.setPassword(request.getParameter("formpassword"));
        novoUser.setFullname(request.getParameter("formfullname"));
        bookDAO.create(novoUser);
        response.sendRedirect("list");

    }

    private void updateUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        UserDAO bookDAO = new UserDAO();
        User bookAtualizado = new User();

        bookAtualizado.setId(Integer.parseInt(request.getParameter("formId")));

        
        bookAtualizado.setEmail(request.getParameter("formemail"));
        bookAtualizado.setFullname(request.getParameter("formfullname"));
        bookAtualizado.setPassword(request.getParameter("formpassword"));

        bookDAO.update(bookAtualizado);
        response.sendRedirect("list");
    }

    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
    
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO bookDAO = new UserDAO();
        User atualizaUser = bookDAO.getResultById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        request.setAttribute("user", atualizaUser);

        dispatcher.forward(request, response);

    }

    private void deleteUserAction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO bookDAO = new UserDAO();
        bookDAO.delete(id);
        response.sendRedirect("list");

    }


}
