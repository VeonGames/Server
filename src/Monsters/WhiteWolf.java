/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Monsters;

import brightdeathserver.Monster;

/**
 *
 * @author nicno90
 */
public class WhiteWolf extends Monster
{

    public WhiteWolf(int x, int y) {
        super(x, y, true);
        type = 1;
        monsterX = x;
        monsterY = y;
        startX = monsterX;
        startY = monsterY;
        starthealth = 50;
        damage = 15;
        speed = 2;
        agroRange = 200;
        chaseRange = 500;
        respawnTime = 400;
        attackSpace = 45;
    }
    
}
