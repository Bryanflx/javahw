package finalproject.game;

import javax.swing.*;
import java.awt.*;

public class YahtzeeFrame extends JFrame {

	
	public YahtzeeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,1040);
		JPanel scorePanel = new JPanel(new GridLayout(2,1));
		JPanel lowerPanel = new JPanel(new FlowLayout());
		JPanel imagePanel = new ImagePanel();
		JPanel dicePanel = ImagePanel.createDicePanel();
		JPanel wholePanel = new JPanel(new BorderLayout());

		PlayerNamePanel playerNamePanel = new PlayerNamePanel();
		UpperSectionPanel upperSectionPanel = new UpperSectionPanel();
		LowerSectionPanel lowerSectionPanel = new LowerSectionPanel();
		scorePanel.add(upperSectionPanel.createUpperSectionPanel());
		scorePanel.add(lowerSectionPanel.createLowerSectionPanel());

		lowerPanel.add(scorePanel);
		lowerPanel.add(dicePanel);

		wholePanel.add(playerNamePanel.createPlayerNamePanel(), BorderLayout.NORTH);
		wholePanel.add(lowerPanel, BorderLayout.SOUTH);

		this.add(wholePanel);

//		this.add(upperSectionPanel.createUpperSectionPanel());
//		this.add(lowerSectionPanel.createLowerSectionPanel());
	}





	
	public static void main(String args[]) {
		YahtzeeFrame yahtzee = new YahtzeeFrame();

		yahtzee.setVisible(true);
	}
}
