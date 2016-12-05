package client;

/**
 * Created by hoisi on 10/26/2016.
 */
public class Official {

    private static final String ps = "Pothering";

    public Official(){

    }

    public void startPoll(){
        Context.getInstance().currentTally().setPollOver(false);
    }
    public void endPoll(){
        Context.getInstance().currentTally().setPollOver(true);
    }

    //Is this just to verify the number of voters = the number of votes and then submit All?
    public void tallyResults(){}

    //TODO: recount method

    public Boolean authenticate(String input){
        if(input.equals(ps)){
            return true;
        }
        else{
            return false;
        }
    }
}