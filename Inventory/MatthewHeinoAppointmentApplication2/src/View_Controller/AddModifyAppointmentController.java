package View_Controller;

import Models.ApplicationData;
import Models.Appointments;
import Models.Contacts;
import Models.Customer;
import Utils.DBUtilities;
import Utils.DateTimeConversUtil;
import Utils.MyAlerts;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**FXML Controller class that will handle the addition and updating of an 
 * appointment.
 * 
 *
 * @author Matthew Heino
 */
public class AddModifyAppointmentController implements Initializable {

     /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;  
    
    /**  Provides database actions */
    DBUtilities dbutils = new DBUtilities();
    
    /** */
    DateTimeConversUtil dtc = new DateTimeConversUtil();
    
    /** Create alerts for the form. */
    MyAlerts myalerts = new MyAlerts();
    
    /** Date and time formatter. */
    DateTimeFormatter timeDTF = DateTimeFormatter.ofPattern("HH:mm");
    
    /** List of start times. */
    private static ObservableList<String> startTimes =  FXCollections.observableArrayList();
    
    /** List of end times. */
    private static ObservableList<String> endTimes =  FXCollections.observableArrayList();
    
    /** Mode of the form. 1 is for update a appointment, 0 is for add appointment. */
    int mode =0;
    
    
    // Components of the page.**************************************************
    @FXML private TableView<Appointments> appointmnetTable;
    @FXML private TableColumn<Appointments, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointments, String> titleCol;
    @FXML private TableColumn<Appointments, String> descriptCol;  
    @FXML private TableColumn<Appointments, String> locationCol;
    @FXML private TableColumn<Appointments, String> contactCol;
    @FXML private TableColumn<Appointments, String> typeCol;
    @FXML private TableColumn<Appointments, String> startCol;
    @FXML private TableColumn<Appointments, String> endCol;
    @FXML private TableColumn<Appointments, Integer> custIDCol;
    
    
    // Comboboxes of the page.**************************************************
    @FXML private ComboBox<String> locationCB; 
    @FXML private ComboBox<Contacts> contactCB;
    @FXML private ComboBox<String> typeCB;
    @FXML private ComboBox<Customer> customerIDCB;
    
    // TextFields of the page.**************************************************
    @FXML private TextField titleTF;
    @FXML private TextField descripTF;
    @FXML private TextField apptIDTF;

    
    //DatePicker and Time Components.*******************************************
    @FXML private DatePicker dateDP;
    @FXML private ComboBox<String> startTimeCB;
    @FXML private ComboBox<String> endTimeCB;
    
    // Buttons.
    @FXML private Button saveModBtn;
     

    /** Method to customize the data picker. Will remove all irrelevant dates 
     *  from the date picker. Weekends and dates that are before the current 
     *  date will not be available for selection. 
     * 
     * <p><b>Lambda</b> used to efficiently remove weekends and dates before 
     * the present date and time.</p>
     * 
     */
    private void customizeDatePicker(){
        
        LocalDate minDate = LocalDate.now(); 
        
        // Restrict dates before the current time.
        dateDP.setDayCellFactory(date -> new DateCell(){
            @Override public void updateItem(LocalDate item, boolean empty){
                super.updateItem(item, empty);
                setDisable( item.isBefore(minDate)
                        || (item.getDayOfWeek() == DayOfWeek.SUNDAY)
                        || (item.getDayOfWeek() == DayOfWeek.SATURDAY));
            
            }
            
        });
       
    } // end customize datepicker.
    
    /** Method to check to see if the information has been entered.  Method that 
     *  will check to make sure that all required information is entered into 
     *  the form's fields.  An appropriate error message will be displayed to 
     *  the user.
     * 
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param contact appointment contact
     * @param type appointment type
     * @param apptDate date of the appointment
     * @param startTime appointment start time
     * @param endTime appointment end time
     * @param customer appointment customer
     * @return  true if filled false otherwise
     * 
     */
    private boolean isFilledOut(String title, String description, String location
            ,Contacts contact, String type, LocalDate apptDate
                ,String startTime,String endTime,Customer customer)
    {
        String message  = "You are missing the following fields: ";
        String errors ="";
        
        boolean isFilledOut = true;
        
        if(title.isEmpty())
        {
            errors = errors + "\n No Title entered. ";
            isFilledOut = false;
        }
        if(description.isEmpty())
        {
            errors = errors + "\n No description entered. ";
            isFilledOut = false; 
        }
        
        if(location == null)
        {
            errors = errors + "\n No location selected. ";
            isFilledOut = false; 
        }
        
        if(contact == null)
        {
            errors = errors + "\n No contact selected. ";
            isFilledOut = false; 
        }
        if(type == null)
        {
            errors = errors + "\n No type selected. ";
            isFilledOut = false; 
        }
        if(apptDate == null)
        {
            errors = errors + "\n Date has not been selected. ";
            isFilledOut = false; 
        }
        if(startTime == null)
        {
            errors = errors + "\n Start time is empty. ";
            isFilledOut = false; 
        }
        
        if(endTime == null)
        {
            errors = errors + "\n End time is empty. ";
            isFilledOut = false; 
        }
        
        if(customer == null)
        {
            errors = errors + "\n Customer is not selected. ";
            isFilledOut = false;
        }
    
        message = message + errors;
        
        //Display error message if fields are missing.
        if(isFilledOut == false)
            myalerts.createFormInputError(message);
        
        
        return isFilledOut;
        
    } // end isFilledOut
    
