package ihm.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launch.Main;
import controller.GameController;


public class ScorePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JLabel lifeLabel;
	private JLabel scoreLabel;
	private JButton replayButton;
	private JButton goBackToMenuButton;

	private Dimension stopGameSize;
	private Dimension runningGameSize;

	public ScorePanel(ActionListener goToMenuAction){
		this.lifeLabel = new JLabel();
		this.scoreLabel = new JLabel();
				
		this.replayButton = new JButton("Replay");
		this.replayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.resetGame();
				resetGame();
			}
		});
		
		this.goBackToMenuButton = new JButton("Menu");
		this.goBackToMenuButton.addActionListener(goToMenuAction);
		
		stopGameSize = new Dimension(Main.MAIN_WIDTH, 35);
		runningGameSize = new Dimension(Main.MAIN_WIDTH, 25);

		add(lifeLabel);
		add(scoreLabel);
		add(replayButton);
		add(goBackToMenuButton);

		resetGame();
	}

	public void stopGame(){
		setText(this.scoreLabel.getText().replaceAll("Score", "Final score"));
		this.replayButton.setVisible(true);
		this.goBackToMenuButton.setVisible(true);
		setPreferredSize(this.stopGameSize);
	}
	
	public void resetGame(){
		setText("Score: 0");
		this.lifeLabel.setText("Lifes: 1");
		this.replayButton.setVisible(false);
		this.goBackToMenuButton.setVisible(false);
		setPreferredSize(this.runningGameSize);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Integer)
			setText("Score: " + arg);
		if (arg instanceof Boolean)
			if (((Boolean) arg).booleanValue())
				stopGame();
		if (arg instanceof String)
			this.lifeLabel.setText((String) arg);
	}
	
	private void setText(String text){
		this.scoreLabel.setText(text);
	}
}
