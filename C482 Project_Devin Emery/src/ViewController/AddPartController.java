/*
 * C482 Inventory Management Project - Devin Emery
 */
package ViewController;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emery
 */
public class AddPartController implements Initializable {

    @FXML
    private ToggleGroup partToggle;
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
    @FXML
    private RadioButton inhouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    
    //Creates AddPartController instance of the sampleInv
    Inventory sampleInv;
    
    //Constructor for the controller 
    public AddPartController(Inventory sampleInv) {
        this.sampleInv = sampleInv; //saves passed in sampleInv to AddPartController's sampleInv
    }
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.partToggle.selectToggle(this.inhouseRadioButton);
        textFieldCompMachine.setPromptText("Machine ID");
    }    

    @FXML
    private void handlePartSaveButton(MouseEvent event) throws IOException {
        if(this.partToggle.getSelectedToggle().equals(this.inhouseRadioButton)) {
            int machID = Integer.parseInt(textFieldCompMachine.getText().trim());
            int partID = sampleInv.getAllParts().size()+1;
            int partStock = Integer.parseInt(textFieldInventory.getText().trim());
            int partMin = Integer.parseInt(textFieldMin.getText().trim());
            int partMax = Integer.parseInt(textFieldMax.getText().trim());
            String partName = textFieldName.getText().trim();
            double partPrice = Double.parseDouble(textFieldPrice.getText().trim());
            
            sampleInv.addPart(new InHouse(machID, partID, partStock, partMin, partMax, partName, partPrice));
            
            handlePartCancelButton(event);
        }
                
        else if(this.partToggle.getSelectedToggle().equals(this.outsourcedRadioButton)) {
            String compName = textFieldCompMachine.getText();
            int partID = sampleInv.getAllParts().size() + 1;
            int partStock = Integer.parseInt(textFieldInventory.getText());
            int partMin = Integer.parseInt(textFieldMin.getText());
            int partMax = Integer.parseInt(textFieldMax.getText());
            String partName = textFieldName.getText();
            double partPrice = Double.parseDouble(textFieldPrice.getText());
            
            sampleInv.addPart(new OutSourced(compName, partID, partStock, partMin, partMax, partName, partPrice));
            
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
}
