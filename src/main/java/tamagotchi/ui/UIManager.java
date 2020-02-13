package tamagotchi.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

  private final ArrayList<UIObject> objects;

  public UIManager() {
    objects = new ArrayList<>();
  }

  public void tick() {
    for (UIObject o : objects)
      o.tick();
  }

  public void render(Graphics g) {
    for (UIObject o : objects)
      o.render(g);
  }

  public void onMouseMove(MouseEvent e) {
    for (UIObject o : objects)
      o.onMouseMove(e);
  }

  public void onMouseRelease(MouseEvent e) {
    for (UIObject o : objects)
      o.onMouseRelease(e);
  }

  public void addObject(UIObject o) {
    objects.add(o);
  }

  public void removeObject(UIObject o) {
    objects.remove(o);
  }
}
