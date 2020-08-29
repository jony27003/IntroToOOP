import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * @author Jonathan Elyovich 204894281
 */
public abstract class SpaceShip
{
    /*----=  Attributes  =-----*/
    /** The physical object of the ship **/
    protected SpaceShipPhysics shipPhysics;
    /** The current energy level of the ship **/
    protected int currentEnergy;
    /** The current health level of the ship **/
    protected int currentHealth;
    /** A variable that represents the number of rounds that have passed since the last
     * fire action, it is initialized to 7 so that the ship can fire at the first rounds.
     */
    protected int roundsSinceLastFire;
    /** The maximal energy level of a ship **/
    protected int maxEnergy;
    /** A boolean variable to represent the state of the shield **/
    protected boolean shieldState;
    /** a boolean that is true if the ship can fire, false otherwise **/
    protected boolean isReadyToFire;

    protected SpaceShip closestShip;
    protected double angleToClosestShip;
    protected double theEnemyAngleOfView;
    protected double distanceToClosestShip;

    /*----=  Constants  =-----*/
    private final boolean INITIAL_READY_TO_FIRE_STATE = true;
    private final int INITIAL_ROUNDS_SINCE_LAST_FIRE = 7;
    private final boolean INITIAL_SHIELD_STATE = false;
    private final int INITIAL_HEALTH = 22;
    private final int INITIAL_ENERGY = 190;
    private final int INITIAL_MAX_ENERGY =210;
    private final int DEAD = 0;
    private final int BASH_ENERGY_BONUS = 18;
    private final int FIRE_HEALTH_HIT = -1;
    private final int FIRE_MAX_ENERGY_HIT = -10;
    private final int OUT_OF_ENERGY = 0;
    private final int RESET_ROUNDS_SINCE_LAST_FIRE = 0;
    private final boolean RESET_READY_TO_FIRE_STATE = false;
    private final boolean READY_TO_FIRE = true;
    private final int ENERGY_FIRE_COST = 19;
    private final boolean SHIELD_IS_ON = true;
    private final int ENERGY_SHIELD_COST = 3;
    private final int ENERGY_TELEPORT_COST = 140;
    protected final boolean NO_SHIELD = false;
    protected final int TURN_RIGHT = -1;
    protected final int TURN_LEFT = 1;
    protected final int KEEP_STRAIGHT = 0;
    protected final int ZERO_RADIANS = 0;
    protected final boolean ACCELERATE = true;
    protected final double agressiveAngleOfAttack = 0.21;
    protected final double TRY_TO_TELEPORT_ANGLE = 0.23;
    protected final double TRY_TO_TELEPORT_DISTANCE = 0.25;




    /*----=  Constructor  =-----*/
    /**
     * Creates a new SpaceShip with the default parameters.
     */
    public SpaceShip()
    {
        this.reset();
    }//End of default SpaceShip Constructor.




