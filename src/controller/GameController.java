package controller;

import ihm.game.GamePanel;
import ihm.game.PowerPanel;
import ihm.game.ScorePanel;

import java.awt.Cursor;
import java.awt.Point;
import java.util.List;
import java.util.Observer;

import model.Game;
import model.Player;
import model.ball.SnowBall;
import model.powers.impl.OneShotPower;

public class GameController {

	// Player model. Only one
	private static Player player;
	// Game model. Only one
	private static Game game;
	// Game ihm. Only one
	private static GamePanel gamePanel;
	
	public static SnowBallController snowBallController;
	
	static {
		player = new Player();
		snowBallController = SnowBallController.getInstance();
	}
	
	public static void createGame(GamePanel gamePanel) {
		game = new Game(gamePanel);
		GameController.gamePanel = gamePanel;
	}
	
	public static void setMousePosition(Point mousePoint){
		game.setMousePoint(mousePoint);
	}
	
	public static Point getMousePosition() {
		return game.getMousePosition();
	}

	public static List<SnowBall> getSnowBallList(){
		return game.getSnowBallList();
	}
	
	public static void shotFire(OneShotPower oneShotPower) {
		oneShotPower.from_x = game.getMousePosition().x - (OneShotPower.oneShotImage.getWidth() / 2);
		oneShotPower.y = gamePanel.getHeight();
		game.getFiredShots().add(oneShotPower);
	}

	public static List<OneShotPower> getFiredShots() {
		return game.getFiredShots();
	}

	public static void addPoint(){
		player.addPoint();
	}
	
	public static int getScore(){
		return player.getScore();
	}
	
	public static void startGame() {
		game.startGame();
	}

	public static void stopGame(){
		game.stopGame();
	}

	public static void resetGame() {
		player.resetPlayer();
		game.resetGame();
		PowerController.resetPower();
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
	
	public static void setGameCursor(Cursor cursor) {
		gamePanel.setCursor(cursor);
	}

	public static void addObserver(Observer observer) {
		if (observer instanceof ScorePanel){
			player.addObserver(observer);
			game.addObserver(observer);
		}
		if (observer instanceof PowerPanel)
			player.getPowerList().addObserver(observer);
	}

	public static boolean isGameEnded() {
		return game.isGameEnded();
	}
}