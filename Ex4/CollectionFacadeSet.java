/**
 * This class wraps an underlying Collection and serves to both simplify its API
 * and give it a common type with the implemented SimpleHashSets.
 * @author jony27003
 */
public class CollectionFacadeSet implements SimpleSet
{
    /*----= Attributes =-----*/
    /** The data structure **/
    private java.util.Collection<java.lang.String> dataStructure;




    /*----= Constructor =-----*/
    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection The Collection to wrap.
     */
    CollectionFacadeSet(java.util.Collection<java.lang.String> collection)
    {
        this.dataStructure = collection;
    }//End of constructor.


    /*----= Instance Methods =-----*/
    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(java.lang.String newValue)
    {
        if(!this.dataStructure.contains(newValue))
            return dataStructure.add(newValue);
        return false;
    }//End of add method.


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal)
    {
        return dataStructure.contains(searchVal);
    }//End of contains method.


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete)
    {
        return dataStructure.remove(toDelete);
    }//End of delete method.


    /**
     * @return The number of elements currently in the set
     */
    public int size()
    {
        return dataStructure.size();
    }//End of size method.




}//End of CollectionFacadeSet class.
