/*
 * C482 Inventory Management Project - Devin Emery
 */
package Model;

/**
 *
 * @author emery
 */
public class Part {
    int partID, partStock, partMin, partMax;
    String partName;
    double partPrice;

    public Part(int partID, int partStock, int partMin, int partMax, String partName, double partPrice) {
        this.partID = partID;
        this.partStock = partStock;
        this.partMin = partMin;
        this.partMax = partMax;
        this.partName = partName;
        this.partPrice = partPrice;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public int getPartStock() {
        return partStock;
    }

    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    public int getPartMin() {
        return partMin;
    }

    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    public int getPartMax() {
        return partMax;
    }

    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
}
