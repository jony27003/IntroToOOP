import java.util.LinkedList;


/**
 * This class packages a LinkedList object.
 * @author jony27003
 */
public class LinkedListPackage
{
    /** The LinkedList object to package **/
    private LinkedList<String> linkedList;




    /*----= Constructor =-----*/
    /**
     * Creates a package for the LinkedList.
     */
    LinkedListPackage()
    {
        linkedList = new LinkedList<>();
    }//End of LinkedListPackage constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method Adds a string to the list.
     * @param stringToAdd The string to add to the list
     * @return return true if element was added successfully, false otherwise.
     */
    public boolean add(String stringToAdd)
    {
        return linkedList.add(stringToAdd);
    }//End of add method.


    /**
     * This method returns true if this LinkedList contains
     * the received string or false otherwise.
     * @param searchVal the string to try and find.
     * @return true if this LinkedList contains the received string or false otherwise.
     */
    public boolean contains(String searchVal)
    {
        return linkedList.contains(searchVal);
    }//End of contains method.


    /**
     * This method returns true if this LinkedList is empty or false otherwise.
     * @return true if this LinkedList is empty or false otherwise.
     */
    public boolean isEmpty()
    {
        return linkedList.isEmpty();
    }//End of isEmpty method.


    /**
     * This method returns the head of this LinkedList.
     * @return the head of this LinkedList.
     */
    public String getFirst()
    {
        return linkedList.getFirst();
    }//End of getFirst method.


    /**
     * This method returns the head of this LinkedList.
     * @return the head of this LinkedList.
     */
    public boolean removeFirstOccurrence(String toDelete)
    {
        return this.linkedList.removeFirstOccurrence(toDelete);
    }//End of removeFirstOccurrence method.


    /**
     * This method returns the linked list that is being packaged.
     * @return the head of this LinkedList.
     */
    public LinkedList<String> getList()
    {
        return this.linkedList;
    }//End of removeFirstOccurrence method.


}//End of LinkedListPackage class.
