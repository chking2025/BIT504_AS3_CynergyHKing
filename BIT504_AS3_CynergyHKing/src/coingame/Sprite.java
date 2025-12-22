package coingame;
import java.awt.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Sprite {

	// CLASS VARIABLES
	
	private static int xPosition, yPosition, xVelocity, yVelocity, 
	width, height, initialXPosition, initialYPosition;
	
	private static Color getColour = Color.orange;
	
	//--------------------------------------------------------------------------//
	
	// GETTERS
	
	public static Color getGetColour() {
		return getColour;
	}

	public static int getxPosition() {
		return xPosition;
	}
	
	public static int getyPosition() {
		return yPosition;
	}
	
	public static int getxVelocity() {
		return xVelocity;
	}
	
	public static int getyVelocity() {
		return yVelocity;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static int getInitialXPosition() {
		return initialXPosition;
	}
	
	public static int getInitialYPosition() {
		return initialYPosition;
	}
	
	
	// SETTERS
	
	public static void setGetColour(Color getColour) {
		Sprite.getColour = getColour;
	}
	
	public static void setxPosition(int xPosition) {
		Sprite.xPosition = xPosition;
	}
	
	public static void setyPosition(int yPosition) {
		Sprite.yPosition = yPosition;
	}
	
	public static void setxVelocity(int xVelocity) {
		Sprite.xVelocity = xVelocity;
	}
	
	public static void setyVelocity(int yVelocity) {
		Sprite.yVelocity = yVelocity;
	}
	
	public static void setWidth(int width) {
		Sprite.width = width;
	}
	
	public static void setHeight(int height) {
		Sprite.height = height;
	}
	
	public static void setInitialXPosition(int initialXPosition) {
		Sprite.initialXPosition = initialXPosition;
	}
	
	public static void setInitialYPosition(int initialYPosition) {
		Sprite.initialYPosition = initialYPosition;
	}
	
	//--------------------------------------------------------------------------//
	
	// METHODS
	
	public Rectangle getRectangle () {
		
		return new Rectangle (getxPosition(), getyPosition(), getWidth(), getHeight());
		
		
	} // end of getRectangle method
	
	//--------------------------------------------------------------------------//
	
	public void setXPosition (int newXPosition, int panelWidth) {
		
		xPosition = newXPosition;
		
		if (xPosition < 0) {
	           xPosition = 0;
	       } else if (xPosition + width > panelWidth) {
	           xPosition = panelWidth - width;
	       }
		
	} // end of setXPosition method
	
	//--------------------------------------------------------------------------//
	
	public void setYPosition (int newYPosition, int panelHeight) {
		
		yPosition = newYPosition;

	      if (yPosition < 0) {
	          yPosition = 0;
	      } else if (yPosition + height > panelHeight) {
	          yPosition = panelHeight - height;
	      }
		
	} // end of setYPosition method
	
	//--------------------------------------------------------------------------//
	
	public void setInitialPosition (int initialX, int initialY) {
		
		initialXPosition = initialX;
		initialYPosition = initialY;
		
		
	} // end of setInitialPosition method
	
	//--------------------------------------------------------------------------//
	
	public void resetToInitialPosition () {
		
		setxPosition(initialXPosition);
		setyPosition(initialYPosition);
		
	} // end of resetToInitialPosition method
	
} // end of Sprite class
