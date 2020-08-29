import oop.ex3.spaceship.Item;



/**
 * Represents a locker of USS Discovery
 * @author Jonathan Elyovich 204894281.
 */
public class Locker extends Storage
{
    /*----= Attributes =-----*/
    /** The long term storage object **/
    static LongTermStorage longTermStorage = new LongTermStorage();


    /*----= Constants =-----*/
    private final int LONG_TERM_STORAGE_WARNING = 1;
    private final int SUCCESS = 0;
    private final int ERROR = -1;
    private final int BASSBALL_FOOTBALL_EROOR = -2;



    /*----= Constructor =-----*/
    /**
     * @param capacity the initial capacity of this locker.
     */
    public Locker(int capacity)
    {
        super(capacity);
    }


    /**
     * This method adds n Items of the given type to the locker.
     * If n items couldn't be added, no items will be added.
     * @param item the item to add to the locker
     * @param n the number of items to add
     * @return 0: if the addition is successful and doesn't cause Items to be moved to long-term
     * storage
     * -1: If n items couldn't be added. An error will be printed.
     * Or, if this action requires Items to be moved to long-term storage, but it doesn't
     * have room to accommodate all n* items, then no items will be added. An error will be printed.
     * -2: If a football or a baseball bat are both going to be in the locker, then no Item will be added
     * 1: If this action causes n* items to be moved to long-term storage and it can accomodate
     * all n* items. A warning message will be printed.
     */
    public int addItem(Item item, int n)
    {
        String type = item.getType();//we were asked to verify this case FIRST,
        // therefore we Assume that Item cannot be null.
        //First case.
        if( (this.itemsHashMap.containsKey("football") && type.equals("baseball bat")) ||
                (this.itemsHashMap.containsKey("baseball bat") && type.equals("football")) )
        {
            System.out.println("Error: Your request cannot be " +
                    "completed at this time. Problem: the locker cannot contain items of type " +
                    type + ", as it contains a contradicting item");
            return BASSBALL_FOOTBALL_EROOR;
        }

        //Second case.
        if(this.checkParametersValidity(item, n))
            return ERROR;

        //Third case.
        if(n==0)
            return SUCCESS;

        //Fourth case.
        //Checking for possible room in this locker.
        if(!this.canAdd((item.getVolume() * n)))
        {//no room in this locker for all of the items.
            System.out.println("Error: Your request cannot be completed at this time. " +
                    "Problem: no room for " + n + " items of type " + type);
            return ERROR;
        }

        //Fifth case.
        //Checking is it possible to move all of the items to this
        //Locker without moving some of them to the long term storage.
        int totalUnitsAfterAddition = (getItemCount(type) + n) * item.getVolume();
        if((totalUnitsAfterAddition) <= 0.5 * this.initialCapacity)
        {//it is possible.
            this.addToStorage(item, n);
            return SUCCESS;
        }

        //if (item.getVolume()==0 is true) then we never reach this case,
        //and therefor do NOT divide by zero.
        int itemsToLongTermStorage =
                (getItemCount(type) + n) - ((int)(0.2 * this.initialCapacity)/(item.getVolume()));
        //this is the minimum number of items that should move to the long term storage.
        if(!longTermStorage.canAdd(itemsToLongTermStorage))
        {
            System.out.println("Error: Your request cannot be completed at this time. Problem: no"+
                    " room for " + itemsToLongTermStorage + " Items of type " + type);
            return ERROR;
        }
        if((n - itemsToLongTermStorage) != 0)
            this.addToStorage(item,(n - itemsToLongTermStorage));
        Locker.longTermStorage.addToStorage(item, itemsToLongTermStorage);
        System.out.println("Warning: Action successful, " +
                "but has caused items to be moved to storage");
        return LONG_TERM_STORAGE_WARNING;
    }//End of addItem method.


    /**
     * Removes n Items of the type type from the locker.
     * @param item the Item to remove
     * @param n the number of items to remove
     * @return 0: if the action is successful
     * -1: there are less than n Items of this type in the locker, no items will be removed.
     * Or, if n is negative, and no items will be removed.
     */
    public int removeItem(Item item, int n)
    {
        if((item == null) || (item.getVolume() < 0))
        {
            System.out.println("Error: Your request cannot be completed at this time.");
            return ERROR;
        }

        String type = item.getType();
        if(n < 0)
        {
            System.out.println("Error: Your request cannot be completed at this time. " +
                    "Problem: cannot remove a negative number of items of type " + type);
            return ERROR;
        }

        int numberOfExistingItems = getItemCount(type);
        if(numberOfExistingItems < n)
        {
            System.out.println("Error: Your request cannot be completed at this time. " +
                    "Problem: the locker does not contain " + n + " items of type " + type);
            return ERROR;
        }

        if(!(itemsHashMap.containsKey(type)))
        {//then n must equal 0.
            return SUCCESS;
        }

        if(numberOfExistingItems == n)
        {
            this.availableCapacity = this.availableCapacity + item.getVolume()*n;
            this.itemsHashMap.remove(type);
            return SUCCESS;
        }

        this.availableCapacity = this.availableCapacity + item.getVolume()*n;
        this.itemsHashMap.put(type, (getItemCount(type) - n));
        return SUCCESS;
    }//End of removeItem method.




}//End of Locker Class.
