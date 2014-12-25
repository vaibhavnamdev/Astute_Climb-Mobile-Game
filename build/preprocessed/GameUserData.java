
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author Vaibhav Namdev
 */
public class GameUserData {//FOR Store

    static RecordStore rmsHighestScores;
    static String[] highestScores = new String[10];
    public static String[] gameHighestScores;// = new String[10];

    //Highest Score
    public static void setHighestScores(String[] scores) {

        highestScores = scores;
        setGameHighestScores(scores);

        try {
            rmsHighestScores = RecordStore.openRecordStore("HighestScores", true);
            if (rmsHighestScores.getNumRecords() == 0) {
                for (int i = 0; i < scores.length; i++) {
                    byte[] bytes = scores[i].getBytes();
                    rmsHighestScores.addRecord(bytes, 0, bytes.length);
                }
            } else {
                for (int i = 0; i < scores.length; i++) {
                    byte[] bytes = scores[i].getBytes();
                    rmsHighestScores.setRecord((i + 1), bytes, 0, bytes.length);
                }
            }
        } catch (RecordStoreException ex) {
            System.out.println("setHighestScores " + ex);
        }

        try {

            if (rmsHighestScores != null) {
                rmsHighestScores.closeRecordStore();
                rmsHighestScores = null;
            }
        } catch (Exception e) {
        }
    }

    public static String[] getHighestScores() {

        try {
            rmsHighestScores = RecordStore.openRecordStore("HighestScores", true);
            if (rmsHighestScores.getNumRecords() == 0) {
                return null;
            } else {
                for (int i = 0; i < highestScores.length; i++) {
                    byte[] bytes = rmsHighestScores.getRecord(i + 1);
                    highestScores[i] = new String(bytes, 0, bytes.length);
                }
                return highestScores;
            }
        } catch (RecordStoreException ex) {
            System.out.println("getHighestScores " + ex);
        }

        try {

            if (rmsHighestScores != null) {
                rmsHighestScores.closeRecordStore();
                rmsHighestScores = null;
            }
        } catch (Exception e) {
        }

        return highestScores;
    }

    public static void setGameHighestScores(String[] userData) {
        gameHighestScores = userData;
    }

    public static String[] getGameHighestScores() {

        if (gameHighestScores == null) {
            gameHighestScores = getHighestScores();
        }

        if (gameHighestScores == null) {
            gameHighestScores = new String[10];
        }

        for (int i = 0; i < gameHighestScores.length; i++) {
            if(gameHighestScores[i] == null){
                gameHighestScores[i] = "0";
            }
        }
        return gameHighestScores;
    }

    //Score
    public static void setHighestScore(int iScore) {

        String currentScores[] = getGameHighestScores();
        String tmpScores[] = new String[10];
        boolean needUpdate = false;
        int iPosition = -1;

        for (int i = (currentScores.length - 1); i >= 0; i--) {

            //System.out.println("Value ="+currentScores[i]);
            if (currentScores[i] == null) {
                currentScores[i] = "0";
            }

            int iValue = Integer.parseInt(currentScores[i]);

            if (iScore > iValue) {
                needUpdate = true;
                iPosition = i;
            } else if (needUpdate && iScore == iValue) {
                needUpdate = false;
            }
        }

        if (needUpdate) {

            for (int i = 0, j = 0; i < tmpScores.length; i++, j++) {

                if (i == iPosition) {
                    tmpScores[i] = Integer.toString(iScore);
                    j--;
                } else {
                    tmpScores[i] = currentScores[j];
                }
            }
            setHighestScores(tmpScores);
        }
    }
    
}
