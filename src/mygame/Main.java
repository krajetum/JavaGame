package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
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
    pg pg1;
    int i,j,k;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    
    @Override
    public void simpleInitApp() {
     //  startLayout();
       init_game();
       initKeys();
       flyCam.setEnabled(false);
    }

    @Override
    public void simpleUpdate(float tpf) {
        camera_first_person();
        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
    }

    @Override
    public void simpleRender(RenderManager rm) {
        
    }
    
    public void add_cube(float x,float y,float z)
    {
       cube_list.add(new Cube()); 
       app_cube=(Cube)cube_list.get(5*k + 20*i + j);
       app_cube.cube_model=assetManager.loadModel("Models/cubo_base/cubo.j3o");
       app_cube.cube_mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
       //app_cube.cube_texture=assetManager.loadTexture("percorso");
       //app_cube.cube_mat.setTexture("textcube", app_cube.cube_texture);
       app_cube.cube_mat.setColor("Color", ColorRGBA.Green);
       app_cube.cube_model.setMaterial(app_cube.cube_mat);
       app_cube.cube_model.setLocalTranslation(x,y,z);
       rootNode.attachChild(app_cube.cube_model);
    }
    public void create_pg(float x,float y,float z)
    {
        pg1=new pg();
        pg1.pg_model=assetManager.loadModel("Models/steve/steve.j3o");
        pg1.pg_mat=new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        pg1.pg_mat.setColor("Color", ColorRGBA.Blue);
        //pg1.pg_texture=assetManager.loadTexture("percorso");
        //pg1.pg_mat.setTexture("textcube", pg1.pg_texture);
        pg1.pg_model.setMaterial(pg1.pg_mat);
        pg1.pg_model.setLocalTranslation(x,y,z);
        rootNode.attachChild(pg1.pg_model);
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
      // guiViewPort.removeProcessor(niftyDisplay); 
      // flyCam.setDragToRotate(false); // disable the fly cam
       //flyCam.setMoveSpeed(15.0f);
       //cam.setLocation(new Vector3f(5.0f,20.0f,1.0f));
       create_pg(5.0f,5.0f-0.5f,5.0f);
       //cam.lookAt(pg1.pg_model.getLocalTranslation(),new Vector3f(0.0f,0.0f,0.0f) );
     //crea cubi iniziali
      for(k=0; k<5; k++) //y
       for(i=0; i<20; i++) //x
         for(j=0; j<20; j++) //z
           add_cube(i,k,j);
    }
    
    private void camera_first_person()
    {
       Vector3f camVec=pg1.pg_model.getLocalTranslation();
       cam.setLocation(new Vector3f(camVec.x,camVec.y+10,camVec.z-15));  
       cam.setRotation(pg1.pg_model.getLocalRotation());
    }
    
      private void initKeys()
      {
        inputManager.addMapping("W", new KeyTrigger( KeyInput.KEY_W));
        inputManager.addMapping("A", new KeyTrigger( KeyInput.KEY_A));
        inputManager.addMapping("S", new KeyTrigger( KeyInput.KEY_S));
        inputManager.addMapping("D", new KeyTrigger( KeyInput.KEY_D));
        inputManager.addListener(analogListener, "W","A","S","D");
      }
      
       private AnalogListener analogListener = new AnalogListener() {
         public void onAnalog(String name, float value, float tpf) {
              if (name.equals("D")) {
                Vector3f v = pg1.pg_model.getLocalTranslation();
                pg1.pg_model.setLocalTranslation(v.x, v.y, v.z+value*speed);
              }
              if (name.equals("A")) {
                Vector3f v =  pg1.pg_model.getLocalTranslation();
                pg1.pg_model.setLocalTranslation(v.x, v.y, v.z - value*speed);
              }
              if (name.equals("W")) {
                Vector3f v =  pg1.pg_model.getLocalTranslation();
                 pg1.pg_model.setLocalTranslation(v.x + value*speed, v.y, v.z);
              }
              if (name.equals("S")) {
                Vector3f v =  pg1.pg_model.getLocalTranslation();
                 pg1.pg_model.setLocalTranslation(v.x - value*speed, v.y, v.z);
              }
      }
    };
      
    
};


class Cube 
{
    Spatial cube_model;
    public float x_cube,y_cube,z_cube;
    Texture cube_texture;
    Material cube_mat;
};

class pg
{
  Spatial pg_model;
  public float x_pg,y_pg,z_pg;
  Texture pg_texture;
  Material pg_mat;
};
