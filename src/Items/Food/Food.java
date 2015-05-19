/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Items.Food;

import Items.Item.Item;

/**
 *
 * @author nicno90
 */
public class Food extends Item
{
    protected int health;
    
    public Food(int amount)
    {
        super(amount);
    }
    
            
    
    //int hunder(); should we

    public int getHealth()
    {
        return health;
    }
}
