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
    int velocity;
    
    public GunWorldBullet(int x, int y, int direction, int velocity)
    {
        super(x, y, 5, 5, EntityID.Bullet);
        this.direction = direction;
        this.velocity = velocity;
    }

    void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

    void update(boolean[] keys, ArrayList<GunWorldEntity> ents) {
        x += direction * velocity;
    }
    
    boolean isHit(GunWorldEntity gwe)
    {
        if(this.x + this.width >= gwe.x && this.x <= gwe.x + gwe.width && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height)
        {
            return(true);
        }
        else if(velocity > 0)
        {
            return(this.x <= gwe.x && this.x + this.velocity > gwe.x && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height);
        }
        else
        {
            return(this.x + this.width >= gwe.x + gwe.width && this.x + this.width + this.velocity < gwe.x + gwe.width && this.y + this.height > gwe.y && this.y < gwe.y + gwe.height);
        }
    }
}
