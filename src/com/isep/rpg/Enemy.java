
package com.isep.rpg;

import java.util.ArrayList;

/**
 * Class Enemy
 */
abstract public class Enemy extends Entity {

  //
  // Fields
  //
  private int clasEnemy;
  private int lifePoints;

  //
  // Constructors
  //
  public Enemy (int clasEnemy, String nameEnemy, int lifePoints) {
    super(nameEnemy, 2);
    this.clasEnemy = clasEnemy;
    this.lifePoints = lifePoints;
  }

  //
  // Methods
  //
  public void attack(){

  }

  public void defend(){

  }

}
