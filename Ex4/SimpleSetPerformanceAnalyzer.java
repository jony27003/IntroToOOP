import java.util.*;


/**
 * This classes measures the time required to complete chosen tests by
 * OpenHashSet, ClosedHashSet, Java's TreeSet,  Java’s LinkedList and Java's HashSet
 * @author jony27003
 */
public class SimpleSetPerformanceAnalyzer
{
    public static void main(String[] args)
    {
        //Initialize Java's data structures.
        CollectionFacadeSet treeSetDataStructure = new CollectionFacadeSet(new TreeSet<>());
        CollectionFacadeSet linkedListDataStructure = new CollectionFacadeSet(new LinkedList<>());
        CollectionFacadeSet hashSetDataStructure= new CollectionFacadeSet(new HashSet<>());

        SimpleSet[] dataStructureArray = new SimpleSet[5];
        dataStructureArray[0] = new OpenHashSet();
        dataStructureArray[1] = new ClosedHashSet();
        dataStructureArray[2] = treeSetDataStructure;
        dataStructureArray[3] = linkedListDataStructure;
        dataStructureArray[4] = hashSetDataStructure;

        String pathToFile1 = "src\\data1.txt";
        String pathToFile2 = "src\\data2.txt";

        System.out.println("Test 1: ");
        addActionMeasurement(dataStructureArray,pathToFile1);
        System.out.println("**********************************************");
        System.out.println();

        System.out.println("Test 2: ");
        addActionMeasurement(dataStructureArray,pathToFile2);
        System.out.println("**********************************************");
        System.out.println();

        System.out.println("Test 3: ");
        containsActionMeasurement(dataStructureArray,pathToFile1, "hi");
        System.out.println("**********************************************");
        System.out.println();

        System.out.println("Test 4: ");
        containsActionMeasurement(dataStructureArray,pathToFile1, "-1317089015");
        System.out.println("**********************************************");
        System.out.println();

        System.out.println("Test 5: ");
        containsActionMeasurement(dataStructureArray,pathToFile2, "23");
        System.out.println("**********************************************");
        System.out.println();

        System.out.println("Test 6: ");
        containsActionMeasurement(dataStructureArray,pathToFile2, "hi");
        System.out.println("**********************************************");
        System.out.println();
    }//End of main method.



    /*----= Instance Methods =-----*/
    /**
     * This method converts the index of a data structure to its name.
     * @param indexInDataStructureArray index of a data structure.
     * @return The data structures name.
     */
    private static String indexToString(int indexInDataStructureArray)
    {
       switch(indexInDataStructureArray)
       {
            case 0: return "OpenHashSet";
            case 1: return "ClosedHashSet";
            case 2: return "treeSet";
            case 3: return "linkedList";
       }
       return "hashSet";
    }//End of indexToString method.


    /**
     * This method returns a word array corresponding with a text file received as input.
     * @param filePathRelativeToProject a path relative to the project’s directory to the file containing the words.
     * @return a word array corresponding with a text file received as input.
     */
    private static String[] filePathToArray(String filePathRelativeToProject)
    {
        return Ex4Utils.file2array(filePathRelativeToProject);
    }//End of filePathToArray method.


    /**
     * This method measures the time required to perform add action.
     * @param dataStructureArray the array of data structures.
     * @param filePathRelativeToProject a path relative to the project’s directory to the file containing the words.
     */
    private static void addActionMeasurement(SimpleSet[] dataStructureArray, String filePathRelativeToProject)
    {
        final int nanoToMiliConstant = 1000000;
        String wordsArray[] = filePathToArray(filePathRelativeToProject);
        for(int i=0; i<dataStructureArray.length; i++)
        {
            long timeBefore = System.nanoTime();
            addWords(dataStructureArray[i],wordsArray);
            long difference = (System.nanoTime() - timeBefore)/nanoToMiliConstant;
            System.out.println(indexToString(i) + " : " + difference); //Print result.
        }
    }//End of addActionMeasurement method.


    /**
     * This method measures the time required to perform contain action.
     * @param dataStructureArray the array of data structures
     * @param filePathRelativeToProject a path relative to the project’s directory to the file containing the words.
     * @param word the word to search for in contains
     */
    private static void containsActionMeasurement(SimpleSet[] dataStructureArray,
                                                  String filePathRelativeToProject, String word)
    {
        String wordsArray[] = filePathToArray(filePathRelativeToProject);
        for(int i=0; i<dataStructureArray.length; i++)
        {
            addWords(dataStructureArray[i], wordsArray);

            int numberOfIterations = 70000;
            if(i == 3)//linkedList case.
                numberOfIterations = 7000;

            else//Warm up
                for (int j = 0; j < numberOfIterations; j++)
                    dataStructureArray[i].contains(word);

            long timeBefore = System.nanoTime();
            for(int j = 0; j < numberOfIterations; j++)
                dataStructureArray[i].contains(word);

            long difference = ((System.nanoTime() - timeBefore)/numberOfIterations);
            System.out.println(indexToString(i) + " : " + difference); //Print result.
        }
    }//End of containsActionMeasurement method.


    /**
     * Adds all of the words to the data structure.
     * @param dataStructure the dataStructure to add all of the words to.
     * @param wordsArray the array of words to add to the set
     */
    private static void addWords(SimpleSet dataStructure, String[] wordsArray)
    {
        for(int i=0; i<wordsArray.length; i++)
            dataStructure.add(wordsArray[i]);
    }//End of addWords method.




}//End of SimpleSetPerformanceAnalyzer class.
