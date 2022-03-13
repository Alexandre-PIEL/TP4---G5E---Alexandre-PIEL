
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
  public SpellCaster(int clasHero, int lifepoints, int armor, int weaponsDamage) {
    super(clasHero, lifepoints, armor, weaponsDamage);
    this.manaPoints = 100;
  }
  
  //
  // Methods
  //

}
