/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * Modify Product controller class.  This controller will handle all the 
 * operations that are required to modify a product
 *
 * @author MAtthew Heino
 */
public class ModifyProductFXMLController implements Initializable {
   
    /** Stage for the scene. */
    Stage stage;                            
    
    /** Parent scene */
    Parent scene;                           
    
    /** Basic Alert object */
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    /** Holds the associated parts. */
    ObservableList<Part> associatedParts  = FXCollections.observableArrayList();
    
    /** Hold the original product. */
    Product modifiedProduct;
    
    /** Hold the index of the part. */
    int index;                                 
    
    // Components of the information pane.--------------------------------------
    
    /** Product ID text field. */
    @FXML private TextField prodIDTF;
    
    /** Product name text field. */
    @FXML private TextField productNameTF;
    
    /** Inventory text field. */
    @FXML private TextField inventoryTF;
    
    /** Price text field. */
    @FXML private TextField priceTF;
    
    /** Maximum text field. */
    @FXML private TextField maxTF;
    
    /** Minimum text field. */
    @FXML private TextField minTF;
    
    /** Textfield for searching. */
    @FXML private TextField searchField;
    
    
    
    // Parts Table Components.--------------------------------------------------
    
    /** Parts table that holds all the parts in inventory.  */
    @FXML private TableView<Part> partsModifyTable;
    
    /** Column for the part ID. */
    @FXML private TableColumn<Part, Integer> partIDColumn;
    
    /** Column for the part name. */
    @FXML private TableColumn<Part, String> partNameColumn;
    
    /** Column for the number in Inventory. */
    @FXML private TableColumn<Part, Integer> inventoryColumn;
    
    /** Column for the part price. */
    @FXML private TableColumn<Part, Double> pricePerUnitColumn;
    
    // Associated Parts Table components. --------------------------------------
    
    /** Parts table that holds all the parts that will make up the product.  */
    @FXML private TableView<Part> AssociatedPartTable;
    
    /** Column for the associated part ID. */
    @FXML private TableColumn<Part, Integer> associatedPartIDColumn;
    
    /** Column for the associated part name. */
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    
    /** Column for the associated part inventory. */
    @FXML private TableColumn<Part, Integer> associatedInventoryColumn;
    
    /** Column for the associated part price. */
    @FXML private TableColumn<Part, Double> associatedPricePerUnitColumn;
    
   /** Method to add an associated part.  This method will take a user selection 
    * from the partModifiedTable and add it to the associatedParts table (Table 
    * the will be used to create an updated Product.   
    * 
    * @param event  action event (add button)
    */
    @FXML
    private void addAssociatedPart(ActionEvent event){
     
        // Get the slected item from parts table.
        Part selectedPart = this.partsModifyTable.getSelectionModel().getSelectedItem();
     
        if(selectedPart != null){
            // add the part to the associated parts list.
            this.associatedParts.add(selectedPart);
        
            // Clear selection
            this.partsModifyTable.getSelectionModel().clearSelection();
        }
        else{
            
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("No part selected. Please select a part to add.");
            errorAlert.setTitle("No Part Selected");
                
            // Show the alert.
            errorAlert.show();
     
        } // end else.
    } // end addAssociatedPart.

    /** Method to cancel the modification of the product.  This method will 
     * cancel the modification of the product.  The product that was passed as 
     * a parameter will not be changed. 
     * 
     * @param event action event (cancel button)
     */
    @FXML
    private void cancelModifyProduct(ActionEvent event){
        
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
    }
    
