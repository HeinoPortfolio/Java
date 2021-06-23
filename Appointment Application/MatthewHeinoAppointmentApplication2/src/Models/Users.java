package Models;

import java.sql.Timestamp;

/** Class that will model data about the users of the application.
 *
 * @author Matthew Heino
 */
public class Users {
    
    /** User's ID number */
    private int userID;
    
    /** User's name */
    private String userName;
    
    /** User's password */
    private String password;
    
    /** User's create date */
    private Timestamp createDate;
    
    /** User's create by */
    private String createdBy;
    
    /** User's last update */
    private Timestamp lastUpdate;

    /** Explicit value constructor
     *  
     * @param userID user's ID
     * @param username user's name
     * @param password user's password
     * @param createDate user's create date
     * @param createdBy user's created by
     * @param lastUpdate user's info last update
     * 
     */
    public Users(int userID, String username, String password, Timestamp createDate, 
            String createdBy, Timestamp lastUpdate) {
        
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
    }

    /** Method to get the user's ID.  Method that returns only the user's ID.  
     *  No other information will be returned.
     * 
     * @return user ID
     * 
     */
    public int getUserID() {
        return userID;
    }

    /** Method to set the user's ID. 
     * 
     * @param userID  user's ID
     * 
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /** Method to get user's password.  Method will retrieve the user's password.
     *  No other information will be returned. 
     * 
     * @return user's password
     * 
     */
    public String getPassword() {
        return password;
    }

    /** Method to set the password.
     * 
     * @param password user's password
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Method to get the time of user's creation.  Will create the time and  
     *  date that the user was created.
     * 
     * @return  create time of user record
     * 
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /** Method to set the create date.  Method that sets the user's create date.
     * Not used in this project.  It was used to test the class.
     * 
     * @param createDate create date of user
     * 
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /** Method to get how the user was created.  Method to return how the user 
     *  was created.  There were two options app or via database script.  This 
     *  was only used to test the class and was not used in the project directly.  
     * 
     * @return  created by
     * 
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** Set method of creation.  Sets how the user was created.  Options were 
     *  database script or by the application.  This method was not used in this 
     *  project, only used to test the class. 
     * 
     * @param createdBy  create by
     * 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** Method to get when the user was last updated.  Method to return the date
     *  of when the user was last updated.
     * 
     * @return date of last update 
     * 
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /** Method to set the last update method. Set the time and date of when the 
     *  user was last updated.
     * 
     * @param lastUpdate time of last update 
     * 
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** Method to get user name. Method to retrieve the user name as a String. 
     *  No other information will be returned. 
     *  
     * @return user name 
     * 
     */
    public String getUserName() {
        return userName;
    }

    /** Method to set user name. Method to set the user name as a String.
     * 
     * @param userName user name
     * 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** Default Constructor.  
     * 
     */
    public Users() {
    }
      
} // end Users
