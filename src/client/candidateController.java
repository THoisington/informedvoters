package client;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class candidateController implements Initializable{

    @FXML
    Label candidateOneName,candidateOneParty,candidateTwoName,candidateTwoParty;
    @FXML
    TextArea candidateOneBio,candidateTwoBio;
    //The other GUI elements too

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList <Candidate> db = Context.getInstance().currentTally().getCandidates();

        //TODO: Add getOffice() and arrow methods
        candidateOneName.setText(db.get(0).getName());
        candidateOneParty.setText(db.get(0).getParty());
        candidateOneBio.setText(db.get(0).getBio());
        candidateTwoName.setText(db.get(1).getName());
        candidateTwoParty.setText(db.get(1).getParty());
        candidateTwoBio.setText(db.get(1).getBio());
    }

    public void proceedBtnClicked(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ballotController.fxml"));
        Scene ballotScene = new Scene(parent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(ballotScene);
        //appStage.setFullScreen(true);
        appStage.show();
    }
}