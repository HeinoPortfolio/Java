
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main Controller(Window) class of the project.   
 *
 * @author Matthew Heino
 * 
 */
public class MainFXMLController implements Initializable {

    /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;                           
    
    /** Basic Alert object */
    Alert errorAlert = new Alert(AlertType.NONE);
    

    // JavaFX Components--------------------------------------------------------
    // Components of the Parts panel.-------------------------------------------
    
    /** Textfield for searching the parts list. */
    @FXML private TextField partSearchField; 
    
    /** Parts table that holds all the parts in inventory.  */
    @FXML private TableView<Part> partsTable;
    
     /** Column for the part ID. */
    @FXML private TableColumn<Part, Integer> partIDColumn;
    
    /** Column for the part name. */
    @FXML private TableColumn<Part, String> partNameColumn;
    
    /** Column for the number in Inventory. */
    @FXML private TableColumn<Part, Integer> InnventoryLevelColumn;
    
    /** Column for the part price. */
    @FXML private TableColumn<Part, Double> pricePerUnitColumn;
    
    // Components of the Product Panel.-----------------------------------------
    
    /** Textfield for searching the product list. */
    @FXML private TextField prodSearchField;  
    
    /** Table for the products. */
    @FXML private TableView<Product> productTable;
    
    /** Column for the product ID. */
    @FXML private TableColumn<Product, Integer> prodIDColumn;
    
    /** Column for the product name. */
    @FXML private TableColumn<Product, String> prodNameClumn;
    
    /** Column for the number in Inventory. */
    @FXML private TableColumn<Product, Integer> inventoryProdColumn;
    
    /** Column for the product price. */
    @FXML private TableColumn<Product, Double> pricePerProdColumn;
    
  
    // Actions for the part Panel. *********************************************
    
    /**
     * Method to add a part to the inventory. The method is invoked when the 
     * add button is pressed. 
     * <p> Receives an action event</p>
     * <p><b>Post:</b> The part has been added to the allParts list of the 
     * inventory </p>
     * 
     * @param event  The button event.
     * @throws IOException   Exception when there is an IO error.
     */
    @FXML 
    private void addPartAction(ActionEvent event) throws IOException  
    {
         try{
            
            // Load the scene for adding the part to the inventory.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartFXML.fxml"));
        
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Add a Part in the System");
            stage.show();
        
        }
        catch(Exception e)   // Catch all exceptions
        {
            // set alert type 
             errorAlert.setAlertType(AlertType.ERROR); 
             errorAlert.setTitle("Error On Load");
  
            // Show the alert.
             errorAlert.show();
        }
    }
    
