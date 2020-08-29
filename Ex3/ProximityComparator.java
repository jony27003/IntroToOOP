import oop.ex3.searchengine.Hotel;
import java.util.Comparator;



/**
 * This is a class for comparing hotels by their proximity
 * @author Jonathan Elyovich 204894281.
 */
public class ProximityComparator implements Comparator<Hotel>
{
    /*----= Attributes =-----*/
    private double x;
    private double y;




    /*----= Constructor =-----*/
    public ProximityComparator(double latitude, double longitude)
    {
        this.x = latitude;
        this.y = longitude;
    }


    /*----= Instance Methods =-----*/
    /**
     * This method imposes a total ordering on Hotel type objects.
     * This method is used to sort the Hotel objects in ascending order,
     * by closest distance, or if the distance is equal,
     * by points of interests in descending order.
     */
    public int compare(Hotel firstHotel, Hotel secondHotel)
    {
        if(this.distanceToHotel(firstHotel) > this.distanceToHotel(secondHotel))
            return 1;
        else if(this.distanceToHotel(firstHotel) < this.distanceToHotel(secondHotel))
            return -1;

        //Equal distance
        if(firstHotel.getNumPOI() > secondHotel.getNumPOI())
            return -1;
        else if(firstHotel.getNumPOI() < secondHotel.getNumPOI())
            return 1;
        return 0;
    }//End of compare method.


    /*----= Added Methods =-----*/
    /**
     * This is a class for comparing hotels by their proximity
     * @param hotel the hotel to which the calculation is done,
     * @return The euclidean normal,
     * based on both of the hotels cartesian coordinates on a mutual plane.
     */
    private double distanceToHotel(Hotel hotel)
    {
        double xPrime = hotel.getLatitude();
        double yPrime = hotel.getLongitude();
        double euclideanNormal = Math.sqrt(Math.pow((x-xPrime),2) + Math.pow((y-yPrime),2));
        return euclideanNormal;
    }//End of distanceToHotel method.




}//End of ProximityComparator Class.
