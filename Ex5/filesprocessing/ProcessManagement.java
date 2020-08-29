package filesprocessing;
import Filters.*;
import Exceptions.*;
import Orders.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;


public class ProcessManagement
{
    /*----=  Attributes  =-----*/
    /** The directory of the source **/
    File sourceDir;
    /** The object file to analyze **/
    File commandFile;




    /*----= constructor =-----*/
    /**
     * ProcessManagement constructor.
     * @param receivedSourceDir - the source directory
     * @param receivedCommandFile - the command file
     */
    ProcessManagement(File receivedSourceDir, File receivedCommandFile)
    {
        this.sourceDir = receivedSourceDir;
        this.commandFile = receivedCommandFile;
    }//End of ProcessManagement constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method orders the files array that is received
     * @param filesToOrder the files to order.
     * @param order the order in which the files should be ordered.
     */
    public void orderFilesArray(Order order, ArrayList<File> filesToOrder)
    {
        order.order(filesToOrder);
    }//End of orderFilesArray method.


    /**
     * This method searches for the saved warnings.
     */
    private void searchForWarningsInPool(Object obj) throws Type1
    {
        if(obj == null)
            throw new Type1();
    }//End of searchForWarningsInPool method.


    /**
     * Manage the execution of the file processing
     */
    public void execution()
    {
        ArrayList<SectionWrapper> sectionsWrapper;
        ArrayList<File> files;
        try
        {
            sectionsWrapper = analyzeFile();
            files = retrieveFilesFromSourceDirectory(this.sourceDir);
        }
        catch(Type2 exception)
        {
            String message = exception.getMessage();
            System.out.println(message);
            return;
        }
        runOnSections(sectionsWrapper, files);
    }//End of execution method.


    /**
     * This method Filters the files array that is received
     * @param files the files to filter.
     * @param filter the filter in which the files should be filtered.
     * @return filteredFilesArray a filtered array.
     */
    public ArrayList<File> filesArrayFilter(Filter filter, ArrayList<File> files)
    {
        ArrayList<File> filteredFilesArray = new ArrayList<>();
        for(File fileObject : files)
            if(filter.didFilePassFilter(fileObject))
                filteredFilesArray.add(fileObject);
        return filteredFilesArray;
    }//End of orderFilesArray method.


    // gets file in the source directory
    /**
     * This method retrieves Files From the Source Directory.
     * @param sourceDirectory - the source Directory .
     * @return filesFromSourceDirectory the Files From the Source Directory.
     */
    private ArrayList<File> retrieveFilesFromSourceDirectory(File sourceDirectory)
    {
        ArrayList<File> filesFromSourceDirectory = new ArrayList<>();
        for(File fileObject : sourceDirectory.listFiles())
            if(!fileObject.isDirectory())
                filesFromSourceDirectory.add(fileObject);
        return filesFromSourceDirectory;
    }//End of retrieveFilesFromSourceDirectory method.


    /**
     * analyses the received file.
     * @return - an array of sectionsWrapper Objects.
     */
    public ArrayList<SectionWrapper> analyzeFile() throws Type2
    {
        ArrayList<SectionWrapper> sectionsWrapperArray = new ArrayList<>();
        FilterFactory filterFactory = new FilterFactory();
        OrderFactory orderFactory   = new OrderFactory();
        BufferedReader in;
        String subSectionName;
        String filterName;
        String lineToParse;
        int lineNum = 0;
        try
        {
            in = new BufferedReader(new FileReader(this.commandFile));
        }
        catch(Exception FileNotFoundException)
        {
            throw new Type2("problem finding the command file. \n");
        }
        try
        {
            lineToParse = in.readLine();
            while(lineToParse != null)
            {
                lineNum++;
                SectionWrapper sectionWrapperArray = new SectionWrapper();
                if (!lineToParse.equals("FILTER"))
                    throw new IllegalSubsectionLineDetected();
                filterName = in.readLine();
                if(filterName == null)
                    throw new Type2();
                lineNum++;

                Filter filter = filterFactory.createFilter(filterName);
                if (filter == null)
                    sectionWrapperArray.addWarningToPool("Warning in line " + lineNum);
                sectionWrapperArray.setFilter(filter);


                subSectionName = in.readLine();
                if(subSectionName == null)
                    throw new Type2();
                lineNum++;
                if (!subSectionName.equals("ORDER"))
                        throw new IllegalSubsectionLineDetected();

                lineToParse = in.readLine();
                if((lineToParse==null) || (lineToParse.equals("FILTER")))
                {
                    sectionWrapperArray.setOrder(orderFactory.createOrder("abs"));
                    sectionsWrapperArray.add(sectionWrapperArray);
                    continue;
                }
                lineNum++;

                Order order = orderFactory.createOrder(lineToParse);
                if (order == null)
                    sectionWrapperArray.addWarningToPool("Warning in line " + lineNum);
                sectionWrapperArray.setOrder(order);
                sectionsWrapperArray.add(sectionWrapperArray);
                lineToParse = in.readLine();
            }
        }
        catch(IOException exception)
        {
            throw new Type2("problem with reading from the command file. \n");
        }
        return sectionsWrapperArray;
    }//End of execution method.


    /**
     * This method iterates on the file that is being processed.
     * @param sectionWrapperArray the sectionWrapper array.
     * @param files the files array.
     */
    private void runOnSections(ArrayList<SectionWrapper> sectionWrapperArray, ArrayList<File> files)
    {
        for (SectionWrapper sectionWrapperObject : sectionWrapperArray)
        {
            Filter filter = sectionWrapperObject.getFilter();
            Order order = sectionWrapperObject.getOrder();
            try
            {
                searchForWarningsInPool(filter);
                searchForWarningsInPool(order);
            }
            catch (Type1 exception)
            {
                for (String stringToIterate : sectionWrapperObject.getWarningsPool())
                    System.err.println(stringToIterate);
            }
            if(filter == null)
                filter = new All();
            if(order == null)
                order = new Abs();
            ArrayList<File> afterFilterArray = filesArrayFilter(filter, files);
            orderFilesArray(order, afterFilterArray);
            for(File fileObject : afterFilterArray)
                System.out.println(fileObject.getName());

        }
    }//End of runOnSections method.




}//End of ProcessManagement class.