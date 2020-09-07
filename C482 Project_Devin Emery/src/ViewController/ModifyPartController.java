/*
 * C482 Inventory Management Project - Devin Emery
 */
package ViewController;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emery
 */
public class ModifyPartController implements Initializable {
    
    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private ToggleGroup partToggle;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private Text textRadioChange;
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldInventory;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldMax;
    @FXML
    private TextField textFieldMin;
    @FXML
    private TextField textFieldCompMachine;
    @FXML
    private Button partSaveButton;
    @FXML
    private Button partCancelButton;
    
    //Creates ModifyPartController instance of the sampleInv
    Inventory sampleInv;
    Part modifiedPart;
    //Constructor for the controller 
    public ModifyPartController(Inventory sampleInv, Part modifiedPart) {
        this.sampleInv = sampleInv; //saves passed in sampleInv to ModifyPartController's sampleInv
        this.modifiedPart = modifiedPart; //same as sampleInv purpose
    }
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(modifiedPart instanceof InHouse) {
            this.partToggle.selectToggle(this.inhouseRadioButton);
            InHouse currentPart = (InHouse) modifiedPart; //allows to make a InHouse object from a Part object        
            textFieldID.setText(Integer.toString(currentPart.getPartID()));
            textFieldName.setText(currentPart.getPartName());
            textFieldInventory.setText(Integer.toString(currentPart.getPartStock()));
            textFieldPrice.setText(Double.toString(currentPart.getPartPrice()));
            textFieldMax.setText(Integer.toString(currentPart.getPartMax()));
            textFieldMin.setText(Integer.toString(currentPart.getPartMin()));
            textFieldCompMachine.setText(Integer.toString(currentPart.getMachineID()));
        }
        
        else if (modifiedPart instanceof OutSourced) {
            this.partToggle.selectToggle(this.outsourcedRadioButton);
            OutSourced currentPart = (OutSourced) modifiedPart; //allows to make a InHouse object from a Part object        
            textFieldID.setText(Integer.toString(currentPart.getPartID()));
            textFieldName.setText(currentPart.getPartName());
            textFieldInventory.setText(Integer.toString(currentPart.getPartStock()));
            textFieldPrice.setText(Double.toString(currentPart.getPartPrice()));
            textFieldMax.setText(Integer.toString(currentPart.getPartMax()));
            textFieldMin.setText(Integer.toString(currentPart.getPartMin()));
            textFieldCompMachine.setText(currentPart.getCompanyName());
        }
    }    

    @FXML
    private void handleInhouseRadioButton(MouseEvent event) {
        if(this.partToggle.getSelectedToggle().equals(this.inhouseRadioButton)) {
            textRadioChange.setText("Machine ID");
            textFieldCompMachine.setPromptText("Machine ID");
        }
    }

    @FXML
    private void handleOutsourcedRadioButton(MouseEvent event) {
        if(this.partToggle.getSelectedToggle().equals(this.outsourcedRadioButton)) {
            textRadioChange.setText("Company Name");
            textFieldCompMachine.setPromptText("Company Name");
        }
    }

    @FXML
    private void handlePartSaveButton(MouseEvent event) throws IOException {
        if(this.partToggle.getSelectedToggle().equals(this.inhouseRadioButton)) {
            int machID = Integer.parseInt(textFieldCompMachine.getText().trim());
            int partID = Integer.parseInt(textFieldID.getText().trim());
            int partStock = Integer.parseInt(textFieldInventory.getText().trim());
            int partMin = Integer.parseInt(textFieldMin.getText().trim());
            int partMax = Integer.parseInt(textFieldMax.getText().trim());
            String partName = textFieldName.getText().trim();
            double partPrice = Double.parseDouble(textFieldPrice.getText().trim());
            
            Part changedPart = new InHouse(machID, partID, partStock, partMin, partMax, partName, partPrice);
            sampleInv.updatePart(partID, changedPart); //updates current part with changed part data
                      
            handlePartCancelButton(event);
        }
                
        else if(this.partToggle.getSelectedToggle().equals(this.outsourcedRadioButton)) {
            String compName = textFieldCompMachine.getText().trim();
            int partID = Integer.parseInt(textFieldID.getText().trim());
            int partStock = Integer.parseInt(textFieldInventory.getText().trim());
            int partMin = Integer.parseInt(textFieldMin.getText().trim());
            int partMax = Integer.parseInt(textFieldMax.getText().trim());
            String partName = textFieldName.getText().trim();
            double partPrice = Double.parseDouble(textFieldPrice.getText().trim());
            
            Part changedPart = new OutSourced(compName, partID, partStock, partMin, partMax, partName, partPrice);
            sampleInv.updatePart(partID, changedPart); 
            
            handlePartCancelButton(event);
        }
    }

    @FXML
    private void handlePartCancelButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/MainScreen.fxml"));
            ViewController.MainScreenController controller = new ViewController.MainScreenController(sampleInv);
            loader.setController(controller);
            Parent root = loader.load(); //marries view to controller
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false); //sets screen to be resizable
            stage.show(); //show view
    }
}
