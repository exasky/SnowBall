package ihm.game;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import launch.Main;
import controller.GameController;

public class MainGamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private GamePanel gamePanel;
	private PowerPanel powerPanel;
	private ScorePanel scorePanel;

	public MainGamePanel(ActionListener goToMenuAction) {
		setLayout(new BorderLayout());

		this.powerPanel = new PowerPanel();
		add(this.powerPanel, BorderLayout.NORTH);

		this.gamePanel = new GamePanel();
		add(this.gamePanel, BorderLayout.CENTER);

		this.scorePanel = new ScorePanel(goToMenuAction);
		add(this.scorePanel, BorderLayout.SOUTH);

		GameController.addObserver(this.powerPanel);
		GameController.addObserver(this.scorePanel);
		GameController.resetGame();

		setBounds(0, 0, Main.MAIN_WIDTH, Main.MAIN_HEIGHT);
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		this.gamePanel.setVisible(aFlag);
		this.scorePanel.setVisible(aFlag);
		this.powerPanel.setVisible(aFlag);
	}
}
