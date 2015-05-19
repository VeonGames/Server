package Monsters;

import brightdeathserver.Monster;

public class Goblin extends Monster
{

    public Goblin(int x, int y) {
        super(x, y, false);
        type = 3;
        passive = false;
        starthealth = 30;
        damage = 10;
        speed = 4;
        agroRange = 200;
        chaseRange = 500;
        respawnTime = 500;
        attackSpace = 50;
    }
    
}
