package client;

/**
 * Created by hoisi on 10/26/2016.
 */
public class Official {

    private static final String ps = "Pothering";

    public Official(){

    }

    public void startPoll(){}
    public void endPoll(){}

    //Is this just to verify the number of voters = the number of votes and then submit All?
    public void tallyResults(){}

    public Boolean authenticate(String input){
        if(input.equals(ps)){
            return true;
        }
        else{
            return false;
        }
    }
}