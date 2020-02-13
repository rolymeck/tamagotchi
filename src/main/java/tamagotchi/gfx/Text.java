package tamagotchi.gfx;

import java.awt.*;

public class Text {

  public static void drawString(Graphics g, String text, int xPos, int yPos, Color c, Font font) {
    g.setColor(c);
    g.setFont(font);
    g.drawString(text, xPos, yPos);
  }

}
