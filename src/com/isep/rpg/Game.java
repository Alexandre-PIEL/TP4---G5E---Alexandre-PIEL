
package com.isep.rpg;

import com.isep.utils.InputParser;
import java.util.ArrayList;

/**
 * Class Game
 */
public class Game {

  //
  // Fields
  //
  private ArrayList<Hero> heroes;
  private int playerTurn;
  private InputParser inputParser;

  //
  // Constructors
  //
  public Game () {
    this.heroes = new ArrayList<>();
    this.playerTurn = 0;
    this.inputParser = new InputParser();
  }
  
  //
  // Methods
  //
  public void playGame() {
    System.out.println("\nMini RPG lite 3000");
    createHeroes();
  }

  public void createHeroes() {
    int numHeroes = inputParser.askIntUser("\nChoisir le nombre de héros : ");
    System.out.println("\n 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior");
    for (int i=0; i<numHeroes; ++i){
      int clasHero = inputParser.askIntUser("\nChoisir la classe du héros N°" + (i+1) + " : ");
      while (true) {
        if (clasHero == 1 || clasHero == 2 || clasHero == 3 || clasHero == 4) {
          switch (clasHero) {
            case 1 -> this.heroes.add(new Hunter());
            case 2 -> this.heroes.add(new Healer());
            case 3 -> this.heroes.add(new Mage());
            case 4 -> this.heroes.add(new Warrior());
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasHero = inputParser.askIntUser("\nChoisir la classe du héros N°" + (i+1) + " : ");
        }
      }
    }
  }

  public void generateEnemies() {
  }

  public void generateCombat() {
  }

}
