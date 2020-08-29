/**
 * This spaceship has a drunk pilot,
 * therefore it performs a few selective actions at random.
 * It is a subclass of "SpaceShip"
 * @author Jonathan Elyovich 204894281
 */
public class DrunkardSpaceShip extends SpaceShip
{
    /*----=  Attributes  =-----*/
    private double randomTeleport;
    private double randomAccelerate;
    private double randomShield;
    private double randomFire;
    private int randomInt;


    /*----=  Constants  =-----*/
    private final double TELEPORT_PERCENTAGE  = 0.001;
    private final double SHIELD_PERCENTAGE  = 0.1;
    private final double FIRE_PERCENTAGE  = 0.009;
    private final double ACCELERATE_PERCENTAGE  = 0.9;




    /*----=  Instance Methods  =-----*/
    /**This method will preform the the following actions:
     * The ship will attempt teleport 1% of the rounds,
     * attempt to activate its shield 10% of the rounds,
     * attempt to fire 0.9% of the rounds,
     * accelerate 90% of the rounds,
     * turn left 50% of the rounds,
     * and continue straight 50% of the rounds.
     */
    public void doAction(SpaceWars game)
    {
        this.readyToFireMaintenance();
        this.shieldState = NO_SHIELD;

        this.randomTeleport = (Math.random());
        this.randomAccelerate = (Math.random());
        this.randomShield = (Math.random());
        this.randomFire = (Math.random());
        this.randomInt =(int) Math.round((Math.random()));//Down Casting Double to Int.

        if(randomTeleport < TELEPORT_PERCENTAGE)
            this.teleport();

        this.shipPhysics.move((randomAccelerate < ACCELERATE_PERCENTAGE),randomInt);

        if(randomShield < SHIELD_PERCENTAGE)
            this.shieldOn();

        if(randomFire < FIRE_PERCENTAGE)
            this.fire(game);


        this.addEnergy();
    }//End of doAction method.




}//End of DrunkardSpaceShip Class.
