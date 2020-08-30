package Orders;
import java.io.File;
import java.util.Comparator;

/**
 * This is a Comparator class for the size order class.
 */
public class SizeComparator implements Comparator<File>
{
    /*----= Instance Methods =-----*/
    public int compare(File firstFile, File secondFile)
    {
        if(firstFile.length() < secondFile.length())
            return -1;
        else if(firstFile.length() > secondFile.length())
            return 1;
        return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
    }//End of compare method.
}//End of SizeComparator class.
