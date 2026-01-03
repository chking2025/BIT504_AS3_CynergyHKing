package coingame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	
	// CLASS VARIABLES
	
	private boolean up, down, left, right; // boolean flags to indicate if a key is pressed up, down, left or right
	private int userScore = 0, pcScore = 0, userHealth;
	private Timer damaged;
	
		// FINAL VARIABLES
	
		private final static Color BACKGROUND_COLOUR = Color.DARK_GRAY;
		private final static int BOUNDARY_ZONE = 120;
		private final static int TIMER_DELAY = 16;
		private final static int OBJECT_MOVEMENT_SPEED = 2;
		private final static int POINTS_TO_WIN = 5;
		private final static int MAX_USER_HEALTH = 5;
			
			// paintScores: FINAL VARIABLES
			private final static int SCORE_TEXT_X = 100;
			private final static int SCORE_TEXT_Y = 100;
			private final static int SCORE_FONT_SIZE = 50;
			private final static String SCORE_FONT_FAMILY = "Arial";
			
				// paintWinner: FINAL VARIABLES
				private final static int WINNER_TEXT_X = 100;
				private final static int WINNER_TEXT_Y = 300;
				private final static int WINNER_FONT_SIZE = 40;
				private final static String WINNER_FONT_FAMILY = "Arial";
				private final static String WINNER_TEXT = "WIN!";
				
					// paintStartScreen: FINAL VARIABLES
					
					// MAIN HEADING
					private final static String SS_TITLE = "Collect Em' All"; 
					private final static Color SS_FONT_COLOUR = Color.orange;
					private final static int SS_FONT_SIZE = 100;
					private final static String SS_FONT_FAMILY = "Arial";
					
					// SUBHEADINGS
					private final static int SS_SUBHEADING_FONT_SIZE = 50;
					private final static Color SS_SUBHEADING_FONT_COLOUR = Color.green;
					private final static String ENTER = "- Press ENTER to Start";
					private final static String WASD = "- Use WASD or Arrows to Move";
					
	
		
	// ENUM
	
	private GameState gameState = GameState.INITIALIZING;
	
	
	// CLASS
	
	private ObjectSettings objectSettings = new ObjectSettings();
	
	
	// LINKEDLISTS
	
	private final LinkedList<Coin> COINS = new LinkedList<>(); 
	private final LinkedList<Enemy> ENEMIES = new LinkedList<>();
	
	
	// OBJECTS
	
	private Player user;
	private Sprite gameWinner;
	
	//--------------------------------------------------------------------------//
	
	// GAME PANEL CONSTRUCTOR
	
	public GamePanel () {
		
		setBackground(BACKGROUND_COLOUR);
		Timer timer = new Timer (TIMER_DELAY, this);
		
		// pauses game for 3 seconds when an enemy damages the player
		damaged = new Timer (3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				gameState = GameState.PLAYING;
				damaged.stop();
				
			}
			
		}); // end of damaged timer function
		
		
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
		
		
		// if the 'Enter' or 'Space' keys are pressed on the Start screen, the game will begin
		if (gameState == GameState.START_SCREEN) {
			
			if (key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE) {
				
				gameState = GameState.PLAYING;
			}
			
			
		} // end of if statement
		
		// if the up, down, left or right keys are pressed, the user will be able to move up, down, left or right in the game
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
				
				// creates player object
				user = new Player (getWidth(), getHeight());
				
				// shows user health bar
				userHealth = MAX_USER_HEALTH;
				
				// creates coins and enemy objects
				objectSettings.createObjects(user, COINS, ENEMIES, getWidth(), getHeight());
				gameState = GameState.START_SCREEN;
				
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
			
		} // end of INITIALIZING 
		
		case START_SCREEN: {
			
			break;
			
			
			
		} // end of START_SCREEN
		
		case PAUSE: {
			
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
			
			objectSettings.gameplay(user, COINS, ENEMIES, userScore, pcScore, getWidth(), getHeight(), BOUNDARY_ZONE, OBJECT_MOVEMENT_SPEED);
			
			// Check coin collisions
			
			for (int i = COINS.size() - 1; i >= 0; i--) {
				Coin c = COINS.get(i);
				
				if (user.getRectangle().intersects(c.getRectangle())) {
					
					COINS.remove(i);
					userScore++;
					
				} // end of if statement
				
				
			} // end of COINS for loop
			
			// Check enemy collisions
			
			for (Enemy e: ENEMIES) {
				
				if (user.getRectangle().intersects(e.getRectangle())) {
					
					pcScore++;
					userHealth--;
					
					// pauses game for 3 seconds when an enemy hits player
					// resets position of enemies on the screen when player is hit by the enemies
					objectSettings.resetGame(user, COINS, ENEMIES, getWidth(), getHeight(), OBJECT_MOVEMENT_SPEED);
					gameState = GameState.PAUSE;
					damaged.start();
					
					break;
					
				} // end of if statement
				
				
				
			} // end of ENEMIES for each loop
			
			checkWin(user, userScore, pcScore, POINTS_TO_WIN);
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
	
	
	// this method checks the winner of the game
	
	private void checkWin (Sprite user, int userScore, int pcScore, int pointLimit) {
		
		if (userScore >= pointLimit) {
			gameWinner = user;
			gameState = GameState.GAME_WON;
			
		} else if (pcScore >= pointLimit) {
			
			gameState = GameState.GAMEOVER;
			
		} // end of if else statement
		
		
		
	} // end of checkWin method
	
	//---------------------------PAINT METHODS-----------------------------------
	
	// paint method for the start screen of the game
	
	private void paintStartScreen (Graphics g) {
		
		// MAIN TITLE
		g.setColor(SS_FONT_COLOUR);
		g.setFont(new Font(SS_FONT_FAMILY, Font.BOLD, SS_FONT_SIZE));
		g.drawString(SS_TITLE, getWidth() / 2 - 350, getHeight() / 2 - 50);
		
		// SUB-TITLES
		g.setFont(new Font (SS_FONT_FAMILY, Font.PLAIN, SS_SUBHEADING_FONT_SIZE));
		g.setColor(SS_SUBHEADING_FONT_COLOUR);
		g.drawString(ENTER, getWidth() / 2 - 255, getHeight() / 2 + 20 );
		g.drawString(WASD, getWidth() / 2 - 370, getHeight() / 2 + 70 );

		
	} // end of paintStartScreen method
	
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
		
		Font scoreFont = new Font (SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(userScore);
		String rightScore = Integer.toString(pcScore);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
		g.drawString(rightScore, getWidth()-SCORE_TEXT_X, SCORE_TEXT_Y);

		
	} // end of paintScores method
	
	//--------------------------------------------------------------------------//
	
	// paint method for winner text
	
	private void paintWinner (Graphics g) {
		
		int xPosition = 0;
		
		if (gameWinner != null) {
			
			Font winnerFont = new Font (WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
			g.setFont(winnerFont);
			xPosition = getWidth()/2;	
		}
		
		if (gameWinner == user) {
			
			xPosition -= WINNER_TEXT_X;
			
		}	else {
				
				xPosition += WINNER_TEXT_Y;
				
			}
			
			g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
		
		
	} // end of paintWinner method
	
	
	//--------------------------------------------------------------------------//
	
	// this method paints the user health (hearts)
	
	private void paintUserHealth (Graphics g) {
		
		// positioning of the hearts
		int userHealthX = 100;
		int userHealthY = 140;
		int spaces = 30;
		String heartSymbol = "\u2665";
		
		g.setFont(new Font ("Arial", Font.PLAIN, 25));
		
		for (int heart = 0; heart < MAX_USER_HEALTH; heart++) {
			
			if (heart < userHealth) {
				
				// draw pink heart for remaining health
				g.setColor(Color.pink);
				g.drawString(heartSymbol, userHealthX + (heart * spaces), userHealthY);
				
			} else {
				
				// draw a grey heart for lost lives
				g.setColor(Color.gray);
				g.drawString(heartSymbol, userHealthX + (heart * spaces), userHealthY);
				
				
			} // end of if else statement
			
			
			
		} // end of for loop
		
		
		
		
		
	} // end of paintUserHealth method
	
	
	
	
	//--------------------------------------------------------------------------//
	
	// paints objects to the console
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync(); // prevents mouse lag
		
		/* when the game starts, the start screen will be paint
		 *  once the game begins, the coins and enemies will be painted to the screen
		 *  the scores will also be painted to the game throughout the game
		 */
		
		if (gameState == GameState.START_SCREEN) {
			
			paintStartScreen(g);
			
		} else if (gameState == GameState.PLAYING || gameState == GameState.PAUSE || gameState == GameState.GAME_WON || gameState == GameState.GAMEOVER) {
			
			for (Coin c: COINS) {
				
				paintCoin(g, c); // draws coin object
				
			} // end of COINS for each loop
			
			for (Enemy e: ENEMIES) {
				
				paintRectangle(g, e); // draws enemy object
				
			} // end of ENEMIES for each loop
			
			paintRectangle(g, user); // draws player object
			paintScores(g);
			paintUserHealth(g);
			
		} // end of if else statement
		
		
		// if game is won or the game is over, it will paint the winner
		if (gameState == GameState.GAME_WON || gameState == GameState.GAMEOVER) {
			
			paintWinner(g);
		} // end of if statement
		
		
		
	} // end of paintComponent method
	
	//--------------------------------------------------------------------------//
	
} // end of GamePanel class
