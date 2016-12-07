package client;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by hoisi on 10/27/2016.
 */
public class Tally {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement statement=null;

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


    public ArrayList<Candidate> getCandidates() throws SQLException {

        return candidates;
    }


    public void addCandidate(Candidate x){
//        conn=databaseConnector.getConnection();
//        String name1=x.getName();
//        String bio1=x.getBio();
//        String party1=x.getParty();
//        String office1=x.getOffice();
//
//        String sql = "INSERT INTO Candidate(name,office,party,bio) VALUES(name1,office1,party1,bio1)";

        //TODO: (Aaron-Done?) If not in DB, add to Candidate table

        try{
            conn=databaseConnector.getConnection();
            String name1=x.getName();
            String bio1=x.getBio();
            String party1=x.getParty();
            String office1=x.getOffice();

            String sql = "INSERT INTO Candidate(name,office,party,bio) VALUES(?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name1);
            statement.setString(2,office1);
            statement.setString(3, party1);
            statement.setString(4, bio1);
            statement.executeUpdate();
            candidates.add(x);

        }
        catch(SQLException e){
            System.out.println("SQL exception occured" + e);
        }finally {
            try{if (statement != null) { statement.close(); }}
            catch(Exception a){
                System.out.println("SQL EXCEPTION FOUND"+a);
            }
        }
        //candidates.add(x);
    }
}