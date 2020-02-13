package tamagotchi.utils;

import tamagotchi.Config;

import java.util.Random;

public class PointManager {

  private static final Random random = new Random();

  public static int getRandomX() {
    return random.nextInt(Config.DISPLAY_WIDTH - 42) + 10;
  }

  public static int getRandomY() {
    return random.nextInt(190) - 160;
  }

}
