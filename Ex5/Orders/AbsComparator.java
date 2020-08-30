package Orders;
import java.io.File;
import java.util.Comparator;

/**
 * This is a Comparator class for the absolute name order class.
 */
public class AbsComparator implements Comparator<File>
{
    /*----= Instance Methods =-----*/
    public int compare(File firstFile, File secondFile)
    {
        return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
    }//End of compare method.
}//End of AbsComparator class.
