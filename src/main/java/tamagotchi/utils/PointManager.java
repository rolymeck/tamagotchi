package tamagotchi.utils;

import tamagotchi.handler.Handler;

import java.util.Random;

public class PointManager {

  private static Random random = new Random();

  public static int getRandomX(Handler handler) {
    return random.nextInt(handler.getWidth() - 42) + 10;
  }

}
