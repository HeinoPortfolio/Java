package View_Controller;

import Models.ApplicationData;
import Models.Contacts;
import Utils.DBUtilities;
import Utils.MyAlerts;
import Models.Appointments;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**FXML Controller class for the generated reports.
 * 
 *
 * @author Matthew Heino
 * 
 */
public class ReportsController implements Initializable {

     /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;  
    
    /** Create alerts for the form. */
    MyAlerts myalerts = new MyAlerts();
    
    /**  Provides database actions */
    DBUtilities dbutils = new DBUtilities();
    
    
    //Compoments of the form.***************************************************  
  
    @FXML private TableView<Appointments> contactApptTable;
    @FXML private TableColumn<Appointments, Integer> apptCol;
    @FXML private TableColumn<Appointments, String> titleCol;
    @FXML private TableColumn<Appointments, String> typeCol;
    @FXML private TableColumn<Appointments, String> descrCol;
    @FXML private TableColumn<Appointments, String> startCol;
    @FXML private TableColumn<Appointments, String> endCol;
    @FXML private TableColumn<Appointments, Integer> custCol;
    
    @FXML private ComboBox<Contacts> contactCB;

    @FXML private ToggleGroup reportTG;
    @FXML private RadioButton totalCustomerRB;
    @FXML private RadioButton otherReportRB;
    
    // TextArea.
    @FXML private TextArea otherReportsTA;

   
    
    /** Method to return to the main application screen.
     * 
     * @param event action event
     */
    @FXML
    private void returnToMainScreen(ActionEvent event) {
        
        try {
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
            
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Main Appointment Screen");
            stage.show();
            
        } // end returnToMainScreen.
        catch (IOException ex) {
            Logger.getLogger(AddModifyAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // end returnToMainScreen
    
    
    /** Method to show the contact schedule.  This method will show the 
     * contact's schedule for each contact in the given list.
     * 
     * @param event action event
     */
    @FXML 
    private void showContactSchedule(ActionEvent event){
        
        // Retrieve selection from the combo box.
        Contacts selectedContact =  contactCB.getSelectionModel().getSelectedItem();
        
        if(selectedContact != null){
            
            //Get the list of appointments
            ObservableList<Appointments> contactAppointments =  FXCollections.observableArrayList();
            
            contactAppointments.addAll(dbutils
                    .createContactAppointmentList(selectedContact.getContact_ID()));
            
            // Set the table with contact's appointments
            contactApptTable.setItems(contactAppointments);
           
        }
        else{
        
            myalerts.createNoSelectionAlert();
        
        }
         
    } // end showContactSchedule
    
    /** Method to switch between views of the two other reports.
     * 
     * @param event action event
     */
    @FXML
    private void switchReports(ActionEvent event){
    
        //Switch between reports.    
        if(this.reportTG.getSelectedToggle().equals(this.totalCustomerRB)){
            
            // Show in the textarea
            otherReportsTA.setText(dbutils.totalMonthly().toString());
            
        }
        else if(this.reportTG.getSelectedToggle().equals(this.otherReportRB)){
           
            // Show the results in text area.
            otherReportsTA.setText(dbutils.avgAppointmentLength().toString());
            
        }
        
    } // end switchReports.
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize the table components.
        apptCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        descrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
       
        // Initialize the contacts list.
        // Initialize the contacts list.
        contactCB.getItems().addAll(ApplicationData.getContactList());
       
        
    } // end initialize    
    
} // end class
