/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GunWorldObjects {
    boolean[] keys = {false, false, false, false, false, false, false, false, false, false, false, false};
    /*
    Corrosponding Keys : {A, S, D, W, V, C, J, K, L, I, B, N}
    */
    ArrayList<GunWorldEntity> entities = new ArrayList<>();
    
    public GunWorldObjects()
    {
        entities.add(new GunWorldPlatform(100, 400, 100, 100, -1));
        entities.add(new GunWorldPlatform(300, 450, 100, 50, -1));
        entities.add(new GunWorldPlatform(600, 100, 50, 50, 1));
        entities.add(new GunWorldPlatform(700, 400, 50, 50, 1));
        entities.add(new GunWorldWall(0, -400, 50, 1000));
        entities.add(new GunWorldWall(750, -400, 50, 1000));
        entities.add(new GunWorldWall(600, 200, 50, 400));
        entities.add(new GunWorldHardPlatform(500, 200, 200, 50));
        entities.add(new GunWorldPistol(200, 200, false));
        entities.add(new GunWorldShotgun(400, 400, false));
        entities.add(new GunWorldMachineGun(500, 100, false));
        entities.add(new GunWorldCeiling(0,0, 800, 50));
        entities.add(new GunWorldPlayer(1));
        entities.add(new GunWorldPlayer(2));
    }
    
    public void updateKeys(int i, boolean b)
    {
        keys[i] = b;
    }
    
    public void paint(Graphics g)
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).paint(g);
        }
    }
        
    public void updateEnts()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update(keys, entities);
        }
    }
}
