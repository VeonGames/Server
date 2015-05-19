package brightdeathserver;
public class AttackType
{
    private final int damage;
    private final int coolDown;
    private final boolean ranged;
    private final String fileName;
    
    public AttackType(int damage, int coolDown, boolean ranged, String fileName)
    {
        this.damage = damage;
        this.coolDown = coolDown;
        this.ranged = ranged;
        this.fileName = fileName;
    }
    
    public String getfileName()
    {
        return fileName;
    }
    
    public boolean isRanged()
    {
        return ranged;
    }
    
    public int getCoolDown()
    {
        return coolDown;
    }
    
    public int getDamage()
    {
        return damage;
    }
}