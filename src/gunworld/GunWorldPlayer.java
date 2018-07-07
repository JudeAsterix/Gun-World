/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kudi
 */
public class GunWorldPlayer extends GunWorldEntity{
    int yVel, xSlide;
    int playerNum;
    int gunCooldown;
    boolean direction = false; // true = left, false = right;
    boolean isFalling;
    boolean isJumping;
    boolean isOnPlatform = false;
    boolean isOnHardPlatform = false;
    boolean canDoubleJump;
    boolean isDoubleJumping;
    int platMin = 0, platMax = 0;
    final int maxKeys = 6;
    int animatedFrames = 0;
    GunWorldGun playerGun;
    ArrayList<GunWorldDust> playerDust = new ArrayList<>();
    
    public GunWorldPlayer(int playerNumber)
    {
        super(100, 455, 30, 45, EntityID.Player);
        if(playerNumber == 2)
        {
            this.x = 500;
            this.y = 455;
        }
        this.playerNum = playerNumber;
    }
    
    public void paint(Graphics g)
    {
        for(int i = 0; i < playerDust.size(); i++)
        {
            this.playerDust.get(i).paint(g);
        }
        
        if(playerNum == 1)
        { 
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);
        }
        else
        {
            g.setColor(new Color(200, 0, 0));
            g.fillRect(x, y, width, height);
        }
        
        if(this.playerGun != null)
        {
            this.playerGun.paint(g);
        }
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        animatedFrames++;
        
        if(keys[0 + (maxKeys * (playerNum - 1))] != keys[2 + (maxKeys * (playerNum - 1))])
        {
            if(keys[0 + (maxKeys * (playerNum - 1))])
            {
                this.x -= 15;
                direction = true;
            }
            else if(keys[2 + (maxKeys * (playerNum - 1))])
            {
                this.x += 15;
                direction = false;
            }
        }
        
        this.x += this.xSlide;
        
        if((isOnPlatform || isOnHardPlatform) && (this.x > this.platMax || this.x + this.width < this.platMin))
        {
            this.isOnPlatform = false;
            this.isFalling = true;
        }
        
        if(keys[3 + (maxKeys * (playerNum - 1))] == true && !isJumping)
        {
            this.yVel = -30;
            isJumping = true;
        }
        else if(keys[3 + (maxKeys * (playerNum - 1))] == true && isJumping && canDoubleJump && !isDoubleJumping)
        {
            this.yVel = -30;
            isDoubleJumping = true;
        }
        else if(keys[3 + (maxKeys * (playerNum - 1))] == false && isJumping)
        {
            canDoubleJump = true;
        }
        
        
        boolean blockDetected = false;
        for(int i = ents.size() - 1; i >= 0; i--)
        {
            if(ents.get(i).id == EntityID.Wall && ((GunWorldWall)(ents.get(i))).blockDetect(this))
            {
                if(keys[0 + (maxKeys * (playerNum - 1))] || (xSlide < 0 && !keys[2 + (maxKeys * (playerNum - 1))]))
                {
                    this.x = ents.get(i).width + ents.get(i).x;
                }
                else if(keys[2 + (maxKeys * (playerNum - 1))] == true || (xSlide > 0 && !keys[0 + (maxKeys * (playerNum - 1))]))
                {
                    this.x = ents.get(i).x - this.width;
                }
            }
            
            if(ents.get(i).id == EntityID.Ceiling && ((GunWorldCeiling)(ents.get(i))).blockDetect(this))
            {
                this.yVel = 0;
                this.y = ents.get(i).y + ents.get(i).height;
            }
            
            if(ents.get(i).id == EntityID.Platform && ((GunWorldPlatform)(ents.get(i))).blockDetect(this))
            {
                this.yVel = 0;
                this.y = ents.get(i).y - this.height;
                isJumping = false;
                isFalling = false;
                isDoubleJumping = false;
                canDoubleJump = false;
                blockDetected = true;
                isOnPlatform = true;
                this.platMax = ents.get(i).x + ents.get(i).width;
                this.platMin = ents.get(i).x;
                createDust();
            }
            else if(ents.get(i).id == EntityID.HardPlatform)
            {
                if(((GunWorldHardPlatform)(ents.get(i))).ceiling.blockDetect(this))
                {
                    this.yVel = 0;
                    this.y = ents.get(i).y + ents.get(i).height;
                }
                else if(((GunWorldHardPlatform)(ents.get(i))).wallLeft.blockDetect(this))
                {
                    this.x = ents.get(i).x - this.width;
                }
                else if(((GunWorldHardPlatform)(ents.get(i))).wallRight.blockDetect(this))
                {
                    this.x = ents.get(i).width + ents.get(i).x;
                }
                else if(((GunWorldHardPlatform)(ents.get(i))).platform.blockDetect(this))
                {
                    this.yVel = 0;
                    this.y = ents.get(i).y - this.height;
                    isJumping = false;
                    isFalling = false;
                    isDoubleJumping = false;
                    canDoubleJump = false;
                    blockDetected = true;
                    isOnHardPlatform = true;
                    this.platMax = ents.get(i).x + ents.get(i).width;
                    this.platMin = ents.get(i).x;
                    createDust();
                }
            }
            
            //Obtaining the gun
            if(ents.get(i).id == EntityID.Gun && this.x < ents.get(i).x + ents.get(i).width && this.x + this.width > ents.get(i).x && this.y < ents.get(i).y + ents.get(i).height && this.y + this.height > ents.get(i).y && this.playerGun == null)
            {
                if(((GunWorldGun)(ents.get(i))).gunID == GunID.pistol)
                {
                    this.playerGun = new GunWorldPistol(this.x + (this.width / 2), this.y + (this.height / 4), true);
                    this.playerGun.playerHolding = this;
                }
                else if(((GunWorldGun)(ents.get(i))).gunID == GunID.shotgun)
                {
                    this.playerGun = new GunWorldShotgun(this.x + (this.width / 2), this.y + (this.height / 4), true);
                    this.playerGun.playerHolding = this;
                }
                else if(((GunWorldGun)(ents.get(i))).gunID == GunID.machineGun)
                {
                    this.playerGun = new GunWorldMachineGun(this.x + (this.width / 2), this.y + (this.height / 4), true);
                    this.playerGun.playerHolding = this;
                }
                ents.remove(i);
            }
            
            if(ents.get(i).id == EntityID.Player && ((GunWorldPlayer)(ents.get(i))).playerNum != this.playerNum && this.entityDetect(ents.get(i))&& ((GunWorldPlayer)(ents.get(i))).playerGun != null && keys[5 + (maxKeys * (playerNum - 1))])
            {
                this.playerGun = ((GunWorldPlayer)(ents.get(i))).playerGun.deepCopy();
                this.playerGun.playerHolding = this;
                ((GunWorldPlayer)(ents.get(i))).playerGun = null;
            }
        }
        
