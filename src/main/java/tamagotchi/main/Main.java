package tamagotchi.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tamagotchi.model.world.World;

public class Main {

  private static final Logger log = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    log.debug("Main start");
    World world = new World(1);

  }
}
