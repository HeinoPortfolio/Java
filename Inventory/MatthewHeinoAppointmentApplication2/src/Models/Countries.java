
package Models;

import java.sql.Date;
import java.sql.Timestamp;

/** Class that will model the data about the countries that will be in the 
 * application.
 *
 * @author Matthew Heino
 */
public class Countries {
    
    /** Country's ID number */
    private int countryID;
    
    /** Country's name */
    private String country;

    /** Default Constructor
     * 
     */
    public Countries() {
    }

    /** Explicit value constructor. 
     * 
     * @param countryID  Country ID 
     * @param country Country name   
     * 
     */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /** Method to get the country ID.  Method to get the ID number that is 
     *  associated with the country.
     * 
     * @return  country ID (int) 
     * 
     */
    public int getCountryID() {
        return countryID;
    }

    /** Method to set the country ID. Method to set the country's ID.
     * 
     * @param countryID country ID (int)
     * 
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /** Method to get the Country name.  Method to get the country object's 
     *  name. Will return a country name as a String. 
     * 
     * @return country name (String) 
     * 
     */
    public String getCountry() {
        return country;
    }

    /** Method to set the country name.  Method to set the country name. 
     * 
     * @param country  country name (String)
     * 
     */
    public void setCountry(String country) {
        this.country = country;
    }
  
} // end class Counties.
