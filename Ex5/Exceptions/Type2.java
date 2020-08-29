package Exceptions;

/**
 * This is a super class of a type 2 Exception.
 */
public class Type2 extends Exception
{
    /*----=  Attributes  =-----*/
    private static final long serialVersionUID = 1l;



    /*----= Constructors =-----*/
    /**
     * initiate a default Type2 Exception object.
     */
    public Type2()
    {
        super("Type II exception occurred");
    }//End of Type2 default Constructor.


    /**
     * initiate a Type2 Exception object with an error string.
     * @param error the error string.
     */
    public Type2(String error)
    {
        super("Error : " + error);
    }//End of Type1 Constructor.




}//End of Type1 class.