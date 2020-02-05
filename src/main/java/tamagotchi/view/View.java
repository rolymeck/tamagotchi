package tamagotchi.view;

import tamagotchi.controller.Controller;

public abstract class View {
  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }
}
