package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class pollController implements Initializable {

    @FXML
    Label feedbackMsg;

    @FXML
    Button exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startBtnClicked(ActionEvent event) throws IOException {
        Candidate hil = new Candidate();
        hil.setName("Hilary Clinton");
        hil.setOffice("President");
        hil.setParty("Democrat");
        hil.setBio("First failed female democractic nominee");
        Candidate trump = new Candidate();
        trump.setName("The Donald");
        trump.setOffice("President");
        trump.setParty("Alt-Right");
        trump.setBio("I can't bring myself to write this");
        ArrayList <Candidate> DB = new ArrayList();  //TODO (Tanner) getting the arraylist from Tally

        DB.add(hil);
        DB.add(trump);

        //End of sample data
        for (int i = 0; i < DB.size(); i++) {
            Context.getInstance().currentTally().setCandidates( DB.get(i));
        }
        Context.getInstance().currentOfficial().startPoll();
        feedbackMsg.setText("Poll Started!");
    }

    public void endBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().endPoll();
        feedbackMsg.setText("Poll Ended!");
    }

    public void tallyBtnClicked(ActionEvent event) throws IOException {
        Context.getInstance().currentOfficial().tallyResults();
        //TODO: (Tanner) add some kind of feedback
    }

    public void exitBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    //TODO: Recount option

    public void addBtnClicked(ActionEvent event) throws IOException {
        //TODO: (tanner) get through second window Name, party, office, bio, image. Create candidate, then call tally.addCandidate
    }
}
