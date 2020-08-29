package Filters;
import java.io.File;

/**
 * This class represents an Executable filter.
 */
public class Executable extends Filter
{
    /*----=  Attributes  =-----*/
    /** boolean parameter to indicate if this file is Executable **/
    boolean isExecutable;




    /*----= Constructor =-----*/
    /**
     * Creates a Executable Object.
     * @param isExecutable a boolean parameter to indicate if this file is Executable.
     */
    Executable(boolean isExecutable)
    {
        this.isExecutable = isExecutable;
    }//End of Executable Constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method checks if the file received passed this filter.
     * @param file the file to check
     * @return true if the file received passes, or false otherwise.
     */
    public boolean didFilePassFilter(File file)
    {
        return (!(file.canExecute() ^ isExecutable) ^ notFlag);
    }//End of didFilePassFilter method.




}//End of Executable Class.
