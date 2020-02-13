package tamagotchi.display;

import tamagotchi.Config;

import javax.swing.*;
import java.awt.*;

public class Display {

  private JFrame frame;
  private Canvas canvas;

  public Display() {
    createDisplay();
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public JFrame getFrame() {
    return frame;
  }

  private void createDisplay() {
    frame = new JFrame(Config.DISPLAY_TITLE);
    frame.setSize(Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    canvas = new Canvas();
    final Dimension d = new Dimension(Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
    canvas.setPreferredSize(d);
    canvas.setMaximumSize(d);
    canvas.setMinimumSize(d);
    canvas.setFocusable(false);

    frame.add(canvas);
    frame.pack();
  }

}