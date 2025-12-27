package coingame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	// CLASS VARIABLES
	
	private final static Color BACKGROUND_COLOUR = Color.GRAY;
	private final static int TIMER_DELAY = 5;
	private final static int COIN_MOVEMENT_SPEED = 2;
	private GameState gameState = GameState.INITIALIZING;
	
	
	// ARRAYS
	
	private final Coin[] COINS = new Coin [10]; 
	private final Enemy[] ENEMIES = new Enemy [10];
	
	
	// OBJECTS
	
	private Player user;
	
	//--------------------------------------------------------------------------//
	
	// DEFAULT CONSTRUCTOR
	
	public GamePanel () {
		
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		setFocusable(true);
		requestFocusInWindow();
		
		timer.start();
		
		
	} // end of default constructor
	
	//--------------------------------------------------------------------------//
	
	// METHODS

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	} // end of keyTyped method
	
	//--------------------------------------------------------------------------//

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	} // end of keyPressed method
	
	//--------------------------------------------------------------------------//

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	} // end of keyReleased method
	
	//--------------------------------------------------------------------------//
	
	@Override
	public void addNotify() {
	    super.addNotify();
	    // This ensures the component is ready to receive focus for the KeyListener
	    requestFocusInWindow(); 
	    
	} // end of addNotify method
	
	//--------------------------------------------------------------------------//
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		update();
		repaint();
		
		
	} // end of actionPerformed method

	//--------------------------------------------------------------------------//
	
	private void update () {
		
		switch (gameState) {
			
		case INITIALIZING: {
			
			// checks if the panel has been created
			
			if (getWidth() > 0 && getHeight() > 0) {

				createObjects();
				gameState = GameState.PLAYING;
				
				for (Coin c: COINS) {
					
					c.setxVelocity(COIN_MOVEMENT_SPEED);
					c.setyVelocity(COIN_MOVEMENT_SPEED);
					
				}
				
				
				
			} // end of if statement
			
			break;
			
		}
		case PLAYING: {
			
			for (Coin c: COINS) {
				
				moveObject(c);
				checkWallBounce(c);
				//checkCollisions(user, c);
				
			} // end of for each loop
			
			
			break;
		}
		
		case GAMEOVER: {
			
			break;
		}
		case GAME_WON:{
			
			
			break;
		}
		
		
		
		default:
			
			System.out.println("ERROR: An error occurred, please contact your administrator.");
			
			break;
		
		} // end of gameState switch
		
		
	} // end of update method
	
	//--------------------------------------------------------------------------//
	
	// this method creates the game objects
	
	public void createObjects () {
		
		// creates coin objects
		
		for (int i = 0; i < COINS.length; i++) {
			
			COINS[i] = new Coin (getWidth(), getHeight());
			
			// Gives each coin a random position across the screen
			
			COINS[i].resetPosition(getWidth(), getHeight());
			
		} // end of COINS for loop
		
		
		for (int i = 0; i < ENEMIES.length; i++) {
			
			ENEMIES[i] = new Enemy (getWidth(), getHeight());
			
			// Gives each enemy a random positions across the screen
			
			//ENEMIES[i].resetPosition(getWidth(), getHeight());
			
		} // end of ENEMIES for loop
		
		user = new Player (getWidth(),getHeight());
		
	} // end of createObjects method
	
	//--------------------------------------------------------------------------//
	
	private void moveObject (Coin c) {

			
			c.setXPosition(c.getxPosition() +  c.getxVelocity(), getWidth());
			c.setYPosition(c.getyPosition() + c.getyVelocity(), getHeight());
			
	
		
		
	} // end of moveObject method
	
	//--------------------------------------------------------------------------//
	
	private void checkWallBounce (Coin c) {
			
			if (c.getxPosition() <= 0) {
				
				// Hit left side of screen
				
				c.setxVelocity(-c.getxVelocity());}
				
				else if (c.getxPosition() >= getWidth() - c.getWidth()) {
					
					// Hit right side of screen
					
					c.setxVelocity(-c.getxVelocity());
					
			} // end of if else statement
			
			if (c.getyPosition() <= 0 || c.getyPosition() >= getHeight() - c.getHeight()) {
				
				// Hits top or bottom screen
				
				c.setyVelocity(-c.getyVelocity());
				
			} // end of if statement
		
		
	} // end of checkWallBounce method
	
	
	//--------------------------------------------------------------------------//
	
	private void checkCollisions(Player player, Enemy enemy, Coin c) {
		
		// check if player hits the coin
		
		if (player.getRectangle().intersects(c.getRectangle())) {
			
			c.resetPosition(getWidth(), getHeight());
			System.out.println("Coin collected!");
			
		} // end of if statement
		
		// check if player hits an enemy
		
		if (player.getRectangle().intersects(enemy.getRectangle())) {
			
			System.out.println("Game Over!");
			gameState = GameState.GAMEOVER;
			
		}
		
		
	} // end of checkCollisions method
	
	//--------------------------------------------------------------------------//
	// paint method for the coin object
	
	private void paintCoin (Graphics g, Coin c) {
		
		g.setColor(c.getColour());
		g.fillOval(c.getxPosition(), c.getyPosition(), c.getWidth(), c.getHeight());
		
		
	} // end of paintCoin method
	
	//--------------------------------------------------------------------------//
	
	// paint method for the player and enemy objects
	
	private void paintRectangle (Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
		
	} // end of paintPlayer method
	
	
	//--------------------------------------------------------------------------//
	
	// paints objects to the console
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		
		if (gameState != GameState.INITIALIZING) {
			
			for (Coin c: COINS) {
				
				paintCoin(g, c); // draws coin object
				
			} // end of COINS for each loop
			
			for (Enemy e: ENEMIES) {
				
				paintRectangle(g, e); // draws enemy object
				
			} // end of ENEMIES for each loop
			
			paintRectangle(g, user); // draws player object
			
			
		} // end of if statement
		
		
		
	} // end of paintComponent method
	
} // end of GamePanel class
