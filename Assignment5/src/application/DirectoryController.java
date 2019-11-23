package application;


import java.io.File;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class DirectoryController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField txtFldName;

    @FXML
    private Label lblCurrRecord;

    @FXML
    private Button btnNavDel;

    @FXML
    private Button btnNavAdd;

    @FXML
    private Button btnNavPrev;

    @FXML
    private Button btnNavNext;

    @FXML
    private TextField txtFldName1;

    @FXML
    private TextField txtFldName2;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnSerialize;

    @FXML
    private Label lblFilename;

    @FXML
    private Button btnExit;

    @FXML
    void loadButtonClicked(ActionEvent event) {
    	txtFldName.setDisable(false);
    	txtFldName1.setDisable(false);
    	txtFldName2.setDisable(false);
    	btnNavPrev.setDisable(false);
    	btnNavNext.setDisable(false);
    	btnNavDel.setDisable(false);
    	btnNavAdd.setDisable(false);
    	btnSerialize.setDisable(false);
    	
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(null);
    }
    
    @FXML
    void exitButtonClicked(ActionEvent event) {      
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
    	
    	Optional<ButtonType> result = alert.showAndWait();

    	if(result.get() == ButtonType.OK) {
    		Platform.exit();
    	}
    	else if(result.get() == ButtonType.CANCEL) {
    		alert.close();
    	}
    }
}
