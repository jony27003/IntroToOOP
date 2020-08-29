/**
 * This spaceship attempts to run away from the fight.
 * It is a subclass of "SpaceShip"
 * @author Jonathan Elyovich 204894281
 */
public class RunnerSpaceShip extends SpaceShip
{
    /*----=  Instance Methods  =-----*/
    /**This method will ensure that this ship will always accelerate, and
     will constantly turn away from the closest ship.
     If the nearest ship is closer than 0.25 units,
     and if its angle to the Runner is less than 0.23 radians,
     the Runner feels threatened and will attempt to teleport.
     */
    public void doAction(SpaceWars game)
    {
        this.closestSpaceShipInformation(game);
        defensiveBehavior();
        this.addEnergy();
    }//End of doAction method.




}//End of RunnerSpaceShip Class.
