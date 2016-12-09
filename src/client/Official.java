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



    public Boolean authenticate(String input){

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


    }
}