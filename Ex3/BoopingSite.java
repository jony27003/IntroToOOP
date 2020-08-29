import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import java.util.*;
import java.util.Iterator;
import java.util.Arrays;


/**
 * This class represents Booping.com, a hotel booking site.
 * @author Jonathan Elyovich 204894281.
 */
public class BoopingSite
{
    /*----= Attributes =-----*/
    /** The name of the dataset **/
    String dataSetName;
    /** The dataset object**/
    private HotelDataset hotelDataset;




    /*----= Constructor =-----*/
    /**
     * @param name the name of the data set
     */
    public BoopingSite(String name)
    {
        this.dataSetName = name;
        hotelDataset = new HotelDataset();
    }//End of constructor.


    /*----= Instance Methods =-----*/
    /**
     * @param city the city of the hotels.
     * @return an array of hotels located in the given city, sorted from the highest star-rating
     * to the lowest. If hotels have the same rating, they will sorted alphabetically.
     * If there are no hotels in the city, an empty array will be returned.
     */
    public Hotel[] getHotelsInCityByRating(String city)
    {
        //HotelDataset hotelDataset = new HotelDataset();
        ArrayList<Hotel> hotelsArray = new ArrayList<>
                (Arrays.asList(this.hotelDataset.getHotels(this.dataSetName)));
        Collections.sort(getHotelsInCity(hotelsArray, city), new StarRatingComparator());
        return (hotelsArray.toArray(new Hotel[hotelsArray.size()]));
    }//End of getHotelsInCityByRating method.


    /**
     * @param latitude the latitude of the geographic location.
     * @param longitude the longitude of the geographic location.
     * @return an array of hotels, sorted according to their distance from the given geographic
     * location in ascending order. Hotels that are at the same distance from the given location
     * are organized according to the number of points of interest for which they are close to
     * (in decreasing order). An empty array will be returned in case of illegal input.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude)
    {
        Hotel[] hotelsArray = this.hotelDataset.getHotels(this.dataSetName);
        Arrays.sort(hotelsArray, (new ProximityComparator(latitude, longitude)));
        return hotelsArray;
    }//End of getHotelsByProximity method.

    /**
     * @param city the city of of the hotels.
     * @param latitude the latitude of the geographic location.
     * @param longitude the longitude of the the geographic location.
     * @return returns an array of hotels in the given city, sorted according to their distance
     * from the given geographic location, in ascending order. Hotels that are at the same distance
     * from the given location are organized according to the number of points of interest for
     * which they are close to.(in a decreasing order). In case of illegal input, this method returns
     * an empty array.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude)
    {
        ArrayList<Hotel> hotelsArray = new ArrayList<>(Arrays.asList(this.hotelDataset.getHotels(this.dataSetName)));
        Collections.sort(getHotelsInCity(hotelsArray, city), new ProximityComparator(latitude, longitude));
        return hotelsArray.toArray(new Hotel[hotelsArray.size()]);
    }//End of getHotelsInCityByProximity method.


    /*----= Added Methods =-----*/
    /**
     * @param city the city of of the hotels.
     * @param hotelsArray the latitude of the geographic location.
     * @param city the longitude of the the geographic location.
     * @return returns an array of hotels in the given city, sorted according to their distance
     * from the given geographic location, in ascending order. Hotels that are at the same distance
     * from the given location are organized according to the number of points of interest for
     * which they are close to.(in a decreasing order). In case of illegal input, this method returns
     * an empty array.
     */
    private ArrayList<Hotel> getHotelsInCity(ArrayList<Hotel> hotelsArray, String city)
    {
        Hotel currentHotel;
        Iterator<Hotel> iterator = hotelsArray.iterator();
        while(iterator.hasNext())
        {
            currentHotel = iterator.next();
            if(!currentHotel.getCity().equals(city))
                iterator.remove();
        }
        return(hotelsArray);
    }//End of getHotelsInCity method.




}//End of BoopingSite Class.
