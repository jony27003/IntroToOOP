package filesprocessing;
import java.io.*;


/**
 * This is the directory processor class.
 */
public class DirectoryProcessor
{





    /**
     * The main method.
     */
    public static void main(String[] args)
    {
        ProcessManagement toProcess = new ProcessManagement(new File(args[0]), new File(args[1]));
        toProcess.execution();
    }//End of main method.




}//End of DirectoryProcessor Class.




