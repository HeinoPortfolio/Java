package View_Controller;

import Models.ApplicationData;
import Models.Appointments;
import Utils.DBUtilities;
import Utils.MyAlerts;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** FXML Controller class for the main window of the program
 *
 * @author Matthew Heino
 */
public class MainFXMLController implements Initializable {

    /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;  
    
    /** Create alerts for the form. */
    MyAlerts myalerts = new MyAlerts();
    
    /**  Provides database actions */
    DBUtilities dbutils = new DBUtilities();
    
    
    //Appointment Table Components.********************************************
    
    @FXML private TableView<Appointments> appointmentsTable;
    @FXML private TableColumn<Appointments, Integer> apptIDCol;
    @FXML private TableColumn<Appointments, String> titleCol;
    @FXML private TableColumn<Appointments, String> descripCol;
    @FXML private TableColumn<Appointments, String> locCol;
    @FXML private TableColumn<Appointments, String> typeCol;
    @FXML private TableColumn<Appointments, String> contactCol;
    @FXML private TableColumn<Appointments, String> startCol;
    @FXML private TableColumn<Appointments, String> endCol;
    @FXML private TableColumn<Appointments, Integer> customerIDCol;

    
    @FXML private ToggleGroup monthlyWeeklyTG;
    @FXML private RadioButton allAppointmentsRB;
    @FXML private RadioButton monthlyViewRB;
    @FXML private RadioButton weeklyViewRB;
    
    /** Method to direct to add an update a appointment.
     * 
     * @param event  action event
     */
    @FXML
    private void addAppointment(ActionEvent event){
    
        try {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyAppointment.fxml"));
            
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Add and Modify Customer Screen.");
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Method to display the add and update Customer.
     * 
     * @param event  action event
     */
    @FXML
    private void addUpdateCustomer(ActionEvent event) {
        
        try {
            // Load the Add and Update Customer screen.
            // Load the scene.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddModifyCustomerFXML.fxml"));
            
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Add and Modify Customer Screen.");
            stage.show();
            
        } // end addUpdateCustomer.
        catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } // end addCustomer.
    
    /** Method to change the view of appointments from All, weekly or monthly.
     *  <p><b>Lambda</b> Lambda to filter the appointments based on whether they 
     *  are weekly or monthly. It will efficiently iterate through the list 
     *  performing the same function as a for each loop. </p>
     */
    @FXML 
    private void changeAppointmentView(){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate now = LocalDate.now();
        
        // Check the state of the radio buttons.
        if(this.monthlyWeeklyTG.getSelectedToggle().equals(this.allAppointmentsRB))
        { 
            // Add the appointments to the atable.
            appointmentsTable.setItems(ApplicationData.getAllAppointments());
            
        }
        else if(this.monthlyWeeklyTG.getSelectedToggle().equals(this.weeklyViewRB))
        {
           
            FilteredList<Appointments> weeklyList = new FilteredList<>(ApplicationData
                    .getAllAppointments());
           
            weeklyList.setPredicate(appt ->{
                
                LocalDate startDate = LocalDate.parse(appt.getStartDate(), dtf);
                
                return startDate.isAfter(now.minusDays(1)) 
                        && startDate.isBefore(now.plusWeeks(1));
            });
            
            // Add the appointments to the atable.
            appointmentsTable.setItems(weeklyList);
            
        }
        else if(this.monthlyWeeklyTG.getSelectedToggle().equals(this.monthlyViewRB))
        {
            
           FilteredList<Appointments> monthlyList = new FilteredList<>(ApplicationData
                    .getAllAppointments());
           
           
           monthlyList.setPredicate(appt ->{
                
                LocalDate startDate = LocalDate.parse(appt.getStartDate(), dtf);
                
                return startDate.isAfter(now.minusDays(1)) 
                        && startDate.isBefore(now.plusDays(30));
            });
            
            // Add the appointments to the atable.
            appointmentsTable.setItems(monthlyList);
            
        } // end else.        
    
    } // end changeAppointmentView.
    
    /** Method to delete the appointment from the schedule.
     * 
     * @param event action event
     */
    @FXML
    public void deleteAppointment(ActionEvent event){
         
        // Get the selected appointment.
        Appointments appt = this.appointmentsTable.getSelectionModel().getSelectedItem();
        
        if(appt != null)
        {
             
            dbutils.deleteAppointment(appt.getAppointmentID());
            
            //Update the appointment list.
            ApplicationData.getAllAppointments().clear();
           
            // Rebuild the All Appointments list.
            ApplicationData.getAllAppointments().addAll(dbutils
                        .createAppointmentList(ApplicationData.getUserID()));
            
            myalerts.createDeletedAppointment(appt);
            
        } // end if
        else{
            
             myalerts.createNoSelectionAlert();
             
        }
        
    } // end deleteAppointment. 
    
    /** Method to exit the application.
     * 
     * @param event action event
     */
    @FXML
    private void exitApplication(ActionEvent event){
        
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
            
    } // end exitApplication. 
    
    /** Method to show the reports screen.
     * 
     * @param event action event
     */
    @FXML
    private void showReports(ActionEvent event){
        
        try {
            // Load the Add and Update Customer screen.
            // Load the scene.
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/Reports.fxml"));
            
            // Show the stage.
            stage.setScene(new Scene(scene));
            stage.setTitle("Reports Screen");
            stage.show();
        } // end addUpdateCustomer.
        catch (IOException ex) {
            
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // end showReports
    
    /** Method to update the appointment
     * 
     * @param event   action event
     * @throws IOException  I/O error
     * 
     */
    @FXML
    private void updateAppointment(ActionEvent event) throws IOException{
        
        //Get the selected Appointment.
         Appointments updateAppointment = appointmentsTable.getSelectionModel()
                 .getSelectedItem();
        
       if(updateAppointment != null)
       { 
            
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/View_Controller/AddModifyAppointment.fxml"));
                
            scene = loader.load();
            stage.setScene(new Scene(scene));
            stage.setTitle("Modify an appointment in the System");
            stage.show();   
                
            // Get the method in the other controller.
            AddModifyAppointmentController controller = loader.getController();
            controller.setmodifyAppointment(updateAppointment);
                
       }
       else{
           
           myalerts.createNoSelectionAlert();
           
       } // end else
        
        
    } // end updateAppointment.
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        //Initialize the table components.
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descripCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        
        // Populate the table.
        appointmentsTable.setItems(ApplicationData.getAllAppointments());
              
    }  // end intialize.   
    
} // end controller.
