/*
 * C482 Inventory Management Project - Devin Emery
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emery
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;
    private int productID, productStock, productMin, productMax;
    String productName;
    double productPrice;

    public Product(int productID, int productStock, int productMin, int productMax, String productName, double productPrice) {
        this.productID = productID;
        this.productStock = productStock;
        this.productMin = productMin;
        this.productMax = productMax;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getProductMin() {
        return productMin;
    }

    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    public int getProductMax() {
        return productMax;
    }

    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public void addAssociatedPart(Part part) {
        if(part != null) {
            this.associatedParts.add(part);
        }
    }
    
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for(int i = 0; i < associatedParts.size(); ++i) {
            if(associatedParts.get(i).getPartID() == selectedAssociatedPart.getPartID()) {
               associatedParts.remove(i);
               break;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
