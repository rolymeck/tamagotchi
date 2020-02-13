package tamagotchi.pet;

import tamagotchi.Config;
import tamagotchi.entities.Food;
import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;

public class RedPet extends Pet {
  private static final long serialVersionUID = 1L;

  public RedPet() {

    final float speed = Config.ANIMATION_SPEED;

    Animation animFrontS = new AnimationStatic(speed, Assets.redPetS_front);
    Animation animLeftS = new Animation(speed, Assets.redPetS_left);
    Animation animRightS = new Animation(speed, Assets.redPetS_right);
    Animation animFrontM = new AnimationStatic(speed, Assets.redPetM_front);
    Animation animLeftM = new Animation(speed, Assets.redPetM_left);
    Animation animRightM = new Animation(speed, Assets.redPetM_right);
    Animation animFrontL = new AnimationStatic(speed, Assets.redPetL_front);
    Animation animLeftL = new Animation(speed, Assets.redPetL_left);
    Animation animRightL = new Animation(speed, Assets.redPetL_right);

    animPack = new AnimationPack(
        new AnimationPack.Size(animFrontS, animLeftS, animRightS),
        new AnimationPack.Size(animFrontM, animLeftM, animRightM),
        new AnimationPack.Size(animFrontL, animLeftL, animRightL)
    );

    food = new Food(Assets.redPetFood);
  }
}
