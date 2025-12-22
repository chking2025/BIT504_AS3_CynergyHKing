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
	
	public void update () {
		
		
		
		
	} // end of update method
	
	//--------------------------------------------------------------------------//
	
	public void paintComponent (Graphics g) {
		
		
		
		
		
	} // end of paintComponent method
	
} // end of GamePanel class
