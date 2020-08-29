import oop.ex3.spaceship.*;
import org.junit.*;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;



/**
 * This class is a tester class for Locker class.
 * @author Jonathan Elyovich 204894281.
 */
public class LockerTest
{
    /*----= Attributes =-----*/
    /** The itemFactory for creating the test items. **/
    private static ItemFactory itemFactory;
    /** An Array of test legal test items. **/
    private static Item[] legalItemsArray;
    /** Locker object for testing. **/
    private Locker locker1;
    /** Long term storage object for testing. **/
    private LongTermStorage lts1;


    /*----= Constants =-----*/
    private final int ERROR = -1;
    private final int SUCCESS = 0;
    private final int LOCKER_CAPACITY = 100;
    private final int BASEBALL_FOOTBALL_ERROR = -2;




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
     * This test method creates the Locker object that will be tested.
     * it is initiated before each and every test method.
     */
    @Before
    public void initializeTestParameters()
    {
        locker1 = new Locker(100);
        lts1 = locker1.longTermStorage;
        lts1.resetInventory();

    }//End of initializeTestParameters method.


    /**
     * Test method for the class constructor.
     */
    @Test
    public void testConstructor()
    {
        assertEquals("Error in Locker constructor or in getCapacity method " +
                        "(Error in initial capacity value)",
                LOCKER_CAPACITY, locker1.getCapacity());
        assertEquals("Error in Locker constructor or in " +
                        "getAvailableCapacity method (Error in available capacity value)",
                LOCKER_CAPACITY, locker1.getAvailableCapacity());
        assertEquals("Error in Locker constructor or in getInventory method " +
                "(HashMap is not empty)", true, locker1.getInventory().isEmpty());
    }//End of testConstructor method.


    /**
     * This method tests the first case of addItem, where there is a baseball bat
     * or a football in the locker, and we are trying to add the other.
     */
    @Test
    public void testAddItemFirstCase()
    {
        // Adding a football while baseball bat inside.
        int methodOutput = locker1.addItem(legalItemsArray[0], 1);
        assertEquals("baseball bat wasn't added", SUCCESS, methodOutput);
        for(int i=-2; i<=2; i++)
        {
            assertEquals("Football shouldn't be added", BASEBALL_FOOTBALL_ERROR, locker1.addItem(legalItemsArray[4], i));
        }

        //Remove a baseball bat
        methodOutput = locker1.removeItem(legalItemsArray[0], 1);
        assertEquals("baseball bat wasn't removed", SUCCESS, methodOutput);

        // Adding a baseball bat while football inside.
        methodOutput = locker1.addItem(legalItemsArray[4], 1);
        assertEquals("football wasn't added", SUCCESS, methodOutput);
        for(int i=-2; i<=2; i++)
        {
            assertEquals("Baseball bat shouldn't be added", BASEBALL_FOOTBALL_ERROR, locker1.addItem(legalItemsArray[0], i));
        }
    }//End of testAddItemFirstCase method.


    /**
     * This method tests the second case of addItem.
     * Test the addItem functionality where n is a negative number.
     */
    @Test
    public void testAddItemSecondCase()
    {
        int methodOutput;
        for(int i=0; i<5; i++)
        {
            for(int j=-5; j<0; j++)
            {
                methodOutput = locker1.addItem(legalItemsArray[i], j);
                assertEquals("Return value isn't correct", ERROR, methodOutput);
            }

        }
        assertEquals("Hash map is not empty", true, locker1.getInventory().isEmpty());
    }//End of testAddItemSecondCase method.


    /**
     *This method tests the third case of addItem.
     *Test the addItem functionality where n equals 0.
     */
    @Test
    public void testAddItemThirdCase()
    {
        int methodOutput;
        Map<String, Integer> testHashMap = locker1.getInventory();
        for(int i=0; i<5; i++)
        {
            methodOutput = locker1.addItem(legalItemsArray[i], 0);
            assertEquals("Return value isn't correct", SUCCESS, methodOutput);
        }
        assertEquals("Hash map is not empty", testHashMap, locker1.getInventory());
    }//End of testAddItemThirdCase method.


