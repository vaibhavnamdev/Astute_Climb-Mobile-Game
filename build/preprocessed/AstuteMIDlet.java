//package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class AstuteMIDlet extends MIDlet{


    private Display display;
    // The display for this MIDlet
   // private Graphics3D graphics3d;

//    AstuteCanvas war;
    public AstuteMIDlet() {


    }

    public void startApp() {

//                for(int i =0;i<360;i++)
//        {
//            double n = Math.sin(i*Math.PI/180);
//            System.out.println(i+"   "+n);
//        }

        AstuteCanvas play = new AstuteCanvas(this);

        display = Display.getDisplay(this);
        display.setCurrent(play);


        try{
        Thread myThread = new Thread(play);
		myThread.start();} catch(Exception e)
                {

                }

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }



}
