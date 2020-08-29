/**
 * This spaceship displays aggressive behavior against a runner space ship,
 * and a defensive behavior towards all other space ships.
 * It is a subclass of "SpaceShip"
 * @author Jonathan Elyovich 204894281
 */
public class SpecialSpaceShip extends SpaceShip
{
    /*----=  Instance Methods  =-----*/
    /**
     * if the closest ship is a runner:
     * this ship will accelerate, and turn towards the runner ship.
     * When its angle to the runner ship. is less than 0.21, radians, it will attempt to fire.
     * otherwise:
     * This ship will accelerate, and will constantly turn away from the closest ship.
     * If the nearest ship is closer than 0.25 units,
     * and if its angle to the this ship is less than 0.23 radians,
     * this ship will will attempt to teleport.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game)
    {
        this.readyToFireMaintenance();
        this.closestSpaceShipInformation(game);

        if(this.closestShip instanceof RunnerSpaceShip)
            aggressiveBehavior(game);
        else
            defensiveBehavior();

        this.addEnergy();
    }//End of doAction method.




}//End of SpecialSpaceShip Class.
