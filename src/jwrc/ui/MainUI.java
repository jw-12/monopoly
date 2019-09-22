package jwrc.ui;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    public MainUI() {
        super("Monopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);

        FlowLayout flow = new FlowLayout();
        setLayout(flow);

        GameSetupUI gameSetupUI = new GameSetupUI();
        add(gameSetupUI.getPlayerNumbersPanel());

        this.setVisible(true);
    }
}
