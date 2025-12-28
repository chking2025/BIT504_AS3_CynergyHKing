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
	
	private boolean up, down, left, right; // boolean flags to indicate if a key is pressed up, down, left or right
	private int userScore = 0, pcScore = 0, userPoints, pcPoints;
	private String winner;
	
		// FINAL VARIABLES
	
		private final static Color BACKGROUND_COLOUR = Color.GRAY;
		private final static int TIMER_DELAY = 16;
		private final static int OBJECT_MOVEMENT_SPEED = 3;
		private final static int POINTS_TO_WIN = 10;
	
		
	// ENUM
	
	private GameState gameState = GameState.INITIALIZING;
	
	
	// ARRAYS
	
	private final Coin[] COINS = new Coin [10]; 
	private final Enemy[] ENEMIES = new Enemy [10];
	
	
	// OBJECTS
	
	private Player user;
	
	//--------------------------------------------------------------------------//
	
	// GAME PANEL CONSTRUCTOR
	
	public GamePanel () {
		
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		setFocusable(true);
		addKeyListener(this);
		requestFocusInWindow();
		
		timer.start();
		
		
	} // end of GamePanel constructor
	
	//--------------------------------------------------------------------------//
	
	// METHODS

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	} // end of keyTyped method
	
	//--------------------------------------------------------------------------//

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) up = true;
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) down = true;
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) left = true;
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) right = true;
		
	} // end of keyPressed method
	
	//--------------------------------------------------------------------------//

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		// stop player object when key is released
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) up = false;
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) down = false;
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) left = false;
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) right = false;
		
	} // end of keyReleased method
	
	//--------------------------------------------------------------------------//
	
	@Override
	public void addNotify() {
	    super.addNotify();
	    this.setFocusable(true);
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
				
				// sets the speed of the coins to move around the screen
				for (Coin c: COINS) {
					
					c.setxVelocity(OBJECT_MOVEMENT_SPEED);
					c.setyVelocity(OBJECT_MOVEMENT_SPEED);
					
				} // end of COINS for each loop
				
				// sets the speed of the enemies to move around the screen
				for (Enemy e: ENEMIES) {
					
					e.setxVelocity(OBJECT_MOVEMENT_SPEED);
					e.setyVelocity(OBJECT_MOVEMENT_SPEED);
					
				} // end of ENEMIES for each loop
				
			} // end of if statement
			
			break;
			
		}
		case PLAYING: {
			
			// setting player speed
			user.setxVelocity(0);
			user.setyVelocity(0);
			
			if (up) user.setyVelocity(-OBJECT_MOVEMENT_SPEED);
			if (down) user.setyVelocity(OBJECT_MOVEMENT_SPEED);
			if (left) user.setxVelocity(-OBJECT_MOVEMENT_SPEED);
			if (right) user.setxVelocity(OBJECT_MOVEMENT_SPEED);
			
			// move player
			moveObject(user);
			
			// move coins
			for (Coin c: COINS) {
				
				moveObject(c);
				checkWallBounce(c);
				
					// checks collision between player and coins
					if (user.getRectangle().intersects(c.getRectangle())) {
						
						c.resetPosition(getWidth(), getHeight());
						
					} // end of if statement
				
			} // end of COINS for each loop
			
			// move enemies
			for (Enemy e: ENEMIES) {
				
				moveObject(e);
				checkWallBounce(e);
					
				// checks collision between enemies and player
					if (user.getRectangle().intersects(e.getRectangle())) {

						gameState = GameState.GAMEOVER;
					}
				
			} // end of ENEMIES for each loop
			
			
			break;
		}
		
		case GAMEOVER: {
			
			break;
		}
		case GAME_WON:{
			
			
			break;
		}
		
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
			
			ENEMIES[i].resetPosition(getWidth(), getHeight());
			
		} // end of ENEMIES for loop
		
		user = new Player (getWidth(),getHeight());
		
	} // end of createObjects method
	
	//--------------------------------------------------------------------------//
	
	// moves an object when called and takes the parent class Sprite as a parameter
	
	private void moveObject (Sprite sprite) {

			
			sprite.setXPosition(sprite.getxPosition() +  sprite.getxVelocity(), getWidth());
			sprite.setYPosition(sprite.getyPosition() + sprite.getyVelocity(), getHeight());

		
	} // end of moveCoin method
	
	//--------------------------------------------------------------------------//
	
	// this method checks if an object hits the wall and ensures it bounces off the wall
	// takes the Sprite class as a parameter
	
	private void checkWallBounce (Sprite sprite) {
			
			if (sprite.getxPosition() <= 0) {
				
				// Hit left side of screen
				
				sprite.setxVelocity(-sprite.getxVelocity());
				addScore(sprite, ENEMIES);}
				
				else if (sprite.getxPosition() >= getWidth() - sprite.getWidth()) {
					
					// Hit right side of screen
					
					sprite.setxVelocity(-sprite.getxVelocity());
					addScore(sprite, ENEMIES);
			} // end of if else statement
			
			if (sprite.getyPosition() <= 0 || sprite.getyPosition() >= getHeight() - sprite.getHeight()) {
				
				// Hits top or bottom screen
				
				sprite.setyVelocity(-sprite.getyVelocity());
				addScore(sprite, ENEMIES);
				
			} // end of if statement
		
		
	} // end of checkWallBounce method
	
	
	//--------------------------------------------------------------------------//
	
	// this method scores the user and enemies
	
	private void addScore (Sprite player, Sprite[] enemies) {
		
		if (player == user) {
			
			userScore++;
			
		} else if (enemies == ENEMIES) {
			
			pcScore++;
			
		} // end of if else statement
		
	} // end of addScore method
	
	//--------------------------------------------------------------------------//
	
	// this method checks the winner of the game
	
	private void checkWin() {
		
		if (userScore >= POINTS_TO_WIN) {
			
			winner = "You are the winner!";
			gameState = GameState.GAME_WON;
			
		} else if (pcScore >= POINTS_TO_WIN) {
			
			winner = "The Enemy has won!";
			gameState = GameState.GAMEOVER;
			
		} // end of if else statement
		
		
		
	} // end of checkWin method
	
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
	
	// paint method for the scores
	
	private void paintScores (Graphics g) {
		
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font ("Arial", Font.BOLD, fontSize);
		String topScore = Integer.toString(userScore);
		String bottomScore = Integer.toString(pcScore);
		g.setFont(scoreFont);
		g.drawString(topScore, xPadding, yPadding);
		g.drawString(bottomScore, -xPadding, -yPadding);
		
		
		
		
		
	} // end of paintScores method
	
	//--------------------------------------------------------------------------//
	
	// paints objects to the console
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync(); // prevents mouse lag
		
		if (gameState != GameState.INITIALIZING) {
			
			for (Coin c: COINS) {
				
				paintCoin(g, c); // draws coin object
				
			} // end of COINS for each loop
			
			for (Enemy e: ENEMIES) {
				
				paintRectangle(g, e); // draws enemy object
				
			} // end of ENEMIES for each loop
			
			paintRectangle(g, user); // draws player object
			paintScores(g);
			
		} // end of if statement
		
		
		
	} // end of paintComponent method
	
	//--------------------------------------------------------------------------//
	
} // end of GamePanel class
