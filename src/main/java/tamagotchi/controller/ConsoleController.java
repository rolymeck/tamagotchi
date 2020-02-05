package tamagotchi.controller;

import tamagotchi.model.world.World;
import tamagotchi.view.ConsoleView;

public class ConsoleController extends Controller {

  public ConsoleController(World world) {
    super(world);
    this.view = new ConsoleView(this);
  }

}