    /** Method to remove associated part from the product's list.  This 
     * method will remove a product from the product's list of associated parts.  
     * This includes parts that were used to populate the table initially.    
     * 
     */
    @FXML
    private void removeAssociatedPart(){
       
        // Get the selection from the associated parts table.
        Part selectedPart = this.AssociatedPartTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null){
            
            //Remove the part from the associated parts list.
            this.associatedParts.remove(selectedPart);
            
            //Clear the selection.
            this.AssociatedPartTable.getSelectionModel().clearSelection();
            
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
    
    /** Method to save the modified Product.  This method will save the modified 
     * product.  This modification will only be saved if this button is pressed 
     * otherwise the old product will remain unchanged.  
     * 
     * @param event action event (save button)
     * @throws IOException  I/O exception
     */
    @FXML
    private void saveModifiedProduct(ActionEvent event) throws IOException{
        
        try{
            
            if(!this.productNameTF.getText().isEmpty() 
                    && !this.inventoryTF.getText().isEmpty() && 
                    !this.priceTF.getText().isEmpty() 
                    &&  !this.minTF.getText().isEmpty()  
                    && !this.maxTF.getText().isEmpty() )
            {
                
                // Get the data from the fields.
                int productID = Integer.parseInt(this.prodIDTF.getText());
                String name = this.productNameTF.getText();
                int inv = Integer.parseInt(this.inventoryTF.getText());
                double price = Double.parseDouble(this.priceTF.getText());
                int min = Integer.parseInt(this.minTF.getText());
                int max = Integer.parseInt(this.maxTF.getText());
        
                if((min < inv) && ( inv < max))
                {
                    // Create a new Product.
                    Product modProd = new Product(this.associatedParts, productID, name, 
                        price, inv, min, max);
        
                    // Update the product.
                    Inventory.getAllProducts().set(this.index, modProd);
                
                
                    //Display confirmation message.
                    errorAlert.setAlertType(Alert.AlertType.INFORMATION);
                    errorAlert.setContentText("The Product has been modified! ");
                    errorAlert.setTitle("Product Modified.");
  
                    // Show the alert.
                    errorAlert.show();
                
     
                    //Show main screen.
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                    // Show the stage.
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Inventory Management System");
                    stage.show();
                
                } // end if min max.
                else{
                
                    //Display error message.
                    errorAlert.setAlertType(Alert.AlertType.ERROR);
                    errorAlert.setContentText("There is an error with the minimum(min), inventory and maximum values." 
                        + " The minumum must be less than Inventory "
                        + "and the inventory must be less than maximum (max).");
                    errorAlert.setTitle("Error in Min, Inventory or Max Fields.");
                    
                    // Show the alert.
                    errorAlert.show();
                }
                
            } // end if.
            else{
            
                // set alert type 
                errorAlert.setAlertType(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error in input. All fields are required." 
                        + "\nNo text entered for one of the following fields: " 
                        + "Name, Inventory, price, Min and/or Max.\n");
                errorAlert.setTitle("Blank Fields Error");
  
                // Show the alert.
                errorAlert.show();
     
            } // end else.
        } // end try.
        catch(NumberFormatException nfe)
        {
            // set alert type 
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error in input. Text given instead of a numeric value!" 
                    + "\nFor one of the following fields: Inventory, price, Min and/or Max.");
            errorAlert.setTitle("Error in Numeric Input.");
  
            // Show the alert.
            errorAlert.show();
        }
    } // saveModifiedProduct.
    
    /** Method to search for a part.  This method will search for a part based 
     * on user input.  This search method is case sensitive and lowercase 
     * strings and uppercase strings are not the same and will yield different 
     * results.
     * 
     * @param event action event (search button) 
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
                   partsModifyTable.setItems(searchParts);
                      
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
                    partsModifyTable.setItems(searchParts);
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
    
    /** Method to set the initial values.  This method will set the initial 
     * values of the product to be modified.  All tables and fields will be 
     * populated using the data that is found in the Product object that is 
     * used as a parameter.
     * 
     * @param modifiedProd Product that will be modified. 
     */
    public void setmodifyProduct(Product modifiedProd){
        
        this.modifiedProduct = modifiedProd;
        this.index = Inventory.getAllProducts().indexOf(modifiedProduct);
       
        
        // Set the text fields of Information panel.
        this.prodIDTF.setText(String.valueOf(this.modifiedProduct.getId()));
        this.productNameTF.setText(modifiedProduct.getName());
        this.inventoryTF.setText(String.valueOf(this.modifiedProduct.getStock()));
        this.priceTF.setText(String.valueOf(this.modifiedProduct.getPrice())) ;
        this.maxTF.setText(String.valueOf(this.modifiedProduct.getMax()));  
        this.minTF.setText(String.valueOf(this.modifiedProduct.getMin()));
        
        // Set the associated parts list
        this.associatedParts.addAll(this.modifiedProduct.getAssociatedParts());
        
        this.AssociatedPartTable.setItems(associatedParts);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        // Initialize the Part Table in the Modify screen. 
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        partsModifyTable.setItems(Inventory.getAllParts());
        
        // Initialize the associated parts table.
        associatedPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // Add a listener to the part search field.
        // add a listener to the  part search textfield.
        searchField.textProperty().addListener
        ((observable, oldValue, newValue) -> 
        {  
            if(newValue.isEmpty()){
               
               // Redo the table
               partsModifyTable.setItems(Inventory.getAllParts());
            }
       });
      
    } // end initialize.    
    
} // end ModifyProduct. 