        if(!blockDetected && this.y + height + this.yVel >= 500 && this.yVel > 0 && (isJumping || isFalling))
        {
            this.yVel = 0;
            this.y = 500 - height;
            isJumping = false;
            isDoubleJumping = false;
            canDoubleJump = false;
        }
        else if(!blockDetected && (isJumping || isFalling))
        {
            this.y += this.yVel;
            this.yVel += 4;
        }
        
        if(this.xSlide != 0)
        {
            if(this.xSlide > 0)
            {
                this.xSlide--;
            }
            else if(this.xSlide < 0)
            {
                this.xSlide++;
            }
        }
        
        if(keys[1 + (maxKeys * (playerNum - 1))] == true && isOnPlatform)
        {
            isOnPlatform = false;
            this.isFalling = true;
        }
        
        if(this.playerGun != null)
        {
            this.playerGun.update(keys, ents);
            if(this.playerGun.gunID == GunID.machineGun)
            {
                if(keys[4 + (maxKeys * (playerNum - 1))])
                {
                    this.playerGun.shoot(direction);
                    this.playerGun.isShot = true;
                }
                else
                {
                    this.playerGun.isShot = false;
                }
            }
            else
            {
                if(keys[4 + (maxKeys * (playerNum - 1))] && this.playerGun.isShot == false)
                {
                    this.playerGun.shoot(direction);
                    this.playerGun.isShot = true;
                }
                else if(!keys[4 + (maxKeys * (playerNum - 1))])
                {
                    this.playerGun.isShot = false;
                }
            }
        }
        
        for(int i = playerDust.size() - 1; i >= 0; i--)
        {
            this.playerDust.get(i).update(keys, ents);
            if(playerDust.get(i).width == 0)
            {
                playerDust.remove(i);
            }
        }
        
        if(this.gunCooldown != 0)
        {
            this.gunCooldown--;
        }
    }
    
    public boolean entityDetect(GunWorldEntity gwp)
    {
        return(this.x < gwp.x + gwp.width && this.x + this.width > gwp.x && this.y < gwp.y + gwp.width && this.y + this.height > this.x);
    }
    
    public void createDust()
    {
        for(int i = 0; i < 10; i++)
        {
            this.playerDust.add(new GunWorldDust(this.x + Math.round((long)(Math.random() * this.width)), this.y + this.height));
        }
    }
}