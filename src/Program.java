import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Program {
    private static Chanson currentSong;
    private static Chanson nextSong;
    private static ArrayList<Chanson> previousSongs = new ArrayList<>();
    private static ArrayList<Chanson> songsList = new ArrayList<>();
    private static Timer timer;
    private static int timeRemaining = 0;
    private static SongThread songThread = new SongThread();
    private static ArrayList<Chanson> allSongs = new ArrayList<Chanson>();
    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JPanel ptitle = new JPanel();
    private static JLabel lbl_title = new JLabel();
    private static JTextField txt_title;
    private static JPanel palbum;
    private static JLabel lbl_album;
    private static JTextField txt_album;
    private static JPanel partist;
    private static JLabel lbl_artist;
    private static JTextField txt_artist;
    private static JPanel pgenres;
    private static JLabel lbl_genres;
    private static JTextArea txt_genres;
    private static JPanel pdanceability;
    private static JLabel lbl_danceability;
    private static JTextField txt_danceability;
    private static JPanel penergy;
    private static JLabel lbl_energy;
    private static JTextField txt_energy;
    private static JPanel ploudness;
    private static JLabel lbl_loudness;
    private static JTextField txt_loudness;
    private static JPanel pnbchansons;
    private static JLabel lbl_nbchansons;
    private static JTextField txt_nbchansons;
    private static JPanel ptimer;
    private static JLabel lbl_timer;
    private static JTextField txt_timer;
    private static JPanel pboutons;
    private static JButton btn_précédent;
    private static JButton btn_suivant;
    private static JButton btn_play;
    private static JPanel pfilters;
    private static JComboBox cb;
    private static JLabel lbl_filtres;


    public static void main(String[] args) throws InterruptedException {
        String fichierCsv = "tracks.csv";
        allSongs = importerCSV(fichierCsv); // Liste de toutes les chansons du fichier
        songsList = allSongs; // Liste des chansons qui correspondent au filtre actuel

        // Choix alÃ©atoire de la premiÃ¨re chanson
        Random r = new Random();
        currentSong = songsList.get(r.nextInt(songsList.size()));
        timeRemaining = currentSong.getDuree();

        // FenÃªtre principale
        frame.setSize(800,500);
        frame.setResizable(false);
        // Panel principal qui va contenir tous les autres panels
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        // Titre
        ptitle = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_title = new JLabel("Title");
        lbl_title.setPreferredSize(new Dimension(140,18));
        lbl_title.setOpaque(true);
        lbl_title.setBackground(Color.black);
        lbl_title.setForeground(Color.white);
        txt_title = new JTextField(currentSong.getTitre());
        txt_title.setBackground(Color.GRAY);
        txt_title.setForeground(Color.white);
        txt_title.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_title.setEditable(false);
        txt_title.setPreferredSize(new Dimension(560,18));
        ptitle.add(lbl_title);
        ptitle.add(txt_title);
        ptitle.setMaximumSize(new Dimension(700, 18));
        panel.add(ptitle);

        // Album
        palbum = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_album = new JLabel("Album");
        lbl_album.setPreferredSize(new Dimension(140,18));
        lbl_album.setOpaque(true);
        lbl_album.setBackground(Color.black);
        lbl_album.setForeground(Color.white);
        txt_album = new JTextField(currentSong.getAlbum());
        txt_album.setBackground(Color.GRAY);
        txt_album.setForeground(Color.white);
        txt_album.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_album.setEditable(false);
        txt_album.setPreferredSize(new Dimension(560,18));
        palbum.add(lbl_album);
        palbum.add(txt_album);
        palbum.setMaximumSize(new Dimension(700,18));
        panel.add(palbum);

        // Artiste
        partist = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_artist = new JLabel("Artist");
        lbl_artist.setPreferredSize(new Dimension(140,18));
        lbl_artist.setOpaque(true);
        lbl_artist.setBackground(Color.black);
        lbl_artist.setForeground(Color.white);
        txt_artist = new JTextField(currentSong.getArtiste());
        txt_artist.setBackground(Color.GRAY);
        txt_artist.setForeground(Color.white);
        txt_artist.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_artist.setEditable(false);
        txt_artist.setPreferredSize(new Dimension(560,18));
        partist.add(lbl_artist);
        partist.add(txt_artist);
        partist.setMaximumSize(new Dimension(700,18));
        panel.add(partist);

        // Genres
        pgenres = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_genres = new JLabel("Genres");
        lbl_genres.setPreferredSize(new Dimension(140,54));
        lbl_genres.setOpaque(true);
        lbl_genres.setBackground(Color.black);
        lbl_genres.setForeground(Color.white);
        txt_genres = new JTextArea(currentSong.getGenresString());
        txt_genres.setBackground(Color.GRAY);
        txt_genres.setForeground(Color.white);
        txt_genres.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_genres.setEditable(false);
        txt_genres.setPreferredSize(new Dimension(560,54));
        txt_genres.setLineWrap(true);
        pgenres.add(lbl_genres);
        pgenres.add(txt_genres);
        pgenres.setMaximumSize(new Dimension(700,18));
        panel.add(pgenres);

        // Danceability
        pdanceability = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_danceability = new JLabel("Danceability");
        lbl_danceability.setPreferredSize(new Dimension(140,18));
        lbl_danceability.setOpaque(true);
        lbl_danceability.setBackground(Color.black);
        lbl_danceability.setForeground(Color.white);
        lbl_danceability.setAlignmentX(Component.LEFT_ALIGNMENT);
        txt_danceability = new JTextField(String.valueOf(currentSong.getDanceability()));
        txt_danceability.setBackground(Color.GRAY);
        txt_danceability.setForeground(Color.white);
        txt_danceability.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_danceability.setEditable(false);
        txt_danceability.setPreferredSize(new Dimension(560,18));
        pdanceability.add(lbl_danceability);
        pdanceability.add(txt_danceability);
        pdanceability.setMaximumSize(new Dimension(700,18));
        panel.add(pdanceability);

        // Energy
        penergy = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_energy = new JLabel("Energy");
        lbl_energy.setPreferredSize(new Dimension(140,18));
        lbl_energy.setOpaque(true);
        lbl_energy.setBackground(Color.black);
        lbl_energy.setForeground(Color.white);
        txt_energy = new JTextField(String.valueOf(currentSong.getEnergy()));
        txt_energy.setBackground(Color.GRAY);
        txt_energy.setForeground(Color.white);
        txt_energy.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_energy.setEditable(false);
        txt_energy.setPreferredSize(new Dimension(560,18));
        penergy.add(lbl_energy);
        penergy.add(txt_energy);
        penergy.setMaximumSize(new Dimension(700,18));
        panel.add(penergy);

        // Loudness
        ploudness = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_loudness = new JLabel("Loudness");
        lbl_loudness.setPreferredSize(new Dimension(140,18));
        lbl_loudness.setOpaque(true);
        lbl_loudness.setBackground(Color.black);
        lbl_loudness.setForeground(Color.white);
        txt_loudness = new JTextField(String.valueOf(currentSong.getLoudness()));
        txt_loudness.setBackground(Color.GRAY);
        txt_loudness.setForeground(Color.white);
        txt_loudness.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_loudness.setEditable(false);
        txt_loudness.setPreferredSize(new Dimension(560,18));
        ploudness.add(lbl_loudness);
        ploudness.add(txt_loudness);
        ploudness.setMaximumSize(new Dimension(700,18));
        panel.add(ploudness);

        // Nombre de chansons
        pnbchansons = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        lbl_nbchansons = new JLabel("Nombre de chansons ");
        lbl_nbchansons.setPreferredSize(new Dimension(140,18));
        lbl_nbchansons.setOpaque(true);
        lbl_nbchansons.setBackground(Color.black);
        lbl_nbchansons.setForeground(Color.white);
        txt_nbchansons = new JTextField(String.valueOf(allSongs.size()));
        txt_nbchansons.setBackground(Color.GRAY);
        txt_nbchansons.setForeground(Color.white);
        txt_nbchansons.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_nbchansons.setEditable(false);
        txt_nbchansons.setPreferredSize(new Dimension(560,18));
        pnbchansons.add(lbl_nbchansons);
        pnbchansons.add(txt_nbchansons);
        pnbchansons.setMaximumSize(new Dimension(700,18));
        panel.add(pnbchansons);

        // Timer
        ptimer = new JPanel();
        lbl_timer = new JLabel("Temps restant");
        lbl_timer.setOpaque(true);
        lbl_timer.setBackground(Color.black);
        lbl_timer.setForeground(Color.white);
        txt_timer = new JTextField();
        txt_timer.setText(String.valueOf(timeRemaining));
        txt_timer.setBackground(Color.GRAY);
        txt_timer.setForeground(Color.white);
        txt_timer.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_timer.setEditable(false);
        txt_timer.setPreferredSize(new Dimension(560,18));
        txt_timer.setFont(txt_timer.getFont().deriveFont(Font.BOLD));
        ptimer.add(lbl_timer);
        ptimer.add(txt_timer);
        ptimer.setMaximumSize(new Dimension(700,18));
        panel.add(ptimer);

        // Boutons
        pboutons = new JPanel();
        btn_précédent = new JButton("Précédent");
        btn_précédent.setEnabled(false);
        btn_précédent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previous();
            }
        });

        btn_suivant = new JButton("Suivant");
        btn_suivant.setEnabled(false);
        btn_suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btn_précédent.setEnabled(true);
                    next("manual");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pboutons.add(btn_précédent);
        pboutons.add(btn_suivant);

        btn_play = new JButton("Play");
        btn_play.setBackground(Color.green);
        btn_play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {checkPlayPauseButton();}
        });
        pboutons.add(btn_play);
        panel.add(pboutons);

        // Filtres
        pfilters = new JPanel();
        lbl_filtres = new JLabel("Filtres : ");
        String filters[] = {"All", "Album", "Artist", "Danceability", "Energy", "Loudness", "Genres"};
        cb = new JComboBox(filters);
        cb.setOpaque(true);
        cb.setBackground(Color.black);
        cb.setForeground(Color.WHITE);
        cb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    songfilter(cb.getSelectedItem().toString());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pfilters.add(lbl_filtres);
        pfilters.add(cb);
        panel.add(pfilters);

        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        songThread.run(songsList, currentSong, previousSongs);
        songThread.join();
        nextSong = songThread.getNextSong();
        btn_suivant.setEnabled(true);
    }


    public static ArrayList<Chanson> importerCSV (String csv){
        ArrayList<Chanson> al = new ArrayList<>();
        try{
            File file = new File(csv);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArray;
            while((line = br.readLine()) != null){
                tempArray = Split_String(line);
                if (al.isEmpty() || al.get(al.size() - 1).getId() != Integer.parseInt(tempArray[0].trim())) {
                    Chanson c = new Chanson(tempArray[0],
                            tempArray[1],
                            tempArray[2],
                            tempArray[3],
                            tempArray[4],
                            tempArray[5],
                            tempArray[6],
                            tempArray[7]
                    );
                    al.add(c);
                } else {
                    al.get(al.size() - 1).setGenres(tempArray[7]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return al;
    }

    public static String[] Split_String(String s){
        String[] temp = new String[8];
        int count = 0;
        int varCount = 0;
        while(varCount < 7) {
            if (s.charAt(0) != '"') {
                int cut = 0;
                for(int i = 0 ; i < s.length() ; i++){
                    if(s.charAt(i) == ','){
                        cut = i;
                        break;
                    }
                }
                String data = s.substring(0, cut);
                temp[varCount] = data;
                s = s.substring(cut+1);
                varCount++;
            }
            else {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '"') {
                        count++;
                        if (s.charAt(i + 1) == ',' && count % 2 == 0) {
                            String data = s.substring(1, i);
                            temp[varCount] = data;
                            s = s.substring(i+2);
                            varCount++;
                            count = 0;
                            break;
                        }
                    }
                }
            }
        }
        temp[7] = s;
        return temp;
    }

    public static void setTimer(){
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                txt_timer.setText(String.valueOf(timeRemaining));

                // Changement de chanson automatique si le timer atteint 0
                if (txt_timer.getText().equals("0")){
                    timer.stop();
                    try {
                        next("auto");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        timer.start();
    }

    public static void checkPlayPauseButton(){
        if(timer != null && timer.isRunning()) {
                timer.stop();
                btn_play.setText("Play");
                btn_play.setBackground(Color.green);
        }
        else {
            btn_play.setText("Pause");
            btn_play.setBackground(Color.red);
            setTimer();
        }
    }

    public static void songfilter(String s) throws InterruptedException {
        List<Chanson> l = new ArrayList<>();
        switch(s){
            case "All":
                l = allSongs;
                break;
            case "Album":
               l = allSongs.stream()
                        .filter(c -> c.getAlbum().equals(currentSong.getAlbum()))
                        .collect(Collectors.toList());
               break;
            case "Artist":
                l = allSongs.stream()
                        .filter(c -> c.getArtiste().equals(currentSong.getArtiste()))
                        .collect(Collectors.toList());
                break;
            case "Genres":
                for (Chanson c : allSongs){
                    int count = 0;
                    for (String genre : c.getGenres()) {
                        if (currentSong.getGenres().contains(genre))
                            count++;
                    }
                    if(count>=2)
                        l.add(c);
                }
                break;
            case "Danceability":
                l = allSongs.stream()
                        .filter(c -> c.getDanceability() >= currentSong.getDanceability() - 0.05 && c.getDanceability() <= currentSong.getDanceability() + 0.05)
                        .collect(Collectors.toList());
                break;
            case "Energy":
                l = allSongs.stream()
                        .filter(c -> c.getEnergy() >= currentSong.getEnergy() - 0.05 && c.getEnergy() <= currentSong.getEnergy() + 0.05)
                        .collect(Collectors.toList());
                break;
            case "Loudness":
                l = allSongs.stream()
                        .filter(c -> (int) c.getLoudness() == (int) currentSong.getLoudness())
                        .collect(Collectors.toList());
                break;
        }

        if(l.size() == 0)
            songsList = allSongs;
        else
            songsList = (ArrayList<Chanson>) l;

        txt_nbchansons.setText(String.valueOf(songsList.size()));
        songThread.run(songsList,currentSong, previousSongs);
        nextSong = songThread.getNextSong();
        next("manual");
    }

    public static void next(String s) throws InterruptedException {
        String mode = s;
        btn_suivant.setEnabled(false);
        btn_play.setEnabled(false);

        // Enregistrement des 5 derniÃ¨res chansons
        if(songsList.size()>0){
            previousSongs.add(currentSong);
            if (previousSongs.size() > 5)
                previousSongs.remove(0);
        }
        else{
            songfilter(cb.getSelectedItem().toString());
        }
        songThread.run(songsList, currentSong, previousSongs);
        songThread.join();
        nextSong = songThread.getNextSong();
        currentSong = nextSong;

        // Changement des donnÃ©es de l'interface
        txt_title.setText(currentSong.getTitre());
        txt_album.setText(currentSong.getAlbum());
        txt_artist.setText(currentSong.getArtiste());
        txt_genres.setText(currentSong.getGenresString());
        txt_danceability.setText(String.valueOf(currentSong.getDanceability()));
        txt_energy.setText(String.valueOf(currentSong.getEnergy()));
        txt_loudness.setText(String.valueOf(currentSong.getLoudness()));
        timeRemaining = currentSong.getDuree();
        if(mode.equals("auto")){
            setTimer();
        }
        else {
            if (timer != null)
                if (timer.isRunning()) {
                    timer.stop();
                    setTimer();
                } else
                    timer.stop();
        }
        txt_timer.setText(String.valueOf(timeRemaining));


        btn_suivant.setEnabled(true);
        btn_play.setEnabled(true);

    }

    public static void previous() {
        nextSong = currentSong;

        // RÃ©cupÃ©ration de la derniÃ¨re chanson jouÃ©e et suppression de cette chanson dans la liste des 5 derniÃ¨res chansons jouÃ©es
        if(previousSongs.size() == 1)
            currentSong = previousSongs.get(0);
        else
            currentSong = previousSongs.get(previousSongs.size()-1);
        previousSongs.remove(currentSong);

        // Changement des donnÃ©es de l'interface
        txt_title.setText(currentSong.getTitre());
        txt_album.setText(currentSong.getAlbum());
        txt_artist.setText(currentSong.getArtiste());
        txt_genres.setText(currentSong.getGenresString());
        txt_danceability.setText(String.valueOf(currentSong.getDanceability()));
        txt_energy.setText(String.valueOf(currentSong.getEnergy()));
        txt_loudness.setText(String.valueOf(currentSong.getLoudness()));
        timeRemaining = currentSong.getDuree();
        if (timer != null)
            if (timer.isRunning()) {
                timer.stop();
                setTimer();
            } else
                timer.stop();
        txt_timer.setText(String.valueOf(timeRemaining));

        if(previousSongs.size() == 0){
            btn_précédent.setEnabled(false);
        }
    }
}
