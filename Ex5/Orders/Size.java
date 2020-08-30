package Orders;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a size order.
 */
public class Size extends Order
{



    /*----= Instance Methods =-----*/
    /**
     * This method orders by size.
     * @param filesArray to order.
     */
    public void order(ArrayList<File> filesArray)
    {

        if(this.reverseFlag)
            adaptedMergeSort(filesArray, Collections.reverseOrder((new SizeComparator())));
        else
            adaptedMergeSort(filesArray, new SizeComparator());
    }//End of order method.




}//End of Size class.
