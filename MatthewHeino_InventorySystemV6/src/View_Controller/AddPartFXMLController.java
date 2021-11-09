package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/** AddPartFXMLController class is a class that handles all the events that 
 * happen on the AddPartFXML view.  Actions that are handled by this controller 
 * are the following: Add part, check status of the radio buttons handle the 
 * cancel action for the window.  
 *
 * @author Matthew Heino
 */
public class AddPartFXMLController implements Initializable {

    /** Main stage. */
    Stage stage;
    
     /** Parent scene. */
    Parent scene;  
    
    /** Declare once and reuse. */
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    /** In-house radio button. */
    @FXML private RadioButton inHouseRB; 
    
    /** The toggle group for part source. */
    @FXML private ToggleGroup partSourceTg; 
    
    /** OutSource Radio button. */
    @FXML private RadioButton outsourcedRB; 
    
    /** The save button (not used). */
    @FXML private Button saveBtn;  
    
    /** The cancel button (not used). */
    @FXML private Button cancelBtn;   
    
    /** Label for the part source. */
    @FXML private Label partSourceLbl;              
    
    // Textfields of the GUI---------------------------------------------------
    
    /** Part name text field. */
    @FXML private TextField partNameTF;             
    
    /** Inventory text field. */
    @FXML private TextField inventoryTF;
    
    /** Maximum text field. */
    @FXML private TextField maxTF;  
    
    /** Price text field. */
    @FXML private TextField priceTF;  
    
    /** Minimum text field. */
    @FXML private TextField minTF;   
    
    /** Combination machine Id and Company name text field. */
    @FXML private TextField machIDCompNAmeTF;      
    
    /** Method to check the status of the radio buttons.  This method will check 
     * the status of the outsource and in-house radio buttons.  It will change 
     * the value of the partSourceLable to the appropriate value.  This will be 
     * either Machine Id or Company name.   
     * 
     */
    @FXML 
    private void inOutRadiosChanged(){
        
        // Check to see the InHouse is slected.
        if(this.partSourceTg.getSelectedToggle().equals(this.inHouseRB)){
            
            // Set the text of the source label.
            this.partSourceLbl.setText("Machine ID");
        }
        
        // Check to see if Outsourced is selected.
        if(this.partSourceTg.getSelectedToggle().equals(this.outsourcedRB)){
            
            // Set the text of the source label.
            this.partSourceLbl.setText("Company Name");
        }
    
    } // end radio.
    
    /** Method to cancel out of the Add Part Window. This method will cancel 
     * out of the Add Part window and return the user to the main window.
     * 
     * @param event  action event (cancel button)
     */
    @FXML
    private void cancelAddPart(ActionEvent event)
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
    } // end cancel.
    
    /** Method to invoke the add Part window.  This method will load the scene 
     * that will be used to add a part to the system.  This method does not 
     * process the adding of the part to the Inventory system.  
     * It only process the event to display the Add part window.  
     * 
     * @param event  the event to be processed
     * @throws IOException  Exception for invalid I/O
     * 
     */
    @FXML
    private void addPartAction(ActionEvent event) throws IOException
    {
        
        try
        {
            if(!this.partNameTF.getText().isEmpty() 
                    && !this.inventoryTF.getText().isEmpty() 
                    && !this.priceTF.getText().isEmpty() && !this.maxTF.getText().isEmpty() 
                    && !this.minTF.getText().isEmpty())
            {
             
                // Get the values of the textfields.
                String name = this.partNameTF.getText();                    // Get part name
                int inv = Integer.parseInt(this.inventoryTF.getText());     // Get inventory
                double price = Double.parseDouble(this.priceTF.getText());  // Get price
                int max = Integer.parseInt(this.maxTF.getText());           // Get the max
                int min = Integer.parseInt(this.minTF.getText());           // Get the min
        
                if((min < inv) && ( inv < max))
                {
                    // Check the condition of the radio buttons.
                    if(this.partSourceTg.getSelectedToggle().equals(this.inHouseRB)){
                   
                        // Get the Machine ID
                        try
                        {
                            int machID = Integer.parseInt(this.machIDCompNAmeTF.getText());
            
                            InHouse newPart = new InHouse(Inventory.getnextAvailID(), name, 
                                price, inv, min, max, machID);
            
                            //Insert the new part into the list.
                            Inventory.addPart(newPart);
                        
                            //Display confirmation message.
                            errorAlert.setAlertType(Alert.AlertType.INFORMATION);
                            errorAlert.setContentText("The In-house part has been created! ");
                            errorAlert.setTitle("InHouse Part Created.");
  
                            // Show the alert.
                            errorAlert.show();
                        
                            // Load the scene for adding the part to the inventory.
                            // Load the scene for adding the part to the inventory.
                            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                            // Show the stage.
                            stage.setScene(new Scene(scene));
                            stage.setTitle("Inventory Management System");
                            stage.show();
                        
                        } // try
                        catch(NumberFormatException nfe)
                        {
                        
                            errorAlert.setAlertType(Alert.AlertType.ERROR);
                            errorAlert.setContentText("Error in input. Text given instead of a numeric value!" 
                                + "\nFor the following field: Machine ID.");
                            errorAlert.setTitle("Error in Numeric Input.");
  
                            // Show the alert.
                            errorAlert.show();
                        
                        } // catch.
                    }
                    else if(this.partSourceTg.getSelectedToggle().equals(this.outsourcedRB))
                    {
                        String company = this.machIDCompNAmeTF.getText();
            
                        OutSourced newpart = new OutSourced(Inventory.getnextAvailID(), name, 
                            price, inv, min, max, company);
            
                        Inventory.addPart(newpart); 
                    
                        //Display confirmation message.
                        errorAlert.setAlertType(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("The outsourced part has been created! ");
                        errorAlert.setTitle("Outsourced Part Created.");
                    
                        // Show the alert.
                        errorAlert.show();
                    
                     
                        // Load the scene for Main.
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                        // Show the stage.
                        stage.setScene(new Scene(scene));
                        stage.setTitle("Inventory Management System");
                        stage.show();
                    
                    } // end else if.
                    
                } // end if min max.
                else{
                    
                    //Display error message.
                    errorAlert.setAlertType(Alert.AlertType.ERROR);
                    errorAlert.setContentText("There is an error with the" 
                        + " minimum(min), inventory and maximum values." 
                        + " The minumum must be less than Inventory and the inventory must be less than maximum (max).");
                    errorAlert.setTitle("Error in Min, Inventory or Max Fields.");
                    
                    // Show the alert.
                    errorAlert.show();
                    
                } // end else.
                
            } // end if
            else{
                
                // set alert type 
                errorAlert.setAlertType(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error in input. All fields are required." 
                        + "\nNo text entered for one of the following fields: " 
                        + "Name, Inventory, price, Min and/or Max.\n");
                errorAlert.setTitle("Blank Fields Error");
  
                // Show the alert.
                errorAlert.show();
                
            } // end else
        }  // try
        catch(NumberFormatException nfe){
            
            // set alert type 
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error in input. Text given instead of a numeric value!" 
                    + "\nFor one of the following fields: Inventory, price, Min and/or Max.");
            errorAlert.setTitle("Error in Numeric Input.");
  
            // Show the alert.
            errorAlert.show();
        }
    } // end Addpart Action
    
    /** Method to initialize the controller
     * 
     * @param url    not used in this assignment
     * @param rb     not used in this assignment   
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
