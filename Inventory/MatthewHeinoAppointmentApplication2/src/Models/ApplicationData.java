package Models;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class that will hold all the application data. It will hold lists that 
 *  contain all the customers, appointments, countries, locations and types of 
 *  appointments.
 *
 * @author Matthew Heino
 */
public class ApplicationData {
    
    /** ObservableList to hold all available customers in the application. */
    private static ObservableList<Customer> allCustomers =  FXCollections.observableArrayList();
    
    /** ObservableList to hold all available countries in the application. */
    private static ObservableList<Countries> countriesList = FXCollections.observableArrayList();
    
    /** ObservableList to hold all appointments in the application. */
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    
    /** ObservableList to hold all locations in the application. */
    private static ObservableList<String> locations = FXCollections
            .observableArrayList("Phoenix, Arizona","White Plains, New York"
                    , "Montreal, Canada", "London, England");
    
    /** ObservableList to hold all types of appointments in the application. */
    private static ObservableList<String> types = FXCollections
            .observableArrayList("De-Briefing","Planning Session"
                    ,"Marketing", "Consultation", "New Account", "Other");
    
    /** ObservableList to hold all contacts in the application. */
    private static ObservableList<Contacts> contactList = FXCollections.observableArrayList(); 
    
    
    /** Holds the User ID who is currently logged in. */
    private static int userID;
      
    /** Method to add a customer to the list.  This method will add a customer 
     * to the application's list of available customers.
     * 
     * @param customer Customer object to be added
     */
    public static void addCustomer(Customer customer){
        
        allCustomers.add(customer);
        
    } // end addCustomer.
    
    /** Method to lookup a customer. To lookup a customer based on the ID of 
     *  the customer.
     * 
     * @param customerID Customer ID to be looked up.
     * @return Customer that has been found else null
     * 
     */
    public static Customer lookupCustomer(int customerID){
    
        for(Customer lookupCustomer : allCustomers ){
            
            if(lookupCustomer.getCustomerID() == customerID){
                return lookupCustomer;
            }
            
        }
        
         return null;
         
    } // end lookupCustomer.
    
    
    /** Method to lookup an appointment. Method to lookup an appointment based 
     *  on a customer object
     * 
     * @param cust Customer object to be looked up.
     * @return  appointment object or null
     * 
     */
    public static Appointments lookupAppointment(Customer cust){
        
        for(Appointments appt : allAppointments)
        {
            if(appt.getCustomerID() == cust.getCustomerID())
                return appt;
        
        }
       
        return null;
    }
    
    /** Method to update the customer list. This will update a customer at a 
     *  given index using the information passed in the Customer object.
     * 
     * @param index index of the customer to be updated.
     * @param updateCustomer  Customer to be added to the customers list.
     * 
     */
    public static void updateCustomer(int index, Customer updateCustomer){
    
        allCustomers.set(index, updateCustomer);
        
    } // end updateCustomer.
    
    /** Method to delete a customer from the list. This method will remove a 
     *  customer from the available list of customers.  This method does not 
     *  remove the customer from the database.  This functionality is handled 
     *  by a method in the DBUitls class located in the Utils package. 
     * 
     * @param deletedCustomer customer to be deleted
     * @return  true if deleted false otherwise
     * 
     */
    public static boolean deleteCustomer(Customer deletedCustomer){
        
        for( Customer ct : allCustomers){
            
            if(deletedCustomer.getCustomerID() == ct.getCustomerID()){
            
                allCustomers.remove(ct);
                
                return true;
            }
        }
        
        return false;
        
    } // end deleteCustomer.
    
    
    /** Method to delete an appointment based on customer ID.  This method will 
     *  delete a customer based on the customer object that has been passed to 
     *  it as an argument. 
     * 
     * @param deletedCustomerID id of the customer to delete
     * @return  true if deleted false otherwise
     * 
     */
    public static boolean deleteAppointment(Customer deletedCustomerID){
 
        for(Appointments appt : allAppointments){
            
            if(appt.getCustomerID() == deletedCustomerID.getCustomerID())
            {
              allAppointments.remove(appt);
              return true;
            }
            
        }
     
        return false;
        
    } // end deleteAppointment
    
