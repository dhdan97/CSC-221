package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.bind.JAXB;
import javax.xml.bind.UnmarshalException;

import org.xml.sax.SAXParseException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class DirectoryController {
	
	private EmployeeList mainList;

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
    private Button btnExit = new Button();

    @FXML
    void loadButtonClicked(ActionEvent event) throws IOException, UnmarshalException {
    	txtFldName.setDisable(false);
    	txtFldName1.setDisable(false);
    	txtFldName2.setDisable(false);
    	btnNavPrev.setDisable(false);
    	btnNavNext.setDisable(false);
    	btnNavDel.setDisable(false);
    	btnNavAdd.setDisable(false);
    	btnSerialize.setDisable(false);
    	
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
    	
    	try(BufferedReader input = Files.newBufferedReader(selectedFile.toPath())){
    		mainList = JAXB.unmarshal(input, EmployeeList.class);
    	}
    	catch(IOException | RuntimeException exception) {
    		System.err.println("Error opening file");
    		mainList = new EmployeeList();
    	}
    	lblFilename.setText("File: " + selectedFile.getName());
    }
    
    @FXML
    void addButtonClicked(ActionEvent event) {
    	//txtFldName.text
    	//System.out.println("boo");
    	//System.out.println(txtFldName1.getText());
    	//System.out.println(txtFldName2.getText());
    	if(txtFldName.getText().matches("([A-Z]+[a-zA-Z]+[a-zA-Z]+){1}|([A-Z]+[a-zA-Z]+){1}\\s([A-Z]+[a-zA-Z]+[a-zA-Z]+){1}")) {
    		System.out.println("guh huh");
    		//mainList.createNew();
    		
    		
    	}
    	else{
    	
    	}
    	//after checking if entries are valid
    	//Employee employee = new Employee();
    	//mainList.createNew();
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
