package Orders;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class is the order super class.
 */
public abstract class Order
{
    /*----=  Attributes  =-----*/
    /** The reverse state of the order **/
    protected boolean reverseFlag;




    /*----= Instance Methods =-----*/
    /**
     * This is an abstract method orders the array of files by the subclasses order.
     * @param filesArray to order.
     */
    public abstract void order(ArrayList<File> filesArray);


    /**
     * This is a setter method for the reverseFlag Attribute.
     * @param reverseState to set,
     */
    public void setReverseFlag(boolean reverseState)
    {
        this.reverseFlag = reverseState;
    }//End of setReverseFlag method.


    /**
     * This is an adapted MergeSort.
     * @param arrayToSort the array To Sort.
     * @param comparator defines how to compare the objects received.
     */
    public void adaptedMergeSort(ArrayList<File> arrayToSort, Comparator<File> comparator)
    {
        if (arrayToSort.size() < 2)
            return;
        int y = arrayToSort.size()/2;
        ArrayList<File> x = new ArrayList<>(Arrays.asList(new File[y]));
        ArrayList<File> z = new ArrayList<>(Arrays.asList(new File[arrayToSort.size()-y]));
        for (int i = 0; i < y; i++)
            x.set(i, arrayToSort.get(i));
        for (int i = y; i < arrayToSort.size(); i++)
            z.set(i-y, arrayToSort.get(i));
        adaptedMergeSort(x, comparator);
        adaptedMergeSort(z, comparator);
        adaptedMerge(arrayToSort, x, z, comparator);
    }//End of adaptedMergeSort method.


    //Helper method for adaptedMergeSort.
    private static void adaptedMerge(ArrayList<File> arrayToMerge, ArrayList<File> left,
                                    ArrayList<File> right, Comparator<File> comp)
    {
        int x=0 ,y=0 , z=0;
        while (x < left.size() && y < right.size())
        {
            if (comp.compare(left.get(x),right.get(y))<0)
            {
                arrayToMerge.set(z, left.get(x));
                x++;
            }
            else
                {
                    arrayToMerge.set(z, right.get(y));
                    y++;
                }
            z++;
        }
        while (x < left.size())
        {
            arrayToMerge.set(z, left.get(x));
            x++;
            z++;
        }
        while (y < right.size())
        {
            arrayToMerge.set(z, right.get(y));
            y++;
            z++;
        }
    }//End of helper method.




}//End of Order Class.



