/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDto;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;
import java.sql.Blob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author satyam dada
 */
public class UserDAO {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    private static Statement st,st2;
    static {
      try
      {
         st=DBConnection.getConnection().createStatement();
         st2=DBConnection.getConnection().createStatement();
          ps= DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=? and password=?");
        ps1=  DBConnection.getConnection().prepareStatement("select CANDIDATE_ID,USERNAME,PARTY,SYMBOL from candidate_details,user_details where candidate_details.USER_ID=user_details.ADHAR_NO and candidate_details.city=(select city from user_details where ADHAR_NO=?  ) order by candidate_id");
       ps2= DBConnection.getConnection().prepareStatement("select USERNAME,EMAIL,MOBILE_NO,ADDRESS,CITY from user_details where ADHAR_NO=? ");
        ps3= DBConnection.getConnection().prepareStatement("delete from user_details where ADHAR_NO=? ");
       ps4=DBConnection.getConnection().prepareStatement("update user_details set PASSWORD=?,USERNAME=?, ADDRESS=?,CITY=?,EMAIL=?,MOBILE_NO=? where ADHAR_NO=? ");  
       
      
      }
        catch(SQLException e)
        {
            if(DBConnection.getConnection()!=null)
                System.out.println("not null");
            e.printStackTrace();
        }
    }
    public static String validateUser(UserDTO user)throws SQLException{
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs =  ps.executeQuery();
        
        if(rs.next()){
            return rs.getString("user_type");
        }
        return null;
    }
    
    
     public static ArrayList<CandidateDto> viewCandidate(String userid)throws SQLException
    {           //ye method unn candidates ki details leke aayege jo uss ctiy se belong karta hai, jaha ka user login hua hai. 
        
        ArrayList<CandidateDto> candidate=new ArrayList<CandidateDto>();
        System.out.println("in viewCandidate "+userid);
        ps1.setString(1, userid);
        ResultSet rs=ps1.executeQuery();
        Blob blob;       
        byte[] imageBytes;
        String base64Image;
        while(rs.next())     
        {
            blob=rs.getBlob(4);
            
             imageBytes=blob.getBytes(1L, (int)blob.length());
            Base64.Encoder ec=Base64.getEncoder();
            base64Image=ec.encodeToString(imageBytes);  
           
            CandidateDto cDto=new CandidateDto(rs.getString(1),rs.getString(2),rs.getString(3),base64Image);
           
           candidate.add(cDto);
            System.out.println("in viewCandidate  "+cDto);
          
          }
   System.out.println("in viewCandidate All 2 "+candidate);
        return candidate;    
        
    }
     public static ArrayList<UserDetails> viewUsers()throws SQLException
    {          
        ArrayList<UserDetails> users=new ArrayList<>();
        ResultSet rs=st.executeQuery("select ADHAR_NO,USERNAME,ADDRESS,CITY,EMAIL,MOBILE_NO from user_details WHERE NOT (adhar_no='101') order by ADHAR_NO");
       
        while(rs.next())     
        {
           UserDetails u=new UserDetails();
           
           u.setUserid(rs.getString(1));
           u.setUsername(rs.getString(2));
           u.setAddress(rs.getString(3));
           u.setCity(rs.getString(4));
           u.setEmail(rs.getString(5));
           u.setMobile(rs.getString(6));
           
           
           
           users.add(u);
          
          }
      return users;    
        
    }
    
     public static ArrayList<String> getUsersId() throws SQLException
    {
        ResultSet rs=st2.executeQuery("select ADHAR_NO from user_details WHERE NOT (adhar_no='101') order by adhar_no ");
        ArrayList<String> id=new ArrayList<>();
        while(rs.next())
        {
            id.add(rs.getString(1));
        }
        System.out.println("ids "+id);
        return id;
    }        
     
    public static UserDetails getUserbyId(String uid)throws SQLException
     {
         ps2.setString(1,uid);
         ResultSet rs=ps2.executeQuery();
         
         UserDetails u=null;
         if(rs.next())
         {
           u=new UserDetails();
          
           u.setUserid(uid);
           u.setUsername(rs.getString(1));
           u.setEmail(rs.getString(2));
           u.setMobile(rs.getString(3));
           u.setAddress(rs.getString(4));
           u.setCity(rs.getString(5));
           
         System.out.println("in showSingleUser Dao "+u);  
         }
        return u; 
         
         
     } 
     
     public static boolean deleteUser(String uid)throws SQLException
     {
         ps3.setString(1,uid);
         System.out.println("in deleteUser Dao "+uid);
         return ps3.executeUpdate()!=0;
         
     }
     
     public static boolean updateUser(UserDetails user)throws SQLException
     {
         System.out.println("in UpdateVoter DAO"+user);
        ps4.setString(1, user.getPassword());
        ps4.setString(2, user.getUsername());
        ps4.setString(3, user.getAddress());
        ps4.setString(4, user.getCity());
        ps4.setString(5, user.getEmail());
        ps4.setString(6, user.getMobile());
        ps4.setString(7, user.getUserid());
        
        return (ps4.executeUpdate()!=0);
        
    }
      
}
