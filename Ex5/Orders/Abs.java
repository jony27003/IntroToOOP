package Orders;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is an absolute name order class.
 */
public class Abs extends Order
{




    /*----= Instance Methods =-----*/
    /**
     * This method orders by Abs.
     * @param filesArray to order.
     */
    public void order(ArrayList<File> filesArray)
    {
        if(this.reverseFlag)
            adaptedMergeSort(filesArray, Collections.reverseOrder((new AbsComparator())));
        else
            adaptedMergeSort(filesArray, new AbsComparator());
    }//End of order method




}//End of Abs class.
