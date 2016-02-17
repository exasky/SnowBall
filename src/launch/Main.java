package launch;

import ihm.game.MainGamePanel;
import ihm.menu.MainMenuPanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int MAIN_WIDTH = 350;
	public static final int MAIN_HEIGHT = 300;

	private MainMenuPanel mainMenuPanel;
	private MainGamePanel mainGamePanel;

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle bounds = getBounds();
		setBounds(bounds.x, bounds.y, MAIN_WIDTH, MAIN_HEIGHT);

		initiateMenu();
		initiateGamePanels();

		setVisible(true);
	}

	private void initiateMenu() {
		ActionListener startGameAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.mainGamePanel.setVisible(true);
				Main.this.mainMenuPanel.setVisible(false);
			}
		};
		this.mainMenuPanel = new MainMenuPanel(startGameAction, this);
		this.mainMenuPanel.setVisible(true);
		add(this.mainMenuPanel);
	}

	private void initiateGamePanels() {
		ActionListener goToMenuAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.this.mainGamePanel.setVisible(false);
				Main.this.mainMenuPanel.setVisible(true);
			}
		};
		this.mainGamePanel = new MainGamePanel(goToMenuAction);
		this.mainGamePanel.setVisible(false);
		add(this.mainGamePanel);
	}

	public static void main(String[] args) {
		new Main();
	}
}
