package model.ball;

import model.powers.Power;
import model.powers.impl.NonePower;

public class SnowBall {
	/**
	 * X position on screen.
	 */
	public int x;
	/**
	 * Y position on screen.
	 */
	public int y;
	/**
	 * Go to the left or to the right.
	 */
	public Direction currentDirection;
	/**
	 * Speed.
	 */
	public int velocity;
	/**
	 * Ball power.
	 */
	public Power ballPower;

	/**
	 * Default constructor.<br/>
	 * Initialize with velocity set to 1, direction to NONE and power to
	 * NonePower.
	 * 
	 * @param x
	 *            The X position on the screen.
	 * @param y
	 *            The Y position on the screen.
	 */
	public SnowBall(int x, int y) {
		this.x = x;
		this.y = y;
		this.velocity = 1;
		this.currentDirection = Direction.NONE;
		this.ballPower = new NonePower();
	}

	/**
	 * Set the speed of the ball.
	 * 
	 * @param newVelocity
	 */
	public void setVelocity(int newVelocity) {
		this.velocity = newVelocity;
		if (this.velocity < 1) {
			this.velocity = 1;
		} else if (this.velocity > 10) {
			this.velocity = 10;
		}
	}

	/**
	 * Set the new x position.
	 * 
	 * @param newX
	 *            The new X to set.
	 * @param maxWidth
	 *            The width of the panel.
	 */
	public void setX(int newX, int maxWidth) {
		this.currentDirection = (newX < this.x) ? Direction.LEFT : Direction.RIGHT;
		this.x = newX;

		if (this.x < 1) {
			this.x = 1;
		} else if (maxWidth != 0 && this.x > maxWidth) {
			this.x = maxWidth;
			this.switchDirection();
		}
	}

	/**
	 * Set the new Y position according to the previous X one.<br/>
	 * This new position is calculated with the formula D²=(x2-x1)²+(y2-y1)².
	 * 
	 * @param prevX
	 */
	public void setY(int prevX) {
		this.y = (int) Math.round(Math.sqrt(Math.pow(this.velocity, 2) + Math.pow(this.x - prevX, 2))) + this.y;
	}

	/**
	 * 
	 * @return True if the ball is powered (e.g. has a different power tha the
	 *         NonePower). False otherwise.
	 */
	public boolean hasPower() {
		return !(this.ballPower instanceof NonePower);
	}

	/**
	 * Toggle of position. Left to Right of Right to Left.
	 */
	public void switchDirection() {
		if (this.currentDirection.equals(Direction.LEFT))
			this.currentDirection = Direction.RIGHT;
		else
			this.currentDirection = Direction.LEFT;
	}

	@Override
	public String toString() {
		return "X:" + x + ";Y:" + y + ";Velocity:" + velocity;
	}
}
