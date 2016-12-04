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
    private ArrayList candidates = new ArrayList();
    private Tally tally = new Tally();
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

    public ArrayList currentCandidates(){
        return candidates;
    }

    public Tally currentTally(){
        return tally;
    }

    public Boolean getFirstStart(){
        return firstStart;
    }

    public void setFirstStart(Boolean x){
        firstStart = x;
    }

}
