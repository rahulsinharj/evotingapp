/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDao;
import evoting.dto.AddCandidateDto;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author rahul
 */
public class UpdateNewCandidateController extends HttpServlet {

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
        System.out.println("in UpdateNewCandidateController");
        RequestDispatcher rd = null;
        try
        {

            ServletFileUpload sfu=new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiparts = sfu.parseRequest(new ServletRequestContext(request)); // multipart me 5 mormal textual data + 1 image hoga 
            ArrayList<String> objValues=new ArrayList<>();
            InputStream fileContent=null;
            for(FileItem item: multiparts)
            {
                 if(item.isFormField())       // to check that the data is textual data
                 {
                    
                     String fieldValues=item.getString();     //for fetching textual data
                     objValues.add(fieldValues);
                    
                 }
                 else
                 {
                     fileContent=item.getInputStream();             // for fetching image or/file binary data
                     
                 }                     

            }                       
           AddCandidateDto candidate=new AddCandidateDto(objValues.get(0),objValues.get(1),objValues.get(3),objValues.get(4),fileContent);
           boolean result=CandidateDao.updateCandidate(candidate);  // above .get(num) numbering is based on the order of data we have appended in the data object addcandidate() in adminoptions.js
           if(result==true)
           {
               rd=request.getRequestDispatcher("success.jsp");
           }
           else
           {
               rd=request.getRequestDispatcher("failure.jsp");
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
