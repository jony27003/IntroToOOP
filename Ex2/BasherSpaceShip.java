/**
 * This class represents a "Basher" spaceship which attempts to bash other ships.
 * It is a subclass of "SpaceShip".
 * @author Jonathan Elyovich 204894281
 */
public class BasherSpaceShip extends SpaceShip
{
    /*----=  Constants  =-----*/
    private final double PREPARING_TO_BASH_DISTANCE = 0.19;




    /*----=  Instance Methods  =-----*/
    /**
     * Does the following actions:
     * collide with other ships. It will always accelerate, and will
     * constantly turn towards the closest ship. If it gets within a distance of
     * 0.19 units (inclusive) from another ship, it will attempt to turn on its shields.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game)
    {
        this.shieldState = NO_SHIELD;
        this.closestSpaceShipInformation(game);
        this.pursueClosestSpaceShip();

        if (this.distanceToClosestShip <= PREPARING_TO_BASH_DISTANCE)
            this.shieldOn();

        this.addEnergy();
    }//End of doAction method.




}//End of BasherSpaceShip Class.