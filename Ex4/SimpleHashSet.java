/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * @author jony27003
 */

public abstract class SimpleHashSet implements SimpleSet
{
    /*----=  Attributes  =-----*/
    /** The capacity of the hashSet **/
    protected int currentCapacity;
    /** The number of elements **/
    private int numberOfElements;
    /** The lower load factor **/
    protected float lowerLoadFactor;
    /** The upper load factor **/
    protected float upperLoadFactor;


    /*----=  Constants  =-----*/
    /** Describes the higher load factor of a newly created hash set **/
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    /** Describes the lower load factor of a newly created hash set **/
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    /** Describes the capacity of a newly created hash set **/
    protected static final int INITIAL_CAPACITY = 16;
    private final int INITIAL_NUMBER_OF_ELEMENTS = 0;




    /*----= Constructors =-----*/
    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    SimpleHashSet()
    {
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.currentCapacity = INITIAL_CAPACITY;
        this.numberOfElements = INITIAL_NUMBER_OF_ELEMENTS;
    }//End of default constructor.


    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor the upper load factor before rehashing
     * @param lowerLoadFactor the lower load factor before rehashing
     */
    SimpleHashSet(float upperLoadFactor, float lowerLoadFactor)
    {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.currentCapacity = INITIAL_CAPACITY;
        this.numberOfElements = INITIAL_NUMBER_OF_ELEMENTS;
    }//End of constructor.


    /*----= Instance Methods =-----*/
    /**
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity()
    {
        return this.currentCapacity;
    }//End of capacity method.


    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index the index before clamping
     * @return an index properly clamped
     */
    protected int clamp(int index)
    {
        return index & ((this.currentCapacity - 1));
    }//End of clamp method.


    /**
     * @return the lower load factor of the table
     */
    protected float getLowerLoadFactor()
    {
        return this.lowerLoadFactor;
    }//End of getLowerLoadFactor method.


    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor()
    {
        return this.upperLoadFactor;
    }//End of getUpperLoadFactor method.


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newVal New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public abstract boolean add(String newVal);//End of add method.


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public abstract boolean contains(String searchVal);//End of contains method.


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public abstract boolean delete(String toDelete);//End of delete method.


    /**
     * @return The number of elements currently in the set
     */
    public int size()
    {
        return this.numberOfElements;
    }//End of size method.


    /*----= Added Methods =-----*/
    /**
     * This method initializes the hash table.
     * @param Table The hash table to be initialized.
     */
    protected void initializeHashTable(LinkedListPackage[] Table)
    {
        for(int i = 0; i < Table.length; i++)
            Table[i] = new LinkedListPackage();
    }//End of initializeHashTable method.


    /**
     * This method doubles this hash tables capacity.
     */
    protected void increaseCapacity()
    {
        this.currentCapacity = this.currentCapacity * 2;
    }//End of increaseCapacity method.


    /**
     * This method decreases this hash tables capacity by a factor of 2.
     */
    protected void decreaseCapacity()
    {
        this.currentCapacity = this.currentCapacity / 2;
        if(this.currentCapacity == 0)
            this.currentCapacity = 1;
    }//End of decreaseCapacity method.


    /**
     * This method sets a new size for the hash table.
     * @param newSize the new size to set.
     */
    public void setSize(int newSize)
    {
        this.numberOfElements = newSize;
    }//End of setSize method.



}//End of SimpleHashSet class.
