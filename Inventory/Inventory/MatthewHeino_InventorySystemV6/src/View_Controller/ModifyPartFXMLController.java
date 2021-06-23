package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
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

/** Class to handle the modification of a part.  This is a controller that will 
 * allow the user to modify a part that was passed to it. 
 *
 * @author Matthew Heino
 */
public class ModifyPartFXMLController  implements Initializable  {

    /** Stage for the scene. */
    Stage stage;   
    
    /** Parent scene */
    Parent scene;                           
    
    /** Basic Alert object */
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    /** Part to be modified */
    Part modifiedPart;
    
    // View Components. --------------------------------------------------------
    
    /** In-house radio button */
    @FXML private RadioButton inHouseRB;
    
    /** The toggle group for part source */
    @FXML private ToggleGroup modifyPartSourceTg;
    
    /** OutSource Radio button */
    @FXML private RadioButton outsourcedRB;
    
    /** Label for the part source */
    @FXML private Label partSourceLbl;
    
    /** The save button (not used) */
    @FXML private Button saveBtn;
    
    // Textfields of the class.-------------------------------------------------
    
    /** Part ID text field. */
    @FXML private TextField modPartIDTF;
    
    /** Part name text field. */
    @FXML private TextField modPartNameTF;
    
    /** Inventory text field. */
    @FXML private TextField modPartInventoryTF;
    
    /** Maximum text field. */
    @FXML private TextField modMaxTF;
    
    /** Price text field. */
    @FXML private TextField modPriceTF;
    
    /** Minimum text field. */
    @FXML private TextField modMinTF;
    
    /** Combination machine Id and Company name text field. */
    @FXML private TextField modMachCompTF;

    /** Holds the index of the Part to be modified.*/
    private int index;
    
