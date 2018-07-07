/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.util.ArrayList;

/**
 *
 * @author kudi
 */
public class GunWorldMachineGun extends GunWorldGun{

    public GunWorldMachineGun(int x, int y, boolean isEquipped)
    {
        super(x, y, 60, 30, isEquipped);
        this.gunID = GunID.machineGun;
        isShot = false;
    }
    
    public GunWorldGun deepCopy() 
    {
        return new GunWorldMachineGun(this.x, this.y, this.isEquipped);
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
            this.playerHolding.xSlide = 2;
            this.bullets.add(new GunWorldBullet(this.x, this.y + this.height / 2, 1, -(30 + Math.round((int)(Math.random() * 6)) - 3), Math.round((int)(Math.random() * 7)) - 3));
        }
        else
        {
            this.playerHolding.xSlide = -2;          
            this.bullets.add(new GunWorldBullet(this.x + this.width, this.y + this.height / 2, 1, (30 + Math.round((int)(Math.random() * 6)) - 3), Math.round((int)(Math.random() * 7)) - 3));
        }
    }
    
}
