/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

//import java.sql.Date;
//import java.sql.Timestamp;

/** Class that will hold data about the divisions in the application. 
 *
 * @author mehei
 */
public class FirstLevelDivisions {
    
    /** Division ID number */
    private int divisionID;
    
    /** Division  */
    private String division;
   
    /** Country ID number */
    private int countryID;

    /** Default constructor
     * 
     */
    public FirstLevelDivisions() {
    }

    /** Explicit value constructor
     * 
     * @param divisionID  division ID
     * @param division division name
     * @param countryID country ID
     * 
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /** Method to get the division ID
     * 
     * @return division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /** Method to set the division ID.
     * 
     * @param divisionID division ID
     * 
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /** Method to get the division
     * 
     * @return  division name
     */
    public String getDivision() {
        return division;
    }

    /** Method to set the division
     * 
     * @param division division name
     * 
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /** Method to get he country ID
     *  
     * @return country ID 
     */
    public int getCountryID() {
        return countryID;
    }

    /** Method to set the country ID.
     * 
     * @param countryID country ID 
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    
} // End First Class divisions.

