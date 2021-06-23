package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to model a product.  The product will be composed of parts.  These 
 * parts can be both outsourced or in-house.
 * 
 * @author Matthew Heino
 */
public class Product {
   
    /** List to hold the associated parts that compose the Product. */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    
    /** The Id of the product. */
    private int id;    
    
    /** The name of the product. */
    private String name;  
    
    /** The price of the product. */
    private double price; 
    
    /** Number of the product in stock. */
    private int stock;  
    
    /** The minimum number in stock. */
    private int min;   
    
    /** The maximum number in stock. */
    private int max;             

    /** Constructor of the Product class that will assign the parameters passed 
     * to it to the appropriate data members.
     * <p><b>Post:</b> The object has been instantiated.</p>
     * 
     * @param associatedParts   The associated parts that make up the Product
     * @param id                The id of the part 
     * @param name              The name of the product
     * @param price             The price of the product
     * @param stock             Number of the product in inventory 
     * @param min               The minimum number in stock
     * @param max               The maximum number in stock
     * 
     */
    public Product(ObservableList<Part> associatedParts, int id, String name, 
            double price, int stock, int min, int max) {
        
        this.associatedParts.addAll(associatedParts);
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /** Method that returns a list of the associated parts.  The parts that the product is 
     * composed of will be returned or will return an empty list if the list is empty.
     * 
     * @return associatedParts  Parts that are associated with the Product
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    /** Method that sets the list of the associated parts that compose the product. 
     * Receives an ObservableList and sets the associated parts list.    
     * <p><b>Post: </b> The list has been assigned to the Product 's associated 
     * parts list. </p>
     * 
     * @param associatedParts  List with the associated parts.
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /** Method that returns the id of the Product.  This method will return 
     * the ID of the product.  The return type is an integer.
     * 
     * @return id  The id of the product
     */
    public int getId() {
        return id;
    }

    /** Method that receives the id as a parameter and assigns it to the data member id of 
     * the Product.  Receives an integer that is the index of the product.
     * <p><b>Post:</b> The id has been set for the product. </p>
     * 
     * @param id  The id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Method that returns the name of type String.  Returns the name of the 
     * part as a String. 
     * 
     * @return name  The name of the product.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method that receives the name as a string and assigns it to the 
     * name data member.  
     * <p><b>Post: </b> The name has been assigned to the name of the product. </p>
     * 
     * @param name  the name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Method returns the price of the product as a string. Returns the price 
     * of the product as a double
     * 
     * @return price  The Price of the product.
     */
    public double getPrice() {
        return price;
    }

    /** Method receives the price as a double parameter and assigns it to the price 
     * data member. The price of the product has been assigned to the price of 
     * the product.
     * <p><b>Post: </b> The price has been assigned to the Product's price. </p>
     * 
     * @param price  The price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Method returns the number of the product in stock as an integer. The 
     * number of the product will be returned as a integer.
     * 
     * @return stock   The number in inventory.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Method sets the inventory(stock) as an integer.
     * <p><b>Post: </b> The number in stock has been assigned to the Product's 
     * stock/inventory. </p>
     * 
     * @param stock  The number in stock. 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Method returns the minimum in stock as an integer. Will return an integer 
     * that is the number of the product in stock.
     * 
     * @return min  The minimum in stock
     */
    public int getMin() {
        return min;
    }

    /** Method that sets the minimum in stock/inventory. Receives as a parameter 
     * the minimum (integer) and assigns it to the min data member.
     * <p><b>Post: </b> The minimum has been assigned to the Product's minimum 
     * inventory. </p>
     * 
     * @param min  The minimum in stock.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Method that returns the maximum number to have in stock.
     * Returns the maximum number in stock as an integer. 
     * 
     * @return max   The maximum in inventory.
     */
    public int getMax() {
        return max;
    }

    /** Method that sets the maximum in inventory. Receives as an integer 
     * parameter the maximum number in stock.
     * <p><b>Post: </b> The maximum has been assigned to the Product's maximum 
     * inventory. </p>
     * 
     * @param max  The maximum in inventory.
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /** Method to add a part to the list of parts of the product.  This method 
     * will assign a parameter Part to the list of parts that compose the product.
     * <p><b>Post: </b> The part has been added to the Product's list 
     * of parts. </p>
     * 
     * @param part  The part to be added to Product's associated part list.
     */
    public void addAssociatedPart(Part part){
        
        this.associatedParts.add(part);
        
    }
    
    /** Method to delete an associated part from the list of associated parts.  
     * This method will delete a part from the list of associated parts of the product.
     * <p><b>Note:</b> This method was not used in the assignment.</p>
     * 
     * @param associatedPart Part to be deleted from the associated parts list
     * @return  isDeleted state
     */
    public boolean deleteAssociatedPart(Part associatedPart){
        
        boolean isDeleted = false;
        
        // TODO ----------
        
        return isDeleted;
        
    }
    
    /** Method to get all the parts that are associated with a product. This 
     * method will get all the parts that are associated with a Product.
     * 
     * <p><b>Note:</b> This method was not used in the assignment.</p>
     * 
     * @return partslist 
     */
    public ObservableList<Part> getAllAssociatedParts(){
       
        ObservableList<Part> partslist = null; 
        
        //Todo ***********
        
        return partslist;
        
    }
} // end class
