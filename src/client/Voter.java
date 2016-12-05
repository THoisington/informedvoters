package client;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Voter{
    int voterID;

    public Voter(){}

    public void openBallot(){
        Context.getInstance().currentBallot().setCandidates(Context.getInstance().currentTally().getCandidates());
    }

    public void vote(){
        //TODO: (Aaron) This is where we write the contents of the object on the next line to the DB
        // Ballot submit = Context.getInstance().currentBallot();
    }

    public int getVoterID() {
        return voterID;
    }

    public void setVoterID(int voterID) {
        this.voterID = voterID;
    }

    public Boolean authenticate(String input){
        //TODO: (Aaron) Search DB for ID input (would be an int) and if it is there and the user hasn't voted return true, else return false
        // int  query =

        String fakequery = "test";
        if(input.equals(fakequery)){
            return true;
        }
        else{
            return false;
        }
    }
}