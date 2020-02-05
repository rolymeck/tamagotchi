package tamagotchi.controller;

import tamagotchi.model.world.World;
import tamagotchi.view.ConsoleView;

public class ConsoleController extends Controller {

  private final Executor executor;

  public ConsoleController(World world) {
    super(world);
    this.view = new ConsoleView(this);
    this.executor = new Executor(world);
  }

  @Override
  public void start() {
    while (true) {
      switch (world.getStage()) {
        case BIRTH:
          createPet();
          break;
        case LIFE:
          makeCycle();
          break;
        case DEATH:
          wipe();
          break;
      }
    }
  }

  private void createPet() {

  }

  private void makeCycle() {

  }

  private void wipe() {

  }


}
