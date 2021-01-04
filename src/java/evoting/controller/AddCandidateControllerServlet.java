/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author satyam dada
 */
public class AddCandidateControllerServlet extends HttpServlet {

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
              response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(); 
        String sessionId=(String)session.getAttribute("userid");   //"id" received from showaddcandidateform() through adminoptions.js
        String candidate=(String)request.getParameter("xid");  //"uid" (Adhar) received from getdetails() through adminoptions.js
        System.out.println("xid: "+candidate);
        String usid=(String)request.getParameter("uid");
        if(sessionId==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        else if(candidate!=null&&candidate.equals("getid"))    // i.e, we r checking that if "candidate id" me "getid" aya hai kya to DB se new #c10X id mangwa lenge.
        {
            try
            {
                String cid=CandidateDao.getNewId();
                   System.out.println("cid: "+cid);  
                
                 out.println(cid);
               return;
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else if(usid!=null)     //i.e, we r checking that "uid" me kuch aaya hai kya, if so then uss adhar uid ka respective "userName" magwa lenge from DB. 
        {
            try
            {
            String username=CandidateDao.getUsernameById(usid);
            ArrayList<String> city=CandidateDao.getCity();
            StringBuffer displayBlock=new StringBuffer("'");
            for(String c:city)
                displayBlock.append("<option value='"+c+"'>"+c+"</option>");
            displayBlock.append("'");
                
	    if(username==null)
		username="wrong";
            out.println(displayBlock+","+username);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }        

     
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
