import oop.ex3.spaceship.Item;
import java.util.HashMap;



/**
 * Represents a LongTermStorage of USS Discovery
 * @author Jonathan Elyovich 204894281.
 */
public class LongTermStorage extends Storage
{
    /*----= Constants =-----*/
    private final int SUCCESS = 0;
    private final int ERROR = -1;




    /*----= Constructor =-----*/
    /**
     * This constructor initializes a Long-Term Storage object.
     */
    public LongTermStorage()
    {
        super(1000);
    }//End of LongTermStorage Constructor.


    /*----= Instance Methods =-----*/
    /**This method adds n Items of the given type to the long-
     term storage unit. If the action is successful, this method should return 0. If n Items cannot be added
     to the locker at this time, no Items should be added, the method should return -1, and the following
     message should be printed to System.out.println: "Error: Your request cannot be completed at this
     time. Problem: no room for n Items of type type ".
     */
    public int addItem(Item item, int n)
    {
        if(this.checkParametersValidity(item, n))
            return ERROR;
        if(n==0)
            return SUCCESS;
        int totalUnitsToAdd = item.getVolume() * n;
        if(this.canAdd(totalUnitsToAdd))
        {
            addToStorage(item, n);
            return(SUCCESS);
        }
        System.out.println("Error: Your request cannot be completed at this time. " +
                "Problem: no room for " + n + " Items of type " + item.getType());
        return ERROR;
    }//End of addItem method.


    /**This method resets the long-term storage's inventory (i.e. after
     it is invoked the inventory does not contain any Item).
     */
    public void resetInventory()
    {
        this.availableCapacity = this.initialCapacity;
        this.itemsHashMap = new HashMap<>();
    }//End of resetInventory method.




}//End of LongTermStorage Class.