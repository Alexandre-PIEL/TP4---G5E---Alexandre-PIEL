
package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Enemy
 */
abstract public class Enemy {

  //
  // Fields
  //
  private final int type;
  private final String name;
  private int lifePoints;
  private final int weaponsDamage;

  //
  // Constructors
  //
  public Enemy (int type, String name, int lifePoints, int weaponsDamage) {
    this.type = type; // 1 = Basic | 2 = Boss
    this.name = name;
    this.lifePoints = lifePoints;
    this.weaponsDamage = weaponsDamage;
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

  public int attack() {
    return this.weaponsDamage;
  }

  public void deleteLifePoints(int number) {
    this.lifePoints = this.lifePoints - number;
  }

}
