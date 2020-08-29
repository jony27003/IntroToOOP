/**
 * This class packages a string object,
 * and information on whether it's deleted or not.
 * @author jony27003
 */
public class StringPackage
{
    /** The string to package **/
    private String string;
    /** The deleted state of the packaged string **/
    private boolean deletedFlag;




    /*----= Constructor =-----*/
    /**
     * Creates a package for the received string.
     * @param stringToPackage the string to package
     */
    StringPackage(String stringToPackage)
    {
        this.string = stringToPackage;
        deletedFlag = false;
    }//End of StringPackage constructor.


    /*----= Instance Methods =-----*/
    /**
     * flags this string as deleted.
     */
    public void delete()
    {
        this.deletedFlag = true;
    }//End of delete method.


    /**
     * This method returns true if this string is flagged as deleted or false otherwise.
     * @return true if this string is flagged as deleted or false otherwise.
     */
    public boolean isDeleted()
    {
        return this.deletedFlag;
    }//End of isDeleted method.


    /**
     * This method returns this string.
     * @return the string this object packages.
     */
    public String getString()
    {
        return this.string;
    }//End of getString method.


    /**
     * @param stringPackage The string package to compare to.
     * @return true if the string representations are equal, false otherwise
     */
    public boolean equals(StringPackage stringPackage)
    {
        return this.string.equals(stringPackage.getString());
    }//End of equals method.


    /**
     * @param stringPackage The string package to compare to.
     * @return true if the string representations are equal, false otherwise
     */
    public boolean equals(String stringPackage)
    {
        return this.string.equals(stringPackage);
    }//End of equals method.


    /**
     * This method returns this string hashCode.
     * @return returns this string hashCode.
     */
    public int hashCode()
    {
        return this.string.hashCode();
    }//End of hashCode method.




}//End of StringPackage method.
