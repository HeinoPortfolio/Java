
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matthew Heino
 */
public class AddProductFXMLController implements Initializable {

    Stage stage;                            // Declare once and reuse.
    Parent scene;                           // Declare once and reuse.
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void cancelAddProduct(ActionEvent event)
    { 
        try{
            System.out.println("IN cancel..."); // REMOVE*****************
        
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
    
    
    /**
     * Method to initialize the addProduct controller  
     * @param url not used in this application
     * @param rb  not used in this application
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
