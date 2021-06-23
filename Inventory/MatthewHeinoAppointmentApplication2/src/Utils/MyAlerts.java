package Utils;

import Models.Appointments;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;

/** An alerts class that will display alerts and other information.
 *
 * @author Matthew Heino
 */
public class MyAlerts {
    
    /** Declare once and reuse. */
    Alert errorAlert = new Alert(Alert.AlertType.NONE);
    
    /** Method to show a login error alert.
     * 
     */
    public void createLoginError(){
        
        //Locale.setDefault(new Locale("fr"));
        
        Locale currentLocale = Locale.getDefault();
        
        try
        {
            ResourceBundle rb = ResourceBundle.getBundle("Properties.HeinoBundle"
                     , Locale.getDefault());
        
            if (currentLocale.getLanguage() == "fr")
            {
                System.out.println(currentLocale);
                System.out.println("French");
             
                // Create resource bundle.
                rb = ResourceBundle.getBundle("Properties.HeinoBundle"
                     , Locale.getDefault());
                errorAlert.setContentText(rb.getString("loginerror"));
                errorAlert.setTitle("errortitle");
             
            } // end initialize.
        }
        catch(MissingResourceException mre)
        {
                // set alert type 
                errorAlert.setContentText("There was an error in your "
                        + "login information. Please try again.");
                errorAlert.setTitle("Login Error");
        }
        finally
        {
            errorAlert.setAlertType(Alert.AlertType.ERROR);
        
            // Show the alert.
            errorAlert.show();
        }
        
    } // end createLoginError 
    
    
    /** Method to display No selection error.
     * 
     */
    public void createNoSelectionAlert(){
    
        // set alert type 
        errorAlert.setAlertType(Alert.AlertType.ERROR);
        errorAlert.setContentText("You did not choose an item.  Please choose "
                + "an item from the table.");
        
        errorAlert.setTitle("No Selection Made.");
  
        // Show the alert.
        errorAlert.show();
    }
    
    /** Method to create successful deletion alert.
     * 
     * @param type type of alert
     * 
     */
    public void createSuccesfulAdditionDeletion(int type)
    {
        errorAlert.setAlertType(Alert.AlertType.CONFIRMATION);
        
        if(type == 1){
             
           errorAlert.setContentText("The customer has been  succesfully deleted.");
           errorAlert.setTitle("Customer Deleted.");
        }
        else if(type == 2)
        {
           errorAlert.setContentText("Successful addition of the Customer.");
           errorAlert.setTitle("Customer Added.");
        }
        else if(type == 3)
        {
           errorAlert.setContentText("Successful addition of the Appointment.");
           errorAlert.setTitle("Appointment Added.");
        }
        else if(type == 4){
        
            errorAlert.setContentText("Successful updating of the Appointment.");
            errorAlert.setTitle("Appointment Updated.");
        }
        else if( type == 5)
        {
            errorAlert.setContentText("Successful deletion of the Appointment.");
            errorAlert.setTitle("Appointment Deleted.");
        }
        
        // Show the alert.
        errorAlert.show();
    
    } // end createSuccessfulDeletion.
    
    /** Method to create an alert for errors in form input. 
     * 
     * @param errorMessage  error message to be displayed
     * 
     */
    public void createFormInputError(String errorMessage){
        
         errorAlert.setAlertType(Alert.AlertType.ERROR);
         errorAlert.setContentText(errorMessage);
         errorAlert.setTitle("Error on Form.");
         
        // Show the alert.
        errorAlert.show();
       
    } // end createFormInputError.
    
   
    /** Method to create an alert that the user has an appointment in 15 minutes.
     * 
     * @param reminderList list of upcoming  appointments in the next 15 minutes
     * 
     */ 
    public  void createAppointmentReminderAlert(FilteredList<Appointments> reminderList )
    {
        String reminderMessage = " You have the following appointments: \n";
        
        for(Appointments appt : reminderList){
        
            reminderMessage = reminderMessage +"\nAppointment ID: "+ appt.getAppointmentID() 
                    + "\nDate: " + appt.getStartDate().substring(0, 10) 
                    + "\nTime: " + appt.getStartDate().substring(11) + "\n" ;
            
        }
        
        errorAlert.setAlertType(Alert.AlertType.INFORMATION);
        errorAlert.setContentText(reminderMessage);
        errorAlert.setTitle("Upcoming Appointments");
  
        // Show the alert.
        errorAlert.showAndWait();
        
    } // end createAppointmentReminderAlert.
    
    /** Method to create an error screen if file cannot be written to.
     * 
     */
    public void createErrorWritingToFile(){
    
        errorAlert.setAlertType(Alert.AlertType.ERROR);
        errorAlert.setContentText("Error writing to the log file.");
        errorAlert.setTitle("Error Writing to the log file.");
         
        // Show the alert.
        errorAlert.show();
     
    }
    
    /** Method to show the user that they do not have any upcoming appointments
     * 
     */
    public void createNoAppointmentsAlert(){
        
        errorAlert.setAlertType(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("You do not have any appointments in the next 15 minutes.");
        errorAlert.setTitle("Upcoming Appointments");
  
        // Show the alert.
        errorAlert.showAndWait();
        
    }
    
    /** Method to show an overlap in times scheduled.
     * 
     * @param message error message 
     */
    public void createOverLapAlert(String message)
    {
    
        errorAlert.setAlertType(Alert.AlertType.ERROR);
         errorAlert.setContentText(message);
         errorAlert.setTitle("Overlap Error.");
         
        // Show the alert.
        errorAlert.show();
    }
    /** Method to display a an message upon successful deletion of appointment. 
     *  This method takes an Appointments object as a parameter
     * 
     * @param appt appointment that was deleted
     */
    public void createDeletedAppointment(Appointments appt){
        
        String message = "Appointment with the following information has been "
                + "deleted: " +"\nAppointment ID: " + appt.getAppointmentID() 
                + "\nType: " + appt.getType();
        
        errorAlert.setAlertType(Alert.AlertType.ERROR);
        errorAlert.setContentText(message);
        errorAlert.setTitle("Appointment Deleted.");
         
        // Show the alert.
        errorAlert.show();
    
    }
   
} // end class MyAlerts
