package Filters;
import java.io.File;

/**
 * This class represents a Hidden filter.
 */
public class Hidden extends Filter
{
    /*----=  Attributes  =-----*/
    /** the hidden state of the filter. **/
    boolean isHidden;




    /*----= Constructor =-----*/
    /**
     * Creates a Hidden filter Object.
     * @param hiddenState the hidden State of the filter.
     */
    Hidden(boolean hiddenState)
    {
        this.isHidden = hiddenState;
    }//End of Hidden Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Checks if the file passes
     * @param file the file to check
     * @return true if passes, false otherwise
     */
    public boolean didFilePassFilter(File file)
    {
        return ((file.isHidden() && this.isHidden) ^ notFlag);
    }//End on didFilePassFilter method.




}//End of Hidden class.