
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
  private int weaponsDamage;
  private ArrayList<Potion> potions;
  private ArrayList<Food> lembas;

  //
  // Constructors
  //
  public Hero (int type, String name, int lifePoints, int armor, int weaponsDamage, ArrayList<Potion> potions, ArrayList<Food> lembas) {
    this.type = type; // 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior
    this.name = name;
    this.lifePoints = lifePoints;
    this.armor = armor;
    this.weaponsDamage = weaponsDamage;
    this.potions = potions;
    this.lembas = lembas;
  }
  
  //
  // Methods
  //
  public String getName() {
    return this.name;
  }

  public int getLifePoints() {
    return this.lifePoints;
  }

  public int getArmor() {
    return this.armor;
  }

  public int getWeaponsDamage() {
    return this.weaponsDamage;
  }

  public ArrayList<Potion> getPotions() {
    return this.potions;
  }

  public ArrayList<Food> getLembas() {
    return this.lembas;
  }

  public int attack(){
    return this.weaponsDamage;
  }

  public void eat(){
    this.lifePoints = this.lifePoints + 5;
    int index = this.lembas.size() - 1;
    this.lembas.remove(index);
  }

  public void heal(){
    this.lifePoints = this.lifePoints + 10;
    int index = this.potions.size() - 1;
    this.potions.remove(index);
  }

  public void upgradeArmor(){
    this.armor = this.armor + 20;
  }

  public void upgradeWeapon(){
    this.weaponsDamage = this.weaponsDamage + 10;
  }

  public void addRessources(){
    this.potions.add(new Potion());
    this.lembas.add(new Food());
  }

  public void deleteLifePoints(int number) {
    if (this.armor != 0 && this.armor<number){
      this.lifePoints = this.lifePoints - (number - this.armor);
    } else if (this.armor == 0){
      this.lifePoints = this.lifePoints - number;
    }
  }

}
