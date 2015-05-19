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
public class BaoBot extends Monster
{

    public BaoBot(int x, int y) {
        super(x, y, false);
        type = 2;
        passive = false;
        starthealth = 100;
        damage = 20;
        speed = 3;
        agroRange = 250;
        chaseRange = 550;
        respawnTime = 500;
        attackSpace = 25;
    }
    
}
