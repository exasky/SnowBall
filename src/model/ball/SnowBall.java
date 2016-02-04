package model.ball;
import javax.swing.JPanel;

import model.powers.Power;
import model.powers.impl.NonePower;

public class SnowBall extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// x position on screen
	public int x;
	// y position on screen
	public int y;
	// go to the left or to the right
	public Direction currentDirection;
	// 'speed'
	public int velocity;
	// 
	public Power ballPower;
	
	
	public SnowBall(int x, int y) {
		this.x = x;
		this.y = y;
		this.velocity = 1;
		this.currentDirection = Direction.NONE;
		this.ballPower = new NonePower();
	}
	
	public void setVelocity(int newVelocity) {
		this.velocity = newVelocity;
		if (this.velocity < 1) {
			this.velocity = 1;
		} else if (this.velocity > 10) {
			this.velocity = 10;
		}
	}
	
	public void setX(int newX, int maxWidth){
		this.currentDirection = (newX < this.x) ? Direction.LEFT : Direction.RIGHT;		
		this.x = newX;
		
		if (this.x < 1) {
			this.x = 1;
		} else if (maxWidth != 0 && this.x > maxWidth) {
			this.x = maxWidth;
			this.switchDirection();
		}
	}
	
	public void setY(int prevX) {
		this.y = (int)Math.round(Math.sqrt(Math.pow(this.velocity, 2) + Math.pow(this.x - prevX, 2))) + this.y;
	}
	
	public boolean hasPower(){
		return !(this.ballPower instanceof NonePower);
	}
	
	public void switchDirection() {
		if (this.currentDirection.equals(Direction.LEFT))
			this.currentDirection = Direction.RIGHT;
		else
			this.currentDirection = Direction.LEFT;	
	}
	
	@Override
	public String toString() {
		return "X:" + x + ";Y:" + y + ";Velocity:" + velocity ;
	}
}
