import oop.ex3.searchengine.Hotel;
import org.junit.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;



/**
 * This is a test class with the objective of testing the BoopingSite functionality.
 * @author Jonathan Elyovich 204894281.
 */
public class BoopingSiteTest
{
    /*----= Attributes =-----*/
    /** BoopingSite objects for testing **/
    private static BoopingSite booping1;
    /** BoopingSite objects for testing **/
    private static BoopingSite booping2;


    /*----= Constants =-----*/
    private double RANDOM_LATITUDE = 15.7;
    private double RANDOM_LONGITUDE = 40.3;




    /*----= Instance Methods =-----*/
    /**
     * initiate the test object
     */
    @BeforeClass
    public static void initiateTestObjects()
    {
        booping1 = new BoopingSite("hotels_dataset.txt");
        booping2 = new BoopingSite("hotels_tst1.txt");
    }//End of initiateTestObjects method.


    /**
     * Tests the constructor
     */
    @Test
    public void testConstructor()
    {
        assertEquals("Problem with constructor",
                "hotels_dataset.txt",booping1.dataSetName);
    }//End of testConstructor method.


    /**
     * Test the addItem method functionality.
     */
    @Test
    public void testGetHotelsInCityByRating()
    {
        Hotel[] hotels = booping1.getHotelsInCityByRating("manali");
        int MethodOutput;
        for(int i=0; i<hotels.length-1; i++)
        {
            if(hotels[i].getStarRating() == hotels[i+1].getStarRating())
            {
                assertTrue(hotels[i].getCity().equals("manali"));
                MethodOutput =
                        hotels[i].getPropertyName().compareTo(hotels[i+1].getPropertyName());
                assertTrue("problem with star rating", MethodOutput < 0);
            }
            assertTrue("problem with star rating",
                    hotels[i].getStarRating() >= hotels[i+1].getStarRating());
        }
    }//End of testGetHotelsInCityByRating method.


    /**
     * Test the getHotelsByProximity method.
     */
    @Test
    public void testGetHotelsByProximity()
    {
        Hotel[] hotelsTestArray =
                booping2.getHotelsByProximity(RANDOM_LATITUDE, RANDOM_LONGITUDE);
        boolean flag;

        for(int i=0; i<hotelsTestArray.length-1; i++)
        {
            if(distanceToHotel(hotelsTestArray[i], RANDOM_LATITUDE, RANDOM_LONGITUDE) ==
                    distanceToHotel(hotelsTestArray[i+1],RANDOM_LATITUDE, RANDOM_LONGITUDE))
            {
                flag = (hotelsTestArray[i].getNumPOI() >= hotelsTestArray[i+1].getNumPOI());
                assertTrue("problem with POI order", flag);
            }
            flag = distanceToHotel(hotelsTestArray[i], RANDOM_LATITUDE, RANDOM_LONGITUDE) <=
                    distanceToHotel(hotelsTestArray[i+1],RANDOM_LATITUDE, RANDOM_LONGITUDE);
            assertTrue("problem with distance order", flag);
        }
    }//End of testGetHotelsByProximity method.


    /**
     * Tests the getHotelsInCityByProximity.
     */
    @Test
    public void testGetHotelsInCityByProximity()
    {
        String city = "manali";
        Hotel[] hotelsArray =
                booping1.getHotelsInCityByProximity(city, RANDOM_LATITUDE, RANDOM_LONGITUDE);

        for(int i = 0; i < hotelsArray.length; i++)
            assertTrue("Hotel from different city problem",
                    hotelsArray[i].getCity().equals(city));


        boolean flag;
        double normal1;
        double normal2;
        for(int i = 0; i < hotelsArray.length-1; i++)
        {
            if(distanceToHotel(hotelsArray[i], RANDOM_LATITUDE, RANDOM_LONGITUDE) ==
                    distanceToHotel(hotelsArray[i+1],RANDOM_LATITUDE,RANDOM_LONGITUDE))
            {
                assertTrue(hotelsArray[i].getCity().equals(city));
                flag = hotelsArray[i].getNumPOI() >= hotelsArray[i+1].getNumPOI();
                assertTrue("problem with POI order", flag);
            }
            normal1 = distanceToHotel(hotelsArray[i], RANDOM_LATITUDE, RANDOM_LONGITUDE);
            normal2 = distanceToHotel(hotelsArray[i+1], RANDOM_LATITUDE, RANDOM_LONGITUDE);
            assertTrue("problem with distance order", normal1 <= normal2);
        }
    }//End of testGetHotelsInCityByProximity method.


    /**
     * This is a class for comparing hotels by their proximity
     * @param hotel the hotel to which the calculation is done,
     * @return The euclidean normal,
     * based on both of the hotels cartesian coordinates on a mutual plane.
     */
    private double distanceToHotel(Hotel hotel, double x, double y)
    {
        double xPrime = hotel.getLatitude();
        double yPrime = hotel.getLongitude();
        double euclideanNormal = Math.sqrt(Math.pow((x-xPrime),2) + Math.pow((y-yPrime),2));
        return euclideanNormal;
    }//End of distanceToHotel method.




}//End of BoopingSiteTest Class.
