package client;
import java.sql.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Voter{
    int voterID;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement statement=null;

    public Voter(){}

    public void openBallot(){
        Context.getInstance().currentBallot().setCandidates(Context.getInstance().currentTally().getCandidates());
        Context.getInstance().currentBallot().setVoterID(this.getVoterID());
    }

    public void vote(){
        int i;
        //TODO: (Aaron-Done?) This is where we write the contents of the object on the next line to the DB
        Ballot submit = Context.getInstance().currentBallot();
        // TODO(Done?): Add one to Candidates Table LOCAL votes column
        ArrayList<Candidate> candidatesTemp=submit.getCandidates();
        boolean isDone=false;
        for(i=0;i<candidatesTemp.size();i++){
                if(candidatesTemp.get(i).getVotesReceived()==1 && isDone==false){
                    String tempName=candidatesTemp.get(i).getName();
                    try{
                        conn=databaseConnector.getConnection();
                        String sql = "UPDATE Candidate SET tempVotes=tempVotes+1 WHERE name=?";

                        statement = conn.prepareStatement(sql);
                        statement.setString(1,tempName);
                        statement.executeUpdate();
                        int tempID=Context.getInstance().currentVoter().getVoterID();
                        //System.out.println("This is a test for ID-"+tempID);
                        String sql1 ="INSERT INTO ballots (ID,votes) VALUES (?,?)";
                        statement=conn.prepareStatement(sql1);
                        statement.setInt(1,tempID);
                        statement.setString(2,tempName);
                        statement.executeUpdate();
                        isDone=true;




                    }catch(Exception e){
                        System.out.println("SQL EXCEPTION FOUND:"+e);
                    }finally{
                        try{if (statement != null) { statement.close(); }}
                        catch(Exception a){
                            System.out.println("SQL EXCEPTION FOUND:" +a);
                        }

                    }

                }
        }

    }

    public int getVoterID() {
        return voterID;
    }

    public void setVoterID(int voterID) {
        this.voterID = voterID;
    }

    public Boolean authenticate(int input){
        String hasVotedString;
        Boolean hasntVoted=false;
        int fakequery = 123;

        //For Testing
//        //TODO: Remove before submission
//        if(input == fakequery){
//            return true;
//        }

        try{
            conn=databaseConnector.getConnection();
            String sql = "SELECT hasvoted FROM voterID WHERE ID=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,input);
            rs=statement.executeQuery();
            while(rs.next()) {
                hasVotedString=rs.getString("hasVoted");
                //System.out.println("HAS THIS USER VOTED OUTPUT TEST: " + hasVotedString);
                if(hasVotedString.toLowerCase().equals("true")){
                    hasntVoted=false;
                    return hasntVoted;

                }else if(hasVotedString.toLowerCase().equals("false")){
                    hasntVoted=true;
                    return hasntVoted;

                }
            }



        }catch(Exception e){
            System.out.println("SQL EXCEPTION FOUND:"+e);
        }finally{
            try{if (statement != null) { statement.close(); }}
            catch(Exception a){
                System.out.println("SQL EXCEPTION FOUND:" +a);
            }

        }


//        int fakequery = 123;
//        if(input == fakequery){
//            return true;
//        }
//        else{
//            return false;
//        }
        return hasntVoted;
    }
}