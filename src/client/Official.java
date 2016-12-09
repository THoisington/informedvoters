package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

/**
 * Created by hoisi on 10/26/2016.
 */
public class Official {
    int voterID;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement statement=null;
    //private static final String ps = "Pothering";

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
//    public void recount(){
//        int ballotsByVoters=-1;
//        int totalVotes=-1;
//        try {
//            conn = databaseConnector.getConnection();
//            String sql = "SELECT COUNT(voterID.ID) as total FROM VoterID INNER JOIN ballots ON ballots.ID=VoterID.ID WHERE VoterID.hasVoted='true';\n";
//
//            stmt = conn.createStatement();
//
//
//            rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                ballotsByVoters=rs.getInt("total");
//
//            }
//            String sql2="SELECT SUM(totalVotes) as total1 FROM candidate";
//            stmt=conn.createStatement();
//            rs=statement.executeQuery(sql2);
//            while(rs.next()){
//                totalVotes=rs.getInt("total1");
//
//            }
//            if(totalVotes!=-1 && totalVotes==ballotsByVoters){
//                System.out.println("The total of all votes for all candidates and the numbers of voters voted seems to match up, results appear to be fine");
//            }else{
//                System.out.println("Possible miscount in the votes,recount needed, print ballots");
//            }
//
//
//
//
//        } catch (Exception e) {
//            System.out.println("SQL EXCEPTION FOUND:" + e);
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//            } catch (Exception a) {
//                System.out.println("SQL EXCEPTION FOUND:" + a);
//            }
//
//        }
//    }

    public Boolean authenticate(String input){
        //TODO: THIS IS JUST SO YOU CAN SEE THE AUTHENTICATE METHOD FOR OFFICIAL
        String tokens[]=input.split("-");
        if(tokens.length==2) {
            String tempInput = tokens[1];
            int tempID = Integer.parseInt(tempInput);
            try {
                conn = databaseConnector.getConnection();
                String sql = "SELECT * FROM official WHERE ID=?";

                statement = conn.prepareStatement(sql);
                statement.setInt(1, tempID);
                rs = statement.executeQuery();
                if (!rs.next()) {
                    return false;
                } else {
                    return true;
                }


            } catch (Exception e) {
                System.out.println("SQL EXCEPTION FOUND:" + e);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception a) {
                    System.out.println("SQL EXCEPTION FOUND:" + a);
                }

            }
        }
        return false;

//        if(input.equals(ps)){
//            return true;
//        }
//        else{
//            return false;
//        }
    }
}