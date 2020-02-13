package tamagotchi.pet;

import tamagotchi.Config;
import tamagotchi.entities.Food;
import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;

public class GreenPet extends Pet {
  private static final long serialVersionUID = 1L;

  public GreenPet() {

    final float speed = Config.ANIMATION_SPEED;

    Animation animFrontS = new AnimationStatic(speed, Assets.greenPetS_front);
    Animation animLeftS = new Animation(speed, Assets.greenPetS_left);
    Animation animRightS = new Animation(speed, Assets.greenPetS_right);
    Animation animFrontM = new AnimationStatic(speed, Assets.greenPetM_front);
    Animation animLeftM = new Animation(speed, Assets.greenPetM_left);
    Animation animRightM = new Animation(speed, Assets.greenPetM_right);
    Animation animFrontL = new AnimationStatic(speed, Assets.greenPetL_front);
    Animation animLeftL = new Animation(speed, Assets.greenPetL_left);
    Animation animRightL = new Animation(speed, Assets.greenPetL_right);

    animPack = new AnimationPack(
        new AnimationPack.Size(animFrontS, animLeftS, animRightS),
        new AnimationPack.Size(animFrontM, animLeftM, animRightM),
        new AnimationPack.Size(animFrontL, animLeftL, animRightL)
    );

    food = new Food(Assets.greenPetFood);
  }
}
