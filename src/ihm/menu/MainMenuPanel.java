package ihm.menu;

import ihm.help.HelpDialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import launch.Main;

public class MainMenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel welcomeLabel;
	private JButton helpButton;
	private JButton startGameButton;

	public MainMenuPanel(ActionListener startGameAction, final JFrame frame) {
		setLayout(new GridBagLayout());

		this.welcomeLabel = new JLabel("Welcome on SnowBall !");

		this.startGameButton = new JButton("Start Game");
		this.startGameButton.addActionListener(startGameAction);

		this.helpButton = new JButton("Help");
		this.helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpDialog(frame);
			}
		});

		addElements();

		setBounds(0, 0, Main.MAIN_WIDTH, Main.MAIN_HEIGHT);
		setVisible(true);
	}

	private void addElements() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 50, 10); // make this component tall
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		add(this.welcomeLabel, c);

		c.insets = new Insets(10, 0, 0, 0);
		c.gridy = 1;
		c.gridwidth = 1;
		add(this.helpButton, c);

		c.gridx = 1;
		add(this.startGameButton, c);
	}
}
