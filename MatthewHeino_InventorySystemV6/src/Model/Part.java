package Model;

/** An abstract class that defines members and methods of a part.  Methods included
 * in the class are getters and setters.
 *
 * @author Matthew Heino
 */
public abstract class Part {
    
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /** 
     * Returns the id of the part as an integer.
     * 
     * @return id  - The id of the part.
     */
    public int getId() {
        return id;
    }

    /** 
     * Receives the id for the part as an integer parameter and assigns 
     * it to the id class member. 
     * 
     * @param id  - The id of part to assigned to the part.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the the name of the part as a string.  
     * @return name  - The name of the part.  
     */
    public String getName() {
        return name;
    }
    
    /**
     * Receives the name of the part as a string parameter and assigns it to the 
     * name data member. 
     * 
     * @param name  - The name of the part.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the Part as a double.
     * 
     * @return price  - The price of the part.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Receives the price of the Part as a double parameter and assigns it to 
     * the price data member.
     * 
     * @param price  - The price of the part.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Returns the stock or the number of the parts on hand as an integer. 
     * 
     * @return stock   - The number of that item in stock.
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Receives as an integer parameter the number in stock and assigns it to 
     * stock quantity for the stock.
     * 
     * @param stock  - The number in stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Returns the minimum number in the stock as an integer. 
     * 
     * @return min  - The minimum in stock.
     */
    public int getMin() {
        return min;
    }
    
    /**
     * Receives  the minimum number in stock as integer parameter and assigns 
     * the min to the class member min.
     * 
     * @param min  - The minimum in stock.
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * Returns the maximum number in stock as an integer.  
     * @return max  -  The maximum in stock.
     */
    public int getMax() {
        return max;
    }
    
    /**
     * Receives an integer as a parameter (the maximum inventory of the part) 
     * and sets the class member max. 
     * 
     * @param max  The maximum number in the inventory.
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     *  Constructor of the class.  Sets the class members to their appropriate
     *  values.
     * <p><b>Post:</b> The object has been instantiated.</p>
     * 
     * @param id -  The id of the part.
     * @param name - The name of the part.
     * @param price - The price of the part.
     * @param stock - The number of the part in inventory.
     * @param min - The minimum number of the part in stock.
     * @param max - The maximum number of the part in stock.
     */
    public Part (int id, String name, double price, int stock, 
            int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
   
} // end Part class.
