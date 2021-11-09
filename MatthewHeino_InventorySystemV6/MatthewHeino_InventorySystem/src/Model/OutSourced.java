package Model;

/**
 * Defines a child class of the Parent class part. This class models the methods 
 * and data that is associated with a part that is outsourced.  Methods 
 * are inherited from the parent class of Part. This class adds the data member
 * that will hold the company name of the part produced.
 * 
 * @author Matthew Heino
 */
public class OutSourced extends Part{
    
    private String companyName;    // Nmae of the company tht produced the part.

    /**
     * Constructor of the class that will assign the parameters passed to it 
     * the appropriate data members. 
     * 
     * @param id - The id of the part.
     * @param name - The name of the part
     * @param price - The Price of the part
     * @param stock - The number in inventory
     * @param min - The minimum number
     * @param max - The maximum number
     * @param companyName  - The company name
     */
    public OutSourced(int id, String name, double price, 
            int stock, int min, int max, String companyName) {
       
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Returns the company name as a string.
     * 
     * @return companyName  - The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Receives the company name as a  string parameter and assigns it to the 
     * companyName data member
     * 
     * @param companyName Name of the company to be assigned
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
} // end class
 