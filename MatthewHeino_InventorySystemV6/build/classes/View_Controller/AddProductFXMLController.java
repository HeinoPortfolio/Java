package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** Add Product controller class. This class will handle all the operations that 
 * are required to add a product to inventory.
 * 
 *
 * @author Matthew Heino
 */
public class AddProductFXMLController implements Initializable {

    /** Stage for the scene. */
    Stage stage;   
    
    /** Parent scene */
    Parent scene;                          
    
    /** Basic Alert object */
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    //Components of the Add Parts table.----------------------------------------
    
    /** Parts table that holds all the parts in inventory.  */
    @FXML private TableView<Part> addProductPartsTable;
    
    /** Column for the part ID. */
    @FXML private TableColumn<Part, Integer> prodPartIDPartsTable;
    
    /** Column for the part name. */
    @FXML private TableColumn<Part, String> prodPartNamePartsTable;
    
    /** Column for the number in Inventory. */
    @FXML private TableColumn<Part, Integer> prodInventoryPartsTable;
    
    /** Column for the part price. */
    @FXML private TableColumn<Part, Double> prodPricePerUnitPartsTable;
    
    /** Textfield for searching the parts list. */
    @FXML private TextField searchField;
    
    // Components of the Associated Parts Table.--------------------------------
    
    /** Parts table that holds all the parts that will make up the product.  */
    @FXML private TableView<Part> associatedPartsTable;
    
    /** Column for the associated part ID. */
    @FXML private TableColumn<Part, Integer> partsIDAssocTable;
    
    /** Column for the associated part name. */
    @FXML private TableColumn<Part, String> partNameAssocTable;
    
    /** Column for the associated part inventory. */
    @FXML private TableColumn<Part, Integer> inventoryAssocTable;
    
    /** Column for the associated part price. */
    @FXML private TableColumn<Part, Double> priceAssocTable;
    
    // Components of the Product Information.-----------------------------------
    
    /** Textfield for product name. */
    @FXML private TextField prodNameTF;
    
    /** Textfield for product inventory. */
    @FXML private TextField inventoryTF;
    
    /** Textfield for product price. */
    @FXML private TextField priceTF;
    
    /** Textfield for product maximum in inventory */
    @FXML private TextField maxTF;
    
    /** Textfield for product minimum in inventory */
    @FXML private TextField minTF;
    
