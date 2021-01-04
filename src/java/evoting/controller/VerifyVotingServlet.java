/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.UserDAO;
import evoting.dao.VoteDao;
import evoting.dto.CandidateDto;
import java.io.IOException;
import java.util.ArrayList;
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
public class VerifyVotingServlet extends HttpServlet {

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
       
        RequestDispatcher rd = null;
        
        HttpSession session=request.getSession();
      try
      {
          String userid=(String)session.getAttribute("userid");  //adhar no
             if(userid==null)
             {
                 session.invalidate();
                 response.sendRedirect("accessdenied.html");
                 return;
             }
             
             String cid=VoteDao.getCandidateId(userid);  // checking that user ne vote diya hai ki ni, cid me null aarha hai to matlab vote nhi diya hai
             CandidateDto candidate;
             if(cid!=null)                  // vote de chuka hoga
             {
                 candidate=VoteDao.getVote(cid);  // fetching the complete details of the cid  jisko user ne vote diya hai
                 request.setAttribute("candidate", candidate);
                 rd = request.getRequestDispatcher("votedenied.jsp");
             
             
             }
             else  // i.e, when cid ki null aaya hai
             {    // unn candidates ki detasils lana hai jo usi city se contest kr rahe hai ,jis city ka ye user hai      
                 
                 ArrayList<CandidateDto> candidateList=UserDAO.viewCandidate(userid);
                 
                 System.out.println("in VerifyVotingControllerServ: "+candidateList);
                 request.setAttribute("candidateList", candidateList);  
                 rd = request.getRequestDispatcher("voteChooseCandidate.jsp");
             }
             
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
