package coingame;
import java.awt.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Player extends Sprite {

	// CLASS VARIABLES
	
	private static final int PLAYER_WIDTH = 30;
	private static final int PLAYER_HEIGHT = 30;
	private static final Color PLAYER_COLOUR = Color.green;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	//--------------------------------------------------------------------------//
	
	// PLAYER CONSTRUCTOR
	
	public Player (int panelWidth, int panelHeight) {
		
		setWidth(PLAYER_WIDTH);
		setHeight(PLAYER_HEIGHT);
		setColour(PLAYER_COLOUR);
		
		int xPosition = panelWidth - DISTANCE_FROM_EDGE - getWidth();
		setInitialPosition (xPosition, panelHeight / 2 - (getHeight() / 2));
		resetToInitialPosition();
		
		
	} // end of Player constructor
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of Player class
