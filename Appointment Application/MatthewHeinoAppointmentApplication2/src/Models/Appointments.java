package Models;

/** Class that will model data for an appointment. This class will hold all 
 *  data that is needed to be used for displaying and manipulating an 
 *  appointment.
 *
 * @author Matthew Heino
 * 
 */
public class Appointments {
    
    /** Appointment's ID number */
    private int appointmentID;
    
    /** Appointment's title */
    private String title;
    
    /** Appointment's description */
    private String description;
    
    /** Appointment's location */
    private String location;
    
    /** Appointment's type */
    private String type;
    
    /** Appointment's contact ID */
    private int contactID;
    
    /** Appointment's contact name */
    private String contactName;
    
    /** Appointment's start date and time */
    private String startDate;
    
    /** Appointment's end date and time */
    private String endDate;
    
     /** Appointment's customer's ID */
    private int customerID;

    /** Default constructor
     * 
     */
    public Appointments() {
    }

    /** Explicit constructor
     * 
     * @param appointmentID appointment's ID (auto generated by database)
     * @param title title of the appointment
     * @param description description of the appointment
     * @param location location of the appointment
     * @param type type of appointment
     * @param contactID appointment contact ID
     * @param contactName contact name of appointment
     * @param startDate start date and time of appointment
     * @param endDate end date and time of  appointment
     * @param customerID  customer ID for appointment
     * 
     */
    public Appointments(int appointmentID, String title, String description,
            String location, String type, int contactID, String contactName, 
            String startDate, String endDate, int customerID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contactID = contactID;
        this.contactName = contactName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerID = customerID;
    }

    /** Method to get appointment ID.  Method will return the appointment ID 
     *  associated with this object instance
     * 
     * @return appointment ID
     * 
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /** Method to set the appointment ID. Method will set the appointment ID 
     *  associated with this object.
     * 
     * @param appointmentID  appointment ID
     * 
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /** Method to get the title. Method to return the title of the appointment 
     *  associated with this instance of the appointment. 
     * 
     * @return title of the appointment
     * 
     */
    public String getTitle() {
        return title;
    }

    /** Method to set the title. Method to set the title of the appointment.
     * 
     * @param title  title of the appointment
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Method to get the description. Method to retrieve the appointment 
     *  description of the appointment
     * 
     * @return  description of the appointment
     * 
     */
    public String getDescription() {
        return description;
    }

    /** Method to set the description. Method to set the description for this 
     *  instance of an appointment.
     * 
     * @param description  description of the appointment
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Method to get the location. Method to return the appointment's location.
     * 
     * @return location of the appointment.
     * 
     */
    public String getLocation() {
        return location;
    }

    /** Method to set the location. Method to set the appointments location.
     * 
     * @param location location of the appointment
     * 
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Method to get type of meeting.  Method will return the type of meeting 
     *  the appointment is.
     * 
     * @return type of appointment
     * 
     */
    public String getType() {
        return type;
    }

    /** Method to set the type. Method to set the type of appointment based on 
     *  a list given in the application
     * 
     * @param type type of the appointment
     * 
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Method to get contact ID. Method to return the contact's ID. This 
     *  method does not return all the contact's information only the contact's 
     *  ID. 
     * 
     * @return contact ID
     * 
     */
    public int getContactID() {
        return contactID;
    }

    /** Method to set the contact ID. Method to set the contact's ID.  
     * <b>Note:</b> This method was only used to test the class early in the 
     *  project's creation.
     * 
     * @param contactID contact ID
     * 
     */ 
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /** Method to get contact name. Method to return the name of the contact. 
     *  This method only returns the contact's name no other information is 
     *  returned.
     * 
     * @return contact name
     * 
     */
    public String getContactName() {
        return contactName;
    }

    /** Method to set the contact name. Method to only set the contact's name. 
     *  <b>Note: </b>This method was only used to test the class early in the project
     * 
     * @param contactName contact name
     * 
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** Method to get the start date.  Method to set the start date of the 
     *  appointment.  The start date will be stored as a string and will 
     *  converted as needed in other parts of the application. 
     * 
     * @return start date and time
     * 
     */
    public String getStartDate() {
        return startDate;
    }

    /** Method to set the start Date. Method to set the start date of the 
     *  appointment. This start date will be a string and date and time 
     *  functions will be handled in code in other areas of the application.
     * 
     * @param startDate start date and time
     * 
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /** Method to get the end date. Method will return the end date of the 
     *  application.  The end state will be returned as a String.  Date and 
     *  time operations will be handled else where in the application.
     * 
     * @return end date and time
     * 
     */
    public String getEndDate() {
        return endDate;
    }

    /** Method to set the end date. Method will set the end date of the 
     *  appointment.  The end date and time will be stored as a string.  Date 
     *  and time operations will be performed in other areas of the application.  
     * 
     * @param endDate end date and time
     * 
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /** Method to get customer ID.  Method to return the ID of the customer.  
     *  This method will only return the ID of the customer. No other 
     *  information will be returned.  
     * 
     * @return customer ID
     * 
     */
    public int getCustomerID() {
        return customerID;
    }

    /** Method to set the customer ID.  Method to set the customer ID. Will only 
     *  set the ID no other information will be changed  
     * 
     * @param customerID customer ID
     * 
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /** Overridden to string - used to display contents
     * 
     * @return string with appointment data 
     */
    @Override
    public String toString() {
        return "Appointments{" + "appointmentID=" + appointmentID 
                + ", title=" + title + ", description=" + description 
                + ", location=" + location + ", type=" + type + ", contactID=" 
                + contactID + ", contactName=" + contactName + ", startDate=" 
                + startDate + ", endDate=" + endDate + ", customerID=" 
                + customerID + '}';
    }
    
} // end class appointments.