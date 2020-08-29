package Filters;
import java.io.File;

/**
 * This class represents a suffix filter.
 */
public class Suffix extends Filters.Filter
{
    /*----=  Attributes  =-----*/
    /** the suffix of this filter. **/
    String suffix;




    /*----= Constructor =-----*/
    /**
     * Creates a suffix filter Object.
     * @param suffix the suffix of this filter
     */
    Suffix(String suffix)
    {
        this.suffix = suffix;
    }//End of suffix Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        String fileSuffix = file.getName().substring(file.getName().length() - suffix.length());
        return(fileSuffix.equals(suffix) ^ notFlag);
    }//End of didFilePassFilter method.




}//End of Suffix class.
