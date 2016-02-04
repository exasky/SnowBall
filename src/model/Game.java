package model;

import java.awt.Color;
import java.awt.Dimension;
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
import controller.GameController;
import controller.PowerController;

public class Game extends Observable {
	public int GAME_SPEED = 100; // Higher -> Slower
	
	private ArrayList<SnowBall> snowBallList;
	
	private Timer ballTimer;
	private Point mousePoint;
	private boolean isGameEnded;
	
	private int ballWidth;
	private int ballHeight;
	
	public Game(final JPanel gamePanel){
		
		this.ballWidth = PowerController.getBallImage(Color.white).getWidth();
		this.ballHeight = PowerController.getBallImage(Color.white).getHeight();
		
		ballTimer = new Timer(GAME_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int toAdd = 0;
				Iterator<SnowBall> ballsIterator = snowBallList.iterator();
				while (ballsIterator.hasNext()) {
					SnowBall ball = ballsIterator.next();
					GameController.snowBallController.nextPos(ball, gamePanel.getWidth() - Game.this.ballWidth);
					if (ball.y > gamePanel.getHeight()) {
						ball.y = 0;
						GameController.addPoint();
						
						if (GameController.getScore() % 10 == 0)	toAdd++;
						if (GameController.getScore() % 50 == 0)	increaseSpeed();
					}
					
					// Mouse treatment
					if (isMouseOnBall(new Point(ball.x, ball.y))){
						if (ball.hasPower()) {
							ball.ballPower.onHoverAction();
							ballsIterator.remove();
						} else {
							GameController.killPlayer();
							if (GameController.getPlayer().getLifes() == 0){
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
			
			private boolean isMouseOnBall(Point ballPoint){
				Rectangle rectangle = new Rectangle(ballPoint, new Dimension(Game.this.ballWidth, Game.this.ballHeight));
				return rectangle.contains(mousePoint);
			}
		});
		
		resetGame();
	}
	
	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
		if ( ! (isGameEnded || ballTimer.isRunning()) ){
			ballTimer.start();
		}
	}

	public void increaseSpeed(){
		if (GAME_SPEED > 20)
			GAME_SPEED -= 5;
		this.ballTimer.setDelay(GAME_SPEED);
	}

	public void resetGame() {
		GAME_SPEED = 100;
		isGameEnded = false;
		mousePoint = new Point(0,0);
		snowBallList = new ArrayList<SnowBall>();
		for (int i = 0; i < 10; i++){
			snowBallList.add(GameController.snowBallController.createBall());
		}		
	}
	
	public void stopGame(){
		this.ballTimer.stop();
		isGameEnded = true;
		setChanged();
		notifyObservers(isGameEnded);
	}

	public List<SnowBall> getSnowBallList() {
		return snowBallList;
	}
}
