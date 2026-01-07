package coingame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

class CoinGameTest {

private GamePanel gp;

// 1. Tests if GamePanel can be setup
@BeforeEach
void setUpGameWindow() {
	
	gp = new GamePanel();
	
	// setup game panel window
	gp.setSize(800, 600);
	
} // end of setupGameWindow method

//--------------------------------------------------------------------------//

// 2. Test if MAX_USER_HEALTH is set to 10

@Test

void testMaxUserHealth () {
	
	System.out.println("Current value of MAX_USER_HEALTH is: " + gp.getMAX_USER_HEALTH());
	assertEquals(10, gp.getMAX_USER_HEALTH(), "MAX_USER_HEALTH is not set to 10.");
	
	
} // end of testMaxUserHealth method

//--------------------------------------------------------------------------//

// 3. Test if userScore is set to 0

@Test

void testUserScore () {
	
	System.out.println("Current value of userScore is: " + gp.getUserScore());
	assertEquals(0, gp.getUserScore(), "userScore is not 0.");
	
} // end of testUserScore method



//--------------------------------------------------------------------------//
} // end of CoinGameTest class