    /**
     * This method tests the fourth case of addItem.
     * Test the addItem functionality where n items can't be added
     */
    @Test
    public void testAddItemFourthCase()
    {
        int methodOutput;
        Locker locker2;
        for(int i=0; i<9; i++)
        {
            locker2 = new Locker(i);
            methodOutput = locker2.addItem(legalItemsArray[0],5);
            assertEquals("1. Item shouldn't be added to the locker", ERROR, methodOutput);
            methodOutput = locker2.addItem(legalItemsArray[1],4);
            assertEquals("2. Item shouldn't be added to the locker", ERROR, methodOutput);
            methodOutput = locker2.addItem(legalItemsArray[2],2);
            assertEquals("3. Item shouldn't be added to the locker", ERROR, methodOutput);
            methodOutput = locker2.addItem(legalItemsArray[3],1);
            assertEquals("4. Item shouldn't be added to the locker", ERROR, methodOutput);
            methodOutput = locker2.addItem(legalItemsArray[4],3);
            assertEquals("5. Item shouldn't be added to the locker", ERROR, methodOutput);

            assertEquals("Hash map is not empty", true, locker2.getInventory().isEmpty());
        }
    }//End of testAddItemFourthCase method.


    /**
     * This method tests the fifth case of addItem.
     * Test the addItem method functionality, the case in which there is space for all n Items
     * and the specific items don't take more than 50% of the storage, this also tests getInventory()
     */
    @Test
    public void testAddItemFifthCase()
    {
        Map<String, Integer> testHashMap = locker1.getInventory();
        int methodOutput;

        for(int i=0; i<25; i++)
        {//Only one type in locker.
            methodOutput = locker1.addItem(legalItemsArray[0],1);
            assertEquals("Return value isn't correct", SUCCESS, methodOutput);
            testHashMap.put(legalItemsArray[0].getType(), 1);
            assertEquals("Hash maps are not identical", testHashMap, locker1.getInventory());
        }

        initializeTestParameters();

        testHashMap = locker1.getInventory();
        for(int i=0; i<10; i++)
        {//more than one type in locker.
            methodOutput = locker1.addItem(legalItemsArray[2],1);
            assertEquals("Return value isn't correct", SUCCESS, methodOutput);
            methodOutput = locker1.addItem(legalItemsArray[0],2);
            assertEquals("Return value isn't correct", SUCCESS, methodOutput);
            testHashMap.put(legalItemsArray[2].getType(), 1);
            testHashMap.put(legalItemsArray[0].getType(), 2);
            assertEquals("Hash maps are not identical", testHashMap, locker1.getInventory());
        }

        //one item = 50% capacity.
        Locker locker2 = new Locker(legalItemsArray[0].getVolume()*2);
        methodOutput = locker2.addItem(legalItemsArray[0],1);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);
    }//End of testAddItemFifthCase method.


    /**
     * This method tests the sixth case of addItem.
     * Test addItem where items are needed to be moved to the
     * long term storage, and there is NOT enough space.
     */
    @Test
    public void testAddItemSixthCase()
    {
        int methodOutput;

        //fill locker to 48%
        methodOutput = locker1.addItem(legalItemsArray[1], 16);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);
        assertEquals("Return value isn't correct", "{helmet, size 1=16}", locker1.getInventory().toString());
        assertEquals("Return value isn't correct", 52, locker1.availableCapacity);

        //fill long term storage completely.
        methodOutput = lts1.addItem(legalItemsArray[0], 500);
        assertEquals("Return value isn't correct", SUCCESS, methodOutput);
        assertEquals("Return value isn't correct", "{baseball bat=500}", lts1.getInventory().toString());
        assertEquals("Return value isn't correct", 0, lts1.availableCapacity);

        //fill locker to 51% + no space in LTS.
        methodOutput = locker1.addItem(legalItemsArray[1],1);
        assertEquals("Return value isn't correct", ERROR, methodOutput);
        assertEquals("Return value isn't correct", "{helmet, size 1=16}", locker1.getInventory().toString());
        assertEquals("Return value isn't correct", 52, locker1.availableCapacity);
        assertEquals("Return value isn't correct", "{baseball bat=500}", lts1.getInventory().toString());
        assertEquals("Return value isn't correct", 0, lts1.availableCapacity);
    }//End of testAddItemSixthCase method.


    /**
     * This method tests the seventh case of addItem.
     * Test addItem where items are needed to be moved to the
     * long term storage, and there is enough space.
     */
    @Test
    public void testAddItemSeventhCase()
    {
        Locker locker = new Locker(50);
        LongTermStorage lts1 = locker.longTermStorage;
        // The number of items we are trying to add to the locker
        int n = 4;
        lts1.resetInventory();

        // Test when the locker is empty
        assertEquals("Items should go to the storage 1",1, locker.addItem(legalItemsArray[3], n));

        // Check how much of the item was kept in the locker
        int methodOutput = locker.getItemCount("spores engine");
        assertTrue("Too many items were kept in the locker", methodOutput <= 0.2 * locker.getCapacity());

        // Check that the rest of the items have gone to the long term storage
        int itemsInLts = lts1.getItemCount("spores engine");
        assertEquals("The item isn't properly in the long term storage", n - methodOutput, itemsInLts);

        // Test when the locker isn't empty
        lts1.resetInventory();
        n = 3;

        locker = new Locker(50);
        locker.addItem(legalItemsArray[1],3);
        assertEquals("Items should go to the storage 2",1, locker.addItem(legalItemsArray[3], n));

        // Check how much of the item was kept in the locker
        methodOutput = locker.getItemCount("spores engine");
        assertTrue("Too many items were kept in the locker", methodOutput <= 0.2 * locker.getCapacity());

        // Check that the rest of the items have gone to the long term storage
        itemsInLts = lts1.getItemCount("spores engine");
        assertEquals("The item isn't properly in the long term storage", n - methodOutput, itemsInLts);

        //Move one item (which is more than 50% capacity) to long term storage.
        Locker locker2 = new Locker(15);
        LongTermStorage lts2 = locker2.longTermStorage;
        lts2.resetInventory();
        methodOutput = locker2.addItem(legalItemsArray[3],1);
        assertEquals("The item isn't properly in the long term storage", 1, methodOutput);
        assertEquals("HashMap is not empty", true, locker2.getInventory().isEmpty());
        assertEquals("item did not move to long term storage", 1, lts2.getItemCount("spores engine"));
    }//End of testAddItemSeventhCase method.


    /**
     * Test the removeItem method.
     */
    @Test
    public void testRemoveItem()
    {
        // add Items to the locker
        int methodOutput = locker1.addItem(legalItemsArray[0],2);
        assertEquals("Item wasn't added to the locker properly 1", SUCCESS, methodOutput);

        methodOutput = locker1.addItem(legalItemsArray[1],2);
        assertEquals("Item wasn't added to the locker properly 2", SUCCESS, methodOutput);

        methodOutput = locker1.addItem(legalItemsArray[2],2);
        assertEquals("Item wasn't added to the locker properly 3", SUCCESS, methodOutput);

        // Test for removing 1 item
        assertEquals("Item wasn't removed properly", SUCCESS, locker1.removeItem(legalItemsArray[0], 1));
        assertEquals("Item wasn't removed properly", 1, locker1.getItemCount("baseball bat"));

        // Test for removing 2 items
        assertEquals("Item wasn't removed properly", SUCCESS, locker1.removeItem(legalItemsArray[1], 2));
        assertEquals("Item wasn't removed properly", 0, locker1.getItemCount("helmet, size 1"));

        // Test for removing zero items
        assertEquals("problem removing zero items", SUCCESS, locker1.removeItem(legalItemsArray[0],0));
        assertEquals("Item wasn't removed properly", 1, locker1.getItemCount("baseball bat"));

        // Test for removing negative number of items
        assertEquals("Cant remove negative number of items", ERROR, locker1.removeItem(legalItemsArray[1],-1));

        // Test for for removing more items than there are in the locker
        assertEquals("Cant remove too many items", ERROR, locker1.removeItem(legalItemsArray[2],5));

        // Test for for removing more items than there are in the locker
        assertEquals("Cant remove negative number of items", ERROR, locker1.removeItem(legalItemsArray[1],5));
    }//End of testRemoveItem method.


    /**
     * Test getInventory()
     */
    @Test public void testGetInventory()
    {
        HashMap<String, Integer> testHashMap = new HashMap<>();
        for(int i=0; i<4; i++)
        {
            for (int j=1;j<5;j++)
            {
                locker1.addItem(legalItemsArray[i], 1);
                testHashMap.put(legalItemsArray[i].getType(), j);
                assertEquals("Item wasn't removed properly", testHashMap , locker1.getInventory());
            }

        }
        for(int i=0; i<4; i++)
        {
                locker1.removeItem(legalItemsArray[i], 4);
                testHashMap.remove(legalItemsArray[i].getType());
                assertEquals("Item wasn't removed properly", testHashMap , locker1.getInventory());


        }
        assertEquals("Item wasn't removed properly", true , locker1.getInventory().isEmpty());


        //check special cases
        locker1.addItem(legalItemsArray[0], 1);
        locker1.addItem(legalItemsArray[1], 1);
        locker1.removeItem(legalItemsArray[1], 1);
        locker1.addItem(legalItemsArray[2], 0);
        assertEquals("HashMap was not updated correctly", "{baseball bat=1}" , locker1.getInventory().toString());
    }//End of testGetInventory method.


    /**
     * Test the getItemCount method.
     */
    @Test
    public void testGetItemCount()
    {
        //Repeated addition
        int measuredCount;
        for(int i=0; i<=3; i++)
        {
            for(int j=1; j<=3; j++)
            {
                locker1.addItem(legalItemsArray[i], 1);
                measuredCount = locker1.getItemCount(legalItemsArray[i].getType());
                assertEquals("Return value isn't correct", j, measuredCount);
            }
        }

        // Check an item that does not exist.
        assertEquals("This item shouldn NOT exist",
                0, locker1.getItemCount("virtual item"));

        // Adding nothing
        locker1.addItem(legalItemsArray[0], 0);
        assertEquals("The item amount isn't correct",
                3, locker1.getItemCount(legalItemsArray[0].getType()));

        // Adding too much
        locker1.addItem(legalItemsArray[0], 100);
        assertEquals("Problem adding item",
                3, locker1.getItemCount(legalItemsArray[0].getType()));

        // checking null item name
        assertEquals("Problem with null", 0, locker1.getItemCount(null));


    }//End of testGetItemCount method.


    /**
     * Test the getCapacity method.
     */
    @Test
    public void testGetCapacity()
    {
        assertEquals("The capacity isn't correct", LOCKER_CAPACITY, locker1.getCapacity());
    }//End of testGetItemCount method.


    /**
     * Test getAvailableCapacity method.
     */
    @Test
    public void testGetAvailableCapacity()
    {
        LongTermStorage lts1 = locker1.longTermStorage;
        lts1.resetInventory();

        // add items to the locker
        // total volume: 2
        locker1.addItem(legalItemsArray[0], 1);
        assertEquals("Incorrect available capacity", 98,locker1.getAvailableCapacity());

        // total volume: 8
        locker1.addItem(legalItemsArray[1], 2);
        assertEquals("Incorrect available capacity", 92,locker1.getAvailableCapacity());

        // total volume: 23
        locker1.addItem(legalItemsArray[2], 3);
        assertEquals("Incorrect available capacity", 77,locker1.getAvailableCapacity());

        // total volume: 31
        locker1.addItem(legalItemsArray[0], 4);
        assertEquals("Incorrect available capacity", 69,locker1.getAvailableCapacity());

        // total volume: 41
        locker1.addItem(legalItemsArray[3], 1);
        assertEquals("Incorrect available capacity", 59,locker1.getAvailableCapacity());

        // remove some items from the locker, total volume: 31
        locker1.removeItem(legalItemsArray[2], 2);
        assertEquals("Incorrect available capacity",69,locker1.getAvailableCapacity());

        // test after moving some items to the long term storage
        // total volume: 25
        locker1.removeItem(legalItemsArray[1],2);
        assertEquals("Incorrect available capacity",75,locker1.getAvailableCapacity());

        //total volume: 15
        locker1.removeItem(legalItemsArray[3],1);

        locker1.addItem(legalItemsArray[3],7);
        int sporesAmount = locker1.getItemCount("spores engine");
        int spVol = legalItemsArray[3].getVolume();
        assertEquals("Incorrect available capacity",85-sporesAmount*spVol,locker1.getAvailableCapacity());
    }//End of testGetAvailableCapacity method.




}//End of LockerTest Class.
