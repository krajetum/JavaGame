package mygame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class start implements ScreenController {

    Main app2; 

    start(Main a) {
        app2=a;
    }
    
    public void bind(Nifty nifty, Screen screen) {

    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {
        
    }
    
     public void initgame()
     { 
       app2.init_game();
     }
}
