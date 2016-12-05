package client;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hoisi on 12/2/2016.
 */
public class Context {

    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Voter voter = new Voter();
    private Official official = new Official();
    private Ballot ballot = new Ballot();
    private Tally tally = new Tally(); //reevaluate use of Tally
    private Boolean firstStart = true;

    public Voter currentVoter(){
        return voter;
    }

    public Official currentOfficial() {
        return official;
    }

    public Ballot currentBallot(){
        return ballot;
    }

    public Tally currentTally(){
        return tally;
    }

    public Boolean getFirstStart(){
        return firstStart;
    }

    public void refresh(){
        ballot.setVoterID(-1);
        voter.setVoterID(-1);
        for (Candidate x: tally.getCandidates()) {
            x.resetVotes();
        }
    }


    public void setFirstStart(Boolean x){
        firstStart = x;
    }

}
