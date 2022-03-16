
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
  private Hero fightingHero;
  private Enemy fightingEnemy;
  private int entityTurn;
  private InputParser inputParser;

  //
  // Constructors
  //
  public Game () {
    this.heroes = new ArrayList<>();
    this.enemies = new ArrayList<>();
    this.entityTurn = 0;
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
    while(true) {
      check();
      if (this.heroes.size()>0 && this.enemies.size()>0) {
        System.out.print("\nTOUR N°" + i + " - ");
        this.entityTurn = (int) (Math.random() * 2);
        generateCombat();
        System.out.print(this.fightingHero.getName());
        System.out.print(" CONTRE ");
        System.out.println(this.fightingEnemy.getName());
        if (this.entityTurn == 0) { //le héros commence
          System.out.println("\n" + this.fightingHero.getName() + " commence ... [ " + this.fightingHero.getLifePoints() + " PV ]");
          askFirstHero();
          check();
          if (this.enemies.size() == 0){
            System.out.print("\nVous avez gagné !");
            break;
          } else {
            this.fightingEnemy.attack();
            System.out.println("\n" + this.fightingEnemy.getName() + " réplique en attaquant ... [ " + this.fightingEnemy.getLifePoints() + " PV ]");
          }
        } else if (this.entityTurn == 1) { //l'énemi commence
          System.out.println("\n" + this.fightingEnemy.getName() + " commence ...[ " + this.fightingEnemy.getLifePoints() + " PV ]");
          this.fightingEnemy.attack();
          System.out.println("\n" + this.fightingEnemy.getName() + " attaque ... ");
          if (this.heroes.size() == 0){
            System.out.print("\nVous avez perdu !");
            break;
          } else {
            askSecondHero();
          }
        }
        i++;
        System.out.print("\nPress enter to continue");
        try {
          System.in.read();
        } catch (Exception e) {
        }
      } else {
        if (this.enemies.size() == 0){
          System.out.print("\nVous avez gagné !");
          break;
        } else {
          System.out.print("\nVous avez perdu !");
          break;
        }
      }
    }
  }

  public void createHeroes() {
    int numHeroes = inputParser.askIntUser("\nChoisir le nombre de héros : ");
    System.out.println("\n 1 = Chasseur | 2 = Sorcier | 3 = Mage | 4 = Guerrier");
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
      this.fightingHero = this.heroes.get(randHeroes);
      int randEnemies = (int)(Math.random() * this.enemies.size());
      this.fightingEnemy = this.enemies.get(randEnemies);
  }

  public void askFirstHero() {
    System.out.println("\n 1 = Attaquer | 2 = Boire | 3 = Manger");
    int clasFirstActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
    while (true) {
      if (clasFirstActionHero == 1 || clasFirstActionHero == 2 || clasFirstActionHero == 3) {
        switch (clasFirstActionHero) {
          case 1 -> {
            this.fightingEnemy.deleteLifePoints(this.fightingHero.attack());
            System.out.println("\n" + this.fightingHero.getName() + " attaque ... ");
          }
          case 2 -> {
            this.fightingHero.heal();
            System.out.println("\n" + this.fightingHero.getName() + " bois ... ");
          }
          case 3 -> {
            this.fightingHero.eat();
            System.out.println("\n" + this.fightingHero.getName() + " mange ... ");
          }
        }
        break;
      } else {
        System.out.println("\nERROR !");
        clasFirstActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      }
    }
  }

  public void askSecondHero() {
    System.out.println("\n 1 = Attaquer | 2 = Défendre | 3 = Boire | 4 = Manger");
    int clasSecondActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
    while (true) {
      if (clasSecondActionHero == 1 || clasSecondActionHero == 2 || clasSecondActionHero == 3 || clasSecondActionHero == 4) {
        switch (clasSecondActionHero) {
          case 1 -> {
            this.fightingEnemy.deleteLifePoints(this.fightingHero.attack());
            System.out.println("\n" + this.fightingHero.getName() + " réplique en attaquant ... ");
          }
          case 2 -> {
            this.fightingHero.defend();
            System.out.println("\n" + this.fightingHero.getName() + " réplique en se défendant ... ");
          }
          case 3 -> {
            this.fightingHero.heal();
            System.out.println("\n" + this.fightingHero.getName() + " réplique en buvant ... ");
          }
          case 4 -> {
            this.fightingHero.eat();
            System.out.println("\n" + this.fightingHero.getName() + " réplique en mangeant ... ");
          }
        }
        break;
      } else {
        System.out.println("\nERROR !");
        clasSecondActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      }
    }
  }

  public void check(){
    for (int y=0; y<this.heroes.size(); ++y){
      if (this.heroes.get(y).getLifePoints() == 0) {
        System.out.println("\n" + this.heroes.get(y).getName() + " éliminé !");
        this.heroes.remove(y);
      }
    }
    for (int z=0; z<this.enemies.size(); ++z){
      if (this.enemies.get(z).getLifePoints() == 0) {
        System.out.println("\n" + this.enemies.get(z).getName() + " éliminé !");
        this.enemies.remove(z);
      }
    }
  }

}
