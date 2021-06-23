package View_Controller;

import Utils.DBUtilities;
import Models.ApplicationData;
import Models.Appointments;
import Models.Users;
import Utils.MyAlerts;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**FXML Controller class handles the login operations for the application
 *
 * @author Matthew Heino
 */
public class LogInFXMLController implements Initializable {

    /** Stage for the scene. */
    Stage stage;     
    
    /** Parent scene */
    Parent scene;  
    
     /** Create alerts for the form. */
    MyAlerts myalerts = new MyAlerts();
    
    @FXML private TextField userNameTF;
    @FXML private TextField passwordTF;
    @FXML private Button loginBtn;
    
    @FXML private Label userNameLbl;
    @FXML private Label zoneIDLbl;
    @FXML private Label passwordLbl;

    
    /** Method to handle user validation and login. 
     * 
     * @param event action event
     * @throws IOException  Invalid IO
     */
    @FXML
    public void loginAction(ActionEvent event) throws IOException{
        
        DBUtilities dbutils = new DBUtilities();
        
        // Verify login
        try {
            if(dbutils.isValidUser(this.userNameTF.getText(), this.passwordTF.getText()))
            {
                //Log the event
                this.logLogin("successful");
                
                // Load the customers list.
                ApplicationData.getAllCustomers().addAll(dbutils.createCustomerList());
                
                // Load the countries list.
                ApplicationData.getCountries().addAll(dbutils.createCountryList());
                
                
                // Get the user data.
                Users currentUser = dbutils.returnUserData(this.userNameTF.getText()
                        ,this.passwordTF.getText());
                
                ApplicationData.setUserID(currentUser.getUserID());
                
                // Populate the appointments list.
                ApplicationData.getAllAppointments().addAll(dbutils
                        .createAppointmentList(ApplicationData.getUserID()));
                
                // Populate the contact list.
                ApplicationData.getContactList().addAll(dbutils.createContactList());
               
                // Show reminder about upcoming appointments.
                this.showAppointmentReminder();
                
                // Load the scene.
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainFXML.fxml"));
                
                // Show the stage.
                stage.setScene(new Scene(scene));
                stage.setTitle("Main Appoinment Screen.");
                stage.show();
                
            }
            else{
               
                myalerts.createLoginError();
                
                this.logLogin("unsuccessful");
                
                // Set the focus back to the username.
                this.userNameTF.requestFocus();
                
                
            }
        } catch (Exception ex) {
           Logger.getLogger(LogInFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    } // end login
    
    /** Method to log events during login.
     * 
     * @param message error message
     * 
     */
    private void logLogin(String message){
        
        BufferedWriter writer = null;
        String filename = "login_activity.txt";
         
        try {
           
            String user = this.userNameTF.getText();
            
            writer = new BufferedWriter(new FileWriter(filename, true));
            
            // Log the event.
            writer.append("User: "+ user + " attempted a(n) " + message 
                    + " login at " +Instant.now() + "\n");
            
        } catch (IOException ex) {
            
            Logger.getLogger(LogInFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            
            try {
                
                writer.flush();
                writer.close();
            
            } catch (IOException ex) {
              
                myalerts.createErrorWritingToFile();
                
                Logger.getLogger(LogInFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
    } // end login.
  
    /** Method to show a reminder of appointments in the next 15 minutes.
     *  <p><b>Lambda</b> An efficient way to populate the reminder list. </p>
     * 
     */
    @FXML
    private void showAppointmentReminder(){
        
        LocalDateTime checkDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        FilteredList<Appointments> reminderList = new FilteredList<>(ApplicationData
                    .getAllAppointments());
        
        reminderList.setPredicate(reminder -> {
            
            LocalDateTime startDate = LocalDateTime.parse(reminder.getStartDate(), dtf);
            
            return startDate.isAfter(checkDate.minusMinutes(1)) 
                    && startDate.isBefore(checkDate.plusMinutes(15));
            
        });
        
        if(reminderList.size() > 0){
            myalerts.createAppointmentReminderAlert(reminderList);
        }
        else{
            
            myalerts.createNoAppointmentsAlert();
            
        }
        
    } // end showAppointment reminder
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // set the focus on the login button.
        this.loginBtn.requestFocus();
        this.zoneIDLbl.setText(ZoneId.systemDefault().toString());
       
        //Locale.setDefault(new Locale("fr"));
        Locale currentLocale = Locale.getDefault();
        
        //Convert to French.
        if (currentLocale.getLanguage() == "fr")
        {
             
             // Create resource bundle.
             rb = ResourceBundle.getBundle("Properties.HeinoBundle"
                     , Locale.getDefault());
             
             // Set the text of the components
             this.loginBtn.setText(rb.getString("login"));
             this.userNameLbl.setText(rb.getString("username"));
             this.passwordLbl.setText(rb.getString("password"));
             
        } // end initialize.
      
    } // end intialize
    
} // end loginFXMLController    
