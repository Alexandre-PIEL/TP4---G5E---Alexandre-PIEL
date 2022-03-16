
package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Hero
 */
abstract public class Hero {

  //
  // Fields
  //
  private final int type;
  private final String name;
  private int lifePoints;
  private int armor;
  private final int weaponsDamage;
  private ArrayList<Potion> potions;
  private ArrayList<Food> lembas;

  //
  // Constructors
  //
  public Hero (int type, String name, int lifePoints, int armor, int weaponsDamage) {
    this.type = type; // 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior
    this.name = name;
    this.lifePoints = lifePoints;
    this.armor = armor;
    this.weaponsDamage = weaponsDamage;
    this.potions = new ArrayList<>();
    this.lembas = new ArrayList<>();
  }
  
  //
  // Methods
  //
  public int getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public int getArmor() {
    return armor;
  }

  public ArrayList<Potion> getPotions() {
    return potions;
  }

  public ArrayList<Food> getLembas() {
    return lembas;
  }

  public int attack(){
    return weaponsDamage;
  }

  public void defend(){}

  public void eat(){}

  public void heal(){}

}
