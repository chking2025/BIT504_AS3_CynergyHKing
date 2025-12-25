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
	
	// OBJECTS
	
	private Coin coin;
	private Player player;
	private Enemy enemy;
	
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
				
			} // end of if statement
			
			break;
			
		}
		case PLAYING: {
			checkCollisions();
			break;
		}
		
		case GAMEOVER: {
			
			break;
		}
		
		} // end of gameState switch
		
		
	} // end of update method
	
	//--------------------------------------------------------------------------//
	
	// this method creates the game objects
	
	public void createObjects () {
		
		coin = new Coin (getWidth(), getHeight());
		player = new Player (getWidth(), getHeight());
		enemy = new Enemy (getWidth(), getHeight());
		
		player.setxPosition(50);
		player.setyPosition(50);
		enemy.setxPosition(100);
		enemy.setyPosition(100);
		
		
		
	} // end of createObjects method
	
	//--------------------------------------------------------------------------//
	
	private void moveObject (Sprite obj) {
	
		obj.setXPosition(obj.getxPosition() +  obj.getxVelocity(), getWidth());
		obj.setYPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
		
		
	} // end of moveObject method
	
	//--------------------------------------------------------------------------//
	
	private void checkWallBounce () {
		
		
		
		
		
		
	} // end of checkWallBounce method
	
	
	//--------------------------------------------------------------------------//
	
	private void checkCollisions() {
		
		// check if player hits the coin
		
		if (player.getRectangle().intersects(coin.getRectangle())) {
			
			coin.resetPosition(getWidth(), getHeight());
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
	
	private void paintCoin (Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColour());
		g.fillOval(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
		
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
			
			paintCoin(g, coin); // draws coin object
			paintRectangle(g, player); // draws player object
			paintRectangle(g, enemy); // draws enemy object
			
		} // end of if statement
		
		
		
	} // end of paintComponent method
	
} // end of GamePanel class
