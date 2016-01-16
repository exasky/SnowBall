import java.awt.Rectangle;

import javax.swing.JFrame;


public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final int X_BOUND=300;
	public static final int Y_BOUND=300;
	
	public Main(){
		add(new MainPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Rectangle bounds = getBounds();
		setBounds(bounds.x, bounds.y, X_BOUND, Y_BOUND);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Main();
	}
}
