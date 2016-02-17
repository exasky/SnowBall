package model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.ball.SnowBall;
import model.powers.PowerID;
import model.powers.impl.OneShotPower;
import controller.GameController;
import controller.PowerController;

public class Game extends Observable {
	public int GAME_SPEED = 100; // Higher -> Slower

	private ArrayList<SnowBall> snowBallList;
	private ArrayList<OneShotPower> shotsFired;

	private Timer ballTimer;
	private Point mousePoint;
	private boolean isGameEnded;

	private int ballWidth;
	private int ballHeight;

	public Game(final JPanel gamePanel) {

		this.ballWidth = PowerController.getBallImage(PowerID.NONE).getWidth();
		this.ballHeight = PowerController.getBallImage(PowerID.NONE).getHeight();

		ballTimer = new Timer(GAME_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int toAdd = 0;
				Iterator<SnowBall> ballsIterator = snowBallList.iterator();
				Iterator<OneShotPower> oneShotIterator;

				for (OneShotPower oneShotPower : shotsFired)
					oneShotPower.y -= 5;

				while (ballsIterator.hasNext()) {
					SnowBall ball = ballsIterator.next();
					GameController.snowBallController.nextPos(ball, gamePanel.getWidth() - Game.this.ballWidth);
					if (ball.y > gamePanel.getHeight()) {
						ball.y = 0;
						GameController.addPoint();

						if (GameController.getScore() % 10 == 0)
							toAdd++;
						if (GameController.getScore() % 50 == 0)
							increaseSpeed();
					}

					// Shot treatment
					oneShotIterator = shotsFired.iterator();
					while (oneShotIterator.hasNext()) {
						OneShotPower currentShotPower = oneShotIterator.next();
						if (hasShotHitBall(currentShotPower, ball)) {
							ballsIterator.remove();
							GameController.addPoint();
						}
						if (currentShotPower.y + OneShotPower.oneShotImage.getHeight() < 1) {
							oneShotIterator.remove();
						}
					}

					// Mouse treatment
					if (isMouseOnBall(ball)) {
						if (ball.hasPower()) {
							ball.ballPower.onHoverAction();
							ballsIterator.remove();
						} else {
							GameController.killPlayer();
							if (GameController.getPlayer().getLifes() == 0) {
								stopGame();
								break;
							}
						}
					}
				}

				for (int i = 0; i < toAdd; i++)
					snowBallList.add(GameController.snowBallController.createBall());

				gamePanel.repaint();
			}

			private boolean isMouseOnBall(SnowBall ball) {
				Rectangle rectangle = new Rectangle(ball.x, ball.y, Game.this.ballWidth, Game.this.ballHeight);
				return rectangle.contains(mousePoint);
			}

			private boolean hasShotHitBall(OneShotPower power, SnowBall ball) {
				Rectangle shotImage = new Rectangle(power.from_x, power.y, OneShotPower.oneShotImage.getWidth(),
						OneShotPower.oneShotImage.getHeight());
				Rectangle ballImage = new Rectangle(ball.x, ball.y, Game.this.ballWidth, Game.this.ballHeight);
				return shotImage.intersects(ballImage);
			}
		});

		resetGame();
	}

	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
	}

	public Point getMousePosition() {
		return this.mousePoint;
	}

	public void increaseSpeed() {
		if (GAME_SPEED > 20)
			GAME_SPEED -= 5;
		this.ballTimer.setDelay(GAME_SPEED);
	}

	public void resetGame() {
		this.GAME_SPEED = 100;
		this.isGameEnded = false;
		this.mousePoint = new Point(0, 0);
		this.snowBallList = new ArrayList<SnowBall>();
		for (int i = 0; i < 10; i++) {
			this.snowBallList.add(GameController.snowBallController.createBall());
		}
		this.shotsFired = new ArrayList<OneShotPower>();
	}

	public void startGame() {
		this.ballTimer.start();
	}

	public void stopGame() {
		this.ballTimer.stop();
		this.isGameEnded = true;
		setChanged();
		notifyObservers(isGameEnded);
	}

	public List<SnowBall> getSnowBallList() {
		return this.snowBallList;
	}

	/**
	 * 
	 * @return The list of {@link OneShotPower} that have been activated.
	 */
	public List<OneShotPower> getFiredShots() {
		return this.shotsFired;
	}

	public boolean isGameEnded() {
		return this.isGameEnded;
	}
}
