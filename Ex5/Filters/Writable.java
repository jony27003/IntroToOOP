package Filters;
import java.io.File;

/**
 * This class represents a Writable filter.
 */
public class Writable extends Filters.Filter
{
    /*----=  Attributes  =-----*/
    /** the Writable state of this filter. **/
    boolean isWritable;




    /*----= Constructor =-----*/
    /**
     * Creates a Writable filter Object.
     * @param writableState the Writable state of this filter.
     */
    Writable(boolean writableState)
    {
        this.isWritable = writableState;
    }//End of Writable Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        return((file.canWrite() && this.isWritable) ^ notFlag);
    }//End of didFilePassFilter method.




}//End of Writable Class.