    /** Method to check to see if the entered times overlap.  This method checks 
     * both new and updated appointment times and dates.  It will check to see if
     * the start time falls between the start and end times of another 
     * appointment. It will check to see if the end time of an appointment 
     * overlaps the time of another appointment.  It will also check to see if 
     * the appointment start and end time encompass another appointment's time 
     * frame.  
     * 
     * @param startDateTimeLDT start time and date
     * @param endTimeLT end time and date
     * @return  true if no overlap false otherwise.
     * 
     */
    private boolean isOverlap(LocalDateTime startDateTimeLDT,LocalDateTime endTimeLT){
        
        boolean overlap = true;
        int id = -1;
        
        String message;
        
        if(mode == 1){ 
            id = Integer.parseInt(this.apptIDTF.getText());
        }
        
        DateTimeFormatter dateTimeDTF = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm");
        
        if(mode == 0)   // New Appointment added
        {
            for(Appointments appt : ApplicationData.getAllAppointments())
            {
               
                LocalDateTime startTime = LocalDateTime.parse(appt.getStartDate(),dateTimeDTF);
                LocalDateTime endTime = LocalDateTime.parse(appt.getEndDate(), dateTimeDTF);
            
                // Check to see if start time falls in between another 
                // appoinments start time.
                if(startDateTimeLDT.isAfter(startTime.minusMinutes(1)) 
                        && startDateTimeLDT.isBefore(endTime))
                {
            
                    message = "Appointment end falls after start of another but "
                         + "before the end of the appointment";
                    myalerts.createOverLapAlert(message);
                
                    return false;
                } // end
                else if(endTimeLT.isAfter(startTime) && endTimeLT.isBefore(endTime.plusMinutes(1)) )
                {
            
                    message = "Appointment end falls after start of another appointment.";
                    myalerts.createOverLapAlert(message);
                
                    return false;
                }
                else if(startDateTimeLDT.isBefore(startTime) && endTimeLT.isAfter(endTime))
                {
                
                    message = "Appointment overlaps both the start and end time";
                    myalerts.createOverLapAlert(message);
                
                    return false;
                }
            
            } // end for. 
        
        } // end if
        else if( mode == 1) // Updated apppointment
        {   
            for(Appointments appt : ApplicationData.getAllAppointments())
            {
                LocalDateTime startTime = LocalDateTime.parse(appt.getStartDate(),dateTimeDTF);
                LocalDateTime endTime = LocalDateTime.parse(appt.getEndDate(), dateTimeDTF);
                
                if((appt.getAppointmentID() == id) && startTime.equals(startDateTimeLDT) 
                        && endTime.equals(endTimeLT))
                {
                    
                    return true;
                    
                }
                // Check the other appointments in the list
                else if(appt.getAppointmentID() != id)
                {
                    if(startDateTimeLDT.isAfter(startTime.minusMinutes(1)) 
                        && startDateTimeLDT.isBefore(endTime))
                    {
            
                        message = "Appointment end falls after start of another but "
                            + "before the end of the appointment";
                        myalerts.createOverLapAlert(message);
                
                        return false;
                    } // end
                    else if(endTimeLT.isAfter(startTime) && endTimeLT.isBefore(endTime.plusMinutes(1)) )
                    {
            
                        message = "Appointment end falls after start of another appointment.";
                        myalerts.createOverLapAlert(message);
                
                        return false;
                    }
                    else if(startDateTimeLDT.isBefore(startTime) && endTimeLT.isAfter(endTime))
                    {
                
                        message = "Appointment overlaps both the start and end time";
                        myalerts.createOverLapAlert(message);
                
                        return false;
                    }
        
                } // end else        
        
            } // end for loop
        }
       return overlap;
        
    } //end isOverlap.   
    
