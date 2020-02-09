package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.model.food.Food;

public class RedPet extends Pet{

  public static final Food food = Food.RED;

  public RedPet(Handler handler) {
    super(handler);
    animFront = new AnimationStatic(500, Assets.redPetS_front);
    animLeft = new Animation(500, Assets.redPetS_left);
    animRight = new Animation(500, Assets.redPetS_right);
  }
}
