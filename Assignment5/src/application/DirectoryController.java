package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Optional;

import javax.xml.bind.JAXB;
import javax.xml.bind.UnmarshalException;
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
	private int currRecord = 0;

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
    private TextField txtFldCompany;

    @FXML
    private TextField txtFldExtension;

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
    	txtFldCompany.setDisable(false);
    	txtFldExtension.setDisable(false);
    	btnNavPrev.setDisable(false);
    	btnNavNext.setDisable(false);
    	btnNavDel.setDisable(false);
    	btnNavAdd.setDisable(false);
    	btnSerialize.setDisable(false);
    	
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
    	
    	try(BufferedReader input = Files.newBufferedReader(selectedFile.toPath())){
    		mainList = JAXB.unmarshal(input, EmployeeList.class);
    		currRecord = 1;
    		txtFldName.setText(mainList.getLst().get(0).getName());
    		txtFldCompany.setText(mainList.getLst().get(0).getDepartment());
    		txtFldExtension.setText(mainList.getLst().get(0).getExtension());
    		btnNavPrev.setDisable(true);
    		lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());	
    	}
    	catch(IOException | RuntimeException exception) {
    		System.err.println("Error opening file");
    		mainList = new EmployeeList();
    		btnNavPrev.setDisable(true);
    		btnNavNext.setDisable(true);
    		btnNavDel.setDisable(true);
    		lblCurrRecord.setText("0 of 0");
    	}
    	lblFilename.setText("File: " + selectedFile.getName());
    }
    
    @FXML
    void SerializeButtonClicked(ActionEvent event) {
    	int  a = addButtonClicked(null);//save current record before moving on
    	
    	
    	if(a == 1) {
    		FileChooser fileChooser = new FileChooser();
    		File selectedFile = fileChooser.showOpenDialog(borderPane.getScene().getWindow());
    		try(BufferedWriter output = Files.newBufferedWriter(selectedFile.toPath())){
    		
    			JAXB.marshal(mainList, output);
    		}
    		catch(IOException | RuntimeException exception) {
    			//deal with exception
    		}
    	}
    	
    }    
    
    @FXML
    int addButtonClicked(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Value");
		
    	if(!txtFldName.getText().matches("([A-Z]+[a-zA-Z]+[a-zA-Z]+){1}|([A-Z]+[a-zA-Z]+){1}\\s([A-Z]+[a-zA-Z]+[a-zA-Z]+){1}")) {
    		alert.setHeaderText("Invalid Name.\n"
    				+ "Names can be one or two words.\n"
    				+ "1. Each word should start with an uppercase letter followed by at least two characters.\n"
    				+ "2. Numbers are not allowed.");
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.OK)
        		alert.close();
    		return 0;
    	}
    	else if(!txtFldCompany.getText().matches("([A-Z]+[0-9a-zA-Z]*){1}|([A-Z]+[0-9a-zA-Z]*){1}\\s([A-Z]+[0-9a-zA-Z]*){1}")){
    		alert.setHeaderText("Invalid Department/Department Name.\n"
    				+ "Department can be one or two words.\n"
    				+ "1. Each word should start with an uppercase letter. A word can be a single uppercase letter\n"
    				+ "2. Numbers are allowed.");
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.OK)
        		alert.close();
    		return 0;
    	}
    	else if(!txtFldExtension.getText().matches("([0-9]{1}|[0-9]{2}|[0-9]{3})"
    			+ "-" + "([0-9]{1}|[0-9]{2})")) {
    		alert.setHeaderText("Invalid Extension.\n"
    				+ "Extension can only start with 1, 2, or 3 numbers followed by a - followed by 1 or 2 numbers");  
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.OK)
        		alert.close();
    		return 0;
    	}
    	else {
    		Employee newEmployee = new Employee(txtFldName.getText(), txtFldCompany.getText(), txtFldExtension.getText());
    		mainList.createNew(newEmployee);
    		txtFldName.clear();
    		txtFldCompany.clear();
    		txtFldExtension.clear();
    		if(mainList.getLst().size() == 1) {
    			currRecord = mainList.getLst().size();
    			lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());
    		
    			btnNavPrev.setDisable(false);
    			btnNavNext.setDisable(true);	
    		}
    		else {currRecord = mainList.getLst().size()-1;
    			lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());
    		
    			btnNavPrev.setDisable(false);
    			btnNavNext.setDisable(true);
    		}
    		
    		return 1;
    	}
    }
    
    @FXML
    void removeButtonClicked(ActionEvent event) {
    	if(mainList.getLst().size() == 1) {
    		btnNavPrev.setDisable(true);
    		currRecord = 0;
    		mainList.getLst().remove(0);
    		lblCurrRecord.setText("0" + " of " + mainList.getLst().size());
    		txtFldName.clear();
    		txtFldCompany.clear();
    		txtFldExtension.clear();   		
    	}
    	
    	else{
    		int indPrevEmployee = currRecord;
    		int indEmployeeToRemove = -1;

    		for (Iterator<Employee> iterator = mainList.getLst().iterator(); iterator.hasNext(); ) {
    			Employee e = iterator.next();
		    
    			if(e.getName().equals(txtFldName.getText()) 
    					&& e.getDepartment().equals(txtFldCompany.getText())
    					&& e.getExtension().equals(txtFldExtension.getText())) {
    				indEmployeeToRemove = mainList.getLst().indexOf(e);
    				}
    			}
    		
    		if(indEmployeeToRemove != -1) {
    			mainList.getLst().remove(indEmployeeToRemove);
    			lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());//currRecord should equal indPrevEmployee
    			txtFldName.setText(mainList.getLst().get(indPrevEmployee-1).getName());
    			txtFldCompany.setText(mainList.getLst().get(indPrevEmployee-1).getDepartment());
    			txtFldExtension.setText(mainList.getLst().get(indPrevEmployee-1).getExtension());
    			}
    		}
    }
    
    @FXML
    void prevButtonClicked(ActionEvent event) {
    	btnNavNext.setDisable(false);
    	
    	currRecord--;
    	if(currRecord == 1)
    		btnNavPrev.setDisable(true);
    	
    	
    	Employee currEmployee = mainList.getLst().get(currRecord);
    	txtFldName.setText(currEmployee.getName());
		txtFldCompany.setText(currEmployee.getDepartment());
		txtFldExtension.setText(currEmployee.getExtension());
		lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());
    }
    
    @FXML
    void nextButtonClicked(ActionEvent event) {
    	btnNavPrev.setDisable(false);
    	
    	currRecord++;
    	if(currRecord + 1 > mainList.getLst().size())
    		btnNavNext.setDisable(true);
    	
    	
    	Employee currEmployee = mainList.getLst().get(currRecord-1);
    	txtFldName.setText(currEmployee.getName());
		txtFldCompany.setText(currEmployee.getDepartment());
		txtFldExtension.setText(currEmployee.getExtension());
		lblCurrRecord.setText(currRecord + " of " + mainList.getLst().size());
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
