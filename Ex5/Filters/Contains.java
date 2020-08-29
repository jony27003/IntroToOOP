package Filters;
import java.io.File;

/**
 * This class represents the contains filter.
 */
public class Contains extends Filter
{
    /*----=  Attributes  =-----*/
    /** the string to find **/
    String stringToFind;




    /*----= Constructor =-----*/
    /**
     * Creates a contains Object.
     * @param string The string to find.
     */
    Contains(String string)
    {
        this.stringToFind = string;
    }//End of Contains Constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method checks if the file received passed this filter.
     * @param file the file to check
     * @return true if the file received passes, or false otherwise.
     */
    public boolean didFilePassFilter(File file)
    {
        if(notFlag)
            return !(file.getName().contains(stringToFind));
        return file.getName().contains(stringToFind);
    }//End of didFilePassFilter method.




}//End of Contains class.
