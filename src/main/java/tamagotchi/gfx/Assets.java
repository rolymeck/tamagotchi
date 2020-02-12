package tamagotchi.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

  private static final int WIDTH_UNIT = 32, HEIGHT_UNIT = 32, BTN_WIDTH = 96, BTN_HEIGHT = 48;

  public static Font font34, font20;

  public static BufferedImage mainScreen, selectionScreen, deathScreen;
  public static BufferedImage[] greenPetSelectionTile, redPetSelectionTile, bluePetSelectionTile;
  public static BufferedImage egg, grave, greenPetFood, redPetFood, bluePetFood, poop;

  public static BufferedImage bg1, bg2, bg3, bg4, bg5, scoreBar, grass, pipes;

  public static BufferedImage[] btn_feed, btn_clean, btn_play, btn_new, btn_return;

  public static BufferedImage[] happinessBar, hungerBar, wasteBar;

  public static BufferedImage greenPetS_front, greenPetM_front, greenPetL_front;
  public static BufferedImage redPetS_front, redPetM_front, redPetL_front;
  public static BufferedImage bluePetS_front, bluePetM_front, bluePetL_front;

  public static BufferedImage[] greenPetS_left, greenPetS_right;
  public static BufferedImage[] greenPetM_left, greenPetM_right;
  public static BufferedImage[] greenPetL_left, greenPetL_right;
  public static BufferedImage[] redPetS_left, redPetS_right;
  public static BufferedImage[] redPetM_left, redPetM_right;
  public static BufferedImage[] redPetL_left, redPetL_right;
  public static BufferedImage[] bluePetS_left, bluePetS_right;
  public static BufferedImage[] bluePetM_left, bluePetM_right;
  public static BufferedImage[] bluePetL_left, bluePetL_right;

  public static void init() {
    font34 = FontLoader.loadFont("res/fonts/KenneyMini.ttf", 34);
    font20 = FontLoader.loadFont("res/fonts/KenneyMini.ttf", 20);

    SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sheet.png"));
    SpriteSheet pets = new SpriteSheet(ImageLoader.loadImage("/textures/Pets.png"));
    SpriteSheet selTiles = new SpriteSheet(ImageLoader.loadImage("/textures/SelectionTiles.png"));
    SpriteSheet happinessBarSheet = new SpriteSheet(ImageLoader.loadImage("/textures/HappinessBar.png"));
    SpriteSheet hungerBarSheet = new SpriteSheet(ImageLoader.loadImage("/textures/HungerBar.png"));
    SpriteSheet wasteBarSheet = new SpriteSheet(ImageLoader.loadImage("/textures/WasteBar.png"));
    SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/Buttons.png"));

    bg1 = ImageLoader.loadImage("/textures/BG1.png");
    bg2 = ImageLoader.loadImage("/textures/BG2.png");
    bg3 = ImageLoader.loadImage("/textures/BG3.png");
    bg4 = ImageLoader.loadImage("/textures/BG4.png");
    bg5 = ImageLoader.loadImage("/textures/BG5.png");
    scoreBar = ImageLoader.loadImage("/textures/ScoreBar.png");
    grass = ImageLoader.loadImage("/textures/Grass.png");
    pipes = ImageLoader.loadImage("/textures/Pipes.png");

    mainScreen = ImageLoader.loadImage("/textures/MainScreen.png");
    selectionScreen = ImageLoader.loadImage("/textures/SelectionScreen.png");
    deathScreen = ImageLoader.loadImage("/textures/DeathScreen.png");

    greenPetSelectionTile = new BufferedImage[2];
    redPetSelectionTile = new BufferedImage[2];
    bluePetSelectionTile = new BufferedImage[2];


    greenPetSelectionTile[0] = selTiles.crop(0, 0, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);
    greenPetSelectionTile[1] = selTiles.crop(0, HEIGHT_UNIT * 6, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);
    redPetSelectionTile[0] = selTiles.crop(WIDTH_UNIT * 4, 0, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);
    redPetSelectionTile[1] = selTiles.crop(WIDTH_UNIT * 4, HEIGHT_UNIT * 6, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);
    bluePetSelectionTile[0] = selTiles.crop(WIDTH_UNIT * 8, 0, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);
    bluePetSelectionTile[1] = selTiles.crop(WIDTH_UNIT * 8, HEIGHT_UNIT * 6, WIDTH_UNIT * 4, HEIGHT_UNIT * 6);

    egg = sheet.crop(0, 0, WIDTH_UNIT, HEIGHT_UNIT);
    grave = sheet.crop(WIDTH_UNIT, 0, WIDTH_UNIT, HEIGHT_UNIT);
    redPetFood = sheet.crop(WIDTH_UNIT * 2, 0, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetFood = sheet.crop(WIDTH_UNIT * 3, 0, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetFood = sheet.crop(WIDTH_UNIT * 4, 0, WIDTH_UNIT, HEIGHT_UNIT);
    poop = sheet.crop(WIDTH_UNIT * 5, 0, WIDTH_UNIT, HEIGHT_UNIT);

    btn_feed = new BufferedImage[2];
    btn_clean = new BufferedImage[2];
    btn_play = new BufferedImage[2];
    btn_new = new BufferedImage[2];
    btn_return = new BufferedImage[2];

    btn_feed[0] = buttons.crop(0, 0, BTN_WIDTH, BTN_HEIGHT);
    btn_feed[1] = buttons.crop(BTN_WIDTH, 0, BTN_WIDTH, BTN_HEIGHT);
    btn_clean[0] = buttons.crop(0, BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
    btn_clean[1] = buttons.crop(BTN_WIDTH, BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
    btn_play[0] = buttons.crop(0, BTN_HEIGHT * 2, BTN_WIDTH, BTN_HEIGHT);
    btn_play[1] = buttons.crop(BTN_WIDTH, BTN_HEIGHT * 2, BTN_WIDTH, BTN_HEIGHT);
    btn_new[0] = buttons.crop(0, BTN_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT);
    btn_new[1] = buttons.crop(BTN_WIDTH, BTN_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT);
    btn_return[0] = buttons.crop(0, BTN_HEIGHT * 4, BTN_WIDTH, BTN_HEIGHT);
    btn_return[1] = buttons.crop(BTN_WIDTH, BTN_HEIGHT * 4, BTN_WIDTH, BTN_HEIGHT);

    happinessBar = new BufferedImage[11];
    hungerBar = new BufferedImage[11];
    wasteBar = new BufferedImage[11];

    for (int i = 0; i < 11; i++) {
      happinessBar[i] = happinessBarSheet.crop(i * 10, 0, 10, 22);
      hungerBar[i] = hungerBarSheet.crop(i * 10, 0, 10, 22);
      wasteBar[i] = wasteBarSheet.crop(i * 10, 0, 10, 22);
    }

    greenPetS_front = pets.crop(0, 0, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetM_front = pets.crop(WIDTH_UNIT, 0, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetL_front = pets.crop(WIDTH_UNIT * 2, 0, WIDTH_UNIT, HEIGHT_UNIT);
    redPetS_front = pets.crop(WIDTH_UNIT * 3, 0, WIDTH_UNIT, HEIGHT_UNIT);
    redPetM_front = pets.crop(WIDTH_UNIT * 4, 0, WIDTH_UNIT, HEIGHT_UNIT);
    redPetL_front = pets.crop(WIDTH_UNIT * 5, 0, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetS_front = pets.crop(WIDTH_UNIT * 6, 0, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetM_front = pets.crop(WIDTH_UNIT * 7, 0, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetL_front = pets.crop(WIDTH_UNIT * 8, 0, WIDTH_UNIT, HEIGHT_UNIT);

    greenPetS_left = new BufferedImage[2];
    greenPetS_right = new BufferedImage[2];
    greenPetM_left = new BufferedImage[2];
    greenPetM_right = new BufferedImage[2];
    greenPetL_left = new BufferedImage[2];
    greenPetL_right = new BufferedImage[2];

    greenPetS_left[0] = pets.crop(0, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetS_left[1] = pets.crop(0, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetS_right[0] = pets.crop(0, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetS_right[1] = pets.crop(0, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetM_left[0] = pets.crop(WIDTH_UNIT, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetM_left[1] = pets.crop(WIDTH_UNIT, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetM_right[0] = pets.crop(WIDTH_UNIT, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetM_right[1] = pets.crop(WIDTH_UNIT, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetL_left[0] = pets.crop(WIDTH_UNIT * 2, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetL_left[1] = pets.crop(WIDTH_UNIT * 2, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetL_right[0] = pets.crop(WIDTH_UNIT * 2, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    greenPetL_right[1] = pets.crop(WIDTH_UNIT * 2, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);

    redPetS_left = new BufferedImage[2];
    redPetS_right = new BufferedImage[2];
    redPetM_left = new BufferedImage[2];
    redPetM_right = new BufferedImage[2];
    redPetL_left = new BufferedImage[2];
    redPetL_right = new BufferedImage[2];

    redPetS_left[0] = pets.crop(WIDTH_UNIT * 3, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    redPetS_left[1] = pets.crop(WIDTH_UNIT * 3, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    redPetS_right[0] = pets.crop(WIDTH_UNIT * 3, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    redPetS_right[1] = pets.crop(WIDTH_UNIT * 3, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    redPetM_left[0] = pets.crop(WIDTH_UNIT * 4, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    redPetM_left[1] = pets.crop(WIDTH_UNIT * 4, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    redPetM_right[0] = pets.crop(WIDTH_UNIT * 4, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    redPetM_right[1] = pets.crop(WIDTH_UNIT * 4, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    redPetL_left[0] = pets.crop(WIDTH_UNIT * 5, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    redPetL_left[1] = pets.crop(WIDTH_UNIT * 5, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    redPetL_right[0] = pets.crop(WIDTH_UNIT * 5, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    redPetL_right[1] = pets.crop(WIDTH_UNIT * 5, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);

    bluePetS_left = new BufferedImage[2];
    bluePetS_right = new BufferedImage[2];
    bluePetM_left = new BufferedImage[2];
    bluePetM_right = new BufferedImage[2];
    bluePetL_left = new BufferedImage[2];
    bluePetL_right = new BufferedImage[2];

    bluePetS_left[0] = pets.crop(WIDTH_UNIT * 6, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetS_left[1] = pets.crop(WIDTH_UNIT * 6, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetS_right[0] = pets.crop(WIDTH_UNIT * 6, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetS_right[1] = pets.crop(WIDTH_UNIT * 6, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetM_left[0] = pets.crop(WIDTH_UNIT * 7, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetM_left[1] = pets.crop(WIDTH_UNIT * 7, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetM_right[0] = pets.crop(WIDTH_UNIT * 7, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetM_right[1] = pets.crop(WIDTH_UNIT * 7, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetL_left[0] = pets.crop(WIDTH_UNIT * 8, HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetL_left[1] = pets.crop(WIDTH_UNIT * 8, HEIGHT_UNIT * 2, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetL_right[0] = pets.crop(WIDTH_UNIT * 8, HEIGHT_UNIT * 3, WIDTH_UNIT, HEIGHT_UNIT);
    bluePetL_right[1] = pets.crop(WIDTH_UNIT * 8, HEIGHT_UNIT * 4, WIDTH_UNIT, HEIGHT_UNIT);
  }
}
