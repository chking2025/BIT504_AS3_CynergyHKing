package coingame;
import java.awt.*;

/* Cynergy Huaki-King
 * BIT504: Assessment 3
 * 5009119
 */

public class Sprite {

	// CLASS VARIABLES
	
	private int xPosition, yPosition, xVelocity, yVelocity, 
	width, height, initialXPosition, initialYPosition;
	
	private Color Colour = Color.orange;
	
	//--------------------------------------------------------------------------//
	
	// GETTERS
	
	public  Color getColour() {
		return Colour;
	}

	public  int getxPosition() {
		return xPosition;
	}
	
	public  int getyPosition() {
		return yPosition;
	}
	
	public  int getxVelocity() {
		return xVelocity;
	}
	
	public  int getyVelocity() {
		return yVelocity;
	}
	
	public  int getWidth() {
		return width;
	}
	
	public  int getHeight() {
		return height;
	}
	
	public  int getInitialXPosition() {
		return initialXPosition;
	}
	
	public  int getInitialYPosition() {
		return initialYPosition;
	}
	
	
	// SETTERS
	
	public  void setColour(Color getColour) {
		this.Colour = getColour;
	}
	
	public  void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public  void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public  void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	
	public  void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	public  void setWidth(int width) {
		this.width = width;
	}
	
	public  void setHeight(int height) {
		this.height = height;
	}
	
	public  void setInitialXPosition(int initialXPosition) {
		this.initialXPosition = initialXPosition;
	}
	
	public  void setInitialYPosition(int initialYPosition) {
		this.initialYPosition = initialYPosition;
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
