/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author nicno90
 */
public class Weapon extends Item
{
    protected int damage;
    protected int typeAttack;
    protected int level;
    protected Rune[] runes;
    
    public Weapon()
    {
        super(1);
        damage = 10;
        typeAttack = 0;
        level = 1;
        name = "Weapon";
        runes = new Rune[1];
        
    }

    public int getLevel()
    {
        return level;
    }
    
    public int getDamage()
    {
        int sum = 0;
        for (Rune r : runes)
        {
            sum += r.damage;
        }
        return damage;
    }

    public int getTypeAttack()
    {
        return typeAttack;
    }
    
    public boolean addRune(Rune r)
    {
        for (Rune rune : runes)
        {
            if (rune == null)
            {
                rune = r;
                return true;
            }
        }
        return false;
    }
    
}
