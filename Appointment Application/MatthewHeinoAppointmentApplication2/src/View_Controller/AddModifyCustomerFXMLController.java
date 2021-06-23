package View_Controller;

import Models.ApplicationData;
import Models.Customer;
import Models.FirstLevelDivisions;
import Utils.DBUtilities;
import Utils.MyAlerts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**FXML Controller class to handle the addition and updating of a customer's 
 * information
 * 
 *
 * @author Matthew Heino
 */
public class AddModifyCustomerFXMLController implements Initializable {
   
    
    /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;  
    
    /**  Provides database actions */
    DBUtilities dbutils = new DBUtilities();
    
    /** Create alerts for the form. */
    MyAlerts myalerts = new MyAlerts();
    
    /** Mode of the form. 1 is for update a customer, 2 is for add user. */
    int mode =0;
    
    // Components of the Customer Table.****************************************
    
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> customerIDCol;
    @FXML private TableColumn<Customer, String> customerNameCol;
    @FXML private TableColumn<Customer, String> addressCol;
    @FXML private TableColumn<Customer, String> postalCodeCol;
    @FXML private TableColumn<Customer, String> phoneCol;
    @FXML private TableColumn<Customer, String> divisionCol;
    @FXML private TableColumn<Customer, String> countryCol;
    
    // Comboboxes for country and states or provinces.**************************
    @FXML private ComboBox<String> countryCB;
    @FXML private ComboBox<String> stateCB;
    
    // Buttons of the form.*****************************************************
    
    @FXML private Button updateCustomerBtn;
    @FXML private Button deleteCustomerBtn;
    @FXML private TextArea instructionsTA;
    @FXML private Button cancelBtn;
    @FXML private Button addModBtn;
    
    // TextFields of the form.**************************************************
    
    @FXML private TextField custIDTF;
    @FXML private TextField custNameTF;
    @FXML private TextField addressTF;
    @FXML private TextField postalCodeTF;
    @FXML private TextField phoneTF;
    
    
    /** Cancel action of the user. This method will reset the from to a blank 
     * state.  The form will default to the add a customer mode.
     * 
     * @param event  action event
     * 
     */
    @FXML
    private void cancelAction(ActionEvent event){
         
        this.resetForm();
         
    } // end cancel action
    
    /** Method to delete a customer from the database and the associated 
     *  application data. 
     * 
     * @param event action event
     * 
     */
    @FXML 
    private void deleteCustomer(ActionEvent event){
       
        // Get the selection from the table.
        Customer deletedCustomer = this.customerTable
                .getSelectionModel().getSelectedItem();
        
        if(deletedCustomer != null)
        { 
            
            // Delete the customer.
            dbutils.deleteCustomer(deletedCustomer.getCustomerID());
            
            // delete from CustomerList
           ApplicationData.getAllCustomers().remove(deletedCustomer);
           
           //ApplicationData.deleteAppointment(deletedCustomer);
           ApplicationData.getAllAppointments().clear();
           
           // Rebuild the All Appointments list.
           ApplicationData.getAllAppointments().addAll(dbutils
                        .createAppointmentList(ApplicationData.getUserID()));
           
           // Clear a selction.
           this.customerTable.getSelectionModel().clearSelection();
           
        }
        else{
       
            myalerts.createNoSelectionAlert();
     
        } // end else.
        
    } // end deleteCustomer.

    /** Method to get divisions.
     * 
     * <p><b>Lambda</b> Used to create first level list</p>
     * @param event  action event
     * 
     */
    @FXML
    private void getDivisions(ActionEvent event){
   
        String cbValue = countryCB.getValue();  
        
        int division_id = ApplicationData.getCountryID(cbValue);
       
        // Create an Observable list
        ObservableList<FirstLevelDivisions> firstLevelList = FXCollections.observableArrayList();
        
        // Populate it.
        firstLevelList.addAll(dbutils.firstLevelDivisions(division_id));
        
        // Make sure the list is empty if there was a previous choice
        stateCB.getItems().clear();
        
        firstLevelList.forEach((fld) -> {
            stateCB.getItems().add(fld.getDivision());
        });
        
    } // end getDivision.

    
    /** Method to update the customer. Method to update the customer and show 
     *  simple directions to user.
     * 
     * @param event action event
     * 
     */
    @FXML 
    private void modifyCustomer(ActionEvent event){
        
        Customer modifiedCustomer = this.customerTable.getSelectionModel()
                .getSelectedItem();
        
        if(modifiedCustomer != null){
            
            //Change mode.
            this.mode = 1;
            
            // Disable the buttons.
            deleteCustomerBtn.setDisable(true);
            
            // Change text on the button to reflect the action.
            addModBtn.setText("Update Customer");
            
            // Set the textArea Instructions.
            instructionsTA.setText("The information from the selected Customer " 
                    + "will appear in the fields to the left.\nYou can edit these " 
                    + "fields as you like. \nAll fields must be given a value. "
                    +"\nAny field left blank will cause the form to be rejected. "
                    +"\nClick on the Update Customer Button to save the updated."
                    +"\nClick on the Cancel button to return the form to the add user state.");
            
            // Set the fields with appropriate values.
            custIDTF.setText(Integer.toString(modifiedCustomer.getCustomerID()));
            custNameTF.setText(modifiedCustomer.getCustomerName());
            addressTF.setText(modifiedCustomer.getAddress());
            postalCodeTF.setText(modifiedCustomer.getPostalCode());
            phoneTF.setText(modifiedCustomer.getPhone());
            
            //Set the combobox to default value.
            countryCB.setValue(modifiedCustomer.getCountry()); 
            stateCB.setValue(modifiedCustomer.getDivision());
          
        }
        else{
             myalerts.createNoSelectionAlert();
        }
        
    } // end modifyCustomer
    
