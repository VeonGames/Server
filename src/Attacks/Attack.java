/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attacks;

import Attacks.AttackBox;
import Items.Weapon.Weapon;
import brightdeathserver.AIcontroller;
import brightdeathserver.Monster;
import brightdeathserver.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicno90
 */
public class Attack
{

    private File side;
    private File dia;
    private char[][] map = new char[9][9];
    private char[][] mapD;
    private Weapon arm;

    //working on
    public Attack(File side, File dia, Weapon w)
    {
        this.side = side;
        toCharAA(side, map);
        this.dia = dia;
        toCharAA(dia, mapD);
        arm = w;
    }

    public Weapon getArm()
    {
        return arm;
    }
    
    
    
    public Attack(File side, Weapon w)
    {
        this.side = side;
        toCharAA(side, map);
        arm = w;
    }

    private void toCharAA(File txt, char[][] map)
    {
        //map = new char[y][x] because cant change [x][y] would npt work trust me i thought about it.
        //if [x][y] the lines would come out sideways 
        BufferedReader in = null;

        int lines = 0;
        try
        {
            in = new BufferedReader(new FileReader(txt));

            String s = in.readLine();

            while (s != null)
            {

                s = in.readLine();
                map[lines] = s.toCharArray();
                lines++;

            }

        } catch (Exception e)
        {

        } finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (Exception e)
                {

                }
            }
        }
        return;
    }

    public List<AttackBox> strike(int dir, Player player)
    {
        //0 up
        //1 right up
        //2 right
        //3 right down
        //4 down
        //5 left down 
        //6 left
        //7 left up
        //x,y

        if (dia == null && !(dir == 0 || dir == 1 || dir == 2 || dir == 3))
        {
            return null;
        }

        List<AttackBox> box = new ArrayList();
        int[] indexP =
        {
            4, 4
        };
        int px = player.getXpos();
        int py = player.getYpos();
        List<Monster> boxes;
        if (dir == 0)
        {
            for (int y = 0; y < 9; y++)
            {
                for (int x = 0; x < 9; x++)
                {         //x  y
                    format(map[x][y], x, y, px, py, player.getDamage(), player.getTeam(), dir);
                }
            }
        }
        if (dir == 2)
        {
            for (int y = 0; y < 9; y++)
            {
                for (int x = 0; x < 9; x++)
                {
                    format(map[x][y], y, x, px, py, player.getDamage(), player.getTeam(), dir);
                }
            }
        }
        if (dir == 4)
        {
            for (int y = 0; y < 9; y++)
            {
                for (int x = 0; x < 9; x++)
                {
                    format(map[x][y], x, -1 * y, px, py, player.getDamage(), player.getTeam(), dir);
                }
            }
        }
        if (dir == 6)
        {
            for (int y = 0; y < 9; y++)
            {
                for (int x = 0; x < 9; x++)
                {
                    format(map[x][y], -1 * y, x, px, py, player.getDamage(), player.getTeam(), dir);
                }
            }
        }
        return box;
    }

    private AttackBox format(char val, int x, int y, int px, int py, int dmg, int team, int dir)
    {
        if (val == 'O') // flip
        {
            return null;
        } else if (val == 'X')
        {
            return new AttackBox(px + ((x - 4) * 50), py - ((y - 4) * 50), dmg, team, dir, 1, 0);
        } else if (val == '>')
        {
            return new AttackBox(px + ((x - 4) * 50), py - ((y - 4) * 50), dmg, team, dir, next(x, y, dir), 3);
        }
        return null;
    }

    private int next(int x, int y, int dir)
    {
        //0 up
        //1 right up
        //2 right
        //3 right down
        //4 down
        //5 left down 
        //6 left
        //7 left up
        int xc;
        int yc;

        if (dir == 0)
        {
            yc = -1;
            xc = 0;
        } else if (dir == 1)
        {
            yc = -1;
            xc = 1;
        } else if (dir == 2)
        {
            yc = 0;
            xc = 1;
        } else if (dir == 3)
        {
            yc = 1;
            xc = 1;
        } else if (dir == 4)
        {
            yc = -1;
            xc = 0;
        } else if (dir == 5)
        {
            yc = -1;
            xc = -1;
        } else if (dir == 6)
        {
            yc = 0;
            xc = -1;
        } else if (dir == 7)
        {
            yc = -1;
            xc = -1;
        } else
        {
            return -1;
        }

        char c = 'O';
        int ctr = 0;
        if (dir == 0 || dir % 2 == 0)
        {
            while (c == 'O')
            {
                c = this.map[y + yc][x + xc];
                y += yc;
                x += xc;
                ctr++;
            }
        } else
        {
            while (c == 'O')
            {
                c = this.mapD[y + yc][x + xc];
                y += yc;
                x += xc;
                ctr++;
            }
        }
        return ctr;
    }

    private int[] find(char c, char[][] map)
    {
        int[] index = new int[2];
        for (int k = 0; k < 9; k++)
        {
            for (int i = 0; i < 9; i++)
            {
                if (map[k][i] == c)
                {
                    index[0] = k;
                    index[1] = i;
                    return index;
                }
            }
        }
        return null;
    }

    public static List<Monster> checkBox(int bx, int by)
    {
        List<Monster> out = new ArrayList();
        int mx, my;
        for (Monster bill : AIcontroller.monsters)
        {
            if (bill != null && bill.isAlive())
            {
                mx = bill.getMonsterX();
                my = bill.getMonsterY();

                //     top x             bottom x         top y               top y
                if ((bx - 50 <= mx && mx <= bx + 50) && (by - 50 <= my && my <= by + 50))
                {
                    out.add(bill);
                }
            }
        }
        //System.out.println("number in out (AIC line 279) " + out.size());
        return out;
    }

}
