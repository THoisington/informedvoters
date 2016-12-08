package client;

import java.util.ArrayList;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Ballot {
    private ArrayList<Candidate> candidates = new ArrayList();

    int voterID;

    public Ballot(ArrayList x){
        //used to be this, but I think this was just a reference. Need a new object
//        this.candidates = x;
        for(int j = 0; j < x.size(); j++) {
            Candidate add = new Candidate();
            Candidate y = (Candidate) x.get(j);
            add.setBio(y.getBio());
            add.setName(y.getName());
            add.setParty(y.getParty());
            this.candidates.add(add);
        }
    }

    public Ballot(){

    }

    public void print(){
        for (Candidate x: candidates) {
            if(x.getVotesReceived()!=0){
                System.out.println("Voter: " + this.getVoterID() + "  voted for: " + x.getName());
            }
        }
    }

    public int getVoterID() {
        return voterID;
    }

    public void setVoterID(int voterID) {
        this.voterID = voterID;
    }

    public ArrayList getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList x) {
        candidates = x;
    }

    public void addOfficeSelection(String name){
        for (Candidate current: candidates) {
            if(current.getName().equals(name)){
                current.addVotesReceived();
            }
        }
    }

}