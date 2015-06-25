/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import Items.Weapon.*;


/**
 *
 * @author PJ
 */

public class Player {

    
    private int xPos;
    private int yPos;
    private boolean[] attack = new boolean[4];
    private boolean attackingNow = false;
    private int attackType;
    private int team;
    private int totalHealth = 100;
    private int health;
    private int xp;
    private int level;
    private int totalXP;
    private int arrayPos;
    private int oX, oY;
    private int strength;
    private boolean leveled;
    private Weapon arms;
    private int[] technique = {1,1,1,1,1,1,1};
    private Weapon current = new Knife();
    
    
    public Player(int x, int y)
    {
        arms = new Knife();
        level = 1;
        health = 100;
        oX = x;
        oY = y;
        xPos = x;
        yPos = y;
        attackType = 0;
        strength = 20;
        for (int i = 0; i < 4; i++)
        {
            attack[i] = false;
        }
        team = 1;
        totalXP = 100;
        leveled = false;
        this.current = new Knife();
    }
    
    public double getClassTechnique(int i)
    {
        return technique[i];
    }
    

    public int getArrayPos()
    {
        return arrayPos;
    }

    public void setArrayPos(int arrayPos)
    {
        this.arrayPos = arrayPos;
    }
    
    public boolean[] getAllAttack()
    {
        return attack;
    }

    public void getHit(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            health = 0;
            xPos = oX;
            yPos = oY; 
            ServerConnector.dead = true;
            
        }
    }

    public int getAttackType()
    {
        return attackType;
    }

    public void setAttackType(int type)
    {
        attackType = type;
    }

    public boolean getAttack(int direction)
    {
        if (attack[direction])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setAttackingNow(boolean now)
    {
        attackingNow = now;
    }

    public boolean getAttackingNow()
    {
        return attackingNow;
    }

    public void setAttack(int direction, boolean isAttacking)
    {
        attack[direction] = isAttacking;
    }

    public void doneAttacking()
    {
        for (int i = 0; i < 4; i++)
        {
            attack[i] = false;
        }
    }

    public int getXpos()
    {
        return xPos;
    }

    public int getYpos()
    {
        return yPos;
    }

    public void setXpos(int x) {
        xPos = x;
    }

    public void setYpos(int y) {
        yPos = y;
    }
    public int getStrength()
    {
        return strength;
    }
    
    public int getHealth()
    {
        return health;
    }

    public int getXP()
    {
        return xp;
    }
    
    public void addXP(int xp)
    {
        this.xp += xp;
//        System.out.println(this.xp);
//        System.out.println(totalXP);
//        System.out.println(percent(false));
        if (this.xp >= totalXP)
        {
            this.xp -= totalXP;
            level++;
            totalXP = 100 * level;
            this.totalHealth = 100 * level;
            health = totalHealth;
            leveled = true;
        }
    }
    
    public int percent(boolean health)
    {
        if (health)
        {
            return (int) (100 * (this.health / (double) totalHealth ));
        }
        else
        {
            return (int) (100 * (this.xp / (double) totalXP));
        }
    }
    
    public boolean hasLeveled()
    {
        return leveled;
    }
    
    public void setLeveled(boolean b)
    {
        leveled = b;
    }
      
    public int getDamage()//int weapondType)
    {
        return strength+current.getDamage() * technique[current.getType()]/100;//((int) this.technique[weapondType] + 1)  ;
    }

    public int getTeam()
    {
        return team;
    }
    
    public void attack(int type)
    {
        
    }
    
}
