package Filters;

import java.io.File;

/**
 * This class represents a filter which Filters files between different sizes.
 */
public class Between extends Filter
{
    /*----=  Attributes  =-----*/
    /** The lower Bound value **/
    double lowerBound;
    /** The upper Bound value **/
    double upperBound;




    /*----= Constructor =-----*/
    /**
     * Creates a Between filter object.
     * @param lower The lower Bound to set
     * @param upper The upper Bound to set
     */
    Between(double lower, double upper)
    {
        this.lowerBound = lower;
        this.upperBound = upper;
    }//End of Constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method checks if the file received passed this filter.
     * @param file the file to check
     * @return true if the file received passes, or false otherwise.
     */
    public boolean didFilePassFilter(File file)
    {
        double size = convertBytesToKiloBytes(file.length());
        boolean isBetween = (size >= lowerBound && size <= upperBound);
        return(isBetween ^ notFlag);
    }//End of didFilePassFilter method.



}//End of Between class.
