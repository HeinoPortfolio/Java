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
   
    // List to hold the associated parts that compose the Product.
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    private int id;             // The Id of the product
    private String name;        // The name of the product  
    private double price;       // The price of the product
    private int stock;          // Number of the product in stock
    private int min;            // The minimum number in stock
    private int max;            // The maximum number in stock

    /**
     * Constructor of the Product class that will assign the parameters passed 
     * to it the appropriate data members.
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
        
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**
     * Returns a list of the associated parts.  The parts that the product is 
     * composed of. 
     * 
     * @return associatedParts 
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
    /**
     * Sets the list of the associated parts that compose the product. 
     * 
     * @param associatedParts  List with the associated parts.
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * Returns the id of the Product
     * 
     * @return id  The id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Receives the id as a parameter and assigns it to the data member id of 
     * the Product.
     * 
     * @param id  The id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of type String 
     * 
     * @return name  The name of the product.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Receives the name as a string and assigns it to the 
     * name data member.
     * 
     * @param name  the name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the product as a string.
     * 
     * @return price  The Price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Receives the price as a double parameter and assigns it to the price 
     * data member.
     * 
     * @param price 
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the number of the product in stock as an integer.
     * 
     * @return stock   The number in inventory.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns the inventory(stock) as an integer.
     * 
     * @param stock  The number in stock. 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns the minimum in stock as an integer.
     * 
     * @return min  The minimum in stock
     */
    public int getMin() {
        return min;
    }

    /**
     * Receives as a parameter the minimum as an integer and assigns it to the 
     * min data member.
     * 
     * @param min  The minimum in stock.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Returns the maximum number in stock as an integer. 
     * 
     * @return max   The maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Receives as an integer parameter the maximum number in stock.
     * 
     * @param max  The maximum
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * 
     * @param part 
     */
    public void addAssociatedPart(Part part){
        
        this.associatedParts.add(part);
        
    }
    
    /**
     * 
     * @param associatedPart
     * @return  isDeleted state
     */
    public boolean deleteAssociatedPart(Part associatedPart){
        
        boolean isDeleted = false;
        
        // TODO ----------
        
        return isDeleted;
        
    }
    
    /**
     * 
     * @return partslist 
     */
    public ObservableList<Part> getAllAssociatedParts(){
       
        ObservableList<Part> partslist = null; 
        
        //Todo ***********
        
        return partslist;
        
    }
} // end class
