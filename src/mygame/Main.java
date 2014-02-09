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
import com.jme3.texture.Texture;
import de.lessvoid.nifty.Nifty;

public class Main extends SimpleApplication {
    
    List cube_list=new LinkedList();
    NiftyJmeDisplay niftyDisplay; // new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
    Cube app_cube;
    int i,j,k;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    
    @Override
    public void simpleInitApp() {
       startLayout();
       init_game();
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
       app_cube=(Cube)cube_list.get(40*k + 15*i + j);
       app_cube.cube_model=assetManager.loadModel("Models/cubo_base/cubo.j3o");
       app_cube.cube_mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
       //app_cube.cube_texture=assetManager.loadTexture("percorso");
       //app_cube.cube_mat.setTexture("textcube", app_cube.cube_texture);
       app_cube.cube_mat.setColor("Color", ColorRGBA.Green);
       app_cube.cube_model.setMaterial(app_cube.cube_mat);
       app_cube.cube_model.setLocalTranslation(x,y,z);
       rootNode.attachChild(app_cube.cube_model);
    }
    
    public void startLayout(){
        niftyDisplay=new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/Start.xml", "start");
        guiViewPort.addProcessor(niftyDisplay); // attach the Nifty display to the gui view port as a processor
        flyCam.setDragToRotate(true); // disable the fly cam
    }

    public void init_game()
    { 
       guiViewPort.removeProcessor(niftyDisplay); 
       flyCam.setDragToRotate(false); // disable the fly cam
       flyCam.setMoveSpeed(15.0f);
      for(k=0; k<40; k++) //y
       for(i=0; i<15; i++) //x
         for(j=0; j<15; j++) //z
           add_cube(i,k,j);
    }
    
};


class Cube 
{
    Spatial cube_model;
    public float x_cube,y_cube,z_cube;
    Texture cube_texture;
    Material cube_mat;
    Cube(float x,float y,float z)
    {
       x_cube=x;
       y_cube=y;
       z_cube=z;
    }
};
