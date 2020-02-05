package tamagotchi.controller;

import tamagotchi.model.world.World;
import tamagotchi.view.View;

public class Controller {
  private final World world;
  View view;

  public Controller(final World world) {
    this.world = world;
  }

  public World getWorld() {
    return world;
  }

  public View getView() {
    return view;
  }
}