    /** Method to populate the end times.  Method to populate the end times for 
     *  the user to select.
     * 
     * @param event  action even occurred
     * 
     */
    @FXML
    private void populateEndTimes(ActionEvent event){
         
        // Reset the list if already used.
        endTimes.clear();
        endTimeCB.setValue(null);
        
        // Get the value of the start time combo.
        String selectedTime = this.startTimeCB.getSelectionModel()
                .getSelectedItem();
        
        if(selectedTime != null)
        {
            String[] hoursmins = selectedTime.split(":");
            int hours = Integer.parseInt(hoursmins[0]);
            int minutes = Integer.parseInt(hoursmins[1]);
            int difference = dtc.getTimeDifferenc();
        
            int index = this.startTimeCB.getSelectionModel().getSelectedIndex();
         
            int populateIndex = (index);
        
            LocalTime endTime = LocalTime.of(hours,minutes);
      
            while(populateIndex <= (26 +  abs(difference / 2)))
            {
             
                endTime = endTime.plusMinutes(30);
                endTimes.add(endTime.format(timeDTF));
           
                if(endTime.equals("23:30"))
                {
                    endTime = LocalTime.of(0, 0);
                }
               
                populateIndex = populateIndex + 1;
            }
        
            this.endTimeCB.setItems(endTimes);
            
        } // end if.
        
      
    } // end populateEndTimes
    
    /** Method to populate the start times of the appointment. 
     * 
     */
    private void populateStartTimes(){
        
       int beginTime;
       int endTime;
       
        int difference = dtc.getTimeDifferenc();
        
        if(difference < 0 )
        {
           beginTime = 8 + -(difference);
           endTime = 22 + difference;
        }
        else if (difference > 0){
            beginTime = 8 - difference;
            endTime = 22 + (difference * 2);
        }
        else{
             beginTime = 8;
             endTime = 26;
        }
       
        LocalTime startTime = LocalTime.of(beginTime,0,0);
        
        int populateIndex = 0;
        
        while(populateIndex <= (endTime)){
           
            startTimes.add(startTime.format(timeDTF));
           
            if(startTime.equals("23:30"))
            {
                System.out.println("ENTERED IF.");
                startTime = LocalTime.of(0, 0);
            }
              
            startTime = startTime.plusMinutes(30) ;
            
            populateIndex = populateIndex + 1;
            
        }
        
      // Assign start times to the startTimes combobox.
      this.startTimeCB.setItems(startTimes);
        
    } // end populateStartTimes.
    
    /** Method to reset the form.  Method to reset the form by removing all 
     *  entered data.  This method will return the for to its add a new 
     *  appointment state. Only new appointments can be added to the database or 
     *  the appointment list.
     * 
     */
    @FXML
    private void resetForm(){
        
        //Re-populate the appointment table
        ApplicationData.getAllAppointments().clear();
        ApplicationData.getAllAppointments().addAll(dbutils
                .createAppointmentList(ApplicationData.getUserID()));
        
        apptIDTF.setText("Auto Generated");
        
        //Reset Comboboxes.*****************************************************
        locationCB.getItems().clear();
        locationCB.getItems().addAll(ApplicationData.getLocations());
        
        // Initialize the contacts list.
        contactCB.getItems().clear();
        contactCB.getSelectionModel().clearSelection();
        contactCB.setValue(null);
        contactCB.getItems().addAll(ApplicationData.getContactList());
    
        // Initialize the type of appointment items.
        typeCB.getItems().clear();
        typeCB.getItems().addAll(ApplicationData.getTypes());
         
        // Initialize the customers list.
        customerIDCB.getItems().clear();
        customerIDCB.getItems().addAll(ApplicationData.getAllCustomers());
        customerIDCB.setValue(null);
        
        //Reset the textFields.
        titleTF.clear();
        descripTF.clear();
        
        //Clear the datepicker.
        dateDP.setValue(null);
        
        //Reset the times boxes.
        startTimeCB.getItems().clear();
        startTimeCB.setValue(null);
        this.populateStartTimes();
        
        endTimeCB.getItems().clear();
        endTimeCB.setValue(null);
        
        this.saveModBtn.setText("Save New Appointment");
        
        // Reset the mode.
        this.mode = 0;
        
    } // end resetFrom. 
    
    
    /** Method to return user to main screen.
     * 
     * @param event action event
     * 
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
        
    } // end returnTMainScreen
    
    
    /**Method to save the appointment.  This method will save both new and 
     * updated appointments.  How the appointment is saved is based on the mode
     * of the form.
     * 
     */
    @FXML
    private void saveAppointment(){
        
        DateTimeFormatter timeDTF = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        
        // Get the values from the form.
       
        String title = this.titleTF.getText();
        String description = this.descripTF.getText();
        String location = this.locationCB.getSelectionModel().getSelectedItem();
        Contacts contact = this.contactCB.getSelectionModel().getSelectedItem();
        String type = this.typeCB.getSelectionModel().getSelectedItem();
        LocalDate apptDate = this.dateDP.getValue();
        String startTime = this.startTimeCB.getSelectionModel().getSelectedItem();
        String endTime = this.endTimeCB.getSelectionModel().getSelectedItem();
        Customer customer = customerIDCB.getSelectionModel().getSelectedItem();
        
        
        if(isFilledOut(title, description, location, contact, type, apptDate
                , startTime, endTime,customer))
        {
            startTime = startTime + ":00";
            endTime = endTime + ":00";
            
            try
            {
         
                // Convert date an time into LocalDateTime Object;
                LocalTime startTimeLT = LocalTime.parse(startTime, timeDTF);
                LocalTime endTimeLT = LocalTime.parse(endTime, timeDTF);
        
                LocalDateTime startDateTimeLDT = LocalDateTime.of(apptDate, startTimeLT);
                LocalDateTime endDateTimeLDT = LocalDateTime.of(apptDate,endTimeLT);
        
                
                // Check to see appointment does not overlap.********************
                if(isOverlap(startDateTimeLDT, endDateTimeLDT ))
                {
                    if(mode == 0)  // Add a new appointment
                    {  
                        // Save the appointment to the database.
                        dbutils.AddAppointment(title, description, location, type, startDateTimeLDT, endDateTimeLDT,
                            customer.getCustomerID(), ApplicationData.getUserID()
                            , contact.getContact_ID());
        
                        // Clear the appointment list.
                        ApplicationData.getAllAppointments().clear();
        
                        // Rebuild the All Appointments list.
                        ApplicationData.getAllAppointments().addAll(dbutils
                                .createAppointmentList(ApplicationData.getUserID()));
                    
                    }// end add appointment if.
                    else if (mode == 1)  // Update an appointment.
                    {
                        int apptID = Integer.parseInt(this.apptIDTF.getText());
                    
                        dbutils.updateAppointment(apptID, title, description, 
                                location, type, startDateTimeLDT, endDateTimeLDT,
                                customer.getCustomerID(), ApplicationData.getUserID()
                                ,contact.getContact_ID());
                    
                    } // end else if
                
                    // Reset the rest of the form.
                    this.resetForm();
                    
                } // end overlap 
                
            } // end try.
            catch(NullPointerException npe){
           
                myalerts.createFormInputError("You have missing fields!");
       
            }
            
        } // end if.
        
    } // end SaveAppointment
    
