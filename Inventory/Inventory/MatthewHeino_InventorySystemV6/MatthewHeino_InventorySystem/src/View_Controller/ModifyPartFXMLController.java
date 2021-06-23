package View_Controller;

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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Heino
 */
public class ModifyPartFXMLController  implements Initializable  {

    Stage stage;                            // Declare once and reuse.
    Parent scene;                           // Declare once and reuse.
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    // View Components
    @FXML private RadioButton inHouseRB;
    @FXML private ToggleGroup modifyPartSourceTg;
    @FXML private RadioButton outsourcedRB;
    @FXML private Label partSourceLbl;
    
    
    @FXML 
    private void inOutRadiosChanged(){
        
        // Check to see the InHouse is slected.
        if(this.modifyPartSourceTg.getSelectedToggle().equals(this.inHouseRB)){
            
            // Set the text of the source label.
            this.partSourceLbl.setText("Machine ID");
        }
        
        // Check to see if Outsourced is selected.
        if(this.modifyPartSourceTg.getSelectedToggle().equals(this.outsourcedRB)){
            
            // Set the text of the source label.
            this.partSourceLbl.setText("Company Name");
        }
       
        // Check the text.
        String labelVal = this.partSourceLbl.getText(); // REMOVE ********************************************
        System.out.println(labelVal); // REMOVE*************************************************************
        
    } // end radio.
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void cancelModifyPart(ActionEvent event)
    { 
        
         try{
            System.out.println("IN cancel..."); // REMOVE***********************************************************
        
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
      
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
