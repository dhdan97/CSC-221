package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

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
    private Button btnNavDel1;

    @FXML
    private Button btnNavNext1;

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
    void buttonClicked(ActionEvent event) {
    	FileChooser filechooser = new FileChooser();
    }

}
