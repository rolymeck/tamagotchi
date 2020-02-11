package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.AnimationStatic;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.model.entities.Food;

public class RedPet extends Pet {

  public RedPet(Handler handler) {
    super(handler);

    Animation animFrontS = new AnimationStatic(ANIMATION_SPEED, Assets.redPetS_front);
    Animation animLeftS = new Animation(ANIMATION_SPEED, Assets.redPetS_left);
    Animation animRightS = new Animation(ANIMATION_SPEED, Assets.redPetS_right);
    Animation animFrontM = new AnimationStatic(ANIMATION_SPEED, Assets.redPetM_front);
    Animation animLeftM = new Animation(ANIMATION_SPEED, Assets.redPetM_left);
    Animation animRightM = new Animation(ANIMATION_SPEED, Assets.redPetM_right);
    Animation animFrontL = new AnimationStatic(ANIMATION_SPEED, Assets.redPetL_front);
    Animation animLeftL = new Animation(ANIMATION_SPEED, Assets.redPetL_left);
    Animation animRightL = new Animation(ANIMATION_SPEED, Assets.redPetL_right);

    animPack = new AnimationPack(
        new AnimationPack.Size(animFrontS, animLeftS, animRightS),
        new AnimationPack.Size(animFrontM, animLeftM, animRightM),
        new AnimationPack.Size(animFrontL, animLeftL, animRightL)
    );

    food = new Food(Assets.redPetFood);
  }
}
