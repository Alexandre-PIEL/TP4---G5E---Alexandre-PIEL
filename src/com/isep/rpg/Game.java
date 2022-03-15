
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
  private ArrayList<Enemy> enemies;
  private ArrayList<Entity> fightingEntities;
  private int entityTurn;
  private InputParser inputParser;

  //
  // Constructors
  //
  public Game () {
    this.heroes = new ArrayList<>();
    this.enemies = new ArrayList<>();
    this.entityTurn = (int)(Math.random() * 2);
    this.fightingEntities = new ArrayList<>();
    this.inputParser = new InputParser();
  }
  
  //
  // Methods
  //
  public void playGame() {
    System.out.println("\nMini RPG lite 3000");
    createHeroes();
    generateEnemies();
    int i = 1;
    while(this.heroes.size()>0) {
      System.out.print("\nTOUR N°" + i + " - ");
      this.fightingEntities.clear();
      generateCombat();
      System.out.print(this.fightingEntities.get(0).getNameEntity());
      System.out.print(" CONTRE ");
      System.out.println(this.fightingEntities.get(1).getNameEntity());
      System.out.println(this.fightingEntities.get(this.entityTurn).getNameEntity() + " commence ...");
      i++;
      System.out.print("Press enter to continue");
      try{System.in.read();}
      catch(Exception e){}
    }

  }

  public void createHeroes() {
    int numHeroes = inputParser.askIntUser("\nChoisir le nombre de héros : ");
    System.out.println("\n 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior");
    for (int i=0; i<numHeroes; ++i){
      int clasHero = inputParser.askIntUser("\nChoisir la classe du héros N°" + (i+1) + " : ");
      while (true) {
        if (clasHero == 1 || clasHero == 2 || clasHero == 3 || clasHero == 4) {
          String nameHero = inputParser.askStringUser("\nChoisir le nom du héros N°" + (i+1) + " : ");
          switch (clasHero) {
            case 1 -> this.heroes.add(new Hunter(nameHero));
            case 2 -> this.heroes.add(new Healer(nameHero));
            case 3 -> this.heroes.add(new Mage(nameHero));
            case 4 -> this.heroes.add(new Warrior(nameHero));
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
    int max = 10;
    int min = 1;
    int range = max - min + 1;
    for (int i = 0; i < this.heroes.size(); i++) {
      int rand = (int)(Math.random() * range) + min;
      if (rand == 8 || rand == 9 || rand == 10){
        String nameBoss = "Boss N°" + Integer.toString(i);
        this.enemies.add(new Boss(nameBoss));
      } else {
        String nameBasic = "Basic N°" + Integer.toString(i);
        this.enemies.add(new Basic(nameBasic));
      }
    }
  }

  public void generateCombat() {
    int randHeroes = (int)(Math.random() * this.heroes.size());
    this.fightingEntities.add(this.heroes.get(randHeroes));
    int randEnemies = (int)(Math.random() * this.enemies.size());
    this.fightingEntities.add(this.enemies.get(randEnemies));
  }

}
