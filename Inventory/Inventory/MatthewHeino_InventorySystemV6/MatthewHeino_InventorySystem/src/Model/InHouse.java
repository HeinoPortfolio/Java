package Model;

/**
 * Defines a child class of the Parent class part. This class models the methods 
 * and data that is associated with a part that is produced in-house.  Methods 
 * are inherited from the parent class of Part. This class adds the data member
 * that will hold the machine ID of the part produced.
 * 
 * @author Matthew Heino
 */
public class InHouse extends Part{

   private int machineId;
   
   /**
    * Constructor of the class will assign the variables to the appropriate 
    * class data members.
    * 
    * @param machineId  The machine id.
    * @param id         The part id.
    * @param name       The name of the part.
    * @param price      The price of the product.
    * @param stock      The number in stock.
    * @param min        The minimum in stock.
    * @param max        The maximum in stock.
    */
    public InHouse( int id, String name, double price, int stock, int min, int max, int machineId) {
        
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
        
    }
    
    /**
     * Returns the machine id as an integer.
     * 
     * @return machineId  The machine Id
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Receives the machineId as an integer parameter and assigns it to 
     * machineId data member. 
     * 
     * @param machineId  The machine id.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

} // end InHouse class.  
