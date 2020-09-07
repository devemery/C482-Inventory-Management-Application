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
public class Inventory {
    
    private ObservableList<Part> allParts = FXCollections.observableArrayList();;
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
          
    public void addPart(Part newPart) {
        if(newPart != null) {
            allParts.add(newPart);
        }
    }
    
    public void addProduct(Product newProduct) {
        if(newProduct != null) {
            allProducts.add(newProduct);
        }
    }
    
    public boolean deletePart(Part partToRemove) {
        for(int i = 0; i < allParts.size(); ++i) {
            if(allParts.get(i).getPartID() == partToRemove.getPartID()) {
                allParts.remove(i);
                break;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    public boolean deleteProduct(Product productToRemove) {
        for(int i = 0; i < allProducts.size(); ++i) {
            if(allProducts.get(i).getProductID() == productToRemove.getProductID()) {
                allProducts.remove(i);
                break;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    public Part lookupPart(int partToSearch) {
        if(!allParts.isEmpty()) {
            for(int i = 0; i < allParts.size(); ++i) {
                if(allParts.get(i).getPartID() == partToSearch) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }
    
    public Product lookupProduct(int productToSearch) {
        if(!allProducts.isEmpty()) {
            for(int i = 0; i < allProducts.size(); ++i) {
                if(allProducts.get(i).getProductID() == productToSearch) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    
    public Part lookupPart(String partToSearch) {
        if(!allParts.isEmpty()) {
            for(int i = 0; i < allParts.size(); ++i) {
                if(allParts.get(i).getPartName().equals(partToSearch)) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }
    
    public Product lookupProduct(String productToSearch) {
        if(!allProducts.isEmpty()) {
            for(int i = 0; i < allProducts.size(); ++i) {
                if(allProducts.get(i).getProductName().equals(productToSearch)) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }
    
    public void updatePart(int index, Part selectedPart) {
        for(int i = 0; i < allParts.size(); ++i) {
            if(allParts.get(i).getPartID() == index) {
                allParts.set(i, selectedPart);
                break;
            }
        }
    }
    
    public void updateProduct(int index, Product selectedProduct) {
        for(int i = 0; i < allProducts.size(); ++i) {
            if(allProducts.get(i).getProductID() == index) {
                allProducts.set(i, selectedProduct);
                break;
            }
        }
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
