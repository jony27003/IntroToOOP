package Filters;
import java.io.File;

/**
 * This class is an abstract filter super class.
 */
public abstract class Filter
{
    /*----=  Attributes  =-----*/
    /** The "NOT" filter state. **/
    protected boolean notFlag;




    /*----= Instance Methods =-----*/
    /**
     * Abstract method that checks if the file passes
     * @param file the file to check
     * @return true if passes, or false otherwise.
     */
    public abstract boolean didFilePassFilter(File file);


    /**
     * This method convert Bytes To Kilo Bytes.
     * @param sizeInBytes the file to check
     * @return true if passes, or false otherwise.
     */
    public double convertBytesToKiloBytes(long sizeInBytes)
    {
        return (double)sizeInBytes/(Math.pow(2,10));
    }//End of convertBytesToKiloBytes method.


    /**
     * This is a setter method for the notFlag value.
     * @param notState the state to set.
     */
    public void setNotFlag(boolean notState)
    {
        this.notFlag = notState;
    }//End of setNotFlag method.




}//End of Filter class.
