package client;

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

    public void read(){
        File f = new File("client/test.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String fileRead = br.readLine();

            while (fileRead != null) {
                Candidate x = new Candidate();
                String[] tokenize = fileRead.split("\\+");
                x.setName(tokenize[0]);
                x.setParty(tokenize[1]);
                x.setOffice(tokenize[2]);
                candidates.add(x);
                fileRead = br.readLine();
            }
            br.close();
        }
        catch (FileNotFoundException a){
            System.out.println("file not found");
        }
        catch (IOException b){
            b.printStackTrace();
        }
    }

    public void display(){

    }


    public ArrayList getCandidates() {
        return candidates;
    }
}