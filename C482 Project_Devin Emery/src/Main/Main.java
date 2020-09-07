/*
 * C482 Inventory Management Project - Devin Emery
 */
package Main;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author emery
 */
public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage stage) throws Exception {
        Inventory sampleInv = new Inventory();
        addTestData(sampleInv);
        
        //Load View and Set Controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewController/MainScreen.fxml"));
        ViewController.MainScreenController controller = new ViewController.MainScreenController(sampleInv);
        loader.setController(controller);
        Parent root = loader.load(); //marries view to controller
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false); //sets screen to be resizable
        stage.show(); //show view
    }
    
    void addTestData(Inventory sampleInv) {
        //Add InHouse Parts
        Part a1 = new InHouse(101, 1, 10, 5, 100, "Part A1", 2.99);
        Part a2 = new InHouse(102, 2, 10, 5, 100, "Part A2", 2.99);
        Part b = new InHouse(103, 3, 10, 5, 100, "Part B", 2.99);
        Part a3 = new InHouse(104, 4, 10, 5, 100, "Part A3", 2.99);
        Part a4 = new InHouse(105, 5, 10, 5, 100, "Part A2", 2.99);
        sampleInv.addPart(a1);
        sampleInv.addPart(a2);
        sampleInv.addPart(b);
        sampleInv.addPart(a3);
        sampleInv.addPart(a4);
        //Add OutSourced Parts
        Part o1 = new OutSourced("ACME Co.", 6, 10, 5, 100,"Part O1", 2.99);
        Part p = new OutSourced("ACME Co.", 7, 10, 5, 100,"Part P", 2.99);
        Part q = new OutSourced("ACME Co.", 8, 10, 5, 100,"Part Q", 2.99);
        Part r = new OutSourced("ACME Co.", 9, 10, 5, 100,"Part R", 2.99);
        Part o2 = new OutSourced("ACME Co.", 10, 10, 5, 100,"Part O2", 2.99);
        sampleInv.addPart(o1);
        sampleInv.addPart(p);
        sampleInv.addPart(q);
        sampleInv.addPart(r);
        sampleInv.addPart(o2);
        //Add Products
        Product prod1 = new Product(1, 20, 5, 100, "Product 1", 9.99);
        prod1.addAssociatedPart(a1);
        prod1.addAssociatedPart(o1);
        sampleInv.addProduct(prod1);
        Product prod2 = new Product(2, 20, 5, 100, "Product 2", 9.99);
        prod2.addAssociatedPart(a2);
        prod2.addAssociatedPart(p);
        sampleInv.addProduct(prod2);
        Product prod3 = new Product(3, 20, 5, 100, "Product 3", 9.99);
        prod3.addAssociatedPart(b);
        prod3.addAssociatedPart(q);
        sampleInv.addProduct(prod3);
        sampleInv.addProduct(new Product(4, 20, 5, 100, "Product 4", 9.99));
        sampleInv.addProduct(new Product(5, 20, 5, 100, "Product 5", 9.99));
    }
}
