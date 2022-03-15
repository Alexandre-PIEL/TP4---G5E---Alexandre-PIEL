
package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Hero
 */
abstract public class Hero extends Entity {

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
  public Hero (int clasHero, String nameHero, int lifePoints, int armor, int weaponsDamage) {
    super(nameHero, 1);
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
