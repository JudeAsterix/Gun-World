/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Graphics;
import java.util.ArrayList;

public class GunWorldPistol extends GunWorldGun{
    
    
    public GunWorldPistol(int x, int y, boolean isEquipped)
    {
        super(x, y, 25, 25, isEquipped);
        this.gunID = GunID.pistol;
        isShot = false;
    }
    
    public GunWorldGun deepCopy()
    {
        return new GunWorldPistol(this.x, this.y, this.isEquipped);
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        if(!isEquipped)
        {
            y += Math.round(Math.sin(space)) * 2;
            space += 0.5;
        }
        else
        {
            if(!playerHolding.direction)
            {
                this.x = playerHolding.x + (playerHolding.width) - this.width / 2;
            }
            else
            {
                this.x = playerHolding.x - this.width / 2;
            }
            this.y = playerHolding.y + (playerHolding.height / 4);
            
            for(int i = 0; i < bullets.size(); i++)
            {
                bullets.get(i).update(keys, ents);
            }
        }
        this.checkIfShot(ents);
    }
    
    public void shoot(boolean direction)
    {
        if(direction)
        {
            this.bullets.add(new GunWorldBullet(this.x, this.y + this.height / 2, 1, -20));
        }
        else
        {
            this.bullets.add(new GunWorldBullet(this.x + this.width, this.y + this.height / 2, 1, 20));
        }
    }
}
