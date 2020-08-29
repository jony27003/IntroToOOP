/**
 * This Class represents a hash set based on chaining which extends SimpleHashSet.
 * @author jony27003
 */
public class OpenHashSet extends SimpleHashSet
{
    /*----=  Attributes  =-----*/
    /** The hash table that corresponds with this hash set **/
    private LinkedListPackage[] hashTable;




    /*----= Constructors =-----*/
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity(16),
     * upper load factor(0.75) and lower load factor(0.25)
     */
    public OpenHashSet()
    {
        this.hashTable = new LinkedListPackage[INITIAL_CAPACITY];
        initializeHashTable(hashTable);
    }//End of default constructor.


    /**
     * Constructs a new, empty table with the specified load factors and the default initial capacity(16).
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        this.hashTable = new LinkedListPackage[INITIAL_CAPACITY];
        initializeHashTable(hashTable);
    }//End of constructor.


    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity(16)
     * upper load factor (0.75) and lower load factor(0.25)
     * @param data Values to add to the set
     */
    public OpenHashSet(java.lang.String[] data)
    {
        if(data.length > INITIAL_CAPACITY)
            this.currentCapacity = (int) Math.pow(2,((int)Math.ceil(Math.log10(data.length) / Math.log10(2))));
        this.hashTable = new LinkedListPackage[this.currentCapacity];
        initializeHashTable(hashTable);
        for(int i=0; i<data.length; i++)
            add(data[i]);
    }//End of constructor.


    /*----= Instance Methods =-----*/
    /**
     * Adds a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set.
     */
    public boolean add(java.lang.String newValue)
    {
        if(contains(newValue))
            return false;

        this.setSize(this.size() + 1);

        float loadFactor = (float)this.size()/this.currentCapacity;
        if(loadFactor > this.upperLoadFactor)
        {
            increaseCapacity();
            cloneToNewHashTable((this.currentCapacity/2));
        }
        hashTable[clamp(newValue.hashCode())].add(newValue);
        return true;
    }//End of add method.


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal)
    {
        return (this.hashTable[clamp(searchVal.hashCode())].contains(searchVal));
    }//End of contains method.


    /**
     * Remove the input element from the set
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public boolean delete(java.lang.String toDelete)
    {
        if(!contains(toDelete))
            return false;


        hashTable[clamp(toDelete.hashCode())].removeFirstOccurrence(toDelete);
        this.setSize(this.size() - 1);

        float loadFactor = (float)this.size()/this.currentCapacity;
        if(loadFactor < this.lowerLoadFactor)
        {
            int previousCapacity = this.currentCapacity;
            decreaseCapacity();
            cloneToNewHashTable((previousCapacity));
        }
        return true;
    }//End of delete method.


    /*----= Added Methods =-----*/
    /**
     * This method changes this hash table actual capacity to the received integer.
     * @param previousHashTableSize received integer.
     */
    protected void cloneToNewHashTable(int previousHashTableSize)
    {
        LinkedListPackage[] newHashTable = new LinkedListPackage[this.currentCapacity];
        initializeHashTable(newHashTable);
        for(int i=0; i < previousHashTableSize; i++)
        {
            while(!this.hashTable[i].isEmpty())
            {
                int index = clamp(this.hashTable[i].getFirst().hashCode());
                newHashTable[index].add(this.hashTable[i].getList().removeFirst());

            }
        }
        this.hashTable = newHashTable;
    }//End of cloneToNewHashTable method.




}//End of OpenHashSet class.
