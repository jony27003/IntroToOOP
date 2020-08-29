package Filters;
import java.io.File;

/**
 * This class represents the All filter.
 */
public class All extends Filter
{




    /**
     * This method returns true if the file received passed the filter or false otherwise.
     * @param fileToCheck the file to check.
     * @return true if the file received passed the filter or false otherwise.
     */
    public boolean didFilePassFilter(File fileToCheck)
    {
        return(!notFlag);
    }//End of didFilePassFilter method.




}//End of All Class.
