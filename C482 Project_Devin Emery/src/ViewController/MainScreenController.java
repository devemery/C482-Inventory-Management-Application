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
import javafx.application.Platform;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emery
 */
public class MainScreenController implements Initializable {

    @FXML
    private Button partSearchButton;
    @FXML
    private TextField partSearchField;
    @FXML
    private Button partAddButton;
    @FXML
    private Button partDeleteButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button productSearchButton;
    @FXML
    private TextField productSearchField;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productDeleteButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button mainScreenExit;
    
    //Configure the table
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partIDCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partStockCol;
    @FXML private TableColumn<Part, Double> partPriceCol;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productIDCol;
    @FXML private TableColumn<Product, String> productNameCol;
    @FXML private TableColumn<Product, Integer> productStockCol;
    @FXML private TableColumn<Product, Double> productPriceCol;
    
    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventorySearch = FXCollections.observableArrayList();
    
    //Creates MainController instance of the sampleInv
    Inventory sampleInv;
    //Constructor for the controller 
    public MainScreenController(Inventory sampleInv) {
        this.sampleInv = sampleInv; //saves passed in sampleInv to MainController's sampleInv
    }
    
    //Initializes the controller class.
    @Override //populates tables with default inventory
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize columns
        partIDCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("partName"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("partPrice"));
                
        productIDCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
        
        generatePartsTable();
        generateProductsTable();
    }

    private void generatePartsTable() {
        partInventory.setAll(sampleInv.getAllParts());
                               
        partTable.setItems(partInventory);
        partTable.refresh();
    }
    
    private void generateProductsTable() {
        productInventory.setAll(sampleInv.getAllProducts());
        
        productTable.setItems(productInventory);
        productTable.refresh();
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
    private void handlePartAddButton(MouseEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/AddPart.fxml"));
            ViewController.AddPartController controller = new ViewController.AddPartController(sampleInv);
            loader.setController(controller);
            Parent root = loader.load(); //marries view to controller
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false); //sets screen to be resizable
            stage.show(); //show view
    }

    @FXML
    private void handlePartDeleteButton(MouseEvent event) {
        Part partToRemove = partTable.getSelectionModel().getSelectedItem();
        partInventory.remove(partToRemove);
        partTable.refresh();
        sampleInv.deletePart(partToRemove);
    }

    @FXML
    private void handlePartModifyButton(MouseEvent event) throws IOException {
        Part modifiedPart = partTable.getSelectionModel().getSelectedItem(); //gets selected part instance in the table column and saves it to variable
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/ModifyPart.fxml"));
            ViewController.ModifyPartController controller = new ViewController.ModifyPartController(sampleInv, modifiedPart);
            loader.setController(controller);
            Parent root = loader.load(); //marries view to controller
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false); //sets screen to be resizable
            stage.show(); //show view
    }

    @FXML
    private void handleProductSearchButton(MouseEvent event) {
        if(!productSearchField.getText().trim().equals("")) {
           if(productSearchField.getText().trim().matches("-?\\d+")) {
            int productID = Integer.parseInt(productSearchField.getText().trim());
            productInventorySearch.setAll(sampleInv.lookupProduct(productID));
            productTable.setItems(productInventorySearch);
            productTable.refresh();
            } 
           else {
            String productName = productSearchField.getText().trim();
            productInventorySearch.setAll(sampleInv.lookupProduct(productName));
            productTable.setItems(productInventorySearch);
            productTable.refresh();
            }
        }
        else {
            productTable.setItems(productInventory);
            productTable.refresh();
        }
        
    }

    @FXML
    private void handleProductAddButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/AddProduct.fxml"));
            ViewController.AddProductController controller = new ViewController.AddProductController(sampleInv);
            loader.setController(controller);
            Parent root = loader.load(); //marries view to controller
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false); //sets screen to be resizable
            stage.show(); //show view
    }

    @FXML
    private void handleProductDeleteButton(MouseEvent event) {
        Product productToRemove = productTable.getSelectionModel().getSelectedItem();
        productInventory.remove(productToRemove);
        productTable.refresh();
        sampleInv.deleteProduct(productToRemove);
        
    }

    @FXML
    private void handleProductModifyButton(MouseEvent event) throws IOException {
        Product modifiedProduct = productTable.getSelectionModel().getSelectedItem(); //gets selected part instance in the table column and saves it to variable
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/ModifyProduct.fxml"));
            ViewController.ModifyProductController controller = new ViewController.ModifyProductController(sampleInv, modifiedProduct);
            loader.setController(controller);
            Parent root = loader.load(); //marries view to controller
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false); //sets screen to be resizable
            stage.show(); //show view
    }

    @FXML
    private void handleMainScreenExit(MouseEvent event) {
        Platform.exit();
    }
}
