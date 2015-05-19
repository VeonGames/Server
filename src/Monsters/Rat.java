package Monsters;

import brightdeathserver.Monster;

/**
 *
 * @author nicno90
 */
public class Rat extends Monster
{

    public Rat(int x, int y) {
        super(x, y, false);
        type = 2;
        passive = false;
        starthealth = 10;
        damage = 20;
        speed = 4;
        agroRange = 50;
        chaseRange = 200;
        respawnTime = 5;
        attackSpace = 25;
    }
    
}