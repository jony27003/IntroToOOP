/**
 * This class is responsible for creating spaceships, by their type.
 * @author Jonathan Elyovich 204894281
 */
public class SpaceShipFactory
{
    /*----=  Instance Methods  =-----*/
    /**
     * The method for creating the spaceShips object
     * @param args array of strings from the command line, for the type of ships.
     * @return an array of spaceship objects.
     */
    public static SpaceShip[] createSpaceShips(String[] args)
    {

        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for(int i=0; i<args.length; i++)
        {
            if (args[i].equals("h"))
                spaceShips[i]= new HumanSpaceShip();
            else if (args[i].equals("b"))
                spaceShips[i]= new BasherSpaceShip();
            else if (args[i].equals("a"))
                spaceShips[i]= new AgressiveSpaceShip();
            else if (args[i].equals("r"))
                spaceShips[i]= new RunnerSpaceShip();
            else if (args[i].equals("d"))
                spaceShips[i]= new DrunkardSpaceShip();
            else if (args[i].equals("s"))
                spaceShips[i]= new SpecialSpaceShip();
        }
        return (spaceShips);
    }//End of createSpaceShips method.




}//End of SpaceShipFactory Class.
