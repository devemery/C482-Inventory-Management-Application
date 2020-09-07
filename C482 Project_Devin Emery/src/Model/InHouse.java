/*
 * C482 Inventory Management Project - Devin Emery
 */
package Model;

/**
 *
 * @author emery
 */
public class InHouse extends Part {
    
    int machineID;

    public InHouse(int machineID, int partID, int partStock, int partMin, int partMax, String partName, double partPrice) {
        super(partID, partStock, partMin, partMax, partName, partPrice);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
    
}
