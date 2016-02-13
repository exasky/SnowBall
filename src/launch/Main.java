package launch;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.GameController;
import ihm.game.GamePanel;
import ihm.game.PowerPanel;
import ihm.game.ScorePanel;
import ihm.menu.MainMenuPanel;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int X_BOUND = 350;
	public static final int Y_BOUND = 300;

	private GamePanel gamePanel;
	private PowerPanel powerPanel;
	private ScorePanel scorePanel;

	private MainMenuPanel mainMenuPanel;

	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle bounds = getBounds();
		setBounds(bounds.x, bounds.y, X_BOUND, Y_BOUND);

		initiateMenu();
		initiateGamePanels();

		mainMenuPanel.setBounds(0, 0, X_BOUND, Y_BOUND);
		mainMenuPanel.setVisible(true);
		setVisible(true);
	}

	private void initiateMenu() {
		ActionListener startGameAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.gamePanel.setVisible(true);
				Main.this.scorePanel.setVisible(true);
				Main.this.powerPanel.setVisible(true);
				Main.this.mainMenuPanel.setVisible(false);
			}
		};
		this.mainMenuPanel = new MainMenuPanel(startGameAction, this);
		add(this.mainMenuPanel);
	}

	private void initiateGamePanels() {
		this.powerPanel = new PowerPanel();
		add(this.powerPanel, BorderLayout.NORTH);

		this.gamePanel = new GamePanel();
		add(this.gamePanel, BorderLayout.CENTER);

		ActionListener goToMenuAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.gamePanel.setVisible(false);
				Main.this.scorePanel.setVisible(false);
				Main.this.powerPanel.setVisible(false);
				Main.this.mainMenuPanel.setVisible(true);
			}
		};
		this.scorePanel = new ScorePanel(goToMenuAction);
		add(this.scorePanel, BorderLayout.SOUTH);

		GameController.addObserver(this.powerPanel);
		GameController.addObserver(this.scorePanel);
		GameController.resetGame();

		this.gamePanel.setVisible(false);
		this.scorePanel.setVisible(false);
		this.powerPanel.setVisible(false);
	}

	public static void main(String[] args) {
		new Main();
	}
}
