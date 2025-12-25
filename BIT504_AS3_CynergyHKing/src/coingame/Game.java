package coingame;
import javax.swing.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Game extends JFrame {
	
	
	// GAME CONSTRUCTOR
	
	public Game () {
		
		setTitle ("Coin Game");
		setSize (800, 600);
		setResizable (false);
		add(new GamePanel());
		setVisible (true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	} // end of Game constructor
	
	//--------------------------------------------------------------------------//
	
	// MAIN METHOD

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			// creates game window
			
			public void run () {
				
				new Game();
				
			} // end of run method
		});
		
		

	} // end of main method
	
	//--------------------------------------------------------------------------//
	
	// METHODS
	


} // end of Game class
