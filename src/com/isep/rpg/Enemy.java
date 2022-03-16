
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

  //
  // Constructors
  //
  public Enemy (int type, String name, int lifePoints) {
    this.type = type; // 1 = Basic | 2 = Boss
    this.name = name;
    this.lifePoints = lifePoints;
  }

  //
  // Methods
  //
  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public void attack() {}

  public void deleteLifePoints(int number) {
    this.lifePoints = this.lifePoints - number;
  }

}
