package Utils;

import Models.ApplicationData;
import Models.Appointments;
import Models.Contacts;
import Models.Countries;
import Models.Customer;
import Models.FirstLevelDivisions;
import Models.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
//import java.sql.Timestamp;
import java.util.ArrayList;

/** Class that will provide all the database functionality.
 * <p><b>Note:</b> The database driver was changed and may need to be changed 
 * in order to run.  THe driver that was mentioned in the videos has been 
 * deprecated. </p>
 * @author Matthew Heino
 * 
 */
public class DBUtilities {
    
    /** Connection to the database */
    private Connection  conn = null;
    
    /** MyAlerts for setting up an alert */
    MyAlerts myAlerts = new MyAlerts();
    
    // Connection information.
    /** Protocol used in database connection */
    private static final String PROTOCOL = "jdbc";
    
    /** Type of database */
    private static final String VENDORNAME = ":mysql:";
    
    /** IP address of the database */
    private static final String IPADDRESS = "//wgudb.ucertify.com/WJ085CV";
    
    /** Complete string of connection */
    private static final String JDBCURL = PROTOCOL + VENDORNAME +IPADDRESS;
    
    /** Driver used in connection */
    private static final String MYJDBCDRIVER = "com.mysql.cj.jdbc.Driver";
    
    

    /** Default constructor. No arguments.*/
    public DBUtilities() {
    }
    
