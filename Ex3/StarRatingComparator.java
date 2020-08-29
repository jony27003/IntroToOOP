import oop.ex3.searchengine.Hotel;
import java.util.*;



/**
 * This is a a comparator class for comparing hotels by their star rating
 * @author Jonathan Elyovich 204894281.
 */
public class StarRatingComparator implements Comparator<Hotel>
{
    /*----= Instance Methods =-----*/
    public int compare(Hotel firstHotel, Hotel secondHotel)
    {
        if(firstHotel.getStarRating() > secondHotel.getStarRating())
            return -1;
        else if(firstHotel.getStarRating() < secondHotel.getStarRating())
            return 1;
        return (firstHotel.getPropertyName().compareTo(secondHotel.getPropertyName()));
    }//End of compare method.



}//End of StarRatingComparator Class.