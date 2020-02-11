package tamagotchi.utils;

import tamagotchi.handler.Game;

import java.util.Random;

public class PointManager {

  private static Random random = new Random();

  public static int getRandomX() {
    return random.nextInt(Game.getCurrentGame().getWidth() - 42) + 10;
  }

}