    /** Method to set the initial values of the fields.  Method used to set the 
     *  initial values of the field when the appointment needs to be updated by 
     *  the user. 
     * 
     * @param appt  appointment data
     * 
     */
    public void setmodifyAppointment(Appointments appt){
      
        this.mode = 1;
        this.apptIDTF.setText(String.valueOf(appt.getAppointmentID()));
        this.titleTF.setText(appt.getTitle());
        this.descripTF.setText(appt.getDescription());
        this.locationCB.setValue(appt.getLocation());
        this.saveModBtn.setText("Update Appointment");
        this.typeCB.setValue(appt.getType());
        
        //Get the contact info
        Contacts contact = dbutils.getAllContactInfo(appt.getContactID());
        this.contactCB.setValue(contact);
        
        // Set the datepicker.
        String date = appt.getStartDate();
        
        //Tokenize the date string.
        String[] timeDate = date.split(" ");
        
        DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(timeDate[0], dateDTF);
        
        // Set the default value of the date picker.
        dateDP.setValue(ld);
        
        // set the startTime
        startTimeCB.setValue(timeDate[1]);
        
        //set the end time
        String endTime = appt.getEndDate();
        String[] endDateTime = endTime.split(" ");
        endTimeCB.setValue(endDateTime[1]);
        
        // Get the customer.
        Customer customer = new Customer();
        customer = dbutils.getAllCustomerInfo(appt.getCustomerID());
       
        // Set the customer combobox to default value.
        customerIDCB.setValue(customer);
        
    } // end setModifyCustomer.
    
    /**
     * Initializes the controller class.
     * @param url NONE
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize the table components.
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        
        // Populate the table.
        appointmnetTable.setItems(ApplicationData.getAllAppointments());
        
        // Initialize the rest of the forms components.************************
        // Initilize the location items.
        locationCB.getItems().addAll(ApplicationData.getLocations());
        
        
        // Initialize the contacts list.
        contactCB.getItems().addAll(ApplicationData.getContactList());

        
        // Initialize the type of appointment items.
        typeCB.getItems().addAll(ApplicationData.getTypes());
        
        
        // Initialize the customers list.
        customerIDCB.getItems().addAll(ApplicationData.getAllCustomers());
        
        // Customize the date picker.
        this.customizeDatePicker();
        
        // initilize the startTimes combobox
        this.populateStartTimes();
    
    } // end initialize.   
    
} // end class.
