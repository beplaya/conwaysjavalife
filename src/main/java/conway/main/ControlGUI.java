package conway.main;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlGUI extends JFrame {

    private final JLabel speedLabel;

    public ControlGUI() {
        setSize(400, 400);
        setVisible(true);
        JButton toggleRun = new JButton("Toggle Run");
        JButton decreaseSpeedBtn = new JButton("-");
        JButton increaseSpeedBtn = new JButton("+");
        JButton resetBtn = new JButton("RESET");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(toggleRun);
        add(decreaseSpeedBtn);
        add(increaseSpeedBtn);
        add(resetBtn);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(1000, 10);

        speedLabel = new JLabel("XXX");
        add(speedLabel);
        updateSpeedLabel();
        toggleRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.run = !Main.run;
            }
        });

        decreaseSpeedBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.decreaseSpeed();
                updateSpeedLabel();

            }
        });

        increaseSpeedBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.increaseSpeed();
                updateSpeedLabel();

            }
        });
        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.resetWorld();
            }
        });
    }

    private void updateSpeedLabel() {
        speedLabel.setText(Math.round(100f * (1 - ((Main.PERIOD / (float) Main.MAX_PERIOD)))) + "%");
    }
}
