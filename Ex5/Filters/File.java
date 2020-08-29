package Filters;

/**
 * This class represents a File name filter.
 */
public class File extends Filter
{
    /*----=  Attributes  =-----*/
    /** the name to filter by. **/
    String fileNameToFilterBy;




    /*----= Constructor =-----*/
    /**
     * Creates a File filter Object.
     * @param fileName the name to filter by.
     */
    File(String fileName)
    {
        this.fileNameToFilterBy = fileName;
    }//End of File Constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method checks if the file received passed this filter.
     * @param file the file to check
     * @return true if the file received passes, or false otherwise.
     */
    public boolean didFilePassFilter(java.io.File file)
    {
        return (notFlag ^ fileNameToFilterBy.equals(file.getName()));
    }//End of didFilePassFilter method.




}//End of File Class.
