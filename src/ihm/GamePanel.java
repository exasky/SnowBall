package ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import model.ball.SnowBall;
import model.powers.Power;
import controller.GameController;
import controller.PowerController;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public GamePanel(){
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				GameController.setMousePosition(e.getPoint());
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				GameController.stopGame();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Power currentPower = GameController.getPlayer().getPowerList().popCurrentPower();
				if (currentPower != null) {
					currentPower.onClickAction();
				}
				
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				PowerController.switchPower(e.getWheelRotation());
			}
		});
		
		GameController.createGame(this);
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (SnowBall ball : GameController.getSnowBallList()){		
			g.drawImage(ball.ballPower.getBallImage(), ball.x, ball.y, this);
		}
	}
}
