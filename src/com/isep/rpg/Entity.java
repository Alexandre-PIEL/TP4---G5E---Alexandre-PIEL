package com.isep.rpg;

/**
 * Class Enity
 */
abstract public class Entity {
    //
    // Fields
    //
    private final String nameEntity;
    private int clasEntity;

    //
    // Constructors
    //
    public Entity (String nameEntity, int clasEntity) {
        this.nameEntity = nameEntity;
        this.clasEntity = clasEntity; // 1 = Hero & 2 = Enemy
    }

    //
    // Methods
    //
    public String getNameEntity() {
        return nameEntity;
    }

    public int getClasEntity() {
        return clasEntity;
    }

}
