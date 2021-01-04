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

/*    By default has no builtin class available in its own JEE platfrom which can support uploading of file.
File uploading is an exclusive feature made availabe by web server , and our web server is Apache Tomcat.

Spacial API given by TomCat
client pe: extra package: swal, jquery. bootstrap, 
server pe :servlet file uplaod by tomcat,
*/

public class AddNewCandidateControllerServlet extends HttpServlet {

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
        try
        {
//            ServletRequestContext srq=new ServletRequestContext(request);
//            DiskFileItemFactory dif=new DiskFileItemFactory();  // support java for parsing the file
//            ServletFileUpload sfu=new ServletFileUpload(dif);
//            List<FileItem> multiparts=sfu.parseRequest(srq); // parseRequest() will break data into obj belonging to class FileItems ,har obj ke andar ek spcific type of data bhar deta hai , to utne file items banata hai jitne waha se field aayi hai(Textfields aayi hai unke liye textual data &, images ke liye binaryDAta)
                                    // i.e, FileItems can represent bih textual and binary data.
            
            ServletFileUpload sfu=new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiparts = sfu.parseRequest(new ServletRequestContext(request)); // multipart me 5 mormal textual data + 1 image hoga 
            ArrayList<String> objValues=new ArrayList<>();
            InputStream fileContent=null;
            for(FileItem item: multiparts)
            {
                 if(item.isFormField())       // to check that the data is textual data
                 {
                    // String fieldName=item.getFieldName();
                     String fieldValues=item.getString();     //for fetching textual data
                     objValues.add(fieldValues);
                    // System.out.println(fieldName+" : "+fieldValues);
                 }
                 else
                 {
                    // String fieldName=item.getFieldName();
                    // String fileName=item.getName();
                    // System.out.println(fieldName+" : "+fileName);
                     fileContent=item.getInputStream();             // for fetching image or/file binary data
                     
                    // System.out.println("content: "+fileContent);
                    // System.out.println(fileName);
                     
                 }
                                         //NOTE:objValues.get(0) --> 0 se start kiye hai, bcoz ArrayList ka obj hai wo, 0 se index start hoga.   
            }                            // NOTE: CandidateDto obj me cname nhi ja rha hai; isliye .get(2) nhi kiye.
           AddCandidateDto candidate=new AddCandidateDto(objValues.get(0),objValues.get(1),objValues.get(3),objValues.get(4),fileContent);
           boolean result=CandidateDao.addCandidate(candidate);  // above .get(num) numbering is based on the order of data we have appended in the data object addcandidate() in adminoptions.js
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
