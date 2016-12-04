package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BallotController implements Initializable {

    @FXML
    RadioButton radioA, radioB, radioC, radioD;
    @FXML
    Label officeName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //TODO: (Tanner) button handler method. If selected, popup verification and then create ballot and submit to DB
}
