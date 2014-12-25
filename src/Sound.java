
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chetan Saraf
 */
public class Sound {

    Player midiPlayer = null;
    String so;

    public Sound(String s, int type) {
        try {
            so = s;
            InputStream us = getClass().getResourceAsStream(so);
            if (type == 0) {
                midiPlayer = Manager.createPlayer(us, "audio/midi");
            } else {
                midiPlayer = Manager.createPlayer(us, "audio/wav");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    public void sound_0N(int cont, int On) {
        try {
            if (midiPlayer != null) {
                midiPlayer.prefetch();
                try {
                    if (On == 1) {
                        if (cont > 0) {
                            midiPlayer.setLoopCount(-1);
                        } else {
                            midiPlayer.setMediaTime(0);
                        }
                        midiPlayer.start();
                    } else {
                        midiPlayer.stop();
                    }
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
}
