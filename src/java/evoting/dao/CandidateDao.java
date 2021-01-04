/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.AddCandidateDto;
import evoting.dto.CandidateDetails;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.LinkedHashSet;

/**
 *
 * @author satyam dada
 */
public class CandidateDao {
    private static Statement st,st2,st3;
    private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    static{
        try{
            st=DBConnection.getConnection().createStatement();
            st2=DBConnection.getConnection().createStatement();
            st3=DBConnection.getConnection().createStatement();
            ps=DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no=?");
           ps1=DBConnection.getConnection().prepareStatement("insert into candidate_details values(?,?,?,?,?)");
           ps2=DBConnection.getConnection().prepareStatement("select * from candidate_details where candidate_id=?");
          ps3= DBConnection.getConnection().prepareStatement("update candidate_details set PARTY=?,SYMBOL=?,CITY=? where USER_ID=?");
         ps4= DBConnection.getConnection().prepareStatement("delete from candidate_details where candidate_id=? ");
        
        
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public static String getNewId()throws SQLException
    {
        ResultSet rs=st.executeQuery("select count(*) from candidate_details");
        if(rs.next())
            return "C"+(100+(rs.getInt(1)+1));
	else
        	return "C101";
    }

   public static String getUsernameById(String uid)throws SQLException
    {
        ps.setString(1, uid);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }

      public static ArrayList<String> getCity()throws SQLException
    {
        ArrayList<String> city=new ArrayList<>();
        ResultSet rs=st2.executeQuery("select distinct city from user_details");
        while(rs.next())
            city.add(rs.getString(1));
        return city;
    }

    public static boolean addCandidate(AddCandidateDto candidate)throws Exception
     {
         ps1.setString(1, candidate.getCandidateId());
         ps1.setString(2,candidate.getParty());
         ps1.setString(3, candidate.getUserid());
         
         InputStream in=candidate.getSymbol();
         ps1.setBinaryStream(4, in,in.available());    // available() abhi kitni bytes file se padha jana bacha hua hai
                                                      // we will get the image by calling the read() method.
         ps1.setString(5,candidate.getCity() );
         return (ps1.executeUpdate()!=0);
     }
        
    public static ArrayList<String> getCandidateId() throws SQLException
    {
        ResultSet rs=st3.executeQuery("select candidate_id from candidate_details order by candidate_id");
        ArrayList<String> id=new ArrayList<>();
        while(rs.next())
        {
            id.add(rs.getString(1));
        }
        return id;
    }
     
    public static CandidateDetails getCandidateDetailsbyId(String cid) throws SQLException
    {
        ps2.setString(1, cid);
        ResultSet rs=ps2.executeQuery();  //  1} Candidate_Id  2}Party  3}User_id  4}Symbol  5}City
        CandidateDetails candidate=new CandidateDetails();
        Blob blob;       
        byte[] imageBytes;
        String base64Image;
        if(rs.next())      // Record pointer ko 1 step aage badhana zaruri hai nahi to Exception aayegi ApproxiResultSet
        {
            blob=rs.getBlob(4);
            imageBytes=blob.getBytes(1L, (int)blob.length()); //(in Argtument) kaha se extraction suru karna hai i.e, image ki 1st byte se.// blob ki full length tak extraction karni hai
                                                        // converting blob to Array of Bytes
            Encoder ec=Base64.getEncoder();
            base64Image=ec.encodeToString(imageBytes);  // converting this Array Of Bytes to--> Base64
            candidate.setSymbol(base64Image);
            
            candidate.setCandidateId(cid);
            candidate.setParty(rs.getString(2));
            
            String candidateAdharId=rs.getString(3);  // userid
            String cname=CandidateDao.getUsernameById(candidateAdharId);
            candidate.setUserId(candidateAdharId);
            candidate.setCandidateName(cname);
            candidate.setCity(rs.getString(5));
  
        }
        return candidate;
           
    }
     public static boolean updateCandidate(AddCandidateDto candidate)throws Exception
     {
        
         ps3.setString(1,candidate.getParty());
        
         InputStream in=candidate.getSymbol();
         ps3.setBinaryStream(2, in,in.available());    // available() abhi kitni bytes file se padha jana bacha hua hai
                                                      // we will get the image by calling the read() method.
         ps3.setString(3,candidate.getCity() );
         ps3.setString(4, candidate.getUserid());
         return (ps3.executeUpdate()!=0);
     }
      public static boolean deleteCandidate(String cid)throws Exception
     {
         System.out.println("cid in dao "+cid);
         ps4.setString(1,cid);
         System.out.println("in deleteCandidate Dao");
         return ps4.executeUpdate()!=0;
         
     }
      public static LinkedHashSet<CandidateDetails> seeAllCandidates()throws SQLException
    {
         LinkedHashSet<CandidateDetails> allCandidate=new LinkedHashSet<>();  // to preserve the insertion order
        ResultSet rs=st2.executeQuery("select CANDIDATE_ID,USER_ID,PARTY,SYMBOL,CITY from candidate_details order by CANDIDATE_ID");
        while(rs.next())
        {    
            CandidateDetails candidate=new CandidateDetails();
            
            candidate.setCandidateId(rs.getString(1));
            candidate.setUserId(rs.getString(2));
            String cname=CandidateDao.getUsernameById(rs.getString(2));
            candidate.setCandidateName(cname);
           candidate.setParty(rs.getString(3));
           
           Blob blob;       
           byte[] imageBytes;
           String base64Image;
           blob=rs.getBlob(4);
           imageBytes=blob.getBytes(1L, (int)blob.length()); //(in Argtument) kaha se extraction suru karna hai i.e, image ki 1st byte se.// blob ki full length tak extraction karni hai
                                                        // converting blob to Array of Bytes
            Encoder ec=Base64.getEncoder();
            base64Image=ec.encodeToString(imageBytes);  // converting this Array Of Bytes to--> Base64
            candidate.setSymbol(base64Image);
            
            candidate.setCity(rs.getString(5));
            
            allCandidate.add(candidate);
         System.out.println("in CandidateDao 2: "+candidate);
         System.out.println("in CandidateDao 3: "+allCandidate);
        }
        System.out.println("in CandidateDao 4 "+allCandidate);
        
        return allCandidate;
            
    }
}