    /** Method to add an appointment to the database
     * 
     * @param title  appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param startDateTime appointment start date and time
     * @param endDateTime appointment end date and time
     * @param custID appointment customer ID
     * @param userID appointment user ID
     * @param contactID appointment contact ID
     * 
     */
    public void AddAppointment(String title,String description, String location, String type, 
            LocalDateTime startDateTime, LocalDateTime endDateTime,int custID
            , int userID, int contactID)
    {
        Connection connDC = this.createConnection();
        
        Timestamp startTime = Timestamp.valueOf(startDateTime);
        Timestamp endTime = Timestamp.valueOf(endDateTime);
          
        int row = 0;
        
        try{
            // Delete the appointments for the user
            PreparedStatement stmnt =  connDC.prepareStatement("INSERT INTO appointments "
                    +"(Title,Description,Location,Type,Start,End,Created_By,"
                    + "Last_Updated_By,Customer_ID, User_ID,Contact_ID)"
                    +"VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            
            
            stmnt.setString(1, title);
            stmnt.setString(2, description);
            stmnt.setString(3,location);
            stmnt.setString(4, type);    
            stmnt.setTimestamp(5, startTime);
            stmnt. setTimestamp(6, endTime);
            stmnt.setString(7,"app");
            stmnt.setString(8,"app");
            stmnt.setInt(9,custID);
            stmnt.setInt(10,userID);
            stmnt.setInt(11,contactID);
             
            row = stmnt.executeUpdate();
           
            conn.close();
            
            if(row == 1){
               
               myAlerts.createSuccesfulAdditionDeletion(3);
               
            }
          
        }
        catch(SQLException se){
            se.printStackTrace();
        
        }
    } // end addAppointment.
    
     
    /** Method to add a customer to the database. Method will take a Customer 
     *  object as a parameter and will add its components the to database.
     * 
     * @param newCustomer  customer to be added
     * 
     */
     public void addCustomer(Customer newCustomer){
        
        Connection connDC = this.createConnection();
        int row = 0;
        
        try{
            // Delete the appointments for the user
            PreparedStatement stmnt =  connDC.prepareStatement("INSERT INTO customers "
                    +"(Customer_Name,Address,Postal_Code,Phone,Create_Date,"
                    +"Created_By,Last_Update,Last_Updated_By,Division_ID) "
                    +"VALUES(?,?,?,?,?,?,?,?,?)");
            
            // Set the parameter.
            stmnt.setString(1,newCustomer.getCustomerName());
            stmnt.setString(2,newCustomer.getAddress());
            stmnt.setString(3,newCustomer.getPostalCode());
            stmnt.setString(4,newCustomer.getPhone());
            stmnt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmnt.setString(6,"app");
            stmnt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmnt.setString(8,"app");
            stmnt.setInt(9, newCustomer.getDivisionID());
            
            row = stmnt.executeUpdate();
           
            conn.close();
            
            if(row == 1){
               
               myAlerts.createSuccesfulAdditionDeletion(2);
               
            }
      
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        
    }// end add addCustomer.
    
    /** Method to get Contact information. Method to get the contact's name 
     *  using the contact's ID.  This method will return a String that contains 
     *  the contact's name
     * 
     * @param contactID contact ID
     * @return  contact info
     * 
     */
     public String getContactInfo(int contactID){
        
        String contactInfo = "";
        Connection contactConn =  this.createConnection();
        
        // Create a prepared statement.
        PreparedStatement stmnt;
        
        try 
        {
            stmnt = contactConn.prepareStatement("SELECT contact_name FROM contacts WHERE Contact_ID = ?");
            
            // Set the values.
            stmnt.setInt(1, contactID);
         
            ResultSet rs = stmnt.executeQuery();
        
            while(rs.next()){
             
               contactInfo = rs.getString("contact_name") ;
            
            }
            
           rs.close();
           conn.close();
           
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return contactInfo;
    } // end getContactINfo.
     
    /** Method to create a list of appointments. Method to create a list of all 
     *  the user's appointments.  The list will be retrieved using the user's 
     *  ID.  The method will return this list as an ArrayList.  The times and 
     *  dates will be converted to the local time of the user. 
     * 
     * @param userID user ID
     * @return appointment list
     * 
     */
    public ArrayList createAppointmentList(int userID){
        
        ArrayList<Appointments> appointmentList =  new ArrayList();
        DateTimeConversUtil dtcu = new DateTimeConversUtil();
        
        try{
             // Get the Connection.
            Connection connCL = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connCL.prepareStatement("SELECT appointments.Appointment_ID,"
                    +"appointments.Title,appointments.Description,appointments.Location,"
                    +"appointments.Type,appointments.Start,appointments.End,appointments.Customer_ID,"
                    +"appointments.Contact_ID, contacts.Contact_Name "
                    +"FROM appointments JOIN contacts ON "
                    +"appointments.Contact_ID = contacts.Contact_ID "
                    +"WHERE appointments.User_ID = ? ORDER BY appointments.start");
            
            stmnt.setInt(1, userID);
        
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
            
               int apptID = rs.getInt("appointments.Appointment_ID");
               String title = rs.getString("appointments.Title");
               String descr = rs.getString("appointments.Description");
               String loc = rs.getString("appointments.Location");
               String type =rs.getString("appointments.Type");
               
               String start =  dtcu.convertDateTime( rs.getString("appointments.Start").substring(0, 16));             
               String end =  dtcu.convertDateTime(rs.getString("appointments.End").substring(0, 16));
               
               int custID = rs.getInt("appointments.Customer_ID");
               int contID = rs.getInt("appointments.Contact_ID");
               String contName = rs.getString("contacts.Contact_Name");
               
               Appointments newAppt = new Appointments(apptID,title,descr,loc
                       ,type,contID,contName,start,end,custID
                       );
                
               appointmentList.add(newAppt);
               
            } // end while
             
        } // try
        catch(SQLException se){
            se.printStackTrace();
        }
      
        return appointmentList;
        
    } // end createApppointmentList
    
    
    /** Method to create a connection to the database.  This method will create 
     * a connection to the database.  <b>Note: </b> This method uses members 
     * that were declared and initialized at the head of the file.
     * 
     * @return Connection 
     * 
     */
    public Connection createConnection()
    {
        
        try{
            
            Class.forName(MYJDBCDRIVER);
            conn = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
            
        }
        catch(ClassNotFoundException cnfe){
            System.out.println(cnfe.getMessage());
        }
        catch(SQLException se){
            System.out.println(se.getMessage());
        }
        
        return conn;
        
    } // end create connection.
    
    
    /** Method to retrieve appointments of the contacts. Method to retrieve a 
     *  contact's appointments of the for use by the application.  This will 
     *  retrieve all contact's that are in the database.
     * 
     * @param contactID   contact's id to be searched
     * @return array list with the contact's appointments
     * 
     */
    public ArrayList createContactAppointmentList(int contactID){
        
        ArrayList<Appointments> contactAppointments = new ArrayList();
        
        DateTimeConversUtil dtcu = new DateTimeConversUtil();
        
        try{
             // Get the Connection.
            Connection connCL = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connCL
                    .prepareStatement("SELECT appointments.Appointment_ID,"
                            + "appointments.Title,appointments.Type,"
                            + "appointments.Description,appointments.Start,"
                            + "appointments.End,appointments.Customer_ID"
                            + ",appointments.Contact_ID FROM appointments "
                            + "WHERE Contact_ID = ? "
                            + "ORDER BY appointments.Start");
         
            stmnt.setInt(1, contactID);
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
            
               int apptID = rs.getInt("appointments.Appointment_ID");
               String title = rs.getString("appointments.Title");
               String type= rs.getString("appointments.Type");
               String description = rs.getString("appointments.Description"); 
               String startDate =  dtcu.convertDateTime( rs.getString("appointments.Start").substring(0, 16));             
               String endDate =  dtcu.convertDateTime(rs.getString("appointments.End").substring(0, 16));
              
               int contID = rs.getInt("appointments.Contact_ID");
               int custID = rs.getInt("appointments.Customer_ID");
                
               // Create a new Apppointment with contact information.
               Appointments appt = new Appointments(apptID, title, description
                       ,null,type,contID,null, startDate,endDate, custID);
           
                // Add country the array list.
                contactAppointments.add(appt);
           
            } // end while
             
        } // try
        catch(SQLException se){
            se.printStackTrace();
        }
      
        return contactAppointments;
        
    } // 
    
    /** Method to create a contact list. Method to create a contact list for 
     *  use by the application.
     * 
     * @return  contact list
     */
    public ArrayList createContactList(){
    
        ArrayList<Contacts> contactList = new ArrayList();
        
        try{
             // Get the Connection.
            Connection connCL = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connCL.prepareStatement("SELECT * FROM contacts");
        
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
            
               int contactID = rs.getInt("Contact_ID");
               String contactName = rs.getString("Contact_Name");
               String email = rs.getString("Email"); 
                
               // Create a new Country.
               Contacts newContact = new Contacts(contactID, contactName, email);
           
               // Add country the array list.
               contactList.add(newContact);
           
            } // end while
             
        } // try
        catch(SQLException se){
            se.printStackTrace();
        }
        
        return contactList;
        
    } // end createContactList.
    
    
    /** Method to create a country list. Method to retrieve a country 
     *  information list.  Will return an ArrayList.
     * 
     * @return country list
     * 
     */
    public ArrayList createCountryList(){
        
        ArrayList<Countries> countryList = new ArrayList();
        
        try{
             // Get the Connection.
            Connection connCL = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connCL.prepareStatement("SELECT  Country_ID,country "
                    +"FROM countries;");
        
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
            
               int countryID = rs.getInt("Country_ID");
               String country = rs.getString("country");
                
               // Create a new Country.
               Countries countries = new Countries(countryID,country);
           
                // Add country the array list.
                countryList.add(countries);
           
            } // end while
            
             rs.close();
             conn.close();
             
        } // try
        catch(SQLException se){
            se.printStackTrace();
        }
        
