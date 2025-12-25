package coingame;

import java.awt.Color;
import java.util.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Coin extends Sprite {

	// CLASS VARIABLES
	
	private static final int COIN_WIDTH = 20;
	private static final int COIN_HEIGHT = 20;
	private static final Color COIN_COLOUR = Color.yellow;
	
	//--------------------------------------------------------------------------//
	
	// COIN CONSTRUCTOR
	
	public Coin (int panelWidth, int panelHeight) {
		
		setWidth (COIN_WIDTH);
		setHeight (COIN_HEIGHT);
		setColour (COIN_COLOUR);
		
		setInitialPosition (panelWidth / 2, panelHeight/ 2);
		resetToInitialPosition();
		
		
	} // end of Coin constructor
	
	//--------------------------------------------------------------------------//
	
	public void resetPosition (int panelWidth, int panelHeight) {
		
		Random randomPosition = new Random();
		
		// this generates random positions for the coins to respawn
		
		int newXPosition = randomPosition.nextInt(panelWidth - getWidth());
		int newYPosition = randomPosition.nextInt(panelHeight - getHeight());
		
		setxPosition (newXPosition);
		setyPosition (newYPosition);
		
		
		
	} // end of resetPosition method
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of Coin class
