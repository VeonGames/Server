package Monsters;

import brightdeathserver.Monster;

/**
 *
 * @author nicno90
 */
public class Ghastiel extends Monster
{

    public Ghastiel(int x, int y) {
        super(x, y, true);
        type = 4;
        monsterX = x;
        monsterY = y;
        startX = monsterX;
        startY = monsterY;
        starthealth = 120;
        damage = 45;
        speed = 1;
        agroRange = 250;
        chaseRange = 700;
        respawnTime = 750;
        attackSpace = 100;
    }
    
}