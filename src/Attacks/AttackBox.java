package Attacks;

public class AttackBox
{
    private int xPos;
    private int yPos;
    final private boolean ranged;
    final private int maxIterations;
    private int iterations = 0;
    final private int damage;
    final int direction;
    final int team;
    final int speed;
    final boolean splash;
    
    public AttackBox(int x, int y, int damage, int team, int direction, int maxIterations, int speed) //for range
    {
        xPos = x;
        yPos = y;
        this.ranged = true;
        this.speed = speed;
        this.damage = damage;
        this.maxIterations = maxIterations;
        this.direction = direction;
        this.team = team;
        splash = true;
    }

    public boolean isSplash()
    {
        return splash;
    }
    
    public int getTeam()
    {
        return team;
    }
    
    public int getDirection()
    {
        return direction;
    }
    
    public void iterate()
    {
        iterations++;
    }
    
    public void setYPos(int y)
    {
        yPos = y;
    }
    
    public void setXPos(int x)
    {
        xPos = x;
    }
    
    public int damage()
    {
        return damage;
    }
    
    public boolean move()
    {
        //0 up
        //1 right up
        //2 right
        //3 right down
        //4 down
        //5 left down 
        //6 left
        //7 left up
        iterations++;
        
        if (direction == 0)
        {
            yPos -= speed;
        }
        else if (direction == 1)
        {
            xPos += speed;
            yPos -= speed;
        }
        else if (direction == 2)
        {
            xPos += speed;
        }
        else if (direction == 3)
        {
            xPos += speed;
            yPos += speed;
        }
        else if (direction == 4)
        {
            yPos += speed;
        }
        else if (direction == 5)
        {
            xPos -= speed;
            yPos += speed;
        }
        else if (direction == 6)
        {
            xPos -= speed;
            
        }
        else if (direction == 7)
        {
            xPos -= speed;
            yPos -= speed;
        }
        
        
        return !(iterations < maxIterations);
    }
    
    public int getIterationsLeft()
    {
        return maxIterations - iterations;
    }
    
    public boolean isRanged()
    {
        return ranged;
    }
    
    public int getXPos()
    {
        return xPos;
    }
    
    public int getYPos()
    {
        return yPos;
    }
}
