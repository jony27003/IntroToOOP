package Exceptions;

/**
 * This is a type 1 Exception class.
 */
public class Type1 extends Exception
{
    /*----=  Attributes  =-----*/
    private static final long serialVersionUID = 1l;




    /*----= Constructors =-----*/
    /**
     * initiate a default Type1 Exception object.
     */
    public Type1()
    {
        super("Type I exception occurred");
    }//End of Type1 default Constructor.


    /**
     * initiate a Type1 Exception object with an error string.
     * @param error the error string.
     */
    public Type1(String error)
    {
        super("Error: "+error);
    }//End of Type1 Constructor.




}//End of Type1 class.