
package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Hero
 */
abstract public class Hero {

  //
  // Fields
  //
  private int clasHero;
  private int lifePoints;
  private int armor;
  private int weaponsDamage;
  private ArrayList<Potion> potions;
  private ArrayList<Food> lembas;

  //
  // Constructors
  //
  public Hero (int clasHero, int lifePoints, int armor, int weaponsDamage) {
    this.clasHero = clasHero;
    this.lifePoints = lifePoints;
    this.armor = armor;
    this.weaponsDamage = weaponsDamage;
    this.potions = new ArrayList<>();
    this.lembas = new ArrayList<>();
  }
  
  //
  // Methods
  //
  public void attack(){

  }

  public void defend(){

  }

}
