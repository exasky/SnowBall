package ihm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.powers.Power;
import controller.PowerController;

public class PowerPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Map<BufferedImage,JLabel> powerLabels;
	private JLabel currentLabel;
	
	public PowerPanel() {
		powerLabels = new HashMap<BufferedImage, JLabel>();
		JLabel currentLabel;
		Collection<BufferedImage> powerImages = PowerController.getPowerImages();
		for (BufferedImage bufferedImage : powerImages) {
			currentLabel = new JLabel(new ImageIcon(bufferedImage));
			currentLabel.setText("0");
			add(currentLabel);
			powerLabels.put(bufferedImage, currentLabel);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Object[]){
			Object[] args = (Object[])arg;
			if (args[0] instanceof BufferedImage && args[1] instanceof Integer){
				if (currentLabel == null) {
					currentLabel = powerLabels.get(args[0]);
					currentLabel.setBorder(new LineBorder(Color.black));
				}
				powerLabels.get(args[0]).setText(((Integer)args[1]).toString());
			}
		}
		if (arg instanceof Power){
			currentLabel.setBorder(BorderFactory.createEmptyBorder());
			currentLabel = powerLabels.get(((Power)arg).getBallImage());
			currentLabel.setBorder(new LineBorder(Color.black));
		}
	}

}