    /*----=  Instance Methods  =-----*/
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);//End of doAction method.


    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip()
    {
        if(this.shieldState)
        {
            this.maxEnergy = this.maxEnergy + BASH_ENERGY_BONUS;
            this.currentEnergy = this.currentEnergy + BASH_ENERGY_BONUS;
            this.regulateEnergy();
            this.regulateHealth();
        }
        else
            this.gotHit();
    }//End of collidedWithAnotherShip method.


    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset()
    {
        this.shipPhysics = new SpaceShipPhysics();
        this.currentHealth = INITIAL_HEALTH;
        this.currentEnergy = INITIAL_ENERGY;
        this.shieldState = INITIAL_SHIELD_STATE;
        this.isReadyToFire = INITIAL_READY_TO_FIRE_STATE;
        this.roundsSinceLastFire = INITIAL_ROUNDS_SINCE_LAST_FIRE;
        this.maxEnergy = INITIAL_MAX_ENERGY;
        this.shieldState = NO_SHIELD;
    }//End of reset method.


    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead()
    {
        return (this.currentHealth <= DEAD);
    }//End of isDead method.


    /**
     * Gets the shipPhysics object that controls this ship.
     * 
     * @return the shipPhysics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics()
    {
        return (this.shipPhysics);
    }//End of SpaceShipPhysics method.


    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit()
    {
        if(!this.shieldState)
        {
            this.maxEnergy = this.maxEnergy + FIRE_MAX_ENERGY_HIT;
            this.currentHealth = this.currentHealth + FIRE_HEALTH_HIT;
            this.regulateEnergy();
            this.regulateHealth();
        }
    }//End of gotHit method.


    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage()
    {
        if(this.shieldState)
            return (GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD);
        return (GameGUI.ENEMY_SPACESHIP_IMAGE);
    }//End of getImage method.


    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game)
    {
        if(this.isReadyToFire)
        {
            game.addShot(this.shipPhysics);
            this.isReadyToFire = RESET_READY_TO_FIRE_STATE;
            this.roundsSinceLastFire = RESET_ROUNDS_SINCE_LAST_FIRE;
            this.currentEnergy = this.currentEnergy - ENERGY_FIRE_COST;

        }
    }//End of fire method.


    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn()
    {
        if(this.currentEnergy >= ENERGY_SHIELD_COST)
        {
            this.shieldState = SHIELD_IS_ON;
            this.currentEnergy = this.currentEnergy - ENERGY_SHIELD_COST;
        }
    }//End of shieldOn method.


    /**
     * Attempts to teleport.
     */
    public void teleport()
    {
        if(this.currentEnergy >= ENERGY_TELEPORT_COST) {
            this.shipPhysics = new SpaceShipPhysics();
            this.currentEnergy = this.currentEnergy - ENERGY_TELEPORT_COST;
        }
    }//End of teleport method.




    /*----=  Added Methods  =-----*/
    //This method will add 1 energy point if possible.
    //this method will be called at the end of every "doAction" method of every ship.
    protected void addEnergy()
    {
        if(this.currentEnergy < this.maxEnergy)
            this.currentEnergy++;
    }//End of addEnergy method.


    //This method ensures that a ship will be able to fire only
    // when it suitable with the rules of the game.
    protected void readyToFireMaintenance()
    {
        this.roundsSinceLastFire++;
        if((this.roundsSinceLastFire >= INITIAL_ROUNDS_SINCE_LAST_FIRE)&&
                (this.currentEnergy >= ENERGY_FIRE_COST))
        {
            this.isReadyToFire = READY_TO_FIRE;
        }
    }//End of readyToFireMaintenance method.


    //This method ensures that the ships current energy and max energy,
    //are never negative, and always in sync with one another.
    protected void regulateEnergy()
    {
        if(this.maxEnergy < OUT_OF_ENERGY)
            this.maxEnergy = OUT_OF_ENERGY;
        if(this.currentEnergy > this.maxEnergy)
            this.currentEnergy = this.maxEnergy;
    }//End of regulateEnergy method.


    //This method ensures that the ships current health is never negative.
    protected void regulateHealth()
    {
        if(this.currentHealth < DEAD)
            this.currentHealth = DEAD;
    }//End of regulateHealth method.


    //This method provides information about the closest ship to this ship, such as:
    //who is the closest ship, angle to it, distance from it, and angle of attack.
    //parameter: game - the game object.
    protected void closestSpaceShipInformation (SpaceWars game)
    {
        closestShip = game.getClosestShipTo(this);
        angleToClosestShip = this.shipPhysics.angleTo(closestShip.getPhysics());
        distanceToClosestShip = this.shipPhysics.distanceFrom(closestShip.getPhysics());
        theEnemyAngleOfView = closestShip.getPhysics().angleTo(this.shipPhysics);
    }//End of closestSpaceShipInformation method.


    //This method is used to pursue the closest ship.
    protected void pursueClosestSpaceShip()
    {
        if (this.angleToClosestShip < ZERO_RADIANS)
            this.shipPhysics.move(ACCELERATE, TURN_RIGHT);
        else if (this.angleToClosestShip > ZERO_RADIANS)
            this.shipPhysics.move(ACCELERATE, TURN_LEFT);
        else
            this.shipPhysics.move(ACCELERATE, KEEP_STRAIGHT);
    }//End of pursueClosestSpaceShip method.


    //This method is used to evade the closest ship.
    protected void evadeClosestSpaceShip()
    {
        if (this.angleToClosestShip < ZERO_RADIANS)
            this.shipPhysics.move(ACCELERATE, TURN_LEFT);
        else if (this.angleToClosestShip > ZERO_RADIANS)
            this.shipPhysics.move(ACCELERATE, TURN_RIGHT);
        else
            this.shipPhysics.move(ACCELERATE, KEEP_STRAIGHT);
    }//End of evadeClosestSpaceShip method.


    //This method is used to pursue and destroy the closest ship by firing on it.
    //parameter: game - the game object.
    protected void aggressiveBehavior(SpaceWars game)
    {
        this.pursueClosestSpaceShip();
        if(Math.abs(this.angleToClosestShip) <= agressiveAngleOfAttack)
            this.fire(game);
    }//End of aggressiveBehavior method.


    //This method is used to run away and evade being fired upon.
    protected void defensiveBehavior()
    {
        if((Math.abs(theEnemyAngleOfView) < TRY_TO_TELEPORT_ANGLE)&&
                (distanceToClosestShip < TRY_TO_TELEPORT_DISTANCE))
            this.teleport();

        evadeClosestSpaceShip();
    }//End of defensiveBehavior method.




}//End of SpaceShip class.