    /** Method to check the state of the radio buttons. This method will check 
     * the radio buttons to determine which text to display.  If the in-house 
     * button is selected the Machine ID will displayed otherwise it will 
     * display the company name.  
     * 
     */
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
      
    } // end radio.
    
   /** Method to set the scene to the data.  This method will initialize the 
    * text fields and other components to the values of the Part that is to be 
    * modified. This method is called from a another controller. 
    * <p><b>RUNTIME ERROR</b> The <b>intanceof</b> method does not seem to work.
    * I was going to use this to check the instance of the object to see if it is
    * an in-house or an outsourced part.  But it would not work. Reasoning unknown.
    * This was fixed using code in lines from approximately 136 to 160.
    * </p>
    * 
    * 
    * @param modifiedpart The part that is to be modified.
    */ 
    public void setModifyPart(Part modifiedpart){
        
        this.modifiedPart  = modifiedpart;
        this.index = Inventory.getAllParts().indexOf(this.modifiedPart);
       
        modPartIDTF.setText(String.valueOf(this.modifiedPart.getId()));
        modPartNameTF.setText(this.modifiedPart.getName());
        modPartInventoryTF.setText(String.valueOf(this.modifiedPart.getStock()));
        modPriceTF.setText(String.valueOf(this.modifiedPart.getPrice()));
        modMaxTF.setText(String.valueOf(this.modifiedPart.getMax()));
        modMinTF.setText(String.valueOf(this.modifiedPart.getMin()));
        
        if (modifiedPart.getClass().equals(InHouse.class))
        {
            
            modMachCompTF.setText(String.valueOf(
                    ((InHouse)this.modifiedPart).getMachineId()));

            //Set radio button to Inhouse.
            this.inHouseRB.setSelected(true);
            
            // Set the label.
            this.partSourceLbl.setText("Machine ID");
        }
        else if(modifiedPart.getClass().equals(OutSourced.class)){
          
            modMachCompTF.setText(((OutSourced)this.modifiedPart).getCompanyName());

            // Set radio for outsourced.
            this.outsourcedRB.setSelected(true);
            
            // Set the text of the source label.
            this.partSourceLbl.setText("Company Name"); 
        } 
    } // End setModifyPart.
    
    /** Method to save the modified part.  This method will save the changes 
     * made by the user.  It will use all the same information and will retain 
     * the original ID and position in the list.   
     * 
     * @param event action event (save button)
     * @throws IOException   I/O exception
     */
    public void saveModifiedPart(ActionEvent event) throws IOException{
       
        try{
            
            if(!this.modPartNameTF.getText().isEmpty() 
                    && !this.modPartInventoryTF.getText().isEmpty() 
                    && !this.modPriceTF.getText().isEmpty() 
                    && !this.modMinTF.getText().isEmpty()
                    && !this.modMaxTF.getText().isEmpty())
            {
                // Get the information for the fields.
                int partID =  Integer.parseInt(this.modPartIDTF.getText());
                String partName = this.modPartNameTF.getText();
                int inv = Integer.parseInt(this.modPartInventoryTF.getText());
                double price = Double.parseDouble(this.modPriceTF.getText());
                int min = Integer.parseInt(this.modMinTF.getText());
                int max = Integer.parseInt(this.modMaxTF.getText());

                if((min < inv) && ( inv < max))
                {
                    //Check the radio buttons state.
                    if(this.modifyPartSourceTg.getSelectedToggle().equals(this.inHouseRB))
                    {
                    
                        try{
                            int machID = Integer.parseInt(this.modMachCompTF.getText());
            
                            // Create a new part.
                            InHouse newPart = new InHouse(partID, partName, 
                            price, inv, min, max, machID);
            
                            // Set the part.
                            Inventory.getAllParts().set(this.index, newPart);
              
                            // Load the scene for Main.                      
                            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                            // Show the stage.
                            stage.setScene(new Scene(scene));
                            stage.setTitle("Inventory Management System");
                            stage.show();
                            
                        } // try
                        catch (NumberFormatException nfe){
                
                            // set alert type 
                            errorAlert.setAlertType(Alert.AlertType.ERROR); 
                            errorAlert.setTitle("Error On Load");
                            errorAlert.setContentText("The Machine ID field requires" 
                                    + " a number for an inhouse part. (ex. 1,2,3...)");
                
                            // Show the alert.
                            errorAlert.show();
             
                            this.modMachCompTF.requestFocus();
                        } // catch.
                    
                    } // if.   
                    else if(this.modifyPartSourceTg.getSelectedToggle().equals(this.outsourcedRB))
                    {
            
                        try
                        {
                            String company = this.modMachCompTF.getText();
            
                            OutSourced newpart = new OutSourced(partID, partName, 
                                price, inv, min, max, company);
            
                            // Set the part.
                            Inventory.getAllParts().set(this.index, newpart);
                 
                            // Load the main scene.
                            stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
                            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
                            // Show the stage.
                            stage.setScene(new Scene(scene));
                            stage.setTitle("Inventory Management System");
                            stage.show();
                        } // try
                        catch(Exception e)
                        {
                            // set alert type 
                            errorAlert.setAlertType(Alert.AlertType.ERROR); 
                            errorAlert.setTitle("Error On Load");
                            errorAlert.setContentText("Error in Field.");
                    
                            // Show the alert.
                            errorAlert.show();
             
                            this.modMachCompTF.requestFocus();
                        } // catch
                
                    } // end else if.
               
                    
                } // end in min max.
                else{
                    
                    //Display error message.
                    errorAlert.setAlertType(Alert.AlertType.ERROR);
                    errorAlert.setContentText("There is an error with the" 
                        + "minimum(min), inventory and maximum values." 
                        + " The minumum must be less than Inventory "
                        + "and the inventory must be less than maximum (max).");
                    errorAlert.setTitle("Error in Min, Inventory or Max Fields.");
                    
                    // Show the alert.
                    errorAlert.show();
                }
            } // if.
            else{
                 // set alert type 
                errorAlert.setAlertType(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error in input. All fields are required." 
                        + "\nNo text entered for one of the following fields: " 
                        + "Name, Inventory, price, Min and/or Max.\n");
                errorAlert.setTitle("Blank Fields Error");
  
                // Show the alert.
                errorAlert.show();
            }
            
        } //try
        catch(NumberFormatException nfe)
        {
            // set alert type 
            errorAlert.setAlertType(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error in input. Text given instead of a numeric value!" 
                    + "\nFor one of the following fields: Inventory, price, Min and/or Max.");
            errorAlert.setTitle("Error in Numeric Input.");
  
            // Show the alert.
            errorAlert.show();
        } // end catch.
        
    }// end saveModifiedPart.
    
    /** Method to cancel the modification of a part.  If the user presses this 
     * button the object that was passed as an argument will not be changed.  
     * The parameter that was passed was not augmented in any way. 
     * 
     * @param event  action event (Cancel button)
     */
    @FXML
    private void cancelModifyPart(ActionEvent event)
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
     
    /** Method to initialize the ModifyPartFXML controller. 
     * 
     * @param arg0 not used in this project
     * @param arg1 not used in this project
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        
    }
    
}
