package client;
import java.io.PrintWriter;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class pollController implements Initializable {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement statement=null;

    @FXML
    Label feedbackMsg;

    @FXML
    Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startBtnClicked(ActionEvent event) throws IOException {

        if(!(Context.getInstance().currentTally().getCandidates().size() >=1)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Missing Candidates");
            alert.setContentText("You must add at least one candidate!");
            alert.show();
        }else{
            Context.getInstance().currentOfficial().startPoll();
            feedbackMsg.setText("Poll Started!");
        }

    }

    public void endBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().endPoll();
        feedbackMsg.setText("Poll Ended!");
        String tallyResults="TallyResults:  ";
        System.out.println("End Button Pressed");
        try{

            conn=databaseConnector.getConnection();
            String sql = "SELECT name,tempVotes FROM Candidate";
            stmt=conn.createStatement();

            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                String tempName = rs.getString("name");
                int tempLocalVotes=rs.getInt("tempVotes");
                if(tempLocalVotes!=0){
                    //System.out.println("WE ARE ADDING LOCAL TO TOTAL IF SOMEONE VOTED FOR THEM AT LOCAL LEVEL");
                    String sql1="UPDATE Candidate SET totalVotes=totalVotes+?,tempVotes=0 WHERE name=?;";
                    statement=conn.prepareStatement(sql1);
                    statement.setInt(1,tempLocalVotes);
                    statement.setString(2,tempName);
                    statement.executeUpdate();
                }
                tallyResults +="[Name: "+tempName+" Local Votes: "+tempLocalVotes+"]";
            }
            //System.out.println("Adding to the tally table?");
            String sql2="INSERT INTO tally (results,pollOver) VALUES (?,'True')";
            statement=conn.prepareStatement(sql2);
            statement.setString(1,tallyResults);
            statement.executeUpdate();
            //System.out.println(tallyResults);




        }catch(Exception e){
            System.out.println("SQL EXCEPTION FOUND:"+e);
        }finally{
            try{if (statement != null) { statement.close(); }}
            catch(Exception a){
                System.out.println("SQL EXCEPTION FOUND:" +a);
            }

        }





    }

    public void recountBtnClicked(ActionEvent event) throws IOException{
        try{
            PrintWriter writer = new PrintWriter("recount.txt", "UTF-8");
            conn=databaseConnector.getConnection();
            String sql = "SELECT name,totalVotes FROM candidate";

            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                String tempName=rs.getString("name");
                int tempTotalVotes=rs.getInt("totalVotes");
                writer.println("TOTAL VOTES:\n"+"[Name:"+tempName+" totalVotes: "+tempTotalVotes+"]\n");

            }
            String sql2="SELECT * from ballots";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql2);
            while(rs.next()){
                int tempVID=rs.getInt("ID");
                String tempCand=rs.getString("votes");
                writer.println("Voter ID: "+tempVID+"  Choice: "+tempCand);
            }

            writer.close();
        }catch(Exception e){
            System.out.println("SQL EXCEPTION FOUND:"+e);
        }finally{
            try{if (statement != null) { statement.close(); }}
            catch(Exception a){
                System.out.println("SQL EXCEPTION FOUND:" +a);
            }

        }
    }

    public void tallyBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().tallyResults();
        int tempRecordCount=-1;
        String tempResults="";

        try{
            PrintWriter writer = new PrintWriter("Tally.txt", "UTF-8");
            conn=databaseConnector.getConnection();
            String sql = "SELECT ID from tally";

            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                tempRecordCount=rs.getInt("ID");
            }
            if(tempRecordCount!=-1) {
                String sql2 = "SELECT results FROM tally WHERE ID=?";
                statement = conn.prepareStatement(sql2);
                statement.setInt(1, tempRecordCount);
                rs=statement.executeQuery();
                while(rs.next()){
                    tempResults=rs.getString("results");
                }

                //System.out.println(tempResults);
                writer.println(tempResults);
                writer.close();

            }

        }catch(Exception e){
            System.out.println("SQL EXCEPTION FOUND:"+e);
        }finally{
            try{if (statement != null) { statement.close(); }}
            catch(Exception a){
                System.out.println("SQL EXCEPTION FOUND:" +a);
            }

        }
    }

    public void exitBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void addBtnClicked(ActionEvent event) throws IOException {
        Candidate insert = new Candidate();

        //Name
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("Candidate Name");
        dialog.setHeaderText("New Candidate Part 1");
        dialog.setContentText("Candidate Name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setName(result.get());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Candidate Add Cancelled");
            alert.setContentText("If you made a mistake, add the candidate again ");
            alert.show();
            return;
        }

        //Office
        dialog = new TextInputDialog("Office");
        dialog.setTitle("Candidate Office");
        dialog.setHeaderText("New Candidate Part 2");
        dialog.setContentText("What they are running for: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setOffice(result.get());
        }

        //Party
        dialog = new TextInputDialog("Party");
        dialog.setTitle("Candidate Party");
        dialog.setHeaderText("New Candidate Part 3");
        dialog.setContentText("Party: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setParty(result.get());
        }

        //Bio
        dialog = new TextInputDialog("Bio");
        dialog.setTitle("Candidate Bio");
        dialog.setHeaderText("New Candidate Part 4");
        dialog.setContentText("Candidate Bio: ");

        result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setBio(result.get());
        }

        Context.getInstance().currentTally().addCandidate(insert);

    }
}
