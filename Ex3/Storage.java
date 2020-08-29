import oop.ex3.spaceship.Item;
import java.util.HashMap;
import java.util.Map;


/**
 * This is an abstract class that represents a kind of storage.
 * @author Jonathan Elyovich 204894281.
 */
public abstract class Storage
{
    /*----= Attributes =-----*/
    /** The initial capacity of this storage. **/
    protected final int initialCapacity;
    /** The initial capacity of this storage. **/
    protected int availableCapacity;
    /** A map to pair items with their respective quantity. **/
    protected HashMap<String, Integer> itemsHashMap;


    /*----= Constants =-----*/
    private final int ITEM_DOES_NOT_EXIST_IN_STORAGE = 0;
    private final int EMPTY_STORAGE = 0;




    /*----= Constructor =-----*/
    /**
     * @param capacity the initial capacity of this storage.
     */
    public Storage(int capacity)
    {
        if(capacity < EMPTY_STORAGE)
            capacity = EMPTY_STORAGE;
        this.initialCapacity = capacity;
        this.availableCapacity = capacity;
        this.itemsHashMap = new HashMap<>();
    }//End of Constructor.


    /*----= Instance Methods =-----*/
    /**
     * Adds n items to the storage
     * @param item the item to add
     * @param n the number of items to add
     * @return 0 or 1 if items were added successfully, -1 otherwise.
     */
    protected abstract int addItem(Item item, int n); //End of addItem method.


    /**
     * @param type the type of the Item.
     * @return the number of Items of type type the storage contains.
     */
    public int getItemCount(String type)
    {
        if( (type!=null) && (itemsHashMap.containsKey(type)) )
            return itemsHashMap.get(type);
        return ITEM_DOES_NOT_EXIST_IN_STORAGE;
    }//End of getItemCount method.


    /**
     * This method returns this storage's initial capacity.
     * @return the storage initial capacity.
     */
    public int getCapacity()
    {
        return this.initialCapacity;
    }//End of getCapacity method.


    /**
     * This method returns this storage's available capacity.
     * @return the storage available capacity.
     */
    public int getAvailableCapacity()
    {
        return this.availableCapacity;
    }//End of getAvailableCapacity method.


    /**
     * This method returns a map of all the items contained in this unit, with their
     * respective quantities.
     * @return a map of all the items contained in this unit, with their
     * respective quantities.
     */
    public Map<String, Integer> getInventory()
    {
        return itemsHashMap;
    }//End of getInventory method.




    /*----= Added Methods =-----*/
    /**
     * This method checks if it is possible to add a received number of units to this storage.
     * @param units the number of units that are requested to be added.
     * @return true if there is enough room, or false otherwise.
     */
    protected boolean canAdd(int units)
    {
        return (units <= this.availableCapacity);
    }//End of canAdd method.


    /**
     * This method adds the received number of items to this storage.
     * @param item the item to add.
     * @param n the amount to add.
     */
    protected void addToStorage(Item item, int n)
    {
        this.itemsHashMap.put(item.getType(), n + this.getItemCount(item.getType()));
        this.availableCapacity = this.availableCapacity - (item.getVolume()*n);
    }


    /**
     * This method checks the validity of its received parameters.
     * @param item the item to add.
     * @param n the amount to add.
     * @return true if the received parameters are valid, or false otherwise.
     */
    protected boolean checkParametersValidity(Item item, int n)
    {
        boolean flag = ((n<0) || (item == null) || (item.getVolume()<0));
        if(flag)
            System.out.println("Error: Your request cannot be completed at this time.");
        return (flag);
    }




}//End of Storage class.
