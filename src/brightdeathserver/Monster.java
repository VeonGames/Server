package brightdeathserver;

/**
 *
 * @author PJ
 */
public class Monster
{

    protected int monsterX;
    protected int monsterY;
    protected int startX;
    protected int startY;
    protected int starthealth;
    protected int health;
    protected int damage;
    protected int respawnTime;
    protected int respawnCounter = 0;
    protected int speed;
    protected int agroRange;
    protected int chaseRange;
    protected int isAgro = -1;
    protected boolean alive = true;
    protected int attackSpace;
    protected boolean passive;
    protected int type;
    private final int id;
    protected boolean inHell; 
    private static int nextMonsterNum = 0;
    protected int xp;
    protected boolean isRanged;

    public Monster(int x, int y, boolean ranged)
    {
        isRanged = ranged;
        inHell = false;
        id = nextMonsterNum;
        nextMonsterNum++;
        type = 0;
        passive = false;
        monsterX = x;
        monsterY = y;
        startX = monsterX;
        startY = monsterY;
        starthealth = 100;
        damage = 10;
        speed = 1;
        agroRange = 200;
        chaseRange = 500;
        respawnTime = 100;
        attackSpace = 50;
        health = starthealth;        
        xp = 30;
    }
    
    public boolean isRanged()
    {
        return isRanged;
    }

    public boolean isInHell()
    {
        return inHell;
    }

    public void setInHell(boolean inHell)
    {
        this.inHell = inHell;
    }
    
    public int getType()
    {
        return type;
    }

    public int getMonsterNum()
    {
        return id;
    }

    public void revive()
    {
        if (respawnCounter >= respawnTime)
        {
            resetPos();
            respawnCounter = 0;
            alive = true;
            health = starthealth;
            inHell = false;
        } else
        {
            respawnCounter++;
        }
    }

    public boolean getHit(int damage) //returns true if dead
    {
        health -= damage;
        //System.out.println(type + " got hit");
        if (health <= 0)
        {
            alive = false;
            health = starthealth;
            isAgro = -1;
            //BrightDeathServer.gui.addCmdTxt("monster has died");
            return true;
        }
        return false;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public int getMonsterX()
    {
        return monsterX;
    }

    public int getMonsterY()
    {
        return monsterY;
    }

    public int getAgroRange()
    {
        return agroRange;
    }

    public int getChaseRange()
    {
        return chaseRange;
    }

    public int isAgro()
    {
        return isAgro;
    }

    public void setAgro(int agro)
    {
        isAgro = agro;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public void setMonsterX(int a)
    {
        monsterX = a;
    }

    public void setMonsterY(int a)
    {
        monsterY = a;
    }

    public int getAttackSpace()
    {
        return attackSpace;
    }

    @Override
    public boolean equals(Object obj)
    {
        Monster bill = (Monster) obj;
        return bill.getMonsterNum() == id;
    }

    public int getXP()
    {
        return xp;
    }
    
    public void resetPos()
    {
        this.monsterX = this.startX;
        this.monsterY = this.startY;
    }
    
}
