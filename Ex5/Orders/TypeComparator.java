package Orders;
import java.io.File;
import java.util.Comparator;

/**
 * This is a Comparator class for the type order class.
 */
public class TypeComparator implements Comparator<File>
{
    /*----= Instance Methods =-----*/
    public int compare(File firstFile, File secondFile)
    {
        if(getFileType(firstFile).equals(getFileType(secondFile)))
            return firstFile.getAbsolutePath().compareTo(secondFile.getAbsolutePath());
        return getFileType(firstFile).compareTo(getFileType(secondFile));
    }//End of compare method.


    //Helper method for compare method.
    private String getFileType(File file)
    {
        int indexForFileType = file.getName().lastIndexOf('.') + 1;
        if(indexForFileType == -1)
            return "";
        return file.getName().substring(indexForFileType);
    }//End of helper method,

}//End of  TypeComparator class.
