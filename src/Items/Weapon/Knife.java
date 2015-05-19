package Items.Weapon;



public class Knife extends Weapon
{

private int amountInStack = 1; 
private int x = -1;
private int y = -1;
private int attackURL = 0;


public Knife()
{
    super();
}
public String name()
{
return "Knife";
}

public int amount()
{
return amountInStack;
}

public int amountAvailable()
{
return 59;
}

public void add()
{
amountInStack++;
}

public int range()
{
return 1;
}

public int damage()
{
return 10;
}

public int getX()
{
return this.x;
}
public int getY()
{
return this.y;
}

public void setX(int x)
{
this.x = x;
}
public void setY(int y)
{
this.y = y;
}

public int value()
{
return 1;
}

public void add(int num)
{
amountInStack += num;
}

}