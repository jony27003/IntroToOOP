package Filters;
import java.io.File;

/**
 * This class represents a SmallerThan filter.
 */
public class SmallerThan extends Filters.Filter
{
    /*----=  Attributes  =-----*/
    /** the value To Be Smaller Than. **/
    double valueToBeSmallerThan;




    /*----= Constructor =-----*/
    /**
     * Creates a SmallerThan filter Object.
     * @param value the value To Be Smaller Than.
     */
    SmallerThan(double value)
    {
        this.valueToBeSmallerThan = value;
    }//End of SmallerThan Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        boolean isSmallerThan = convertBytesToKiloBytes(file.length()) < this.valueToBeSmallerThan;
        return(isSmallerThan ^ notFlag);
    }//End of didFilePassFilter method.




}//End of SmallerThan class.
