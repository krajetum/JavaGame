package mygame;

import com.jme3.app.SimpleApplication;
import java.util.LinkedList;
import java.util.List;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;

public class Main extends SimpleApplication {

    List cube_list=new LinkedList();
    Cube app_cube;
    int i,j;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
       startLayout();
       
       for(i=0; i<5; i++)
       {
         for(j=0; j<5; j++)
           add_cube(i,0,j);
       }
    }

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }
    
    public void add_cube(float x,float y,float z)
    {
       cube_list.add(new Cube(x,y,z)); 
       app_cube=(Cube)cube_list.get( 5*i + j);
       app_cube.cube_model=assetManager.loadModel("Models/cubo_base/cubo.j3o");
       Material mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
       mat.setColor("Color", ColorRGBA.Green);
       app_cube.cube_model.setMaterial(mat);
       app_cube.cube_model.setLocalTranslation(x,y,z);
       rootNode.attachChild(app_cube.cube_model);
    }
    public void startLayout(){
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
        assetManager, inputManager, audioRenderer, guiViewPort);
        /** Create a new NiftyGUI object */
        Nifty nifty = niftyDisplay.getNifty();
        /** Read your XML and initialize your custom ScreenController */
        nifty.fromXml("Interface/Start.xml", "start");
        // nifty.fromXml("Interface/helloworld.xml", "start", new MySettingsScreen(data));
        // attach the Nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);
        // disable the fly cam
        flyCam.setDragToRotate(true);
    }
    
};


class Cube 
{
    Spatial cube_model;
    public float x_cube,y_cube,z_cube;
    Cube(float x,float y,float z)
    {
       x_cube=x;
       y_cube=y;
       z_cube=z;
    }
};
