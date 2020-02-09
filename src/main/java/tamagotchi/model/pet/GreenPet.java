package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.model.food.Food;

public class GreenPet extends Pet{

  public static final Food food = Food.GREEN;

  public GreenPet(Handler handler) {
    super(handler);
    animFront = new AnimationStatic(500, Assets.greenPetS_front);
    animLeft = new Animation(500, Assets.greenPetS_left);
    animRight = new Animation(500, Assets.greenPetS_right);
  }
}
