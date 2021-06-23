package Utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/** Class that will provide utilities for converting time and calculations.
 *
 * @author Matthew Heino
 */
public class DateTimeConversUtil {
    
    
    /** Date Time Formatter */
    private DateTimeFormatter dateTimeFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");
    
    /** ZoneID to hold the default system Zone */
    private ZoneId localZoneID = ZoneId.systemDefault();
    
    /** UTC Zone ID */
    private ZoneId utcZone = ZoneId.of("UTC");

    /** Default Constructor.
     * 
     */
    public DateTimeConversUtil() {
        
    } // end constructor.
    
    /** Method to convert a time from UTC to a local time.
     * 
     * @param dateTime date and time to be converted
     * @return  converted string as a string
     * 
     */
    public String convertDateTime(String dateTime){
        
        
        // Convert to local date and time from UTC.
        LocalDateTime ldt = LocalDateTime.parse(dateTime, dateTimeFormat);
        
        //Convert to local time zone.
        ZonedDateTime lzt = ldt.atZone(utcZone).withZoneSameInstant(localZoneID);
        
        //Convert for insertion into list and return.
        
        return lzt.format(dateTimeFormat);
        
    } // end convertDateTime.
    
    /** Method to get the differences from timezones.  
     * 
     * @return time difference between UTC and local time.
     * 
     */
    public int getTimeDifferenc(){
        
        int diff;
        
        TimeZone tz1 = TimeZone.getTimeZone("EST");
        TimeZone tz2 = TimeZone.getDefault();
        //TimeZone tz2 = TimeZone.getTimeZone("Europe/London");
        //TimeZone tz2 = TimeZone.getTimeZone("America/Montreal");
        //TimeZone tz2 = TimeZone.getTimeZone("America/Phoenix");
        
        long timediff = tz1.getRawOffset() - tz2.getRawOffset();
        diff = (int) TimeUnit.MILLISECONDS.toHours(timediff);
        
        
        return diff;
        
    } // getTimeDifference. 
    
} // end class.
