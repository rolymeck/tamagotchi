# Requirements
- JDK version 9 or higher
- Maven

# Install
1. `git clone https://github.com/rolymeck/tamagotchi.git`
2. `cd tamagotchi`
3. `mvn compile assembly:single`
***

# Usage
`java -jar game.jar`
***

# Description
The application is based on the world famous game Tamagotchi.  
Interaction with the game occurs with the mouse.  
The game has the ability to feed a pet and clean up the world, as well as play.  
The pet has the parameters of hunger, waste and happiness.  
If the parameters of hunger and waste are at maximum, happiness will begin to decrease, after which the pet will die.  
The game state is maintained even after leaving the game.  
***  

# Features of the implementation.  
 The game was created using Java Swing.  
 Game parameters are in the Config.java file.  
 It is not recommended to change game parameters except _WORLD_PERIOD_ and _NEW_GAME_WAIT_SEC_.  
 _WORLD_PERIOD_ is a parameter that is responsible for the rate of change of a pet’s characteristics.  
 _NEW_GAME_WAIT_SEC_ is a parameter that sets the period of time during which the user cannot create a new pet.  
 The game has _FPS_ set to 60.  
 This means that every second the main methods tick and render are called 60 times.  
 _WORLD_PERIOD_ represents the number of calls to the tick method, after which the pet's characteristics will be updated.  
 The values ​​of hunger, waste and happiness are represented by percentages from 0 to 100.  
 Also, in the Config.java file there are constants _AGE_STEP_, _HUNGER_STEP_, _WASTE_STEP_, _HAPPINESS_STEP_ which determine the magnitude of the change in the parameters of the pet in percent when updating its characteristics.  
 By changing these parameters together, you can adjust the necessary speed of change in the game world.  
 ***  
 
 # Description of the application logic, packages and classes.  
 A game is represented by an object of class **Game**.  
 The specified object stores a static link to itself in order to access it from **anywhere** in the code.  
 The execution of the code of the object of the Game class takes place in a **separate** thread, in which all the necessary resources (mainly graphic elements - sprites) are **initialized** into RAM, after which the object goes into an **endless** loop acting as an **FPS synchronizer**.  
 The code written in the indicated cycle allows the application to work **evenly** regardless of the technical characteristics of the hardware of the user's computer.  
 **60** times per second, the tick and render methods are called.  
 The tick method is responsible for **updating the state** of the application, and the render method for **rendering the image** to the user, respectively.  
 **Only one** state can be **active** in a game at any time, a complete list of which is in a package called **"states"**.  
 All concrete state objects are inherited from the abstract class State, which has a static variable that stores a link to the current state, and static methods for accessing it, as well as for changing the state.  
 By analogy with the Game class, **each** State class has tick and render methods, which are responsible for the logic of the game in a specific state and display of the image to the user.  
 The **"controller"** package contains a class for processing the user's mouse.  
 The **"display"** package is responsible for creating the game window and uses the Swing library for this.  
 The **"entities"** package is responsible for processing information about food and pet waste located in the game world.  
 The **"game"** package is responsible for processing information about the main class of the Game, as well as the game world.  
 The **"gfx"** package is responsible for loading, storing and processing the images and fonts used by the game.  
 The **"pet"** package is responsible for processing pet information.  
 The **"play"** package is responsible for processing information in the game play mode.  
 The **"ui"** package is responsible for displaying all user interface elements to the user.  
 The **"utils"** package contains various utility classes with static methods.  
