package ihm.help;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.powers.PowerID;
import controller.PowerController;

public class HelpDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private static Map<PowerID, String> powerHelp;

	public HelpDialog(JFrame mainFrame) {
		setModal(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(mainFrame);
		setTitle("SnowBall Help");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				HelpDialog.this.dispose();
			}
		});

		generateHelp();
		generateLabels();

		pack();
		setVisible(true);
	}

	private void generateHelp() {
		if (HelpDialog.powerHelp == null) {
			HelpDialog.powerHelp = new HashMap<PowerID, String>();
			HelpDialog.powerHelp.put(PowerID.NONE, "<html>Default snow ball.<br/>If you hit one, you'll lose one life.</html>");
			HelpDialog.powerHelp.put(PowerID.ExtraLife, "Add an extra life.");
			HelpDialog.powerHelp.put(PowerID.Shield,
					"<html>Gives you invincibility for 3 seconds.<br/>The cursor's appearance will be modified.</html>");
			HelpDialog.powerHelp.put(PowerID.OneShot,
					"<html>Launch a bullet that annihilate other snow balls.<br/>Even the powered ones.</html>");
		}
	}

	private void generateLabels() {
		int panelWidth = 350;
		int panelHeight = 275;
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(panelWidth, panelHeight));

		// Create info game
		JLabel helpLabel = new JLabel("<html>Hello you.<br/>"
				+ "The principle of this game is to keep the cursor in the game area without letting it out.<br/>"
				+ "To help, some balls contain power !</html>");
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setPreferredSize(new Dimension(310, 75));

		panel.add(helpLabel);

		// Create power help
		JLabel powerLabel;
		for (PowerID powerId : PowerID.values()) {
			powerLabel = new JLabel(powerHelp.get(powerId), new ImageIcon(PowerController.getBallImage(powerId)), 0);
			powerLabel.setPreferredSize(new Dimension(panelWidth - 10, 40));
			powerLabel.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(powerLabel);
		}

		add(panel);
	}
}
