
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDto;
import evoting.dto.VoteDto;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;

public class VoteDao {
private static Statement st,st2;
    private static PreparedStatement ps,ps2,ps3,ps4;
    static
    {
        try
        {
         st=DBConnection.getConnection().createStatement();
         st2=DBConnection.getConnection().createStatement();
           
         ps=DBConnection.getConnection().prepareStatement("select CANDIDATE_ID from voting_details where VOTER_ID=?");
         ps2=DBConnection.getConnection().prepareStatement("select candidate_id,username,party,symbol from candidate_details,user_details where candidate_details.user_id=user_details.adhar_no and candidate_id=?" );
         ps3=DBConnection.getConnection().prepareStatement("insert into voting_details values(?,?)");
        ps4=DBConnection.getConnection().prepareStatement("delete from voting_details where voter_id=?");
        
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getCandidateId(String userid)throws SQLException
    {
        ps.setString(1,userid);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
           return rs.getString(1); // will return something when user has already casted the vote.
        return null;     // when user has not casted the vote yet.
        
    }   
    
   public static CandidateDto getVote(String candidateid)throws Exception  
    {                                       // fetching the complete details of the cid jisko user ne vote diya hai
        CandidateDto candidate=null;
        Blob blob;       
        byte[] imageBytes;
        String base64Image;
        ps2.setString(1, candidateid);
        ResultSet rs=ps2.executeQuery();
        if(rs.next())     
        {
            blob=rs.getBlob(4);
            imageBytes=blob.getBytes(1L, (int)blob.length()); 
            Base64.Encoder ec=Base64.getEncoder();
            base64Image=ec.encodeToString(imageBytes);  
            candidate=new CandidateDto(rs.getString(1),rs.getString(2),rs.getString(3),base64Image);
          System.out.println("in VoteDao_getVote: "+candidate);
          
          }
      return candidate; 
        
    }
    public static boolean addVote(VoteDto vote)throws SQLException
    {
        ps3.setString(1,vote.getCandidateId()); 
        ps3.setString(2,vote.getVoterId() );
        return (ps3.executeUpdate()!=0);
        
    }   
    public static LinkedHashMap<String,Integer> getResult()throws Exception
    {
         LinkedHashMap<String,Integer> result=new LinkedHashMap<>();  // to preserve the insertion order
        ResultSet rs=st2.executeQuery("select candidate_id,count(*) from voting_details group by candidate_id order by count(*) desc");
        while(rs.next())
            result.put(rs.getString(1), rs.getInt(2));
        return result;
            
    }
    public static int getVoteCount()throws SQLException
    {
        ResultSet rs=st.executeQuery("select count(*) from voting_details ");
        if(rs.next())
           return rs.getInt(1);
        return 0;
        
    }
    public static boolean deleteVote(String uid)throws SQLException
    {
        System.out.println("in delete vote "+uid);
        ps4.setString(1,uid); 
        return (ps4.executeUpdate()!=0);
        
    }   
    
    
}



