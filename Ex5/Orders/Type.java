package Orders;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is an type order class.
 */
public class Type extends Order
{



    /*----= Instance Methods =-----*/
    /**
     * This method orders by type.
     * @param arrayOfFiles to order.
     */
    public void order(ArrayList<File> arrayOfFiles)
    {
        if(this.reverseFlag)
            adaptedMergeSort(arrayOfFiles, Collections.reverseOrder((new TypeComparator())));
        else
            adaptedMergeSort(arrayOfFiles, new TypeComparator());
    }//End of order method.




}//End of type class.
