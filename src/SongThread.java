import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class SongThread extends Thread{
    private Chanson nextSong;

    public void run(ArrayList<Chanson> al, Chanson c, ArrayList p){

        ArrayList<Chanson> songsList = al;
        ArrayList<Chanson> previousSongs = p;
        nextSong = c;
        Chanson currentSong = c;
        if(songsList.size() == 1){
            nextSong = currentSong;
        }
        else {
            Random r = new Random();
            while(nextSong.equals(currentSong)) {
                nextSong = songsList.get(r.nextInt(songsList.size()));
            }

            if(songsList.size() > 5) {
                while (previousSongs.contains(nextSong)) {
                    nextSong = songsList.get(r.nextInt(songsList.size()));
                }
            }
        }
    }

    public Chanson getNextSong() {
        return nextSong;
    }

}
