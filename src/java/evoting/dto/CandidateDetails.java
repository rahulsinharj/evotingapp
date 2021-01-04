/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author visha
 */
public class CandidateDetails {
    
    private String candidateId;
    private String userId;
    private String candidateName;
    private String symbol;  //the image type we have set is String bcoz, 
    private String party;
    private String city;

    @Override
    public String toString() {
        return "CandidateDetails{" + "candidateId=" + candidateId + ", userId=" + userId + ", candidateName=" + candidateName + ", symbol=" + symbol + ", party=" + party + ", city=" + city + '}';
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public CandidateDetails() {
    }

    public CandidateDetails(String candidateId, String userId, String candidateName, String symbol, String party, String city) {
        this.candidateId = candidateId;
        this.userId = userId;
        this.candidateName = candidateName;
        this.symbol = symbol;
        this.party = party;
        this.city = city;
    }
    
    
}
