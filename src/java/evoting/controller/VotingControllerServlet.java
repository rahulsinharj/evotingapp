/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author visha
 */         
public class VotingControllerServlet extends HttpServlet {

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
        // sabse pehle ye VotingControllerServlet ye check karega that user ne vote daal diya hai ya nhi
        // agar vote nhi dala hai to candidates ke list jayege uske city ke.
       System.out.println("In the VotingControllerServlet");
       
        RequestDispatcher rd = null;
        
        HttpSession session=request.getSession();
      try
      {
          String userid=(String)session.getAttribute("userid");
          System.out.println("in VotingControllerServlet "+userid);
             if(userid==null)
             {
                 session.invalidate();
                 response.sendRedirect("accessdenied.html");
                 return;
             }
             rd = request.getRequestDispatcher("votermodule.jsp");
      }
      
      catch(Exception e)
      {
            request.setAttribute("exception",e);
            rd = request.getRequestDispatcher("showexception.jsp");
            e.printStackTrace();
      }
      
      finally
      {
           rd.forward(request,response);
      }  
        
    }

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