    /** Method to return all the customers. This method will return all the 
     * customers that are contained in the observable list.
     * 
     * @return all the customers in the observable list
     */
    public static ObservableList<Customer> getAllCustomers(){
    
        return allCustomers;
        
    }
    
    /** Add a single country to the list. Method to add a single country to the 
     *  list of countries that are maintained by the application.
     * 
     * @param country country to be added to the countries list.
     * 
     */
    public static void addCountry(Countries country){
        
        countriesList.add(country);
    }
    
    /** Method to add a list of countries.  Method to add more than one country 
     *  to the list of countries that is maintained by the application.
     * 
     * @param countries list of countries to be added
     * 
     */
    public static void addCountries(ArrayList<Countries> countries){
        
        countriesList.addAll(countries);
        
    }
    
    
    /** Method to get all the countries currently in the list.  Method to return 
     *  all the countries that are maintained by the application.
     * 
     * @return list of countries 
     */
    public static ObservableList<Countries> getCountries(){
        
        return countriesList;
        
    }
    
    /** Method to get country. Method to return a specific country's ID given 
     *  the string name of the country.
     * 
     * @param country country to be searched for
     * @return  country ID or -1 if not found
     * 
     */
    public static int getCountryID(String country){
        
        for(Countries cntry : countriesList){
            
            if(cntry.getCountry().equals(country))
            {
                return cntry.getCountryID();
            }
            
        }
        
        return -1;
    
    } // end getCountryID.

    /** Method to get the User ID.  Method to retrieve the user's ID.  This ID 
     *  was retrieved during the first successful login of the user.
     * 
     * @return user id
     */
    public static int getUserID() {
        return userID;
    }

    /** Method to set the User ID.  Method to set the user ID.  This method 
     *  will initialize the class member associated with the user ID. 
     * 
     * @param userid  user ID 
     * 
     */
    public static void setUserID(int userid) {
       
        userID = userid;
    }

    /** Method to get all the available appointments list. Method to get all 
     *  the appointments that are in the application.
     * 
     * @return all appointments in the application
     * 
     */
    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    /** Method to set the appointments list. Method that will take an Observable 
     *  list that contains appointments for the given user.
     * 
     * @param allAppointments  appointments used to initialize the user's appointments
     * 
     */
    public static void setAllAppointments(ObservableList<Appointments> allAppointments) {
        ApplicationData.allAppointments = allAppointments;
    }

    /** Method to get locations. Method to return the locations that are 
     * contained within the application.
     * 
     * @return list of locations.
     * 
     */
    public static ObservableList<String> getLocations() {
        return locations;
    }

    /** Method to set the locations. Method to set the locations that will be 
     *  available in the application. 
     * 
     * @param locations locations to set the locations list
     * 
     */
    public static void setLocations(ObservableList<String> locations) {
        ApplicationData.locations = locations;
    }

    /** Method to get the types of appointments. Method will return a list with 
     *  the available types of appointments.
     * 
     * @return list of types of appointments
     * 
     */
    public static ObservableList<String> getTypes() {
        return types;
    }

    
    /** Method that sets the types of appointments. Method that sets the types 
     *  of appointments that are available in the application.
     * 
     * @param types  list with types of appointments
     * 
     */ 
    public static void setTypes(ObservableList<String> types) {
        ApplicationData.types = types;
    }

    /** Method to get a contact list. Method will return all the contacts that 
     * are contained in the application.  The list will be an observable list.
     * 
     * @return contact list
     * 
     */
    public static ObservableList<Contacts> getContactList() {
        return contactList;
    }

    /** Method to set a contact list.  Method to set the contact list for the 
     *  applications.  The contact list.   
     * 
     * @param contactList contact list to set the application's contact list.
     * 
     */
    public static void setContactList(ObservableList<Contacts> contactList) {
        ApplicationData.contactList = contactList;
    }
     
}// end class.
