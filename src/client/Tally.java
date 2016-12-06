package client;

import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Tally {

    ArrayList candidates = new ArrayList();

    boolean pollOver;

    public Tally(){}

    //The class diagram also calls for addBallotResults(). What did that do?
    public void addBallot(Ballot b){
        if (b.isBallotComplete() == true){
            for(int i = 0; i < this.candidates.size(); i++){
                Candidate fromTally = (Candidate) this.candidates.get(i);
                Candidate fromBallot = (Candidate) b.getCandidates().get(i);
                if(fromBallot.getVotesReceived()!= 0){
                    fromTally.addVotesReceived();
                }
            }
        }
    }

    public boolean isPollOver() {
        return pollOver;
    }

    public void setPollOver(boolean pollOver) {
        this.pollOver = pollOver;
    }

    public void submitBallots(){

    }

    public void display(){

    }


    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidate fromDB){
        candidates.add(fromDB);
    }

    public void addCandidate(Candidate x){
        //TODO: (Aaron) If not in DB, add to Candidate table
        candidates.add(x);
    }
}