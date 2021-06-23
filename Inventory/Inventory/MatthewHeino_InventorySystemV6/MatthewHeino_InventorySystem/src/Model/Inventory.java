package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class  this class will hold and inventory all the parts and products 
 * that currently in the system.  This class will have methods that will allow 
 * for the addition, deletion and the updating of a part or product that is 
 * currently in the lists of parts or products. 
 * 
 * @author Matthew Heino
 */
public class Inventory {
    
    // Data members.-----------------------------------------------------------
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    private static int id;                     // ID for part or Product
    
    /**
     * Method to add a new part to the list of parts in the inventory.
     * <p>Receives as a parameter a Part object.</p>
     * <p><b>Post:</b>  The part has been added to the allParts list.
     * </p>
     * 
     * @param newPart  The part to be added.
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    /**
     * Method to add a new product to the list of parts in the inventory.
     * Receives as a parameter a Product object.
     * <p><b>Post:</b>  The Product has been added to the product list.
     * </p>
     * 
     * @param newProduct  - Product to be added.
     */
    public static void addProduct(Product newProduct){
        
        System.out.println("ENTERED ADD Product \n\n");  // REMOVE***********************************************
        
        allProducts.add(newProduct);
        
    }
    /**
     * Method that search(looks-up) the part in the Parts list.  This method 
     * searches by the part id.  
     * <p>Receives as a parameter the part as an integer</p>
     * <p>Returns a Part object if it is found in the list of parts otherwise 
     * a null will be returned. </p>
     * <p><b>Post:</b> The Part has been found or a null value has been returned
     * </p>
     * 
     * @param partId  The part Id
     * @return  Part or null 
     */
    public static Part lookupPart(int partId){
        
        System.out.println("ENTERED lookupPart");   //REMOVE**************************************************
        
        // Check to see list is empty
        
        
        // Iterate through list of Parts to look for a part with given ID.
        for(Part lookupPart : allParts){
    
            // See if part exists.
            if(lookupPart.getId() == partId ){
                // Return the Part
                    return lookupPart;
             }     
        }
        // If not found return null.
        return null;
        
    } // End lookupPart(int).
    
    /**
     * Method to search(look-up) a Product using the the product Id.
     * <p>Receives as an integer parameter the  product id.</p>
     * <p>Returns a product object if the product is found in the list 
     * otherwise it returns null.  Signifying that the value is not in the 
     * current list of products. </p>
     * <p><b>Post</b> The Product has been found and returned or the value 
     * of null has been returned indicating not found. </p>
     * 
     * @param productId   The product Id
     * @return lookupProduct or null 
     */
    public static Product lookupProduct(int productId){
      
         System.out.println("ENTERED lookupPart");   //REMOVE******************************************************
         
         // Iterate through list of Products to look for a part with given ID.
         for(Product lookupProduct: allProducts){
             
             //Check to see if the Product exists.
             if(lookupProduct.getId() == productId){
                 //Return the Product
                 return lookupProduct;
             }
         }
        return null;
    }
    
    /**
     * Method to search(look-up) a Part using the the part name.
     * <p>Receives as a string parameter the part name.</p>
     * <p>Returns an ObservableList if the part is found in the list 
     * otherwise it returns null.  Signifying that the value is not in the 
     * current list of parts. </p>
     * <p><b>Post</b> The Parts have been found and returned or the value 
     * of null has been returned indicating not found. </p>
     * 
     * @param partName  The part name
     * @return  lookupPart or null
     */
    public static ObservableList<Part> lookupPart(String partName){
        
        ObservableList<Part> lookupPartList = FXCollections.observableArrayList();
        
        // Create a regular expression to check for instances of the string.
        // Look the text in a substring.
        String regpattern = ".*"+ partName +".*" ;
        
        boolean matches = false;
        
        for(Part lookupPart : allParts){
        
            matches = lookupPart.getName().matches(regpattern);
            
            //See if the given string matches any Part in the list.
            if(matches == true){
               
                // Add the part to the list.
                lookupPartList.add(lookupPart);
            }
        }  
        return lookupPartList;
        
    } // end lookupPart
    
