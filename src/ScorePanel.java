import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScorePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	int score;
	JLabel scoreLabel;
	JButton replayButton;
	
	public ScorePanel(){		
		this.score = 0;
		this.scoreLabel = new JLabel("Score: " + this.score);
		this.replayButton = new JButton("Do you want to replay?");
		
		this.scoreLabel.setVisible(true);
		this.replayButton.setVisible(false);
		
		add(scoreLabel);
		add(replayButton);
	}
	
	public void addPoint(){
		addPoints(1);
	}
	
	public void addPoints(int points){
		this.score += points;
		this.scoreLabel.setText("Score: " + this.score);
	}
	
	public void addReplayAction(ActionListener al){
		this.replayButton.addActionListener(al);
	}
	
	public void stopGame(){
		this.scoreLabel.setText("Final score: " + score);
		this.replayButton.setVisible(true);
	}
	
	public void startGame(){
		this.score = 0;
		this.scoreLabel.setText("Score: " + this.score);
		this.replayButton.setVisible(false);
	}
}
