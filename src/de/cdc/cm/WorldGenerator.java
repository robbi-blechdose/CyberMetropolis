package de.cdc.cm;

import com.jme3.asset.AssetManager;
import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import java.util.Random;

/**
 *
 * @author Julius + Joscha
 * 
 */
public class WorldGenerator
{
    private AssetManager assetManager;
    private Node world;
    
    public WorldGenerator(Node world, AssetManager assetManager)
    {
        this.world = world;
        this.assetManager = assetManager;
        generateWorld();
    }
    
    private void generateWorld()
    {
        OpenSimplexNoise osn = new OpenSimplexNoise();
        for(int i = 0; i < 100; i++)
        {
            for(int o = 0; o < 100; o++)
            {
                float height = (float) osn.eval(i * 0.1f, o * 0.1f)* 3f;
                Node hex;
                if (height < -1.4f)
                {
                    hex = (Node) assetManager.loadModel("Models/Hexagon/Hexagon_Water.j3o");
                }
                else if(height < -0.3f)
                {
                    hex = (Node) assetManager.loadModel("Models/Hexagon/Hexagon_DeadGrass.j3o");
                }
                else
                {
                    hex = (Node) assetManager.loadModel("Models/Hexagon/Hexagon_Grass.j3o");
                }
                world.attachChild(hex);
                float x = 0;      
                if(i%2 == 0)
                {
                    x = 1.445f;
                }
                hex.setLocalTranslation(o*1.43f*2+x,
                        (float) osn.eval(i * 0.1f, o * 0.1f) * 3f, i*1.22f*2); 
                
                System.out.println(hex.getLocalTranslation());
            }
        }
    }
    //(float)random.nextFloat()
}