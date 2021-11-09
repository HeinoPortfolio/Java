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

/**
 * AddPartFXMLController class is a class that handles all the events that 
 * happen on the AddPartFXML view.  Actions that are handled by this controller 
 * are the following: Add part, check status of the radio buttons handle the 
 * cancel action for the window.  
 *
 * @author Matthew Heino
 */
public class AddPartFXMLController implements Initializable {

    Stage stage;                            // Declare once and reuse.
    Parent scene;                           // Declare once and reuse.
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    
    @FXML private RadioButton inHouseRB;
    @FXML private ToggleGroup partSourceTg;
    @FXML private RadioButton outsourcedRB;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private Label partSourceLbl;
    
    // Textfields of the GUI
    @FXML private TextField partNameTF;

    @FXML private TextField inventoryTF;

    @FXML private TextField maxTF;

    @FXML private TextField priceTF;

    @FXML private TextField minTF;
    
    @FXML private TextField machIDCompNAmeTF;
    
    
    /**
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
    
    /**
     * 
     * @param event 
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
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addPartAction(ActionEvent event) throws IOException
    {
        // Get the values of the textfields.
        String name = this.partNameTF.getText();                    // Get part name
        int inv = Integer.parseInt(this.inventoryTF.getText());     // Get inventory
        double price = Double.parseDouble(this.priceTF.getText());  // Get price
        int max = Integer.parseInt(this.maxTF.getText());           // Get the max
        int min = Integer.parseInt(this.minTF.getText());           // Get the min
        
        
        // Check the condition of the radio buttons.
        if(this.partSourceTg.getSelectedToggle().equals(this.inHouseRB))
        {
            System.out.println("Entered add Part (INHOUSE)"); // REMOVE ********************************************************
              
            // Get the Machine ID
            int machID = Integer.parseInt(this.machIDCompNAmeTF.getText());
            
            InHouse newPart = new InHouse(Inventory.getnextAvailID(), name, 
                    price, inv, min, max, machID);
            
            //Insert the new part into the list.
            Inventory.addPart(newPart);   
        }
        else if(this.partSourceTg.getSelectedToggle().equals(this.outsourcedRB))
        {
            
            System.out.println("Entered add Part (OUTSOURCED)"); // REMOVE ********************************************************
           
            String company = this.machIDCompNAmeTF.getText();
            
            OutSourced newpart = new OutSourced(Inventory.getnextAvailID(), name, 
                    price, inv, min, max, company);
            
            Inventory.addPart(newpart); 
        }
        
        // Load the scene for adding the part to the inventory.
        // Load the scene for adding the part to the inventory.
        stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
        // Show the stage.
        stage.setScene(new Scene(scene));
        stage.setTitle("Inventory Management System");
        stage.show();
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
