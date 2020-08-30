package Orders;

/**
 * This class is responsible for creating new and valid order objects.
 */
public class OrderFactory
{




    /*----= Instance Methods =-----*/
    /**
     * Creates an order object
     * @param currentLine the string which contains the information for creating the order
     * @return returns he correct type of order, or null otherwise.
     */
    public Order createOrder(String currentLine)
    {
        try
        {
            String [] segments = currentLine.split("#");
            Order orderToCreate;
            switch (segments[0])
            {
                case "abs":
                    orderToCreate = new Abs();
                    break;
                case "type":
                    orderToCreate = new Type();
                    break;
                case "size":
                    orderToCreate = new Size();
                    break;
                default:
                    orderToCreate = null;
            }
            if(orderToCreate != null && segments[segments.length-1].equals("REVERSE"))
                orderToCreate.setReverseFlag(true);
            return orderToCreate;

        }
        catch(Exception exception)
        {
            return null;
        }
    }//End of createOrder method.




}//End OrderFactory class.
