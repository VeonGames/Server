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
public class Bill extends Monster
{

    public Bill(int x, int y) {
        super(x, y, false);
        type = 100;
        starthealth = 1000;
        damage = 100;
        speed = 9;
        agroRange = 200;
        chaseRange = 1000;
        respawnTime = 500;
        attackSpace = 10;
    }
    
}
