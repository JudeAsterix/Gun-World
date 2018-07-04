/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author kudi
 */
public abstract class GunWorldGun extends GunWorldEntity{
    boolean isEquipped;
    GunWorldPlayer playerHolding;
    double space = 0;
    boolean isShot;
    GunID gunID;
    ArrayList<GunWorldBullet> bullets = new ArrayList<>();
    
    public GunWorldGun(int x, int y, int width, int height, boolean isEquipped)
    {
        super(x, y, width, height, EntityID.Gun);
        this.isEquipped = isEquipped;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);
        
        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).paint(g);
        }
    }
    
    abstract public GunWorldGun deepCopy();
    
    abstract public void update(boolean[] keys, ArrayList<GunWorldEntity> ents);
    
    abstract public void shoot(boolean direction);
    
    public void checkIfShot(ArrayList<GunWorldEntity>ents)
    {
        int i = ents.size() - 1;
        while(ents.get(i).id == EntityID.Player && i > 0)
        {
            for(int j = bullets.size() - 1; j >= 0; j--)
            {
                if(bullets.get(j).isHit(ents.get(i)))
                {
                    ((GunWorldPlayer)(ents.get(i))).yVel = -20;
                    ((GunWorldPlayer)(ents.get(i))).isJumping = true;
                    bullets.remove(j);
                }
            }
            i--;
        }
    }
}
