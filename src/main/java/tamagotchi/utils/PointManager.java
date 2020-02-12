package tamagotchi.utils;

import tamagotchi.game.Game;

import java.util.Random;

public class PointManager {

  private static Random random = new Random();

  public static int getRandomX() {
    return random.nextInt(Game.getCurrentGame().getWidth() - 42) + 10;
  }

  public static int getRandomY() {
    return random.nextInt(190) - 160;
  }

}
