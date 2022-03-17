
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
        System.out.print("\nCOMBAT N°" + i + " - ");
        this.entityTurn = (int) (Math.random() * 2);
        generateCombat();
        System.out.print(this.fightingHero.getName());
        System.out.print(" CONTRE ");
        System.out.println(this.fightingEnemy.getName());
        if (this.entityTurn == 0) { //le héros commence
          System.out.println("\n" + this.fightingHero.getName() + " commence ... ");
          while (this.fightingHero.getLifePoints() > 0 && this.fightingEnemy.getLifePoints() > 0) {
            askActionHero();
            if (this.fightingHero.getLifePoints() <= 0) {
              System.out.println("\nVous avez perdu le combat N°" + i + " !");
              break;
            } else if (this.fightingEnemy.getLifePoints() <= 0) {
              System.out.println("\nVous avez gagné le combat N°" + i + " !");
              askOptionHero();
              break;
            } else {
              this.fightingHero.deleteLifePoints(this.fightingEnemy.attack());
              System.out.println("\n" + this.fightingEnemy.getName() + " attaque ... [ " + this.fightingEnemy.getLifePoints() + " PV ]");
              if (this.fightingHero.getLifePoints() <= 0) {
                System.out.println("\nVous avez perdu le combat N°" + i + " !");
                break;
              } else if (this.fightingEnemy.getLifePoints() <= 0) {
                System.out.println("\nVous avez gagné le combat N°" + i + " !");
                askOptionHero();
                break;
              }
              check();
            }
          }
          check();
          if (this.heroes.size() <= 0){
            System.out.print("\nVous avez perdu la partie !");
            break;
          } else if(this.enemies.size() <= 0){
            System.out.print("\nVous avez gagné la partie !");
            break;
          }
        } else if (this.entityTurn == 1) { //l'énemi commence
          System.out.println("\n" + this.fightingEnemy.getName() + " commence ... ");
          while (this.fightingHero.getLifePoints() > 0 && this.fightingEnemy.getLifePoints() > 0) {
            this.fightingHero.deleteLifePoints(this.fightingEnemy.attack());
            System.out.println("\n" + this.fightingEnemy.getName() + " attaque ... [ " + this.fightingEnemy.getLifePoints() + " PV ]");
            if (this.fightingHero.getLifePoints() <= 0) {
              System.out.println("\nVous avez perdu le combat N°" + i + " !");
              break;
            } else if (this.fightingEnemy.getLifePoints() <= 0) {
              System.out.println("\nVous avez gagné le combat N°" + i + " !");
              if (this.enemies.size() > 0){
                askOptionHero();
              }
              break;
            } else {
              askActionHero();
              if (this.fightingHero.getLifePoints() <= 0) {
                System.out.println("\nVous avez perdu le combat N°" + i + " !");
                break;
              } else if (this.fightingEnemy.getLifePoints() <= 0) {
                System.out.println("\nVous avez gagné le combat N°" + i + " !");
                if (this.enemies.size() > 0){
                  askOptionHero();
                }
                break;
              }
              check();
            }
          }
          check();
          if (this.heroes.size() <= 0){
            System.out.print("\nVous avez perdu la partie !");
            break;
          } else if(this.enemies.size() <= 0){
            System.out.print("\nVous avez gagné la partie !");
            break;
          }
        }
        i++;
        System.out.print("\nPress enter to continue");
        try {
          System.in.read();
        } catch (Exception e) {
        }
      } else {
        if (this.enemies.size() <= 0){
          System.out.print("\nVous avez gagné la partie !");
          break;
        } else {
          System.out.print("\nVous avez perdu la partie !");
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
            case 2 -> {
              ArrayList<Potion> potions = new ArrayList<Potion>();
              ArrayList<Food> lembas = new ArrayList<Food>();
              this.heroes.add(new Healer(nameHero));
            }
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

  public void askActionHero() {
    System.out.println("\nPoints de vie : " + this.fightingHero.getLifePoints());
    System.out.println("Points d'armure : " + this.fightingHero.getArmor());
    System.out.println("Points de dégâts : " + this.fightingHero.getWeaponsDamage());
    System.out.println("Potions : " + this.fightingHero.getPotions().size());
    System.out.println("Lembas : " + this.fightingHero.getLembas().size());
    if (this.fightingHero.getPotions().size() > 0 && this.fightingHero.getLembas().size() > 0){
      System.out.println("\n 1 = Attaquer | 2 = Boire | 3 = Manger");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2 || clasActionHero == 3) {
          switch (clasActionHero) {
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
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
        }
      }
    } else if (this.fightingHero.getPotions().size() <= 0 && this.fightingHero.getLembas().size() > 0){
      System.out.println("\n 1 = Attaquer | 2 = Manger");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2) {
          switch (clasActionHero) {
            case 1 -> {
              this.fightingEnemy.deleteLifePoints(this.fightingHero.attack());
              System.out.println("\n" + this.fightingHero.getName() + " attaque ... ");
            }
            case 2 -> {
              this.fightingHero.eat();
              System.out.println("\n" + this.fightingHero.getName() + " bois ... ");
            }
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
        }
      }
    } else if (this.fightingHero.getPotions().size() > 0 && this.fightingHero.getLembas().size() <= 0){
      System.out.println("\n 1 = Attaquer | 2 = Boire");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1 || clasActionHero == 2) {
          switch (clasActionHero) {
            case 1 -> {
              this.fightingEnemy.deleteLifePoints(this.fightingHero.attack());
              System.out.println("\n" + this.fightingHero.getName() + " attaque ... ");
            }
            case 2 -> {
              this.fightingHero.heal();
              System.out.println("\n" + this.fightingHero.getName() + " bois ... ");
            }
          }
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
        }
      }
    } else {
      System.out.println("\n 1 = Attaquer");
      int clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
      while (true) {
        if (clasActionHero == 1) {
          this.fightingEnemy.deleteLifePoints(this.fightingHero.attack());
          System.out.println("\n" + this.fightingHero.getName() + " attaque ... ");
          break;
        } else {
          System.out.println("\nERROR !");
          clasActionHero = inputParser.askIntUser("\nChoisir l'action de " + this.fightingHero.getName() + " : ");
        }
      }
    }
  }

  public void askOptionHero(){
    System.out.println("\nPoints de vie : " + this.fightingHero.getLifePoints());
    System.out.println("Points d'armure : " + this.fightingHero.getArmor());
    System.out.println("Points de dégâts : " + this.fightingHero.getWeaponsDamage());
    System.out.println("Potions : " + this.fightingHero.getPotions().size());
    System.out.println("Lembas : " + this.fightingHero.getLembas().size());
    System.out.print("\n1 = Augmenter son armure");
    System.out.print("\n2 = Augmenter les dégâts de son arme");
    System.out.println("\n3 = Augmenter le nombre de potions ou de nourriture");
    int clasOptionHero = inputParser.askIntUser("\nChoisir l'option de " + this.fightingHero.getName() + " : ");
    while (true) {
      if (clasOptionHero == 1 || clasOptionHero == 2 || clasOptionHero == 3) {
        switch (clasOptionHero) {
          case 1 -> {
            this.fightingHero.upgradeArmor();
            System.out.println("\n" + this.fightingHero.getName() + " augmente son armure ... ");
          }
          case 2 -> {
            this.fightingHero.upgradeWeapon();
            System.out.println("\n" + this.fightingHero.getName() + " augmente les dégâts de son arme ... ");
          }
          case 3 -> {
            this.fightingHero.addRessources();
            System.out.println("\n" + this.fightingHero.getName() + " augmente le nombre de potions et de nourriture ... ");
          }
        }
        break;
      } else {
        System.out.println("\nERROR !");
        clasOptionHero = inputParser.askIntUser("\nChoisir l'option de " + this.fightingHero.getName() + " : ");
      }
    }
  }

  public void check(){
    for (int y=0; y<this.heroes.size(); ++y){
      if (this.heroes.get(y).getLifePoints() <= 0) {
        this.heroes.remove(y);
      }
    }
    for (int z=0; z<this.enemies.size(); ++z){
      if (this.enemies.get(z).getLifePoints() <= 0) {
        this.enemies.remove(z);
      }
    }
  }

}
