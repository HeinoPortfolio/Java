package Models;

/** Class that will hold and model data about the contacts that will be in the 
 * application.
 *
 * @author Matthew Heino
 */
public class Contacts {
    
    /** Contact's ID number */
    private int Contact_ID;
    
    /** Contact's name */
    private String contactName;
    
    /** Contact's email */
    private String email;

    /**  Default Constructor for Contact Class.
     * 
     */
    public Contacts() {
    }

    /** Explicit value constructor for the Contacts class
     * 
     * @param Contact_ID contact's ID number
     * @param contactName contact's name
     * @param email  contact's email
     * 
     */
    public Contacts(int Contact_ID, String contactName, String email) {
        this.Contact_ID = Contact_ID;
        this.contactName = contactName;
        this.email = email;
    }

    /** Method to get the Contact's ID.  Method to only return the contact's ID.
     *  No other information will be returned
     * 
     * @return contact's ID
     * 
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /** Method to set ContactID. Method to set the contact's ID no other 
     *  information will be changed.
     * 
     * @param Contact_ID  contact's ID
     * 
     */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    /** Method to get the contact's name. Method will return only the contact's 
     *  name as a String
     * 
     * @return contact's name
     * 
     */
    public String getContactName() {
        return contactName;
    }

    /** Method to set the contact name. Method to set the contact's name.  No 
     *  other information will be changed. 
     * 
     * @param contactName contact's name
     * 
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** Method to get the email information. Method to return the email contact 
     *  information of the the contact.  The method will return this 
     *  information as a String.
     * 
     * @return contact's email
     * 
     */
    public String getEmail() {
        return email;
    }

    /** Method to set the email information. Method to change the contact's 
     *  email information.  <b>Note: </b> This method was not used in the 
     *  project but only to test the class.
     * 
     * @param email contact's email
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Method to print Contact data.
     * 
     * @return string with contact name and email
     * 
     */
    @Override
    public String toString() {
        
        return  contactName + " ( " + email + " ) ";
    }
  
} // end class.
