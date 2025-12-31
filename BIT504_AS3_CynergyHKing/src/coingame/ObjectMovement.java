package coingame;

import java.util.LinkedList;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class ObjectMovement{
	
	// CLASS VARIABLES
	
	
	

	//---------------------------METHODS--------------------------------//
	
	
		// moves an object when called and takes the parent class Sprite as a parameter
		
		protected void moveObject (Sprite sprite, int width, int height) {

				
				sprite.setXPosition(sprite.getxPosition() +  sprite.getxVelocity(), width);
				sprite.setYPosition(sprite.getyPosition() + sprite.getyVelocity(), height);

			
		} // end of moveCoin method
		
		//--------------------------------------------------------------------------//
		
		// this method checks if an object hits the wall and ensures it bounces off the wall
		
		protected void checkWallBounce (Sprite sprite, int boundary, int width, int height) {
				
				if (sprite.getxPosition() <= 0) {
					
					// Hit left side of screen
					
					sprite.setxVelocity(-sprite.getxVelocity());}

					
					else if (sprite.getxPosition() >= width - sprite.getWidth()) {
						
						// Hit right side of screen
						
						sprite.setxVelocity(-sprite.getxVelocity());

				} // end of if else statement
				
				
				if (sprite.getyPosition() <= boundary) {
					
					// Hits the boundary zone (where the scores are printed)
					
					sprite.setyVelocity(Math.abs(sprite.getyVelocity()));
					sprite.setYPosition(boundary, height);

				} else if (sprite.getyPosition() >= height - sprite.getHeight()) {
					
					// Hits the bottom
					sprite.setyVelocity(-Math.abs(sprite.getyVelocity()));
					
				} // end of if else statement

			
		} // end of checkWallBounce method
		
		
		//--------------------------------------------------------------------------//
	
		// this method positions a user, coins and enemies in different area of the game
		// it prevents the user, coins and enemies objects from overlapping each other when the game resets
		
		protected void setNewPosition (Sprite sprite, Sprite user, LinkedList<Coin> coins, LinkedList<Enemy> enemies, int width, int height) {
			
			boolean overlap = true;
			
			while (overlap) {
				
				//-------------USER-------------//
				
				sprite.resetPosition(width, height);
				overlap = false;
				
				// checks against user
				if (sprite.getRectangle().intersects(user.getRectangle())) {
					
					overlap = true;
					continue;
				} // end of if statement
				
				//-------------COINS-------------//
				
				// checks collision with all coins
				
				for (Sprite c: coins) {
					
					if (sprite != c && sprite.getRectangle().intersects(c.getRectangle())) {
						
						overlap = true;
						break;
					} // end of inner if statement
					
					
				} // end of coin for each loop
				
				if (overlap) continue; // if the coin hits, restart loop
				
				//-------------ENEMIES-------------//
				
				// checks collision with all enemies
				
				for (Sprite e: enemies) {
					
					if (sprite != e && sprite.getRectangle().intersects(e.getRectangle())) {
						
						overlap = true;
						break;
					} // end of inner if statement
					
					
				} // end of enemy for each loop
				
				
			} // end of while loop
			
		} // end of setNewPosition method
		
		//--------------------------------------------------------------------------//
	
		protected void resetGame(Sprite user, LinkedList<Coin> coins, LinkedList<Enemy> enemies, int width, int height, int speed, int enemySpeed) {

			speed++;
			int newVelocity = speed + enemySpeed;
			
			// resets user position
			user.resetPosition(width, height);
			
			// resets coin in random positions across the screen
			for (Sprite c: coins) {
				
				setNewPosition(c, user,coins, enemies, width, height);
			}
			
			// resets enemies in random positions and increases the speed
			for (Sprite e: enemies) {
				
				setNewPosition(e, user, coins, enemies, width, height);
				e.setxVelocity(newVelocity);
				e.setyVelocity(newVelocity);
			}
			
		} // end of resetGame method
		
		//--------------------------------------------------------------------------//
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of ObjectMovement class
