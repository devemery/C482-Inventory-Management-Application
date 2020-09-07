/*
 * C482 Inventory Management Project - Devin Emery
 */
package Model;

/**
 *
 * @author emery
 */
public class OutSourced extends Part {
    
    String companyName;

    public OutSourced(String companyName, int partID, int partStock, int partMin, int partMax, String partName, double partPrice) {
        super(partID, partStock, partMin, partMax, partName, partPrice);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