       return countryList;
    }
    
    /** Method to populate to the customer list.  Method will create a list of 
     *  customers to be used by the application. The list will be returned 
     *  as an ArrayList.
     * 
     * @return customer list
     *  
     */
    public ArrayList createCustomerList() {
        
        ArrayList<Customer> customerList = new ArrayList();
         
        try{
            
            // Get the Connection.
            Connection connCL = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connCL.prepareStatement("SELECT customers.Customer_ID,customers.Customer_Name," 
                    +"customers.Address,customers.Postal_Code,customers.Phone,first_level_divisions.Division_ID,"
                    +"first_level_divisions.Division,countries.Country_ID,"
                    +"countries.Country FROM customers JOIN first_level_divisions" 
                    +" ON customers.Division_ID = first_level_divisions.Division_ID" 
                    +" JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID;");
        
            ResultSet rs = stmnt.executeQuery();
        
            // Iterate through the result set.
            while(rs.next()){
            
                // Create a new Customer
            
                int ID = rs.getInt("customers.Customer_ID");
                String name = rs.getString("customers.Customer_Name");
                String addr = rs.getString("customers.Address");
                String postal = rs.getString("customers.Postal_Code");
                String phone = rs.getString("customers.Phone"); 
                int divID = rs.getInt("first_level_divisions.Division_ID");
                String division = rs.getString("first_level_divisions.Division");
                int countryID = rs.getInt("countries.Country_ID");
                String country = rs.getString("countries.Country");
               
                Customer newCustomer = new Customer(ID,name,addr,postal,phone,
                        divID,division,countryID,country);
            
                // Add customer the array list
                customerList.add(newCustomer);
            
            } // end while
     
            rs.close();
            conn.close();
           
        } // edn try
        catch(SQLException se){
            se.printStackTrace();
        }
         return customerList;
    } // end createCustomerList  
    
    /** Method to remove an appointment from the database.  This method will 
     *  delete an appointment given an appointment ID. The ID will be passed as 
     *  a parameter and its type will be an int.
     * 
     * @param appointmentID  appointment ID
     * @return  rows affected
     * 
     */
    public int deleteAppointment(int appointmentID){
        
        Connection connDC = this.createConnection();
        int row = 0;
        
        try{
            // Delete the appointments for the user
            PreparedStatement stmnt =  connDC.prepareStatement("DELETE FROM appointments " 
                + "WHERE  appointments.Appointment_ID =  ?");
            
            // Set the parameter.
            stmnt.setInt(1, appointmentID);
            row = stmnt.executeUpdate();
       
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        
        return row;
        
    } // end deleteApppointment.
    
    /** Method to delete a customer from the database. This method will delete 
     *  a customer from the database.  The customer's ID will be used to delete 
     *  the customer from the database.  
     * 
     * @param customerID customer ID
     * @return  rows affected
     * 
     */
    public int deleteCustomer(int customerID){
        
        Connection connDC = this.createConnection();
        int row = 0;
        
        try{
            // Delete the appointments for the user
            PreparedStatement stmnt =  connDC.prepareStatement("DELETE FROM appointments " 
                + "WHERE Customer_ID = ?");
            
            // Set the parameter.
            stmnt.setInt(1, customerID);
            row = stmnt.executeUpdate();
           
            PreparedStatement stmnt2 =  connDC.prepareStatement("DELETE FROM customers " 
                + "WHERE Customer_ID = ?");
            
            stmnt2.setInt(1, customerID);
            
            row = stmnt2.executeUpdate();  
            
            if(row == 1){
                myAlerts.createSuccesfulAdditionDeletion(1);
            }
            conn.close();
      
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        
        return row;
        
    } // end deleteCustomer.
    
    
    /** Method to retrieve first level divisions. Method to retrieve the first 
     *  level divisions.  These are retrieved by the country ID that is 
     *  associated with the division.
     * 
     * @param countryID country ID
     * @return  list of first level divisions
     * 
     */
    public ArrayList firstLevelDivisions(int countryID){
        
         ArrayList<FirstLevelDivisions> firstLevelList = new ArrayList();
         
          try{
            
            // Get the Connection.
            Connection connFLD = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connFLD.prepareStatement("SELECT division_ID,"
                    +" Division, COUNTRY_ID FROM first_level_divisions "
                    +"WHERE COUNTRY_ID = ?");
            
            // Set the parameter.
            stmnt.setInt(1, countryID);
        
            ResultSet rs = stmnt.executeQuery();
        
            // Iterate through the result set.
            while(rs.next()){
                
                int divID = rs.getInt("division_ID");
                String division = rs.getString("Division");
                int cID = rs.getInt("COUNTRY_ID");
            
               // Create a new FirstLevelDivision
               FirstLevelDivisions fld = new FirstLevelDivisions(divID
                       ,division,cID);
        
                // Add customer the array list
                firstLevelList.add(fld);
            
            } // end while
     
            rs.close();
            conn.close();
           
        } // edn try
        catch(SQLException se){
            se.printStackTrace();
        }
         
        return firstLevelList;
    
    }// end firstLevelDivsions.
    
    /** Method to get all contact information. Method to retrieve all the 
     *  contact's information.  The contact's information will be returned as a 
     *  Contact object.
     * 
     * @param contactID contact ID
     * @return  all the contact's information
     * 
    */
    public Contacts getAllContactInfo(int contactID){
    
        Contacts contact = new Contacts();
        
        Connection contactConn =  this.createConnection();
        
        // Create a prepared statement.
        PreparedStatement stmnt;
        
        try 
        {
            stmnt = contactConn.prepareStatement("SELECT Contact_ID,Contact_Name,Email "
                    + "FROM contacts WHERE Contact_ID = ?");
            
            // Set the values.
            stmnt.setInt(1, contactID);
         
            //The Result Set.
            ResultSet rs = stmnt.executeQuery();
           
            while(rs.next())
            {
                
                contact.setContact_ID(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
            
            }
            
            rs.close();
            conn.close();
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return contact;
        
    } // end getAllContactInfo.
     
    /** Method to get all a customers information.  Method to retrieve all the 
     *  customer's information.  The customer's ID will be used to retrieve the 
     *  information. The information will be returned as a Customer object.
     * 
     * @param custID customer ID
     * @return  all the customer's information
     */
    public Customer getAllCustomerInfo(int custID){
       
        Customer customer = new Customer();
        Connection custConn =  this.createConnection();
        
         // Create a prepared statement.
        PreparedStatement stmnt;
        
        try 
        {
            stmnt = custConn.prepareStatement("SELECT customers.Customer_ID,customers.Customer_Name," 
                    +"customers.Address,customers.Postal_Code,customers.Phone,first_level_divisions.Division_ID,"
                    +"first_level_divisions.Division,countries.Country_ID,"
                    +"countries.Country FROM customers JOIN first_level_divisions" 
                    +" ON customers.Division_ID = first_level_divisions.Division_ID" 
                    +" JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID "
                    + "WHERE customers.Customer_ID = ?");
            
            // Set the values.
            stmnt.setInt(1, custID);
         
            //
            ResultSet rs = stmnt.executeQuery();
           
            while(rs.next())
            {
                
                  customer.setCustomerID(rs.getInt("customers.Customer_ID"));
                  customer.setCustomerName(rs.getString("customers.Customer_Name"));
                  customer.setAddress(rs.getString("customers.Address"));
                  customer.setPostalCode(rs.getString("customers.Postal_Code"));
                  customer.setPhone(rs.getString("customers.Phone"));
                  customer.setDivisionID(rs.getInt("first_level_divisions.Division_ID"));
                  customer.setDivision(rs.getString("first_level_divisions.Division"));
                  customer.setCountryID(rs.getInt("countries.Country_ID"));
                  customer.setCountry(rs.getString("countries.Country"));
              
            } // end while
            
            rs.close();
            conn.close();
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customer;
        
    } // end getAllCustomerInfor
     
    /** Method to get the division ID. Method to return a division ID given the 
     *  division name.  The division is passed as a String parameter.
     * 
     * @param divisionName division name
     * @return  division ID
     * 
     */
    public int getDivisionID(String divisionName){
        
        Connection connDC = this.createConnection();
        int division = 0;
        
         try{
            
            // Get the Connection.
            Connection connFLD = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connFLD.prepareStatement("SELECT Division_ID FROM first_level_divisions "
                    + "WHERE Division = ?");
            
            // Set the parameter.
            stmnt.setString(1, divisionName);     
        
            ResultSet rs = stmnt.executeQuery();
        
            // Iterate through the result set.
            while(rs.next()){
                
                division = rs.getInt("Division_ID");
            
            } // end while
     
            rs.close();
            conn.close();
           
        } // edn try
        catch(SQLException se){
            se.printStackTrace();
        }
         
        return division;
    
    } // end getDivsionID. 
  
   
    /** Method to validate the user. Method to validate the user's password and 
     *  username are correct. This will facilitate access to the rest of the 
     *  application.
     * 
     * @param username user name
     * @param password pass word
     * @return true if valid false otherwise
     *  
     */
    public boolean isValidUser(String username, String password) {
        boolean isValid = false;
        
        try {
            
            
            Connection connValid = this.createConnection();
            
            // Create a prepared statement.
            PreparedStatement stmnt =  connValid.prepareStatement("SELECT User_ID,User_Name"
                    + " FROM users WHERE User_Name = ? AND Password = ?");
            
            // Set the values.
            stmnt.setString(1, username);
            stmnt.setString(2, password);
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
                
                isValid = true;
            }
            
            
        } // end isValidUser.
        catch (SQLException ex) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isValid;
    }
    
    /** Method to retrieve the average length of an appointment. Method that 
     *  displays the average length of an appointment. <b>Note: </b> This method 
     *  was used to meet the requirement for another report as given in the task.
     *  Will return a StringBuilder object to display the information.
     * 
     * @return  average appointment length as String
     * 
     */
    public StringBuilder avgAppointmentLength(){
        
        StringBuilder apptLength = new StringBuilder();
        
        apptLength.append("The avergage length of appointments by month. "
                + "Are the following: \n");
        
        try {
           
            Connection avgConn =  this.createConnection();
            
            // Create a prepared statement.
            PreparedStatement stmnt;
            
            stmnt = avgConn.prepareStatement("SELECT monthname(appointments.start) "
                    + "AS 'Month', AVG(timestampdiff(MINUTE,appointments.Start, "
                    + "appointments.End)) AS 'Monthly Average' FROM appointments "
                    + "GROUP BY monthname(appointments.start);");
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
                
                String month = rs.getString("Month");
                float number = rs.getFloat("Monthly Average");
                
                // Append to the 
                apptLength.append(month + " " + number +" " + "\n");
             
            }
          
        } catch (SQLException ex) {
            
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return apptLength;
        
    } // end avgAppointmentLength.
    
    /** Method to return the user data. Method to retrieve the user's data to be 
     *  used by the application.
     * 
     * @param user user
     * @param password password
     * @return  user object
     * 
     */
    public Users returnUserData(String user, String password){
        
        Users currentUser = new Users();
        Connection userConn =  this.createConnection();
        
        // Create a prepared statement.
        PreparedStatement stmnt;
        
        try 
        {
            stmnt = userConn.prepareStatement("SELECT User_ID, User_Name"
                    + " FROM users WHERE User_Name = ? AND Password = ?");
            
            // Set the values.
            stmnt.setString(1, user);
            stmnt.setString(2, password);
            
            //
            ResultSet rs = stmnt.executeQuery();
        
            while(rs.next()){
             
                currentUser.setUserID(rs.getInt("User_ID"));
                currentUser.setUserName(rs.getString("User_Name"));
            
            }
           
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return currentUser;
        
    } // end returnUserData.
    
    /**  Method to show how many of each type of appointment broken 
     *  down by month. 
     * 
     * @return Count type and month 
     */
    public StringBuilder totalMonthly(){
        
        StringBuilder totalMonthly = new StringBuilder();
        
        totalMonthly.append("The number of appointments by type and month. Are the following: \n");
        
        try {
           
            Connection totalConn =  this.createConnection();
            
            // Create a prepared statement.
            PreparedStatement stmnt;
            
            stmnt = totalConn.prepareStatement("SELECT monthname(appointments.Start) AS 'MONTH', Count(appointments.Appointment_ID) AS 'Number Per Month', appointments.Type AS 'Type of Appointment'\n" +
                    "FROM appointments\n" +
                    "GROUP BY monthname(appointments.start), appointments.Type;");
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
             
                
                String month = rs.getString("Month");
                int number = rs.getInt("Number Per Month");
                String typeOfAppt = rs.getString("Type of Appointment");
                
                // Append to the 
                totalMonthly.append(month + " " + number +" " + typeOfAppt + "\n");
             
            }
          
        } catch (SQLException ex) {
            
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return totalMonthly;
     
    } // end totalMonthly
    
   
    /** Method to update and appointment. Method will update all fields in the 
     *  database that are passed to it.  It does not differentiate between 
     *  changed and unchanged fields.  All fields will be updated. 
     * 
     * @param apptID appointment ID
     * @param title appointment title
     * @param description appointment description
     * @param location appointment location
     * @param type appointment type
     * @param startUTC start time and date
     * @param endUTC end time and date
     * @param custID customer ID
     * @param userID user ID
     * @param contactID contact ID
     * 
     */
    public void updateAppointment(int apptID, String title, String description
            ,String location,String type,LocalDateTime startUTC
            ,LocalDateTime endUTC, int custID,int userID,int contactID)
    {
       
        // Update the appointment
        try{
            
            // Get the Connection.
            Connection connUC = this.createConnection();
            
            Timestamp startTime = Timestamp.valueOf(startUTC);
            Timestamp endTime = Timestamp.valueOf(endUTC);
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connUC.prepareStatement("UPDATE appointments"
                    + " SET Title=?,Description=?,Type=?,Start=?,End=?"
                    + ",Last_Update=NOW(),Last_Updated_By='app',Customer_ID=?"
                    + ",User_ID=?,Contact_ID=? WHERE Appointment_ID=?"
                    );
            
            // Set the parameters.
            stmnt.setString(1,title);
            stmnt.setString(2,description);
            stmnt.setString(3,type);
            stmnt.setTimestamp(4,startTime);
            stmnt.setTimestamp(5,endTime);
            stmnt.setInt(6,custID);
            stmnt.setInt(7,userID);
            stmnt.setInt(8,contactID);
            stmnt.setInt(9,apptID);
        
           stmnt.executeUpdate();
        
           conn.close();
           
           // Show message
           myAlerts.createSuccesfulAdditionDeletion(4);
           
        } // edn try
        catch(SQLException se){
            se.printStackTrace();
        }
        
    } // end updateAppointment.
   
    /** Method to update Customer in the database.  Method to update a 
     *  customer's information in the database. The method will received this 
     *  information as a Customer object.
     * 
     * @param updatedCustomer customer to be updated
     * 
     */
     public void updateCustomer(Customer updatedCustomer){
        
        try{
            
            // Get the Connection.
            Connection connUC = this.createConnection();
        
            // Create a prepared statement.
            PreparedStatement stmnt =  connUC.prepareStatement("UPDATE customers SET "+
                    "Customer_Name=?,address=?,Postal_Code=?,Phone=?,Last_Update=Now()"
                    +",Last_Updated_By='app',Division_ID=? WHERE Customer_ID=?");
            
            // Set the parameters.
            
            stmnt.setString(1,updatedCustomer.getCustomerName());
            stmnt.setString(2,updatedCustomer.getAddress());
            stmnt.setString(3,updatedCustomer.getPostalCode());
            stmnt.setString(4,updatedCustomer.getPhone());
            stmnt.setInt(5,updatedCustomer.getDivisionID());
            stmnt.setInt(6,updatedCustomer.getCustomerID());
         
            stmnt.executeUpdate();
        
           conn.close();
           
        } // edn try
        catch(SQLException se){
            se.printStackTrace();
        }
        
    }// end updateCustomer.
   
}// end DBUtlities.
