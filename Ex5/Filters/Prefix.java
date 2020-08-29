package Filters;
import java.io.File;

/**
 * This class represents a prefix filter.
 */
public class Prefix extends Filters.Filter
{
    /*----=  Attributes  =-----*/
    /** the prefix of the filter. **/
    String prefix;



    /*----= Constructor =-----*/
    /**
     * Creates a prefix filter Object.
     * @param prefix the prefix of the filter.
     */
    Prefix(String prefix)
    {
        this.prefix = prefix;
    }//End of prefix Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        String filePrefix = file.getName().substring(0 , prefix.length());
        return(filePrefix.equals(prefix) ^ notFlag);
    }//End of didFilePassFilter method.




}//End of Prefix Class.
