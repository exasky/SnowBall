package controller;

import java.util.Random;

import launch.Main;
import model.ball.Direction;
import model.ball.SnowBall;
import model.powers.Power;
import model.powers.impl.ExtraLifePower;
import model.powers.impl.NonePower;
import model.powers.impl.OneShotPower;
import model.powers.impl.ShieldPower;

public class SnowBallController {

	private static SnowBallController instance;

	private Random myRand;

	static SnowBallController getInstance() {
		if (instance == null) {
			instance = new SnowBallController();
		}
		return instance;
	}

	private SnowBallController() {
		this.myRand = new Random();
	}

	/**
	 * Create a new snow ball, with a random power.
	 * 
	 * @return A new snow ball.
	 */
	public SnowBall createBall() {
		SnowBall snowBall = new SnowBall(myRand.nextInt(Main.MAIN_WIDTH), 0);
		snowBall.ballPower = getRandPower();
		return snowBall;
	}

	/**
	 * Update the position of the ball.
	 * 
	 * @param ball
	 *            The ball to update.
	 * @param maxWidth
	 *            This parameter is used in order to not get out of bounds.
	 */
	public void nextPos(SnowBall ball, int maxWidth) {
		int prevX = ball.x;

		ball.setVelocity((myRand.nextInt(2) == 0) ? ball.velocity + 1 : ball.velocity - 1);

		int randDirection = myRand.nextInt(100);
		int offset = (randDirection > 0) ? 1 : -1;

		switch (ball.currentDirection) {
		case LEFT:
			offset = -offset;
			break;
		case RIGHT:
			break;
		default:
			ball.currentDirection = (randDirection <= 49) ? Direction.LEFT : Direction.RIGHT;
			break;
		}

		ball.setX(ball.x + offset, maxWidth);
		ball.setY(prevX);
	}

	private Power getRandPower() {
		switch (myRand.nextInt(10)) {
		/*
		 * case 6: return new DummyPower();
		 */
		case 7:
			return new OneShotPower();
		case 8:
			return new ShieldPower();
		case 9:
			return new ExtraLifePower();
		default:
			return new NonePower();
		}
	}

}
