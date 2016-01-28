package ihm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;


public class ScorePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JLabel lifeLabel;
	private JLabel scoreLabel;
	private JButton replayButton;
	
	public ScorePanel(){
		this.scoreLabel = new JLabel();
		this.lifeLabel = new JLabel();
		this.replayButton = new JButton("Replay?");
		
		this.replayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.resetGame();
				resetGame();
			}
		});
		
		resetGame();
		
		add(lifeLabel);
		add(scoreLabel);
		add(replayButton);
	}
	
	public void addReplayAction(ActionListener al){
		this.replayButton.addActionListener(al);
	}
	
	public void stopGame(){
		setText(this.scoreLabel.getText().replaceAll("Score", "Final score"));
		this.replayButton.setVisible(true);
	}
	
	public void resetGame(){
		setText("Score: 0");
		this.lifeLabel.setText("Lifes: 1");
		this.replayButton.setVisible(false);
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