    /**
     * Method to search(look-up) a Product using the the part name.
     * <p>Receives as a string parameter the product name.</p>
     * <p>Returns an ObservableList if the product is found in the list 
     * otherwise it returns null.  Signifying that the value is not in the 
     * current list of products. </p>
     * <p><b>Post</b> The Products have been found and returned or the value 
     * of null has been returned indicating not found. </p>
     * 
     * @param productName The name of the product to be searched for.
     * @return ObservableList or null
     */
    public static ObservableList<Product> lookupProduct(String productName){
        
        ObservableList<Product> lookupProductList = FXCollections.observableArrayList();
        
        // Create a regular expression to check for instances of the string.
        // Look the text in a substring.
        String regpattern = ".*"+ productName +".*" ;
        boolean matches = false;
        
        for(Product lookupProduct : allProducts){
        
            matches = lookupProduct.getName().matches(regpattern);
            
            //See if the given string matches any Part in the list.
            if(matches == true){
                
                // Add the part to the list.
                lookupProductList.add(lookupProduct);
            }
        }  
        return lookupProductList;
        
    } // end lookupProduct.
    
    
    /**
     *  
     * @param index
     * @param selectedPart 
     */
    public static void updatePart(int index, Part selectedPart){
        
        System.out.println("Entered updatePart."); // REMOVE*************************************
        
        //Update the part in allParts list. Using the list index.
        allParts.set(index, selectedPart);
        
    } // end updatePart
    
    /**
     * 
     * @param index
     * @param newProduct 
     */
    public static void updateProduct(int index, Product newProduct){
        
        System.out.println("Entered updateProduct."); // REMOVE********************************************
        
        //Update the part in allProducts list. Using the index.
        allProducts.set(index, newProduct);
        
    }
    
    /**
     * Method to delete a part from the allPartsList.  It will delete the 
     * selected part from the list of allParts.
     * <p>Receives a part object to be deleted.</p>
     * <p>Returns true if the part has been deleted successfully other wise 
     * false if the part was not found in the allParts list.</p>
     * <p><b>Post:</b> The part has been deleted.</p>
     * 
     * @param selectedPart The Part to be deleted.
     * @return  Boolean true if deleted false if was not deleted
     */
    public static boolean deletePart(Part selectedPart){
    
        for(Part pt : allParts){
            
            // Check to see if part exists if it does remove it from list.
            if(selectedPart.getId() == pt.getId()){
                allParts.remove(pt);
                return true;
            }
        }
        return false; 
    
    } // end deletePart.
    
    /**
     * Method to delete a product from the allProduct list.  It will delete the 
     * selected product from the list of allProducts.
     * <p>Receives a Product object to be deleted.</p>
     * <p>Returns true if the product has been deleted successfully other wise 
     * false if the product was not found in the allProducts list. </p>
     * <p><b>Post:</b> The product has been deleted. </p>
     * 
     * @param selectedProduct  The product to be deleted
     * @return Boolean true for deleted false for not deleted
     */
    public static boolean deleteProduct(Product selectedProduct){
        
         System.out.println("Entered delete product.****"); // REMOVE*************************************************
        
        for(Product pt : allProducts){
          
            // Check to see if product exists if it does remove it from list.
            if(selectedProduct.getId() == pt.getId()){
                allProducts.remove(pt);
                return true;
            }
        }
        return false;      
         
    } // end deleteProduct.
    
    /**
     * Method that returns a list of all the parts that are in the system.
     * <p>Returns a parts list of the inventory system. </p>
     * <p><b>Post:</b> The list has been returned. </p>  
     * 
     * @return allParts  List of all parts returned
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    /**
     * Method that returns a list of all the products that are in the system.
     * <p>Returns a products list of the inventory system. </p>
     * <p><b>Post:</b> The list has been returned. </p> 
     * 
     * @return allProducts 
     */
    public static ObservableList<Product> getAllProducts(){

        return allProducts;
    }

    /**
     * Method to return the next available Id in the system.  It updates when 
     * ever a new product or part enters the system. This id will be assigned 
     * to the new Part or Product object. 
     * <p>Returns a integer Id</p>
     * <p><b>Post:</b> The next id has been returned. </p>
     * 
     * @return  id
     */
    public static int getnextAvailID()
    {
         // Increment the id.
        Inventory.id = Inventory.id + 1;
 
        return id;
    }
    
    /**
     * Method to rollback the Id in the system.  It rolls back id when 
     * called. 
     * <p><b>Post:</b> The next id has been returned. </p>
     * <p><b>Note:</b> Was not used in this assignment/task. </p>
     * 
     */
    public void rollbackID (){
        
        // Rollback ID if not used.
        this.id = this.id - 1;
        System.out.println("IN ROLLBACK:" + this.id);         
    }
} // end class
