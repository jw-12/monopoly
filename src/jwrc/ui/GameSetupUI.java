package jwrc.ui;

import javax.swing.*;

public class GameSetupUI {

    private JPanel playerNumbersPanel;
    private JPanel playerSelectionPanel;
    private int numPlayersSelected;

    public GameSetupUI() {
        numPlayersSelected = 0;
        generatePlayerNumbersPanel();
        generatePlayerSelectionPanel();

    }

    private void generatePlayerNumbersPanel() {
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

    private void generatePlayerSelectionPanel() {
        JPanel playerSelectionPanel = new JPanel();
        BoxLayout vertical = new BoxLayout(playerSelectionPanel, BoxLayout.Y_AXIS);
        playerSelectionPanel.setLayout(vertical);

        JTextField playerOne = new JTextField("Player One");
        JTextField playerTwo = new JTextField("Player Two");
        JTextField playerThree = new JTextField("Player Three");
        JTextField playerFour = new JTextField("Player Four");
        JTextField playerFive = new JTextField("Player Five");
        JTextField playerSix = new JTextField("Player Six");

        playerSelectionPanel.add(playerOne);
        playerSelectionPanel.add(playerTwo);
        playerSelectionPanel.add(playerThree);
        playerSelectionPanel.add(playerFour);
        playerSelectionPanel.add(playerFive);
        playerSelectionPanel.add(playerSix);

        this.playerSelectionPanel = playerSelectionPanel;

    }

    public JPanel getPlayerNumbersPanel() {
        return this.playerNumbersPanel;
    }

    public JPanel getPlayerSelectionPanel() {
        return this.playerSelectionPanel;
    }
}
