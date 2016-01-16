import java.util.Random;

import javax.swing.JPanel;

public class SnowBall extends JPanel{
	private static final long serialVersionUID = 1L;
	
	int x;
	int y;
	
	// 'speed'
	int velocity;
	
	// -1 to the left; +1 to the right
	int currentDirection;
	
	Random rand;
	JPanel parentPanel;
	
	public SnowBall(JPanel parentPanel) {
		rand = new Random();
		y = 0;
		x = rand.nextInt(Main.X_BOUND);
		currentDirection=0;
		velocity = 1;
		
		this.parentPanel = parentPanel;
	}
	
	public void nextPos() {
		int prevX = x;
		
		velocity = (rand.nextInt(2) == 0) ? velocity + 1 : velocity - 1;
		if (velocity < 1) {
			velocity = 1;
		} else if (velocity > 10) {
			velocity = 10;
		}

		int randDirection = rand.nextInt(100);
		switch (currentDirection){
			case -1:
				if (randDirection < 99) {
					x = x - 1;
				} else {
					x = x + 1;
					currentDirection = 1;
				}
				break;
			case 1:
				if (randDirection > 0) {
					x = x + 1;
				} else {
					x = x - 1;
					currentDirection = -1;
				}
				break;
			default:
				currentDirection = (randDirection <= 49) ? -1 : 1;
				break;
		}
		
		int panelWidth = this.parentPanel.getSize().width;
		if (x < 1) {
			x = 1;
		} else if (panelWidth != 0 && x > panelWidth) {
			x = panelWidth;
		}
		
		y = (int)Math.round(Math.sqrt(Math.pow(velocity, 2) + Math.pow(x - prevX, 2))) + y;
	}
	
	@Override
	public String toString() {
		return "X:" + x + ";Y:" + y + ";Velocity:" + velocity ;
	}
}
