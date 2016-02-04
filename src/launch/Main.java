package launch;
import ihm.GamePanel;
import ihm.PowerPanel;
import ihm.ScorePanel;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;

import controller.GameController;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final int X_BOUND=300;
	public static final int Y_BOUND=300;
	
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle bounds = getBounds();
		setBounds(bounds.x, bounds.y, X_BOUND, Y_BOUND);
		
		add(new GamePanel());
		
		ScorePanel scorePanel = new ScorePanel();
		scorePanel.resetGame();
		scorePanel.setVisible(true);
		add(scorePanel,BorderLayout.SOUTH);
		
		PowerPanel powerPanel = new PowerPanel();
		add(powerPanel, BorderLayout.NORTH);
		
		GameController.addObserver(scorePanel);
		GameController.addObserver(powerPanel);
		GameController.resetGame();
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Main();
	}
}
