/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Zachary Bianucci
 * can set all the ID's for our game so the game knows what is a player,
 * what is an enemy, what is a coin
 */
public enum ID {
    //These allow you to ID something as a player and ID something as an Enemy
    Player(),
    BasicEnemy(),
    FastEnemy(),
    SmartEnemy(),
    HardEnemy(),
    EnemyBoss(),
    MenuParticle(),
    ColorParticle(),
    Trail(),
    BlueBoss(),
    InvisibleBoss(),
    FinalBoss(),
    OrangeBoss();
}
