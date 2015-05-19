/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brightdeathserver;

/**
 *
 * @author nicno90
 */
public class Pet extends Monster
{
    private Player owner;
    public Pet(Player p) {
        super(p.getXpos() - 100, p.getYpos() - 100, false);
        speed = 3;
        agroRange = 100;
        attackSpace = 150;
        isAgro = owner.getArrayPos();
    }
    
}
