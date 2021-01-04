/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;



import java.io.InputStream;

/**
 *
 * @author visha
 */
public class AddCandidateDto {

    private String candidateId;
    private String userid;  //adhar num
    private String party;
    private String city;
    private InputStream symbol;
    
    public AddCandidateDto(String candidateId, String userid, String party, String city, InputStream symbol) {
        this.candidateId = candidateId;
        this.userid = userid;
        this.party = party;
        this.city = city;
        this.symbol = symbol;
    }
    
    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "AddCandidateDto{" + "candidateId=" + candidateId + ", userid=" + userid + ", party=" + party + ", city=" + city + ", symbol=" + symbol + '}';
    }
    

    

    public AddCandidateDto() {
    }

   

   
    
}

