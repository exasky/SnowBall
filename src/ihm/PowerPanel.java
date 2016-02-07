package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.PowerController;
import model.powers.Power;

public class PowerPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Map<BufferedImage,JLabel> powerLabels;
	private JLabel currentLabel;
	
	public PowerPanel() {
		powerLabels = new HashMap<BufferedImage, JLabel>();
		initializePowerLabels();
	}
	
	private void initializePowerLabels(){
		JLabel currentLabel;
		powerLabels.clear();
		for (BufferedImage bufferedImage : PowerController.getPowerImages()) {
			currentLabel = new JLabel(new ImageIcon(bufferedImage));
			currentLabel.setText("0");
			add(currentLabel);
			powerLabels.put(bufferedImage, currentLabel);
		}
	}
	
	private void resetPowerLabels() {
		for (Component component : getComponents()) {
			if (component instanceof JLabel){
				((JLabel) component).setText("0");
				((JLabel) component).setBorder(BorderFactory.createEmptyBorder());
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			resetPowerLabels();
			this.currentLabel = null;
		} else if (arg instanceof Object[]){
			Object[] args = (Object[])arg;
			if (args[0] instanceof BufferedImage && args[1] instanceof Integer){
				if (currentLabel == null) {
					currentLabel = powerLabels.get(args[0]);
					currentLabel.setBorder(new LineBorder(Color.black));
				}
				powerLabels.get(args[0]).setText(((Integer)args[1]).toString());
			}
		} else if (arg instanceof Power){
			currentLabel.setBorder(BorderFactory.createEmptyBorder());
			currentLabel = powerLabels.get(((Power)arg).getBallImage());
			currentLabel.setBorder(new LineBorder(Color.black));
		}
		
	}

}
