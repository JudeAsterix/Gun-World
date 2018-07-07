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
public class GunWorldBullet extends GunWorldEntity{
    int direction;
    int xVelocity;
    int yVelocity;
    
    public GunWorldBullet(int x, int y, int direction, int velocity, int xVelocity)
    {
        super(x, y, 5, 5, EntityID.Bullet);
        this.direction = direction;
        this.xVelocity = velocity;
        this.yVelocity = xVelocity;
    }

    void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

    void update(boolean[] keys, ArrayList<GunWorldEntity> ents) {
        x += direction * xVelocity;
        y += yVelocity;
    }
    
    boolean isHit(GunWorldEntity gwe)
    {
        if(this.x + this.width >= gwe.x && this.x <= gwe.x + gwe.width && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height)
        {
            return(true);
        }
        else if(xVelocity > 0)
        {
            return(this.x <= gwe.x && this.x + this.xVelocity > gwe.x && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height);
        }
        else
        {
            return(this.x + this.width >= gwe.x + gwe.width && this.x + this.width + this.xVelocity < gwe.x + gwe.width && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height);
        }
    }
}
