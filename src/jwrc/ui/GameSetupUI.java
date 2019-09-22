package jwrc.ui;

import javax.swing.*;

public class GameSetupUI {

    private JPanel playerNumbersPanel;

    public GameSetupUI() {
        JPanel playerNumbersPanel = new JPanel();
        BoxLayout horizontal = new BoxLayout(playerNumbersPanel, BoxLayout.X_AXIS);
        playerNumbersPanel.setLayout(horizontal);

        JButton twoPlayer = new JButton("2 Players");
        JButton threePlayer = new JButton("3 Players");
        JButton fourPlayer = new JButton("4 Players");
        JButton fivePlayer = new JButton("5 Players");
        JButton sixPlayer = new JButton("6 Players");

        playerNumbersPanel.add(twoPlayer);
        playerNumbersPanel.add(threePlayer);
        playerNumbersPanel.add(fourPlayer);
        playerNumbersPanel.add(fivePlayer);
        playerNumbersPanel.add(sixPlayer);

        this.playerNumbersPanel = playerNumbersPanel;
    }

    public JPanel getPlayerNumbersPanel() {
        return this.playerNumbersPanel;
    }
}
