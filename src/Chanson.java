import java.util.ArrayList;
import java.util.List;

public class Chanson {
    private int id;
    private String titre;
    private double danceability;
    private double energy;
    private double loudness;
    private String album;
    private String artiste;
    private List<String> genres = new ArrayList<>();
    private final int duree = 5;

    public Chanson(String i, String t,String d, String e, String l, String al, String ar, String g){
        this.id = Integer.parseInt(i.trim());
        this.titre = t;
        this.danceability = Double.parseDouble(d.trim());
        this.energy = Double.parseDouble(e.trim());
        this.loudness = Double.parseDouble(l.trim());
        this.album = al;
        this.artiste = ar;
        this.genres.add(g);
    }

    public int getId() {return id;}
    public String getTitre() {return titre;}
    public double getDanceability() {return danceability;}
    public double getEnergy() {return energy;}
    public double getLoudness() {return loudness;}
    public String getAlbum() {return album;}
    public String getArtiste() {return artiste;}
    public String getGenres() {
        String genresString = "";
        for(String genre : this.genres){
            genresString = genresString + genre + " " + " | " + " ";
        }
        genresString = genresString.substring(0, genresString.length()-3);
        return genresString;
    }
    public int getDuree() {return duree;}

    public void setGenres(String s) {
        genres.add(s);
    }
}
