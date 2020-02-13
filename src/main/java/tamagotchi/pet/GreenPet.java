package tamagotchi.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;
import tamagotchi.entities.Food;

public class GreenPet extends Pet {
  private static final long serialVersionUID = 1L;

  public GreenPet() {

    Animation animFrontS = new AnimationStatic(ANIMATION_SPEED, Assets.greenPetS_front);
    Animation animLeftS = new Animation(ANIMATION_SPEED, Assets.greenPetS_left);
    Animation animRightS = new Animation(ANIMATION_SPEED, Assets.greenPetS_right);
    Animation animFrontM = new AnimationStatic(ANIMATION_SPEED, Assets.greenPetM_front);
    Animation animLeftM = new Animation(ANIMATION_SPEED, Assets.greenPetM_left);
    Animation animRightM = new Animation(ANIMATION_SPEED, Assets.greenPetM_right);
    Animation animFrontL = new AnimationStatic(ANIMATION_SPEED, Assets.greenPetL_front);
    Animation animLeftL = new Animation(ANIMATION_SPEED, Assets.greenPetL_left);
    Animation animRightL = new Animation(ANIMATION_SPEED, Assets.greenPetL_right);

    animPack = new AnimationPack(
        new AnimationPack.Size(animFrontS, animLeftS, animRightS),
        new AnimationPack.Size(animFrontM, animLeftM, animRightM),
        new AnimationPack.Size(animFrontL, animLeftL, animRightL)
    );

    food = new Food(Assets.greenPetFood);
  }
}