    /**
     * Method to delete a part from the inventory. This method will delete the 
     * part that has been selected from the table. Displays the results of the 
     * deletion to the user.
     * 
     * @param event  The button even(Delete) 
     */
    @FXML
    private void deletePartAction(ActionEvent event){
     
        // get the Selected part from the list.
        Part selectedPart = this.partsTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null){
           
            if(Inventory.deletePart(selectedPart))
            {
                // Display a message confirming deletion.
                // set alert type 
                errorAlert.setAlertType(AlertType.INFORMATION);
                errorAlert.setContentText("Selection has been deleted.");
                errorAlert.setTitle("Deletion Successful");
                // Show the alert.
                errorAlert.show();
             
                // Show the new table.
                this.partsTable.setItems(Inventory.getAllParts());
                
                //Clear the selection.
                this.partsTable.getSelectionModel().clearSelection();
            }
            else
            {
                errorAlert.setAlertType(AlertType.ERROR);
                errorAlert.setContentText("Selection has not been deleted.");
                errorAlert.setTitle("Deletion Unsuccessful");
                
                // Show the alert.
                errorAlert.show();
            }  
        }
        else{
            //No item was selected.
            // set alert type 
            errorAlert.setAlertType(AlertType.ERROR);     
            errorAlert.setTitle("Error No Selection Made");
            errorAlert.setContentText("You did not select an item");
  
            // Show the alert.
            errorAlert.show();
        }
  
    } // end deletePart.
    
    /** Method to invoke Modify Part.  This method loads and passes the necessary 
     * data to the method in the  ModifyPartFXMLController to initialize the 
     * fields for editing.
     * 
     * @param event  action event
     */
    @FXML 
    private void modifyPartAction(ActionEvent event) 
    {
        Part modifyPart = this.partsTable.getSelectionModel().getSelectedItem();
        
        if(modifyPart != null)
        {
           
            try{
           
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyPartFXML.fxml"));
           
                scene = loader.load();
                stage.setScene(new Scene(scene));
                stage.setTitle("Modify a Part in the System");
                stage.show();
           
                // Get controller from other stage.
                ModifyPartFXMLController controller = loader.getController();
                controller.setModifyPart(modifyPart);
  
            }
            catch(Exception e)   // Catch all exceptions
            {
                // set alert type 
                errorAlert.setAlertType(AlertType.ERROR); 
                errorAlert.setTitle("Error On Load");
  
                // Show the alert.
                errorAlert.show();
            }
        }
        else{
            
            // No item was selected.
            // set alert type 
            errorAlert.setAlertType(AlertType.ERROR); 
            errorAlert.setTitle("Error No Selection Made");
            errorAlert.setContentText("You did not select an item");
  
            // Show the alert.
            errorAlert.show();
        }
    }
    
    /**
     * Method to search for a part from the parts table.  
     * Receives a string from the text field and uses that to search. 
     * <p><b>RUNTIME ERROR</b> The search did not return a grouping of parts 
     * that matched the criteria given. This was fixed by using a regular 
     * expression to search for more parts than matching a simple string. (See 
     * main class for suggested improvements.) </p>
     * 
     * @param event action event(search button)  
     */
    @FXML
    private void searchPartAction(ActionEvent event)
    {
        
        String searchText = this.partSearchField.getText();
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
                    partsTable.setItems(searchParts);
                      
                }
                else{
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(AlertType.ERROR); 
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
                    partsTable.setItems(searchParts);
                }
                else
                {
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(AlertType.ERROR); 
                    errorAlert.setTitle("No Matches Found");
                    errorAlert.setContentText("No Matches are found!");
  
                    // Show the alert.
                    errorAlert.show();
            
                    // Give the textfield focus.
                    this.partSearchField.requestFocus();
                }
            } // end catch.
        } // end if.
        else // Empty search field.
         { 
            // Notify user that no part was found.
            // set alert type 
            errorAlert.setAlertType(AlertType.ERROR); 
            errorAlert.setTitle("Empty Search Field");
            errorAlert.setContentText("You did not enter a value in the search area!");
  
            // Show the alert.
            errorAlert.show();
            
            // Give the textfield focus.
            this.partSearchField.requestFocus();
        }// end else.
        
    } // end searchPartAction
   
    // Actions for the Product Panel.*******************************************
    
    /** Method to invoke the add product screen.  This method loads the scene 
     * where a new product will be created. 
     * 
     * @param event action event (Add button) 
     */
    @FXML
    private void addProdAction(ActionEvent event)
    {

        try{
          
            // Load the scene for adding the part to the inventory.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddProductFXML.fxml"));
        
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Add a Product in the System");
            stage.show();
        
        }
        catch(Exception e)   // Catch all exceptions
        {
            // set alert type 
            errorAlert.setAlertType(AlertType.ERROR); 
            errorAlert.setTitle("Error On Load");
  
            // Show the alert.
             errorAlert.show();
        }
            
    } // end addProdAction.
    
    /** Method to delete a product from the inventory.  This method will delete 
     * a product from the inventory.  The deletion will only occur if the 
     * product's associated part list is empty.
     * 
     */
    @FXML
    private void deleteProductAction(){
       
        // get the Selected product from the list.
        Product selectedProduct = this.productTable .getSelectionModel().getSelectedItem();
        
        if(selectedProduct != null){
          
            // Check to see if the products part list is empty
            if(selectedProduct.getAssociatedParts().isEmpty())
            {
            
                if(Inventory.deleteProduct(selectedProduct))
                {
                    // Display a message confirming deletion.
                    // set alert type 
                    errorAlert.setAlertType(AlertType.INFORMATION);
                    errorAlert.setContentText("Selection has been deleted.");
                    errorAlert.setTitle("Deletion Successful");
                
                    // Show the alert.
                    errorAlert.show();
             
                    // Show the new table.
                    this.productTable.setItems(Inventory.getAllProducts());
                
                    // Clear the selection(reset the selection).
                    this.productTable.getSelectionModel().clearSelection();
                }
                else
                {
                    errorAlert.setAlertType(AlertType.ERROR);
                    errorAlert.setContentText("Selection has not been deleted.");
                    errorAlert.setTitle("Deletion Unsuccessful");
                
                    // Show the alert.
                    errorAlert.show();
                }  
            }// end if 
            else{
                errorAlert.setAlertType(AlertType.ERROR);
                    errorAlert.setContentText("Selection has not been deleted. The associated parts list is not empty!");
                    errorAlert.setTitle("Deletion Unsuccessful");
                
                    // Show the alert.
                    errorAlert.show();
            }
        }
        else{
           
            //No Product was selected.
            // set alert type 
            errorAlert.setAlertType(AlertType.ERROR);     
            errorAlert.setTitle("Error No Selection Made");
            errorAlert.setContentText("You did not select an item");
  
            // Show the alert.
            errorAlert.show(); 
        }
      
    } // end deleteProductAction.
    
    
    /** Method to load the modify product screen.  This method will load the modify product screen. 
     * It will pass the data to the  ModifyProductFXMLController controller.  
     * This controller  will handle the initialization of the fields.  
     * 
     * @param event  action event (modify button)
     */
    @FXML 
    private void modifyProdAction(ActionEvent event)
    {  
        
        // get the Selected product from the list.
        Product modifyProduct = this.productTable.getSelectionModel().getSelectedItem();
   
        
        if(modifyProduct != null){
            try{
        
                // Load the scene for adding the part to the inventory.
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/ModifyProductFXML.fxml"));
               
                scene = loader.load();
                stage.setScene(new Scene(scene));
                stage.setTitle("Modify a Product in the System");
                stage.show();
                
                // Get the controller.
                // Get controller from other stage.
                ModifyProductFXMLController controller = loader.getController();
                controller.setmodifyProduct(modifyProduct);
                
            }
            catch(Exception e)   // Catch all exceptions
            {
                // set alert type 
                errorAlert.setAlertType(AlertType.ERROR); 
                errorAlert.setTitle("Error On Load");
  
                // Show the alert.
                errorAlert.show();
            }
        } // end if.
        else{
            errorAlert.setAlertType(AlertType.ERROR);
                errorAlert.setContentText("Please choose a Product to Modify.");
                errorAlert.setTitle("No Product Selected to Modify.");
                
                // Show the alert.
                errorAlert.show();
        }
        
    } // end modifyProdAction
    
    /** Method to search for products in inventory.  This method will search 
     * for a product either by ID or a string.  The string is case sensitive and 
     * does differentiate between upper and lowercase strings.  
     * 
     * @param event action event (Search button)
     */
    @FXML
    private void searchProductAction(ActionEvent event)
    {
       
        String searchText = this.prodSearchField.getText();
        ObservableList<Product> searchProducts = FXCollections.observableArrayList();
        
        if(!searchText.isEmpty()){
            
            try{
            
                // Determine if string is numeric or string literal.
                int productID = Integer.parseInt(searchText);
            
                // Perform a integer based lookup
                // Will return only one item
                // Make call to the Inventory method.
                Product foundProduct = Inventory.lookupProduct(productID);
            
                if(foundProduct != null)
                {
                   
                    // Insert into the current table.
                    // Set the tableview with the found parts.
                    searchProducts.add(foundProduct);
                    productTable.setItems(searchProducts);     
                    
                }
                else{
                    
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(AlertType.ERROR); 
                    errorAlert.setTitle("Product not found!");
                    errorAlert.setContentText("The product with ID:  " + productID  
                     + " does not exits");
  
                    // Show the alert.
                    errorAlert.show();
                } 
            }
            catch(NumberFormatException nfe){
                
                // It is a string perfrom a string based lookup
                searchProducts = Inventory.lookupProduct(searchText);
                
                // Insert into the table.
                if(!searchProducts.isEmpty()){
                   
                    productTable.setItems(searchProducts);
                }
                else
                {
                    // Notify user that no part was found.
                    // set alert type 
                    errorAlert.setAlertType(AlertType.ERROR); 
                    errorAlert.setTitle("No Matches Found");
                    errorAlert.setContentText("No Matches are found!");
  
                    // Show the alert.
                    errorAlert.show();
            
                    // Give the textfield focus.
                    this.partSearchField.requestFocus();
                }  
            } // edn catch.  
        } // end if.
    } // end searchProductAction.
    
    /** Method to end the application.  This method will close the application 
     * terminating the program.  None of the data will be stay after closing the 
     * program. <b>Program will reinitialize the next time it is run.</b> 
     * 
     * @param event  action event (exit button)
     */     
    @FXML 
    private void exitButtonAction(ActionEvent event){
        
        // Confirm the user wants to terminate the program.
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.initModality(Modality.NONE);
        exitAlert.setTitle("Exit Confirmation");
        exitAlert.setHeaderText("Exit Confirm");
        exitAlert.setContentText("Please confirm you want to exit program?");
        
        // Retrieve the button value.
        Optional<ButtonType> btnValue = exitAlert.showAndWait();
        
        // Process the response.
        if(btnValue.get() == ButtonType.OK ){
            // Close the program.
            System.exit(0);  
        }
   
    }  // end exitButtonAction.
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // add a listener to the  part search textfield.
        partSearchField.textProperty().addListener
        ((observable, oldValue, newValue) -> 
        {  
            if(newValue.isEmpty()){
               // Redo the table
               partsTable.setItems(Inventory.getAllParts());
            }
       });
        
       // add a listener to the  product search textfield.
        prodSearchField.textProperty().addListener
        ((observable, oldValue, newValue) -> 
        {  
            if(newValue.isEmpty()){
                
               // Redo the table
               productTable.setItems(Inventory.getAllProducts());
               
            }
       });
        
        // Populate the the Parts TableView. -----------------------------------
        partsTable.setItems(Inventory.getAllParts());
       
        // Setup the columns.
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InnventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // Populate the Products table.-----------------------------------------
        productTable.setItems(Inventory.getAllProducts());
        
        // Setup the columns.
        prodIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameClumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryProdColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerProdColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    
    }    
} // MainFXMLController
