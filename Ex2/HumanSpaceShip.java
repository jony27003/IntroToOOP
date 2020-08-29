import oop.ex2.GameGUI;
import java.awt.*;

/**
 * This class represents a human controlled spaceship which is  controlled by the player.
 * It is a subclass of "SpaceShip".
 * @author Jonathan Elyovich 204894281
 */
public class HumanSpaceShip extends SpaceShip
{
    /*----=  Instance Methods  =-----*/
    /**
     * implements the behavior of the human spaceship,
     * which is dictated by the player actions
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game)
    {
        this.readyToFireMaintenance();
        this.shieldState = NO_SHIELD;
        GameGUI gui = game.getGUI();

        if (gui.isTeleportPressed())
            this.teleport();

        int howToTurn = KEEP_STRAIGHT;
        if(gui.isLeftPressed())
            howToTurn++;
        if(gui.isRightPressed())
            howToTurn--;
        shipPhysics.move(gui.isUpPressed(),howToTurn);

        if(gui.isShieldsPressed())
            this.shieldOn();

        if(gui.isShotPressed())
            this.fire(game);

        this.addEnergy();
    }//End of doAction method.


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
            return (GameGUI.SPACESHIP_IMAGE_SHIELD);
        return (GameGUI.SPACESHIP_IMAGE);
    }//End of getImage method.




}//End of HumanSpaceShip Class.
