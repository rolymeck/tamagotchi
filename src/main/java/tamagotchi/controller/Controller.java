package tamagotchi.controller;

import tamagotchi.model.world.World;
import tamagotchi.view.View;

public abstract class Controller {
  protected final World world;
  protected View view;

  public Controller(final World world) {
    this.world = world;
  }

  public World getWorld() {
    return world;
  }

  public View getView() {
    return view;
  }

  public abstract void start();
}
