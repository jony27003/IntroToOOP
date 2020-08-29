import oop.ex3.spaceship.*;
import org.junit.*;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;



/**
 * This class is a tester class for LongTermStorage class.
 * @author Jonathan Elyovich 204894281.
 */
public class LongTermTest
{
    /*----= Attributes =-----*/
    /** The itemFactory for creating the test items. **/
    private static ItemFactory itemFactory;
    /** An Array of test legal test items. **/
    private static Item[] legalItemsArray;
    /** Long term storage object for testing. **/
    private LongTermStorage lts1;
    /** Long term storage object for testing. **/
    private LongTermStorage lts2;
    /** Long term storage object for testing. **/
    private LongTermStorage lts3;


    /*----= Constants =-----*/
    private final int ERROR = -1;
    private final int SUCCESS = 0;
    private final int LONG_TERM_STORAGE_CAPACITY = 1000;



    /*----= Instance Methods =-----*/
    /**
     * This test method creates the test items.
     * it is initiated once at the beginning of this class tests.
     */
    @BeforeClass
    public static void createTestObjects()
    {
        itemFactory = new ItemFactory();
        legalItemsArray = itemFactory.createAllLegalItems();
    }//End of createTestObjects method.


    /**
     * This test method creates the Long term storage objects that will be tested.
     * it is initiated before each and every test method.
     */
    @Before
    public void initializeTestParameters()
    {
        lts1 = new LongTermStorage();
        lts2 = new LongTermStorage();
        lts3 = new LongTermStorage();
    }//End of initializeTestParameters method.


    /**
     * Test method for the class constructor.
     */
    @Test
    public void testConstructor()
    {
        assertEquals("Error in long term storage constructor or in getCapacity method " +
                        "(Error in initial capacity value)",
                LONG_TERM_STORAGE_CAPACITY, lts1.getCapacity());
        assertEquals("Error in long term storage constructor or in " +
                        "getAvailableCapacity method (Error in available capacity value)",
                LONG_TERM_STORAGE_CAPACITY, lts1.getAvailableCapacity());
        assertEquals("Error in long term storage constructor or in getInventory method " +
                "(HashMap is not empty)", true, lts1.getInventory().isEmpty());
    }//End of testConstructor method.


    /**
     * Test method for addItem method.
     */
    @Test
    public void testAddItem()
    {
        // Check for correctly adding an item
        int methodOutput = lts1.addItem(legalItemsArray[0], 10);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);

        // Fill to the limit.
        methodOutput = lts2.addItem(legalItemsArray[3], 100);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);

        // Check for not adding items when there is no space
        methodOutput = lts3.addItem(legalItemsArray[0], 499);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);
        methodOutput = lts2.addItem(legalItemsArray[1], 1);
        assertEquals("Return value isn't correct", ERROR, methodOutput);

        initializeTestParameters();

        // Check for zero items.
        assertEquals("Problem with adding zero items",
                SUCCESS, lts1.addItem(legalItemsArray[0],0));

        // Check for negative n
        assertEquals("Can't add a negative number",
                ERROR, lts1.addItem(legalItemsArray[0],-1));

        // Adding too much
        assertEquals("Can't add more than 1000 units",
                ERROR, lts1.addItem(legalItemsArray[0], 2000));
        assertEquals("Can't add more than 1000 units",
                true, lts1.getInventory().isEmpty());

        initializeTestParameters();

        //Repeated addition
        for(int i=0; i<=4; i++)
        {
            for(int j=0; j<10; j++)
            {
                methodOutput = lts1.addItem(legalItemsArray[i], 1);
                assertEquals("Return value isn't correct", SUCCESS, methodOutput);
            }
        }

    }//End of testAddItem method.


    /**
     * Test method for resetInventory method.
     */
    @Test
    public void testResetInventory()
    {
        for(int i=0; i<=4; i++)
        {
            lts1.addItem(legalItemsArray[i], 10);
        }
        lts1.resetInventory();
        assertEquals("HashMap is not empty", true, lts1.getInventory().isEmpty());
    }//End of testResetInventory method.


    /**
     * Test the getItemCount method.
     */
    @Test
    public void testGetItemCount()
    {
        //Repeated addition
        int measuredCount;
        for(int i=0; i<=4; i++)
        {
            for(int j=1; j<=10; j++)
            {
                lts1.addItem(legalItemsArray[i], 1);
                measuredCount = lts1.getItemCount(legalItemsArray[i].getType());
                assertEquals("Return value isn't correct", j, measuredCount);
            }
        }

        // Check an item that does not exist.
        assertEquals("This item shouldn NOT exist",
                0, lts1.getItemCount("virtual item"));

        // Adding nothing
        lts2.addItem(legalItemsArray[0], 0);
        assertEquals("The item amount isn't correct",
                10, lts1.getItemCount(legalItemsArray[0].getType()));

        // Adding too much
        lts1.addItem(legalItemsArray[0], 2000);
        assertEquals("Problem adding item",
                10, lts1.getItemCount(legalItemsArray[0].getType()));

        // checking null item name
        assertEquals("Problem with null", 0, lts1.getItemCount(null));
    }//End of testGetItemCount method.



    /**
     * Test getInventory()
     */
    @Test public void testGetInventory()
    {
        Map <String, Integer> testMap = new HashMap<String, Integer>();

        //Repeated inventory comparison.
        for(int i=0; i<=4; i++)
        {
            for(int j=1; j<=10; j++)
            {
                lts1.addItem(legalItemsArray[i], 1);
                testMap.put(legalItemsArray[i].getType(), j);
                assertEquals("Hash maps are not identical"
                        , testMap, lts1.getInventory());
            }
        }
    }//End of testGetInventory method.


    /**
     * Test the getCapacity method.
     */
    @Test
    public void testGetCapacity()
    {
        assertEquals("The capacity isn't correct",
                LONG_TERM_STORAGE_CAPACITY, lts1.getCapacity());
    }//End of testGetCapacity method.


    /**
     * Test getAvailableCapacity
     */
    @Test
    public void testGetAvailableCapacity()
    {
        //Repeated available capacity management;
        int predictedAvailableCapacity = LONG_TERM_STORAGE_CAPACITY;
        int measuredAvailableCapacity;

        for(int i=0; i<=4; i++)
        {
            for(int j=1; j<=10; j++)
            {
                lts1.addItem(legalItemsArray[i], 1);
                predictedAvailableCapacity =
                        predictedAvailableCapacity - legalItemsArray[i].getVolume();
                measuredAvailableCapacity = lts1.getAvailableCapacity();
                assertEquals("Capacities do not match",
                        predictedAvailableCapacity, measuredAvailableCapacity);
            }
        }//available capacity = 760


        // Adding too much
        lts1.addItem(legalItemsArray[0], 379);//available capacity = 2
        assertEquals("Problem in addItem method", 2, lts1.getAvailableCapacity());
        lts1.addItem(legalItemsArray[1], 1);
        assertEquals("Problem in addItem method", 2, lts1.getAvailableCapacity());
    }//End of testGetAvailableCapacity method.




}//End of LongTermTest Class.
