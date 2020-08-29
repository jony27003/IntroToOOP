package Filters;

 /**
 * This class is responsible for creating new and valid filter objects.
 */
public class FilterFactory
 {
     /*----= Constructors =-----*/
     final String YES = "YES";
     final String NO = "NO";




     /*----= Instance Methods =-----*/
     /**
      * This method creates new and valid filter objects.
      * @param line the line that holds the filter information.
      * @return returns a valid filter or null otherwise.
      */
     public Filter createFilter(String line)
     {
         try
         {
             Filter filterToCreate = null;
             String[] segments = line.split("#");
             switch (segments[0])
             {
                 case "greater_than":
                     {
                         double valueToBeGreaterThan = Double.parseDouble((segments[1]));
                         if((valueToBeGreaterThan < 0) || (segments.length > 2) && (!segments[2].equals("NOT")))
                            return null;
                         filterToCreate = new GreaterThan(valueToBeGreaterThan);
                         break;
                     }
                 case "between":
                     {
                         double lowerBound = Double.parseDouble((segments[1]));
                         double upperBound = Double.parseDouble((segments[2]));
                         if((lowerBound < 0 || upperBound < 0) || (lowerBound > upperBound))
                             return null;
                         filterToCreate = new Between(lowerBound,upperBound);
                         break;
                     }
                case "smaller_than":
                     {
                         double valueToBeSmallerThan = Double.parseDouble((segments[1]));
                         if((valueToBeSmallerThan < 0) || (segments.length > 2) && (!segments[2].equals("NOT")))
                             return null;
                         filterToCreate = new SmallerThan(valueToBeSmallerThan);
                         break;
                     }
                case "file":
                     {
                         filterToCreate = new File(segments[1]);
                         break;
                     }
                case "contains":
                     {
                         filterToCreate = new Contains(segments[1]);
                         break;
                     }
                case "prefix":
                     {
                         filterToCreate = new Prefix(segments[1]);
                         break;
                     }
                case "suffix":
                    {
                        filterToCreate = new Suffix(segments[1]);
                        break;
                    }
                case "writable":
                    {
                        if (!(segments[1].equals(YES) || segments[1].equals(NO)))
                            return null;
                        if (segments[1].equals(YES))
                            filterToCreate = new Writable(true);
                        else if (segments[1].equals(NO))
                                filterToCreate = new Writable(false);
                        break;
                    }
                case "executable":
                    {
                        if(!(segments[1].equals(YES) || segments[1].equals(NO)))
                            return null;
                        if(segments[1].equals(YES))
                            filterToCreate = new Executable(true);
                        else if(segments[1].equals(NO))
                            filterToCreate = new Executable(false);
                        break;
                    }
                case "hidden":
                    {
                        if(!(segments[1].equals(YES) || segments[1].equals(NO)))
                            return null;
                        if(segments[1].equals(YES))
                            filterToCreate = new Hidden(true);
                        else if(segments[1].equals(NO))
                            filterToCreate = new Hidden(false);
                        break;
                    }
                case "all":
                    {
                        filterToCreate  = new All();
                        break;
                    }
                default:
                    {
                        filterToCreate = null;
                    }
            }

            if(filterToCreate != null && segments[segments.length-1].equals("NOT"))
                filterToCreate.setNotFlag(true);
            return filterToCreate;
        }
        catch(Exception exception)
        {
            return null;
        }
    }//End of createFilter method.




}//End of FilterFactory class.