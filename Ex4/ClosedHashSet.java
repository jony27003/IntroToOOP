/**
 * This Class represents a hash set based on closed hashing with quadratic probing.
 * Extends SimpleHashSet.
 * @author jony27003
 */
public class ClosedHashSet extends SimpleHashSet implements SimpleSet
{
    /*----=  Attributes  =-----*/
    /** The hash table that corresponds with this hash set **/
    private StringPackage[] hashTable;




    /*----= Constructors =-----*/
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity(16),
     * upper load factor(0.75) and lower load factor(0.25)
     */
    public ClosedHashSet()
    {
        this.hashTable = new StringPackage[INITIAL_CAPACITY];
    }//End of default constructor.


    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor)
    {
        super(upperLoadFactor, lowerLoadFactor);
        this.hashTable = new StringPackage[INITIAL_CAPACITY];
    }//End of constructor.


    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should by ignored. The new table has the default values of
     * initial capacity (16), upper load factor (0.75), and lower load factor (0.25).
     * @param data Values to add to the set
     */
    public ClosedHashSet(java.lang.String[] data)
    {
        if (data.length > INITIAL_CAPACITY)
            this.currentCapacity = (int) Math.pow(2,((int)Math.ceil(Math.log10(data.length) / Math.log10(2))));
        this.hashTable = new StringPackage[this.currentCapacity];
        for (int i = 0; i < data.length; i++)
            add(data[i]);
    }//End of constructor.


    /*----= Instance Methods =-----*/
    /**
     * Add a a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set.
     */
    public boolean add(java.lang.String newValue)
    {
        if(newValue == null)
            return false;

        int indexToAdd = quadraticProbing(newValue, this.hashTable);

        if(wasElementFound(indexToAdd,newValue))
            return false;

        StringPackage stringToAdd = new StringPackage(newValue);
        this.setSize(this.size() + 1);

        float loadFactor = (float)this.size()/this.currentCapacity;
        if(loadFactor > this.upperLoadFactor)
        {
            increaseCapacity();
            cloneToNewHashTable();
            indexToAdd = quadraticProbing(newValue, this.hashTable);
        }
        this.hashTable[indexToAdd] = stringToAdd;
        return true;
    }//End of add method.


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal)
    {
        if(searchVal == null)
            return false;
        return wasElementFound(quadraticProbing(searchVal, this.hashTable), searchVal);
    }//End of contains method.


    /**
     * Remove the input element from the set
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete)
    {
        if(toDelete == null)
            return false;

        int indexToDelete = quadraticProbing(toDelete, this.hashTable);

        if(!wasElementFound(indexToDelete,toDelete))
            return false;

        this.hashTable[indexToDelete].delete();
        this.setSize(this.size() - 1);

        float loadFactor = (float)this.size()/this.currentCapacity;
        if(loadFactor < this.lowerLoadFactor)
        {
            decreaseCapacity();
            cloneToNewHashTable();
        }

        return true;
    }//End of delete method.


    /*----= Added Methods =-----*/
    /**
     * This method changes this hash table actual capacity by a factor of 2
     * depending on it's current capacity.
     */
    protected void cloneToNewHashTable()
    {
        StringPackage[] newHashTable = new StringPackage[this.currentCapacity];
        int index;

        for(int i = 0; i < hashTable.length; i++)
        {
            if ((hashTable[i] != null) && (!hashTable[i].isDeleted()))
            {
                index = quadraticProbing(hashTable[i].getString(), newHashTable);
                newHashTable[index] = hashTable[i];
            }
        }
        this.hashTable = newHashTable;
    }//End of cloneToNewHashTable method.


    /**
     * This method receives a string and returns either its current location
     * or the location it would be inserted to if it would be added.
     * @param stringToFind The string that is received.
     * @return An index that represents either the received string current location,
     * or the location it would be inserted to if it would be added.
     */
    private int quadraticProbing(String stringToFind, StringPackage[] table)
    {
        int index;
        StringPackage value;
        StringPackage stringToFindPackage = new StringPackage (stringToFind);

        for(int i=0; i < table.length; i++)
        {
            index = clamp(stringToFindPackage.hashCode() + (i + i*i) / 2);
            value = table[index];
            if(value == null || (value.equals(stringToFindPackage) && value.isDeleted()) )
            {
                return index;
            }
            if(stringToFindPackage.equals(value))
            {
                return index;
            }

        }
        //Was not found, will return 0, but wasElementFound method will return false.
        //This is a two step identification.
        return 0;
    }//End of quadraticProbing method.


    /**
     * This method receives a string and an index and returns true if this
     * string is in the received index or false otherwise
     * @param stringToFind The string that is received.
     * @return true if this string is in the received index or false otherwise.
     */
    private boolean wasElementFound(int index, String stringToFind)
    {
        return ((stringToFind != null) && (this.hashTable[index] != null) &&
                (this.hashTable[index].equals(stringToFind) && (!this.hashTable[index].isDeleted())));
    }//End of wasElementFound method.




}//End of ClosedHashSet class.





