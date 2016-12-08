package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class BallotController implements Initializable {

    @FXML
    RadioButton radioA, radioB, radioC, radioD;
    @FXML
    Label officeName;

    int index = 0, lastTail;
    ArrayList <RadioButton> buttons = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Context.getInstance().currentVoter().openBallot(); //When we get into the voting, we start using ballot not tally
        buttons.add(radioA);
        buttons.add(radioB);
        buttons.add(radioC);
        buttons.add(radioD);
        for(int i = 0; i < Context.getInstance().currentBallot().getCandidates().size() && i < 4; i++){
            Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(i);
            buttons.get(i).setText(current.getName());
            lastTail = index;
            index++;
        }


    }

    public void leftBtnClicked(ActionEvent event) throws IOException{
        if(index>=5) {

            ArrayList <Candidate> test = Context.getInstance().currentBallot().getCandidates();
            index= lastTail; // On first set, index now equals 3

            for (RadioButton x : buttons) {
                x.setText("");
                x.setVisible(true);
            }

            //index is currently 4, arraylist spot 3 gotten
            for (int i = 0; i < 4; i++) {
                Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(index);
                buttons.get(buttons.size()-1-i).setText(current.getName()); //I know this is wonky
                index--;
            }

            for (RadioButton x : buttons) {
                if (x.getText().equals("")) {
                    x.setVisible(false);
                }
            }
        }
    }

    public void rightBtnClicked(ActionEvent event) throws IOException{

        if(index<Context.getInstance().currentBallot().getCandidates().size()) {

            for (RadioButton x : buttons) {
                x.setText("");
                x.setVisible(true);
            }

            //Test
            index = lastTail + 1;

            //index is currently 4, arraylist spot 3 gotten
            for (int i = 0; index < Context.getInstance().currentBallot().getCandidates().size() && i < 4; i++) {
                Candidate current = (Candidate) Context.getInstance().currentBallot().getCandidates().get(index);
                buttons.get(i % 4).setText(current.getName()); //% not needed?
                if(i == 3) {
                    lastTail = index;
                }
                index++;
            }

            for (RadioButton x : buttons) {
                if (x.getText().equals("")) {
                    x.setVisible(false);
                }
            }
        }
    }

    public void writeBtnClicked(ActionEvent event) throws IOException{
        Candidate insert = new Candidate();

        //Name
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("Candidate Name");
        dialog.setHeaderText("Enter your write-in");
        dialog.setContentText("Candidate Name: ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            insert.setName(result.get());
        }

        insert.setOffice("Write In");
        insert.setParty("Write In");
        insert.setBio("Write In");

        //Context.getInstance().currentTally().addCandidate(insert);
        Context.getInstance().currentBallot().addWriteIn(insert);
        Context.getInstance().currentBallot().addOfficeSelection(insert.getName());

        Context.getInstance().currentVoter().vote();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Voting Complete");
        alert.setContentText("Democracy thanks you!");
        alert.show();

        Context.getInstance().currentBallot().print();
        Context.getInstance().refresh();
        Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene homeScene = new Scene(parent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homeScene);
        appStage.setFullScreen(true);
        appStage.show();

    }


    public void submitBtnClicked(ActionEvent event) throws IOException{

        Boolean nonEmpty = false;
        ArrayList <RadioButton> candidates  = new ArrayList<RadioButton>();
        candidates.add(radioA);
        candidates.add(radioB);
        candidates.add(radioC);
        candidates.add(radioD);

//
        for (RadioButton current: candidates) {
            if(current.isSelected() == true){
                Context.getInstance().currentBallot().addOfficeSelection(current.getText());
                nonEmpty = true;
            }
        }

        if(nonEmpty == true) {
            Context.getInstance().currentVoter().vote();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Voting Complete");
            alert.setContentText("Democracy thanks you!");
            alert.show();

            //TODO: (Aaron) set voter to having voted in DB
            try{

            }
            catch(Exception e){

            }finally{

            }

            Context.getInstance().currentBallot().print();

            Context.getInstance().refresh();
            Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene homeScene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homeScene);
            //appStage.setFullScreen(true);
            appStage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Invalid ballot");
            alert.setContentText("You must select a candidate!");
            alert.show();
        }


    }

}
