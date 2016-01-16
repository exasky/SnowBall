import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public static BufferedImage snowBallImg;
	public static ArrayList<SnowBall> snowBallList;
	public static int BASE_GAME_SPEED = 100; // Higher -> Slower
		
	ScorePanel scorePanel;
	
	Timer ballTimer;
	Point mousePoint;
	
	boolean isGameEnded;
	
	
	public MainPanel(){
		InitAttributes();
		
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		ballTimer = new Timer(BASE_GAME_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int toAdd = 0;
				for (Iterator<SnowBall> balls = snowBallList.iterator(); balls.hasNext(); ) {
					// SnowBall treatment
					SnowBall ball = balls.next();
					ball.nextPos();
					if (ball.y > MainPanel.this.getHeight() - 25) {
						ball.y = 0;
						scorePanel.addPoint();
						
						if (scorePanel.score % 10 == 0)	toAdd++;
						if (scorePanel.score % 50 == 0)	increaseLevel();
					}
					
					// Mouse treatment
					if (isMouseOnBall(new Point(ball.x, ball.y))){
						stopGame();
						break;
					}
				}
				
				for (int i = 0; i < toAdd; i++)
					snowBallList.add(new SnowBall(MainPanel.this));
				
				repaint();
			}
			
			private boolean isMouseOnBall(Point ballPoint){
				Rectangle rectangle = new Rectangle(ballPoint, new Dimension(snowBallImg.getWidth(), snowBallImg.getHeight()));
				return rectangle.contains(mousePoint);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if ( ! isGameEnded && ! ballTimer.isRunning() ){
					ballTimer.start();
				}
				mousePoint = e.getPoint();
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				stopGame();
			}
		});
		
		add(scorePanel,BorderLayout.SOUTH);
		scorePanel.setVisible(true);
		
		setVisible(true);
	}
	
	private void InitAttributes(){
		mousePoint = new Point(0,0);
		isGameEnded = false;
		scorePanel = new ScorePanel();
		
		scorePanel.addReplayAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetAttributes();
				scorePanel.startGame();
				ballTimer.start();
			}
		});
		
		try {
			snowBallImg = ImageIO.read(new File(Main.class.getClassLoader().getResource("Snowball.png").toURI()));
		} catch (Exception e1) {e1.printStackTrace();}
		
		snowBallList = new ArrayList<SnowBall>();
		for (int i = 0; i < 10; i++)
			snowBallList.add(new SnowBall(this));
	}
	
	private void ResetAttributes(){
		mousePoint = new Point(0,0);
		isGameEnded = false;
		snowBallList.clear();
		for (int i = 0; i < 10; i++){
			snowBallList.add(new SnowBall(this));
		}
		scorePanel.startGame();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (SnowBall ball : snowBallList){
			g.drawImage(snowBallImg, ball.x, ball.y, this);
		}
	}
	
	private void increaseLevel(){
		if (this.ballTimer.getDelay() > 20)
			this.ballTimer.setDelay(this.ballTimer.getDelay() - 5);
		else
			System.out.println("Level Max");
	}
	
	private void stopGame(){
		ballTimer.stop();
		isGameEnded = true;
		scorePanel.stopGame();
	}
}
