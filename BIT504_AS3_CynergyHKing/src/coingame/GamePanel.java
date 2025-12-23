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
	private boolean gameInitialised = false;
	private static GameState currentState = GameState.INITIALIZING;
	
	// OBJECTS
	
	private static Coin coin;
	private static Player player;
	private static Enemy enemy;
	
	//--------------------------------------------------------------------------//
	
	// DEFAULT CONSTRUCTOR
	
	public GamePanel () {
		
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		
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
	public void actionPerformed(ActionEvent e) {
		
		update();
		repaint();
		
		
	} // end of actionPerformed method

	//--------------------------------------------------------------------------//
	
	private void update () {
		
		if (!gameInitialised) {
			
			createObjects();
			gameInitialised = true;
		}
		
		
	} // end of update method
	
	//--------------------------------------------------------------------------//
	
	// this method creates the game objects
	
	public void createObjects () {
		
		coin = new Coin (getWidth(), getHeight());
		player = new Player (getWidth(), getHeight());
		enemy = new Enemy (getWidth(), getHeight());
		
		
		
	} // end of createObjects method
	
	//--------------------------------------------------------------------------//
	
	private void paintSprite (Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColour());
		g.fillOval(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
		
	} // end of paintSprite method
	
	//--------------------------------------------------------------------------//
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		
		if (gameInitialised) {
			
			paintSprite(g, coin);
			
		}
		
		
		
	} // end of paintComponent method
	
} // end of GamePanel class
