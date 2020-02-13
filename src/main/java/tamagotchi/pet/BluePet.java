package tamagotchi.pet;

import tamagotchi.Config;
import tamagotchi.entities.Food;
import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;

public class BluePet extends Pet {
  private static final long serialVersionUID = 1L;

  public BluePet() {

    final float speed = Config.ANIMATION_SPEED;

    Animation animFrontS = new AnimationStatic(speed, Assets.bluePetS_front);
    Animation animLeftS = new Animation(speed, Assets.bluePetS_left);
    Animation animRightS = new Animation(speed, Assets.bluePetS_right);
    Animation animFrontM = new AnimationStatic(speed, Assets.bluePetM_front);
    Animation animLeftM = new Animation(speed, Assets.bluePetM_left);
    Animation animRightM = new Animation(speed, Assets.bluePetM_right);
    Animation animFrontL = new AnimationStatic(speed, Assets.bluePetL_front);
    Animation animLeftL = new Animation(speed, Assets.bluePetL_left);
    Animation animRightL = new Animation(speed, Assets.bluePetL_right);

    animPack = new AnimationPack(
        new AnimationPack.Size(animFrontS, animLeftS, animRightS),
        new AnimationPack.Size(animFrontM, animLeftM, animRightM),
        new AnimationPack.Size(animFrontL, animLeftL, animRightL)
    );

    food = new Food(Assets.bluePetFood);
  }
}
