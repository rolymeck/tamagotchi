package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.model.food.Food;

public class BluePet extends Pet {

  public static final Food food = Food.BLUE;

  public BluePet(Handler handler) {
    super(handler);
    animFront = new AnimationStatic(500, Assets.bluePetS_front);
    animLeft = new Animation(500, Assets.bluePetS_left);
    animRight = new Animation(500, Assets.bluePetS_right);
  }
}
