package coingame;

import java.awt.Color;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Enemy extends Sprite {

	// CLASS VARIABLES
	
	private static final int ENEMY_WIDTH = 30;
	private static final int ENEMY_HEIGHT = 30;
	private static final Color ENEMY_COLOUR = Color.red;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	//--------------------------------------------------------------------------//
	
	// ENEMY CONSTRUCTOR
	
	public Enemy (int panelWidth, int panelHeight) {
		
		setWidth(ENEMY_WIDTH);
		setHeight(ENEMY_HEIGHT);
		setColour(ENEMY_COLOUR);
		
		int xPosition = panelWidth - DISTANCE_FROM_EDGE - getWidth();
		setInitialPosition (xPosition, panelHeight / 2 - (getHeight() / 2));
		resetToInitialPosition();
		
		
	} // end of Enemy constructor
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of Enemy class
