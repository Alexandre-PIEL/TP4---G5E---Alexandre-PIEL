
package com.isep.rpg;

/**
 * Class SpellCaster
 */
abstract public class SpellCaster extends Hero {

  //
  // Fields
  //
  private int manaPoints;


  //
  // Constructors
  //
  public SpellCaster(int type, String name, int lifepoints, int armor, int weaponsDamage) {
    super(type, name, lifepoints, armor, weaponsDamage);
    this.manaPoints = 100;
  }
  
  //
  // Methods
  //

}
