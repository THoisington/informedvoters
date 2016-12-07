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

    public boolean isPollOver() {
        return pollOver;
    }

    public void setPollOver(boolean pollOver) {
        this.pollOver = pollOver;
    }

    public void display(){

    }


    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }


    public void addCandidate(Candidate x){
        //TODO: (Aaron) If not in DB, add to Candidate table
        candidates.add(x);
    }
}