    /** Holds the associated parts of the product. */
    ObservableList<Part> associatedParts  = FXCollections.observableArrayList();
    
    
    /** Method to add a part to the associated product parts table.  This method 
     * will take a selection from the addProductPartsTable and add the part to 
     * associatedParts table (table that will make of the new product).
     * 
     * @param event  action event
     */
    @FXML
    private void addAssociatedPartAction(ActionEvent event){
        
        // Get the selected part from Parts Inventory.
        Part selectedPart = this.addProductPartsTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null){
            
            //Add Part to the associated parts list.
            this.associatedParts.add(selectedPart);
        
            // Display the associated part.
            this.associatedPartsTable.setItems(associatedParts);
            
            // Clear the selection(reset the selection).
            this.addProductPartsTable.getSelectionModel().clearSelection();
          
        }
        else
        {  
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("No part selected. Please select a part to add.");
            errorAlert.setTitle("No Part Selected");
                
            // Show the alert.
            errorAlert.show();
        }
    } // end AddAssociatedPartAction
    
    /** Method to add a new product to the Inventory.  This method will extract 
     * the information required from the appropriate textfields and the table. 
     * 
     * @param event  action event
     * @throws IOException  Exception for invalid I/O
     */
    @FXML
    private void addProductAction(ActionEvent event) throws IOException{
     
        try
        {
            if(!this.prodNameTF.getText().isEmpty() && !this.priceTF.getText().isEmpty()
                    && !this.inventoryTF.getText().isEmpty() 
                    && !this.maxTF.getText().isEmpty() && !this.minTF.getText().isEmpty())
            {
               
                //Get the data from the fields.
                String name = this.prodNameTF.getText();
                double price = Double.parseDouble(this.priceTF.getText());  // Get price
                int inv = Integer.parseInt(this.inventoryTF.getText());     // Get inventory
                int max = Integer.parseInt(this.maxTF.getText());           // Get the max
                int min = Integer.parseInt(this.minTF.getText());           // Get the min
        
                if((min < inv) && ( inv < max))
                {
                    // Create the new Product.
                    Product newProd = new Product(this.associatedParts, 
                            Inventory.getnextAvailID(), name, price, inv, min, max);
        
                    // Set associated parts list to empty.
                    this.associatedParts.clear();
        
                    // Set the fields to empty.
                    prodNameTF.setText("");
                    inventoryTF.setText("");
                    priceTF.setText("");
                    maxTF.setText("");
                    minTF.setText("");
    
                    // Add Product to the inventory.
                    Inventory.addProduct(newProd);
                
                    //Display confirmation message.
                    errorAlert.setAlertType(Alert.AlertType.INFORMATION);
                    errorAlert.setContentText("The Product has been created! ");
                    errorAlert.setTitle("Product Created.");
  
                    // Show the alert.
                    errorAlert.show();
                
                
                    //Show main screen.
                    // Load the scene for adding the part to the inventory.
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                    // Show the stage.
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Inventory Management System");
                    stage.show();
                    
                } // if min max.
                else{
                    
                    //Display error message.
                    errorAlert.setAlertType(Alert.AlertType.ERROR);
                    errorAlert.setContentText("There is an error with the" 
                        + " minimum(min), inventory and maximum values." 
                        + " The minumum must be less than Inventory "
                        + "and the inventory must be less than maximum (max).");
                    errorAlert.setTitle("Error in Min, Inventory or Max Fields.");
                    
                    // Show the alert.
                    errorAlert.show();
                    
                } // end else.
            } // if
            else
            {
                
                // set alert type 
                errorAlert.setAlertType(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error in input. All fields are required." 
                        + "\nNo text entered for one of the following fields: " 
                        + "Name, Inventory, price, Min and/or Max.\n");
                errorAlert.setTitle("Blank Fields Error");
  
                // Show the alert.
                errorAlert.show();
                
            } // else
        } // try outer.
        catch(NumberFormatException nfe){
   
            // set alert type 
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error in input. Text given instead of a numeric value!" 
                    + "\nFor one of the following fields: Inventory, price, Min and/or Max.");
            errorAlert.setTitle("Error in Numeric Input.");
  
            // Show the alert.
            errorAlert.show();
        }
    } // End addProduct
    
    
    /** Method to cancel the add product screen.  This method will cancel the 
     * add product screen and return the user back to the main screen
     * 
     * @param event action event
     */
    @FXML
    private void cancelAddProduct(ActionEvent event)
    { 
        try{
            
            // Load the scene for adding the part to the inventory.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Inventory Management System");
            stage.show();
        }
        catch(Exception e)   // Catch all exceptions
        {
            // set alert type 
             errorAlert.setAlertType(Alert.AlertType.ERROR); 
             errorAlert.setTitle("Error On Load");
  
            // Show the alert.
             errorAlert.show();
        }       
    } // end cancel
    
    /** Method to remove a part from the list that is associated with the 
     * product. This method will remove a selected part from the list that is 
     * to be associated with a product.  
     * 
     * @param event action event
     */
    @FXML
    private void removePartAsoociatedList(ActionEvent event){
    
        Part selectedPart = this.associatedPartsTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null){
            
            //Remove the part from the associated parts list.
            this.associatedParts.remove(selectedPart);
            
            // Display the associated part.
            this.associatedPartsTable.setItems(associatedParts);
            
            // Clear the selection(reset the selection).
            this.associatedPartsTable.getSelectionModel().clearSelection();
            
            //Confirm part has been removed.
            errorAlert.setAlertType(Alert.AlertType.INFORMATION);
            errorAlert.setContentText("Associated part has been removed from list.");
            errorAlert.setTitle("Associated Part Removed.");
                
            // Show the alert.
            errorAlert.show();
        
        }
        else{
            
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("No part selected. Please select a part to remove.");
            errorAlert.setTitle("No Part Selected");
                
            // Show the alert.
            errorAlert.show();
        }
         
    } // end removeAssociatedPart.
 
    /** Method to search for a part.  This method will search for a part based 
     * on user input into the search text field. 
     * 
     * @param event action event
     */
    @FXML
    private void searchPartAction(ActionEvent event)
    {
        
        String searchText = this.searchField.getText();
        ObservableList<Part> searchParts = FXCollections.observableArrayList();
        
        if(!searchText.isEmpty())
        {
            try{
            
                // Determine if string is numeric or string literal.
                int partID = Integer.parseInt(searchText);
            
                // Perform a integer based lookup
                // Will return only one item
                // Make call to the Inventory method.
                Part foundPart = Inventory.lookupPart(partID);
            
                if(foundPart != null)
                {
               
                   // Insert into the current table.
                   // Set the tableview with the found parts.
                   searchParts.add(foundPart);
                   addProductPartsTable.setItems(searchParts);
                      
                }
                else{
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(Alert.AlertType.ERROR); 
                    errorAlert.setTitle("Part not found!");
                    errorAlert.setContentText("The part with ID:  " + partID  
                     + " does not exits");
  
                    // Show the alert.
                    errorAlert.show();
                } 
            }
            catch(NumberFormatException nfe)
            {
            
                // It is a string perfrom a string based lookup
                searchParts = Inventory.lookupPart(searchText);
             
                // Insert into the table.
                if(!searchParts.isEmpty()){
                    addProductPartsTable.setItems(searchParts);
                }
                else
                {
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(Alert.AlertType.ERROR); 
                    errorAlert.setTitle("No Matches Found");
                    errorAlert.setContentText("No Matches are found!");
  
                    // Show the alert.
                    errorAlert.show();
            
                    // Give the textfield focus.
                    this.searchField.requestFocus();
                }
    
            }
        }
        else // Empty search field.
         { 
            // Notify user that no part was found.
            // set alert type 
            errorAlert.setAlertType(Alert.AlertType.ERROR); 
            errorAlert.setTitle("Empty Search Field");
            errorAlert.setContentText("You did not enter a value in the search area!");
  
            // Show the alert.
            errorAlert.show();
            
            // Give the textfield focus.
            this.searchField.requestFocus();
        }
        
    } // end searchPartAction
    
    /** Method to initialize the addProduct controller.  
     * @param url not used in this application
     * @param rb  not used in this application
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Parts Table.--------------------------------------------------------
        prodPartIDPartsTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodPartNamePartsTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInventoryPartsTable.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPricePerUnitPartsTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        addProductPartsTable.setItems(Inventory.getAllParts());
        
        // Associated Parts Table.-----------------------------------------------
        partsIDAssocTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAssocTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryAssocTable.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceAssocTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        // Add a listener to the part search field.
        // add a listener to the  part search textfield.
        searchField.textProperty().addListener
        ((observable, oldValue, newValue) -> 
        {  
            if(newValue.isEmpty()){
               
               // Redo the table
               addProductPartsTable.setItems(Inventory.getAllParts());
            }
       });
        
    } // end initialize    
    
} // end AddProductFXMLController.
