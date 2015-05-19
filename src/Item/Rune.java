/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author nicno90
 */
public class Rune extends Item
{
    protected int strength;
    
    protected int damage;
    
    protected int speed;
    
    protected int health;
    
    public Rune()
    {
        super(1);
        strength = 1;
        damage = 1;
        speed = 1;
        health = 5;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getHealth()
    {
        return health;
    }
    
}
