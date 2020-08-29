/**
 * This ship pursues other ships and tries to fire at them.
 * It is a subclass of "SpaceShip".
 * @author Jonathan Elyovich 204894281
 */
public class AgressiveSpaceShip extends SpaceShip
{
    /*----=  Instance Methods  =-----*/
    /**
     * This method will ensure that this ship will always accelerate,
     * and turn towards the nearest ship.
     * When its angle to the nearest ship is less than 0.21,
     * radians, it will try to fire.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game)
    {
        this.closestSpaceShipInformation(game);
        this.readyToFireMaintenance();
        aggressiveBehavior(game);
        this.addEnergy();
    }//End of doAction method.




}//End of AgressiveSpaceShip Class.
