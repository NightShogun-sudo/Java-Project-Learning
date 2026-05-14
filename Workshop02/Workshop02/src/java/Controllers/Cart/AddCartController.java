/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Cart;

import Models.DAO.BookDAO;
import Models.Entities.Book;
import Models.Entities.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Models.DTO.User;

/**
 *
 * @author This PC
 */
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {
    private final String bookController = "BookController";

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        String message, bookId;
        Book selectedBook = null;
        CartItem item = null;
        HashMap<String, CartItem> itemsInCart = null;

        try {
            BookDAO bookDAO = new BookDAO();
            HttpSession shoppingCart = request.getSession();
            User loginUser = (User) shoppingCart.getAttribute("userLoggedIn");
            if(loginUser == null){
                response.sendRedirect("Login.jsp");
                return;
            }
            String cartKey = "Cart_" + loginUser.getUserName();
            itemsInCart = (HashMap<String, CartItem>) shoppingCart.getAttribute(cartKey);

            bookId = request.getParameter("BookId");
            selectedBook = bookDAO.getBookById(bookId);

            if (itemsInCart == null) {
                itemsInCart = new HashMap<String, CartItem>();
                shoppingCart.setAttribute(cartKey, itemsInCart);
            }

            if(selectedBook != null){
                item = itemsInCart.get(selectedBook.getId());
            

            if (item == null) {
                item = new CartItem(selectedBook.getId(),
                        selectedBook.getTitle(), 1, selectedBook.getUnitPrice());

                itemsInCart.put(item.getItemId(), item);

                message = "The book " + item.getItemName()
                        + " has been added to cart successfully.";
            } else {
                item.setQuantity(item.getQuantity() + 1);
                message = "The cart has been updated successfully.";
            }
            
            url = bookController + "?action=ViewBookList";
            request.setAttribute("Message", "<h4>" + message + "</h4>");
            }
        } catch (Exception ex) {
            log("AddCartController has error:" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

}