    /** Method to populate the countries combobox.
     *  <p><b>Lambda</b> Uses lambda to populate the countries box.</p>
     */
    private void populateComboCountries(){
    
        ApplicationData.getCountries().forEach((country) -> {
            countryCB.getItems().add(country.getCountry());
        });
        
    } // end populateComboCountries.
    
    /** Method to set the form to add customer state. This method will set the 
     * form back to a blank state.  The default mode in this state is to  
     * be able to add and delete customers.  Once the a customer is chosen the 
     * mode is changed to update only.  The delete button is disabled until the 
     * user cancels or saves the changes to the customer.
     * 
     */
    private void resetForm(){
    
        try {
            // Renable all the buttons.
            deleteCustomerBtn.setDisable(false);
            
            // Reset the text on the button.
            addModBtn.setText("Add Customer");
            
            // Clear a selection.
            this.customerTable.getSelectionModel().clearSelection();
            
            // Clear all the fields.
            custIDTF.clear();
            custNameTF.clear();
            addressTF.clear();
            postalCodeTF.clear();
            phoneTF.clear();
            
            //Clear ComboButtons.
            countryCB.getItems().clear();
            stateCB.getItems().clear();
            
            // Rebuild the country list.
            this.populateComboCountries();
              
            this.mode = 0;
            
            // Update the Customer List.
            ApplicationData.getAllCustomers().clear();
            ApplicationData.getAllCustomers().addAll(dbutils.createCustomerList());
            customerTable.setItems(ApplicationData.getAllCustomers());
     
            instructionsTA.setText("On this screen you will be able to perform a few functions. "
                + "\nYou will be able to delete a customer. "
                + "\nYou will be able to update a Customer.  "
                +"\nYou will be able to add a new Customer to the database"
                +"\nYou can find instructions how to accomplish these tasks in this area."
                +"\nTo undo all actions that have yet to be saved to the database click Cancel."
                +"\nTo return to the main screen click return to the main screen button.");
        } // end resetForm.
        catch (Exception ex) {
            Logger.getLogger(AddModifyCustomerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    /** Method to return to the main screen.
     * 
     * @param event action event
     * @throws IOException  IO exception.
     */
    @FXML
    private void returnToMainScreen(ActionEvent event) throws IOException{
        
      stage = (Stage)((Button)event.getSource()).getScene().getWindow(); 
      scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
        
      // Show the stage.
      stage.setScene(new Scene(scene));
      stage.setTitle("Main Appointment Screen");
      stage.show();
        
    }
    
    /**Method to save the customer.
     * 
     */
    @FXML 
    private void saveCustomer() {
    
        Customer tempCust = new Customer();
        
        int userID;
        String username;
        String addr; 
        String postalcode; 
        String phone; 
        int divisionID = -1;
        String division;
        String country ="";
    
        username = custNameTF.getText();
        addr = addressTF.getText();
        postalcode = postalCodeTF.getText();
        phone = phoneTF.getText();
        
        // Get the division.
        division = stateCB.getSelectionModel().getSelectedItem(); 
        divisionID =  dbutils.getDivisionID(division);
        
        if(mode ==1){
         
            tempCust.setCustomerID(Integer.parseInt(custIDTF.getText()));
        }
        else{
            userID = -1;
        }
        
        tempCust.setCustomerName(custNameTF.getText());
        tempCust.setAddress(addressTF.getText());
        tempCust.setPostalCode(postalCodeTF.getText());
        tempCust.setPhone(phoneTF.getText());
        tempCust.setDivisionID(divisionID);
          
        // Check for errors.
        String errorMessage = tempCust.isValid(username, addr, postalcode, phone
                , divisionID);
        
        // Check for valid input.
        if(errorMessage.length() == 0 )
        {
                
            if(mode == 1) { 
                  
                // Update the customer.
                dbutils.updateCustomer(tempCust);
                
                // Clear and reset the form.
                this.resetForm();
                
            }  // End update customer.  
            else if (mode == 0){
               
                //Add the new customer to the database.
                dbutils.addCustomer(tempCust);
               
                // Clear and reset the form.
                this.resetForm();
            }
        }
        else{
           
            myalerts.createFormInputError(errorMessage);
            
        } // error message else.
       
    } // end saveCustomer.
    
    /**
     * Initializes the controller class.
     * @param url   Not used
     * @param rb not used
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Initialize the customer table.     
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        // set the initial contents of the table.
        customerTable.setItems(ApplicationData.getAllCustomers());
        
        // Populate the countries combobox.
        this.populateComboCountries();
        
        
        //Display some instructions.
        instructionsTA.setText("On this screen you will be able to perform a few functions. " 
                    + "\nYou will be able to delete a customer. " 
                    + "\nYou will be able to update a Customer.  "
                    +"\nYou will be able to add a new Customer to the database"
                    +"\nYou can find instructions how to accomplish these tasks in this area."
                    +"\nTo undo all actions that have yet to be saved to the database click Cancel."
                    +"\nTo add a new customer to the database simply enter the information "
                    +"into the fields on the right side of the screen."
                    +"\nTo return to the main screen click return to the main screen button.");
  
    } // end initialize.   
    
} //end class controller.
