import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class SongThread extends Thread{

    public void run(ArrayList<Chanson> al, JButton btn, Chanson c){
        btn.setEnabled(false);
        Chanson nextSong = c;
        Chanson currentSong = c;
        ArrayList<Chanson> listeChanson = new ArrayList<Chanson>();
        listeChanson = (ArrayList<Chanson>) al.clone();
        Random r = new Random();
        while(currentSong == nextSong) {
            currentSong = listeChanson.get(r.nextInt(listeChanson.size() + 1 - 1) + 1);
        }
        btn.setEnabled(true);

    }
}
