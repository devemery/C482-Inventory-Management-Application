/*
 * C482 Inventory Management Project - Devin Emery
 */
package ViewController;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emery
 */
public class AddProductController implements Initializable {
    
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TextField partSearchField;
    @FXML
    private Button partAddButton;
    @FXML
    private Button partSearchButton;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partStockCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> associatedPartTable;
    @FXML
    private TableColumn<Part, Integer> associatedpartIDCol;
    @FXML
    private TableColumn<Part, String> associatedpartNameCol;
    @FXML
    private TableColumn<Part, Integer> associatedpartStockCol;
    @FXML
    private TableColumn<Part, Double> associatedpartPriceCol;
    @FXML
    private Button partDeleteButton;
    @FXML
    private Button productCancelButton;
    @FXML
    private Button productSaveButton;
    @FXML
    private TextField textFieldMin;
    @FXML
    private TextField textFieldMax;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldInventory;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldID;
    @FXML
    private AnchorPane errorBox; 
    @FXML
    private Button errorOKButton;
    @FXML
    private AnchorPane priceErrorBox;
    @FXML
    private Button priceErrorOKButton;
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> associatedPartInventory = FXCollections.observableArrayList();
        
    //Creates AddProductController instance of the sampleInv
    Inventory sampleInv;
    //Constructor for the controller 
    public AddProductController(Inventory sampleInv) {
        this.sampleInv = sampleInv; //saves passed in sampleInv to AddProductController's sampleInv
    }
    
    //Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
        
        associatedpartIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        associatedpartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        associatedpartStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partStock"));
        associatedpartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
        
        generatePartsTable();
        generateAssociatedPartsTable();
    }    

    private void generatePartsTable() {
        partInventory.setAll(sampleInv.getAllParts());
                               
        partTable.setItems(partInventory);
        partTable.refresh();
    }
    
    private void generateAssociatedPartsTable() {
              
        associatedPartTable.setItems(associatedPartInventory);
        associatedPartTable.refresh();
    }
    
    @FXML
    private void handlePartAddButton(MouseEvent event) {
        Part addPart = partTable.getSelectionModel().getSelectedItem(); //gets selected part instance in the part table and adds it to the associated parts table
        associatedPartInventory.add(addPart);
        generateAssociatedPartsTable();
    }

    @FXML
    private void handlePartSearchButton(MouseEvent event) {
        if(!partSearchField.getText().trim().equals("")) {
           if(partSearchField.getText().trim().matches("-?\\d+")) {
            int partID = Integer.parseInt(partSearchField.getText().trim());
            partInventorySearch.setAll(sampleInv.lookupPart(partID));
            partTable.setItems(partInventorySearch);
            partTable.refresh();
            } 
           
           else {
            String partName = partSearchField.getText().trim();
            partInventorySearch.setAll(sampleInv.lookupPart(partName));
            partTable.setItems(partInventorySearch);
            partTable.refresh();
            }
        }
        else {
            partTable.setItems(partInventory);
            partTable.refresh();
        }
           
    }

    @FXML
    private void handlePartDeleteButton(MouseEvent event) {
        Part deletePart = associatedPartTable.getSelectionModel().getSelectedItem();
        associatedPartInventory.remove(deletePart);
        generateAssociatedPartsTable();
    }

    @FXML
    private void handleProductCancelButton(MouseEvent event) throws IOException {
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
    private void handleProductSaveButton(MouseEvent event) throws IOException {
        double productPrice = Double.parseDouble(textFieldPrice.getText().trim());
        
        if (associatedPartInventory.isEmpty()) {
            errorBox.setVisible(true);
            productSaveButton.setDisable(true);
            productCancelButton.setDisable(true);
            partAddButton.setDisable(true);
            partSearchButton.setDisable(true);
            partDeleteButton.setDisable(true);
        }
        else if (!productPriceCompare(productPrice)) {
            priceErrorBox.setVisible(true);
            productSaveButton.setDisable(true);
            productCancelButton.setDisable(true);
            partAddButton.setDisable(true);
            partSearchButton.setDisable(true);
            partDeleteButton.setDisable(true);
        }
        else {
        //get all from associatedPartsInventory and addAssociatedPart method after creating and adding product
        int productID = sampleInv.getAllProducts().size()+1;
        int productStock = Integer.parseInt(textFieldInventory.getText().trim());
        int productMin = Integer.parseInt(textFieldMin.getText().trim());
        int productMax = Integer.parseInt(textFieldMax.getText().trim());
        String productName = textFieldName.getText().trim();
                
        Product newProduct = new Product(productID, productStock, productMin, productMax, productName, productPrice);
        
        for(int i = 0; i < associatedPartInventory.size(); ++i) {
            newProduct.addAssociatedPart(associatedPartInventory.get(i));
        }
        
        sampleInv.addProduct(newProduct);
        
        handleProductCancelButton(event);
        }
    }
    @FXML
    void handleErrorOKButton(MouseEvent event) {
        errorBox.setVisible(false);
        productSaveButton.setDisable(false);
        productCancelButton.setDisable(false);
        partAddButton.setDisable(false);
        partSearchButton.setDisable(false);
        partDeleteButton.setDisable(false);

    }
    
    @FXML
    void handlePriceErrorOKButton(MouseEvent event) {
        priceErrorBox.setVisible(false);
        productSaveButton.setDisable(false);
        productCancelButton.setDisable(false);
        partAddButton.setDisable(false);
        partSearchButton.setDisable(false);
        partDeleteButton.setDisable(false);

    }
    
    boolean productPriceCompare(double productPrice) {
        double totalPartPrice;
        totalPartPrice = 0;
        for(int i = 0; i < associatedPartInventory.size(); ++i) {
            totalPartPrice += associatedPartInventory.get(i).getPartPrice();
        }
        return productPrice >= totalPartPrice;
    }
}
