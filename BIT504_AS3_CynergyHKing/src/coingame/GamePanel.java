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
	private static String arrow = "\u25BA";
	
	// ARRAYS
	
	private static String[] winner = {"YOU WIN!", "THE ENEMY HAS WON!"};
	private static String[] arrows = {"\u2190", "\u2192", "\u2191", "\u2193"};
	
		// FINAL VARIABLES
	
		private final static Color BACKGROUND_COLOUR = new Color (0xA9A9A9); // grey
		private final static int BOUNDARY_ZONE = 120;
		private final static int TIMER_DELAY = 16;
		private final static int USER_SPEED = 2;
		private final static double COIN_ENEMY_SPEED = 2;
		private final static int POINTS_TO_WIN = 10;
		private final static int MAX_USER_HEALTH = 10;
			
			// paintScores: FINAL VARIABLES
			private final static int SCORE_TEXT_X = 70;
			private final static int SCORE_TEXT_Y = 90;
			private final static int SCORE_FONT_SIZE = 50;
			private final static String SCORE_FONT_FAMILY = "Arial";
			
				// paintWinner: FINAL VARIABLES
				private final static int WINNER_FONT_SIZE = 50;
				private final static String WINNER_FONT_FAMILY = "Arial";
				private final static int NEXT_SCREEN_FONT_SIZE = 30;
				private final static String NEXT_SCREEN_TEXT = "Press the 'N' key to go the next screen.";
				
					// paintStartScreen & paintGameOverScreen: FINAL VARIABLES
					
					// MAIN HEADINGS
					private final static String SS_HEADING = "Collect Em' All"; 
					private final static String GS_HEADING = "GAMEOVER!";
					private final static Color SCREEN_FONT_COLOUR = new Color (0xFFD700); // gold
					private final static int SCREEN_FONT_SIZE = 100;
					private final static String SCREEN_FONT_FAMILY = "Arial";
					
					// SUBHEADINGS
					private final static int INSTRUCTIONS_FONT_SIZE = 15;
					private final static int GAMEPLAY_FONT_SIZE = 15;
					private final static Color INSTRUCTIONS_FONT_COLOUR = new Color (0x337357); // dark green
					private final static Color GAMEPLAY_TEXT_FONT_COLOUR = new Color (0xBF211E); // mahogany red
					private final static String ENTER = arrow + " Press ENTER to Start";
					private final static String ARROWS = arrow + " Use arrows " + "[" + arrows[0] + arrows[2] + arrows[3] + arrows[1] + "]" + " to move";
					private final static String GAME_DESCRIPTION = "Collect all 10 coins to win the game.";
					private final static String ENEMY_WARNING = "BEWARE: There will be just as many enemies trying to catch you, avoid them at all cost! ";
					private final static String SPEED_WARNING = "The speed of the enemies will increase each time you take damage.";
					private final static String RESTART = arrow + " Press 'R' to restart game ";
					private final static String EXIT = arrow + " Press 'E' to exit game";
					
	
		
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
			} // end of inner if statement
		} // end of START_SCREEN if statement
		
		//--------------------------------------------------------//
		
		// if the game is won, it will take user to the GAMEOVER screen
		if (gameState == GameState.GAME_WON) {
			
			if (key == KeyEvent.VK_N) {
				
				gameState = GameState.GAMEOVER;
				
			} // end of inner if statement
		} // end of GAME_WON if statement
		
		//--------------------------------------------------------//
		
		// if the game is over, user will need to click on 'r' to restart game or 'e' to exit the game
		
		// restart game
		if (gameState == GameState.GAMEOVER) {
			
			if (key == KeyEvent.VK_R) {
				
				userScore = 0;
				pcScore = 0;
				userHealth = MAX_USER_HEALTH;
				gameWinner = null;
				COINS.clear();
				ENEMIES.clear();
				
				// recreate game
				objectSettings.createObjects(user, COINS, ENEMIES, getWidth(), getHeight());
				
				// reset velocities for coin and enemy objects
				
				for (Coin c: COINS) {
					
					c.setxVelocity(COIN_ENEMY_SPEED);
					c.setyVelocity(COIN_ENEMY_SPEED);
					
				} // end of COINS for each loop
				
				for (Enemy enemies: ENEMIES) {
					
					enemies.setxVelocity(COIN_ENEMY_SPEED);
					enemies.setyVelocity(COIN_ENEMY_SPEED);

				} // end of ENEMIES for each loop
				
				// resets the speed
				objectSettings.resetSpeed();
				
				// game restarts again
				gameState = GameState.PLAYING;
				
			}
			
			// exit game
			if (key == KeyEvent.VK_E) {
				
				System.exit(0);
				
			}
			
			
		} // end of GAMEOVER if statement
		
		//--------------------------------------------------------//
		
		// if the up, down, left or right keys are pressed, the user will be able to move up, down, left or right in the game
		if (key == KeyEvent.VK_UP) up = true;
		if (key == KeyEvent.VK_DOWN) down = true;
		if (key == KeyEvent.VK_LEFT) left = true;
		if (key == KeyEvent.VK_RIGHT) right = true;
		
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
	
	// this method controls the game logic
	
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
					
					c.setxVelocity(COIN_ENEMY_SPEED);
					c.setyVelocity(COIN_ENEMY_SPEED);
					
				} // end of COINS for each loop
				
				// sets the speed of the enemies to move around the screen
				for (Enemy e: ENEMIES) {
					
					e.setxVelocity(COIN_ENEMY_SPEED);
					e.setyVelocity(COIN_ENEMY_SPEED);
					
				} // end of ENEMIES for each loop
				
			} // end of if statement
			
			break;
			
		} // end of INITIALIZING 
		
		case START_SCREEN: {
			
			break;
			
			
			
		} // end of START_SCREEN
		
		//--------------------------------------------------------//
		
		case PAUSE: {
			
					// setting player speed
						user.setxVelocity(0);
						user.setyVelocity(0);
						
						if (up) user.setyVelocity(-USER_SPEED);
						if (down) user.setyVelocity(USER_SPEED);
						if (left) user.setxVelocity(-USER_SPEED);
						if (right) user.setxVelocity(USER_SPEED);
						
						// move player and checks for the wall boundaries
						objectSettings.moveObject(user, getWidth(), getHeight());
						
						if (user.getyPosition() < BOUNDARY_ZONE) {
							
							user.setYPosition(BOUNDARY_ZONE, getHeight());
						}
						
						// Check coin collisions
						
						for (int i = COINS.size() - 1; i >= 0; i--) {
							Coin c = COINS.get(i);
							
							if (user.getRectangle().intersects(c.getRectangle())) {
								
								COINS.remove(i);
								userScore++;
								
							} // end of if statement
							
							
						} // end of COINS for loop
			
						checkWin(user, userScore, pcScore, POINTS_TO_WIN);
			
			break;
			
		}
		
		//--------------------------------------------------------//
		
		case PLAYING: {
			
			// setting player speed
			user.setxVelocity(0);
			user.setyVelocity(0);
			
			if (up) user.setyVelocity(-USER_SPEED);
			if (down) user.setyVelocity(USER_SPEED);
			if (left) user.setxVelocity(-USER_SPEED);
			if (right) user.setxVelocity(USER_SPEED);
			
			objectSettings.gameplay(user, COINS, ENEMIES, userScore, pcScore, getWidth(), getHeight(), BOUNDARY_ZONE);
			
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
					objectSettings.resetObjects(user, COINS, ENEMIES, getWidth(), getHeight(), COIN_ENEMY_SPEED);
					gameState = GameState.PAUSE;
					damaged.start();
					
					break;
					
				} // end of if statement
				
				
				
			} // end of ENEMIES for each loop
			
			checkWin(user, userScore, pcScore, POINTS_TO_WIN);
			break;
		}
		
		//--------------------------------------------------------//
		
		case GAME_WON: {
			
			damaged.stop();
			
			break;
		}
		
		//--------------------------------------------------------//
		
		case GAMEOVER:{
			
			
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
			
		} else if (pcScore >= pointLimit || userHealth <= 0) {
			
			gameWinner = null;
			gameState = GameState.GAME_WON;
			
		} // end of if else statement
		
		
		
	} // end of checkWin method
	
	//---------------------------PAINT METHODS-----------------------------------
	
	// paint method for the start screen of the game
	
	private void paintStartScreen (Graphics g) {
		
		// MAIN TITLE
		g.setColor(SCREEN_FONT_COLOUR);
		g.setFont(new Font(SCREEN_FONT_FAMILY, Font.BOLD, SCREEN_FONT_SIZE));
		g.drawString(SS_HEADING, getWidth() / 2 - 350, getHeight() / 2 - 50);
		
		// GAMEPLAY DESCRIPTION
		g.setColor(GAMEPLAY_TEXT_FONT_COLOUR);
		g.setFont(new Font (SCREEN_FONT_FAMILY, Font.PLAIN, GAMEPLAY_FONT_SIZE));
		g.drawString(GAME_DESCRIPTION, getWidth()/2 - 120, getHeight() / 2 + 5);
		g.drawString(ENEMY_WARNING, getWidth()/2 - 280, getHeight() / 2 + 30);
		g.drawString(SPEED_WARNING, getWidth()/2 - 225, getHeight() / 2 + 55);
		
		// GAMEPLAY INSTRUCTIONS
		g.setFont(new Font (SCREEN_FONT_FAMILY, Font.BOLD, INSTRUCTIONS_FONT_SIZE));
		g.setColor(INSTRUCTIONS_FONT_COLOUR);
		g.drawString(ENTER, getWidth() / 2 - 120, getHeight() / 2 + 100 );
		g.drawString(ARROWS, getWidth() / 2 - 120, getHeight() / 2 + 120 );

		
	} // end of paintStartScreen method
	
	//--------------------------------------------------------------------------//
	
	private void paintGameOverScreen (Graphics g) {
		
		// MAIN HEADING
		g.setColor(SCREEN_FONT_COLOUR);
		g.setFont(new Font(SCREEN_FONT_FAMILY, Font.BOLD, SCREEN_FONT_SIZE));
		g.drawString(GS_HEADING, getWidth() / 2 - 300, getHeight() / 2 - 50);
				
		// SUB HEADINGS
		g.setFont(new Font (SCREEN_FONT_FAMILY, Font.BOLD, INSTRUCTIONS_FONT_SIZE));
		g.setColor(INSTRUCTIONS_FONT_COLOUR);
		g.drawString(RESTART, getWidth() / 2 - 120, getHeight() / 2 + 20 );
		g.drawString(EXIT, getWidth() / 2 - 120, getHeight() / 2 + 50 );

	} // end of paintGameOverScreen
	
	//--------------------------------------------------------------------------//
	
	// paint method for the coin object
	
	private void paintCoin (Graphics g, Coin c) {
		
		g.setColor(c.getColour());
		g.fillOval(c.getxPosition(), c.getyPosition(), c.getWidth(), c.getHeight());
		
		
	} // end of paintCoin method
	
	//--------------------------------------------------------------------------//
	
	// paint method for user and enemy objects
	
	private void paintRectangle (Graphics g, Sprite sprite) {
		
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
		
		
	} // end of paintRectangle method
	
	
	//--------------------------------------------------------------------------//
	
	// paint method for the scores
	
	private void paintScores (Graphics g) {
		
		Font scoreFont = new Font (SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(userScore);
		String rightScore = Integer.toString(pcScore);
		g.setFont(scoreFont);
		
		FontMetrics metrics = g.getFontMetrics(scoreFont);
		int rightScoreWidth = metrics.stringWidth(rightScore);
		
		// user score colour (left)
		g.setColor(new Color (0x337357));
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
		
		// enemy score colour (right)
		g.setColor(new Color (0xBF211E));
		int rightX = getWidth() - SCORE_TEXT_X - rightScoreWidth;
		g.drawString(rightScore, rightX, SCORE_TEXT_Y);

	} // end of paintScores method
	
	//--------------------------------------------------------------------------//
	
	// paint method for winner and nextScreen text
	
	private void paintWinner (Graphics g) {
		
		// paints winner text
		
		Font winnerFont = new Font (WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
		
		g.setFont(winnerFont);
		
		String win;
		
		if (gameWinner == user) {
			
			win = winner[0];
			g.setColor(new Color (0x337357)); // dark green
			
		} else {
			
			win = winner[1];
			g.setColor(new Color (0xBF211E)); // mahogany red
			
		}
		
		// center align winning text
		FontMetrics metrics = g.getFontMetrics(winnerFont);
		
		int x = (getWidth() - metrics.stringWidth(win)) / 2;
		int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
		
		// paints winner text
		g.drawString(win, x, y);
		
		//--------------------------------------------------------//
		
		// paints nextScreen text below winner text
		
		g.setFont(new Font (WINNER_FONT_FAMILY, Font.PLAIN, NEXT_SCREEN_FONT_SIZE));
		g.setColor(new Color (0x337357)); // dark green
		
		// center aligns text, ensuring its below the winner text
		FontMetrics nextScreenMetrics = g.getFontMetrics();
		int newX = (getWidth() - nextScreenMetrics.stringWidth(NEXT_SCREEN_TEXT)) / 2;
		
		g.drawString(NEXT_SCREEN_TEXT, newX, y + 50);

	} // end of paintWinner method
	
	//--------------------------------------------------------------------------//
	
	// this method paints the user health (hearts)
	
	private void paintUserHealth (Graphics g) {
		
		// positioning of the hearts
		int userHealthX = 70;
		int userHealthY = 120;
		int spaces = 30;
		String heartSymbol = "\u2665";
		
		g.setFont(new Font ("Arial", Font.PLAIN, 25));
		
		for (int heart = 0; heart < MAX_USER_HEALTH; heart++) {
			
			if (heart < userHealth) {
				
				// draw pink heart for remaining health
				g.setColor(new Color (0xFDB0C0)); // pink
				g.drawString(heartSymbol, userHealthX + (heart * spaces), userHealthY);
				
			} else {
				
				// draw a grey heart for lost lives
				g.setColor(Color.GRAY);
				g.drawString(heartSymbol, userHealthX + (heart * spaces), userHealthY);
				
				
			} // end of if else statement

		} // end of for loop
		
	} // end of paintUserHealth method
	
	//--------------------------------------------------------------------------//
	
	// paints objects to the console
	
	public void paintComponent (Graphics g) {
		
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync(); // prevents mouse lag
		
		/* when the game starts, the start screen will be painted
		 *  once the game begins, the coins and enemies will be painted to the screen
		 *  the scores will also be painted to the game throughout the game
		 */
		
		if (gameState == GameState.START_SCREEN) {
			
			paintStartScreen(g);
			
		} else if (gameState == GameState.PLAYING || gameState == GameState.PAUSE) {
			
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
		
		
		// if game is won, it will paint the winner
		if (gameState == GameState.GAME_WON) {
			
			paintWinner(g);
		} // end of if statement
		
		// once the game is over, the paintGameOverScreen will be painted and the user will get the choice to restart or exit the game
		if (gameState == GameState.GAMEOVER) {
			
			paintGameOverScreen(g);
		}
		
	} // end of paintComponent method
	
	//--------------------------------------------------------------------------//
	
} // end of GamePanel class
