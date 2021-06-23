
package View_Controller;

import Model.Inventory;
import Model.Part;
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

    
    Stage stage;                            // Declare once and reuse.
    Parent scene;                           // Declare once and reuse.
    Alert errorAlert = new Alert(AlertType.NONE);
    

    // JavaFX Components--------------------------------------------------------
    // Components of the Parts panel.-------------------------------------------
    @FXML private TextField partSearchField;
    
    @FXML private TableView<Part> partsTable;

    @FXML private TableColumn<Part, Integer> partIDColumn;

    @FXML private TableColumn<Part, String> partNameColumn;

    @FXML private TableColumn<Part, Integer> InnventoryLevelColumn;

    @FXML private TableColumn<Part, Double> pricePerUnitColumn;
    
    
    
    // Components of the Product Panel.-----------------------------------------
    @FXML private TextField prodSearchField;  
    
    
    
    
    // Actions for the part Panel. ---------------------------------------------
    
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
            System.out.println("Entered Add Part."); // REMOVE*************************************************
        
        
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
        
        System.out.println("Entered Delete part"); // REMOVE*************************************************************
        
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
  
    } // edn deletePart.
    
    /**
     * 
     * @param event 
     */
    @FXML 
    private void modifyPartAction(ActionEvent event) 
    {
        Part modifyPart = this.partsTable.getSelectionModel().getSelectedItem();
        
        if(modifyPart != null)
        {
            System.out.println("Contents of deleted part ID: " + modifyPart.getId() 
                + " Name: " + modifyPart.getName() + " Max:" + modifyPart.getMax()); // REOMVE*****************************
            try{
           
                System.out.println("Value of the selected:" + modifyPart.getName()); // REMOVE*********************************
            
                // Load the scene for adding the part to the inventory.
                stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPartFXML.fxml"));
        
                // Show the stage.
                stage.setScene(new Scene(scene));
                stage.setTitle("Modify a Part in the System");
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
     * Inventory class for suggested improvements.) </p>
     * @param event 
     */
    @FXML
    private void searchPartAction(ActionEvent event)
    {
        //System.out.println("Entered search for part.");    // REMOVE********************************************
         
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
                   
                    System.out.println("FOUND PART!!!!"); // REMOVE*****************************************
                    
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
            
                System.out.println("ENTERED CATCH IN SEARCH PART Action"); // REMOVE*****************************
                
                // It is a string perfrom a string based lookup
                searchParts = Inventory.lookupPart(searchText);
                
                System.out.println("Number of items in the Search Parts list: " + searchParts.isEmpty()); // REMOVE ************************
                
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
                
          
            }
        }
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
        }
        
    } // end modifyProdAction
   
    // Actions for the Product Panel.-------------------------------------------
   
    /**
     * 
     * @param event 
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
    
    /**
     * 
     */
    @FXML
    private void deleteProductAction(){
        
        System.out.println("Entered Delete prod"); // REMOVE*********************************************
        
        
    }
    
    
    /**
     * 
     * @param event 
     */
    @FXML 
    private void modifyProdAction(ActionEvent event)
    {
        
        try{
            System.out.println("Entered modify product."); // REMOVE*********************************************
        
        
            // Load the scene for adding the part to the inventory.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProductFXML.fxml"));
        
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Modify a Product in the System");
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
    } // end modifyProdAction
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void searchProductAction(ActionEvent event)
    {
        System.out.println("Entered search for product.");    // REMOVE***********************************************
        
    }
    /**
     * 
     * @param event 
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
               System.out.println("The textfield is now empty.");
               partsTable.setItems(Inventory.getAllParts());
            }
       });
        
       // add a listener to the  product search textfield.
        prodSearchField.textProperty().addListener
        ((observable, oldValue, newValue) -> 
        {  
            if(newValue.isEmpty()){
                
               // Redo the table
               System.out.println("The textfield is now empty.");
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
        
        
        // Setup the columns.
         
    }    
} // MainFXMLController
