package controller;

import ihm.PowerPanel;
import ihm.ScorePanel;

import java.awt.Point;
import java.util.List;
import java.util.Observer;

import javax.swing.JPanel;

import model.Game;
import model.Player;
import model.ball.SnowBall;

public class GameController {

	private static Player player;
	private static Game game;
	private static JPanel mainPanel;
	
	public static SnowBallController snowBallController;
	
	static {
		player = new Player();
		snowBallController = SnowBallController.getInstance();
	}
	
	public static void createGame(JPanel mainPanel){
		game = new Game(mainPanel);
		GameController.mainPanel = mainPanel;
	}
	
	public static void setMousePosition(Point mousePoint){
		game.setMousePoint(mousePoint);
	}
	
	public static List<SnowBall> getSnowBallList(){
		return game.getSnowBallList();
	}
	
	public static void addPoint(){
		player.addPoint();
	}
	
	public static int getScore(){
		return player.getScore();
	}
	
	public static void stopGame(){
		game.stopGame();
	}

	public static void resetGame() {
		player.resetPlayer();
		game.resetGame();
	}
	
	/**
	 * Remove one life from the player
	 */
	public static void killPlayer(){
		player.kill();
	}	
	
	public static Player getPlayer(){
		return player;
	}
	
	public static JPanel getMainPanel(){
		return mainPanel;
	}

	public static void addObserver(Observer observer) {
		if (observer instanceof ScorePanel){
			player.addObserver(observer);
			game.addObserver(observer);
		}
		if (observer instanceof PowerPanel)
			player.getPowerList().addObserver(observer);
	}
}
