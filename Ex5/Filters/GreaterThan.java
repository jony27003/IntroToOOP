package Filters;
import java.io.File;

/**
 * This class represents a GreaterThan filter.
 */
public class GreaterThan extends Filter
{
    /*----=  Attributes  =-----*/
    /** the value To Be Greater Than. **/
    double valueToBeGreaterThan;




    /*----= Constructor =-----*/
    /**
     * Creates a GreaterThan filter Object.
     * @param value the value To Be Greater Than.
     */
    GreaterThan(double value)
    {
        this.valueToBeGreaterThan = value;
    }//End of GreaterThan Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, or false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        boolean isGreaterThan = (convertBytesToKiloBytes(file.length()) > this.valueToBeGreaterThan);
        return(isGreaterThan ^ notFlag);
    }//End of didFilePassFilter method.




}//End of class GreaterThan class.