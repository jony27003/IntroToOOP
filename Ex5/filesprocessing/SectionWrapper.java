package filesprocessing;
import Filters.*;
import Orders.*;
import java.util.ArrayList;

/**
 * This class is a section wrapper that wraps a filter and a corresponding order.
 */
public class SectionWrapper
{
    /*----=  Attributes  =-----*/
    /** A filter object **/
    Filter filter;
    /** An order object **/
    Order order;
    /** The warningsPool of this section(type I errors) **/
    ArrayList<String> warningsPool;




    /*----= Constructors =-----*/
    /**
     * Creates a SectionWrapper Object.
     */
    SectionWrapper()
    {
        this.filter = null;
        this.order = null;
        this.warningsPool = new ArrayList<>();
    }//End of Constructor.


    /*----= Instance Methods =-----*/
    /**
     * This method is a filter getter.
     */
    public Filter getFilter()
    {
        return this.filter;
    }//End of getFilter method.


    /**
     * This method is a order getter.
     */
    public Order getOrder()
    {
        return this.order;
    }//End of getOrder method.


    /**
     * This method is a filter setter.
     * @param filterToSet the filter to set.
     */
    public void setFilter(Filter filterToSet)
    {
        this.filter = filterToSet;
    }//End of setFilter method.


    /**
     * This method is a order setter.
     * @param orderToSet the order to set.
     */
    public void setOrder(Order orderToSet)
    {
        this.order = orderToSet;
    }//End of setOrder method.


    /**
     * This method adds a warning to this SectionWrapper object pool.
     * @param warningToAdd the warning to add to the pool of warnings for this sectionWrapper.
     */
    public void addWarningToPool(String warningToAdd)
    {
        this.warningsPool.add(warningToAdd);
    }//End of addWarningToPool method.


    /**
     * This method returns the warning poll of this SectionWrapper object.
     * @return warningsPool the warning poll of this SectionWrapper object.
     */
    public ArrayList<String> getWarningsPool()
    {
        return this.warningsPool;
    }//End of getWarningsPool method.




}//End of SectionWrapper Class.
