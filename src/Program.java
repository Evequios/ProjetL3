import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    private static Chanson currentChanson;
    private static Timer timer;
    private static SongThread songThread = new SongThread();
    private static ArrayList<Chanson> listeChanson = new ArrayList<Chanson>();

    public static void main(String[] args) {
        String fichierCsv = "tracks.csv";
        listeChanson = importerCSV(fichierCsv);
        Random r = new Random();

        currentChanson = listeChanson.get(r.nextInt(listeChanson.size() + 1 - 1) + 1);
        System.out.println(listeChanson.size());

        // Fenêtre principale
        JFrame frame = new JFrame();
        frame.setSize(600,500);
        frame.setResizable(false);
        // Panel principal qui va contenir tous les autres panels
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        // Titre
        JPanel ptitle = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_title = new JLabel("Title");

        lbl_title.setOpaque(true);
        lbl_title.setBackground(Color.black);
        lbl_title.setForeground(Color.white);
        JTextField txt_title = new JTextField(currentChanson.getTitre());
        txt_title.setBackground(Color.GRAY);
        txt_title.setForeground(Color.white);
        txt_title.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_title.setEditable(false);
        lbl_title.setPreferredSize(new Dimension(140,18));
        txt_title.setPreferredSize(new Dimension(360,18));
        ptitle.add(lbl_title);
        ptitle.add(txt_title);
        ptitle.setMaximumSize(new Dimension(500, 18));
        panel.add(ptitle);

        // Album
        JPanel palbum = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_album = new JLabel("Album");
        lbl_album.setOpaque(true);
        lbl_album.setBackground(Color.black);
        lbl_album.setForeground(Color.white);
        JTextField txt_album = new JTextField(currentChanson.getAlbum());
        txt_album.setBackground(Color.GRAY);
        txt_album.setForeground(Color.white);
        txt_album.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_album.setEditable(false);
        lbl_album.setPreferredSize(new Dimension(140,18));
        txt_album.setPreferredSize(new Dimension(360,18));
        palbum.add(lbl_album);
        palbum.add(txt_album);
        palbum.setMaximumSize(new Dimension(500,18));
        panel.add(palbum);

        // Artiste
        JPanel partist = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_artist = new JLabel("Artist");
        lbl_artist.setOpaque(true);
        lbl_artist.setBackground(Color.black);
        lbl_artist.setForeground(Color.white);
        JTextField txt_artist = new JTextField(currentChanson.getArtiste());
        txt_artist.setBackground(Color.GRAY);
        txt_artist.setForeground(Color.white);
        txt_artist.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_artist.setEditable(false);
        lbl_artist.setPreferredSize(new Dimension(140,18));
        txt_artist.setPreferredSize(new Dimension(360,18));
        partist.add(lbl_artist);
        partist.add(txt_artist);
        partist.setMaximumSize(new Dimension(500,18));
        panel.add(partist);

        // Genres
        JPanel pgenres = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_genres = new JLabel("Genres");
        lbl_genres.setOpaque(true);
        lbl_genres.setBackground(Color.black);
        lbl_genres.setForeground(Color.white);
        JTextArea txt_genres = new JTextArea(currentChanson.getGenres());
        txt_genres.setBackground(Color.GRAY);
        txt_genres.setForeground(Color.white);
        txt_genres.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_genres.setEditable(false);
        lbl_genres.setPreferredSize(new Dimension(140,54));
        txt_genres.setPreferredSize(new Dimension(360,54));
        txt_genres.setLineWrap(true);

        pgenres.add(lbl_genres);
        pgenres.add(txt_genres);
        pgenres.setMaximumSize(new Dimension(500,18));
        panel.add(pgenres);

        // Danceability
        JPanel pdanceability = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_danceability = new JLabel("Danceability");
        lbl_danceability.setOpaque(true);
        lbl_danceability.setBackground(Color.black);
        lbl_danceability.setForeground(Color.white);
        lbl_danceability.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField txt_danceability = new JTextField(String.valueOf(currentChanson.getDanceability()));
        txt_danceability.setBackground(Color.GRAY);
        txt_danceability.setForeground(Color.white);
        txt_danceability.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_danceability.setEditable(false);
        lbl_danceability.setPreferredSize(new Dimension(140,18));
        txt_danceability.setPreferredSize(new Dimension(360,18));
        pdanceability.add(lbl_danceability);
        pdanceability.add(txt_danceability);
        pdanceability.setMaximumSize(new Dimension(500,18));
        panel.add(pdanceability);

        // Energy
        JPanel penergy = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_energy = new JLabel("Energy");
        lbl_energy.setOpaque(true);
        lbl_energy.setBackground(Color.black);
        lbl_energy.setForeground(Color.white);
        JTextField txt_energy = new JTextField(String.valueOf(currentChanson.getEnergy()));
        txt_energy.setBackground(Color.GRAY);
        txt_energy.setForeground(Color.white);
        txt_energy.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_energy.setEditable(false);
        lbl_energy.setPreferredSize(new Dimension(140,18));
        txt_energy.setPreferredSize(new Dimension(360,18));
        penergy.add(lbl_energy);
        penergy.add(txt_energy);
        penergy.setMaximumSize(new Dimension(500,18));
        panel.add(penergy);

        // Loudness
        JPanel ploudness = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_loudness = new JLabel("Loudness");
        lbl_loudness.setOpaque(true);
        lbl_loudness.setBackground(Color.black);
        lbl_loudness.setForeground(Color.white);
        JTextField txt_loudness = new JTextField(String.valueOf(currentChanson.getLoudness()));
        txt_loudness.setBackground(Color.GRAY);
        txt_loudness.setForeground(Color.white);
        txt_loudness.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_loudness.setEditable(false);
        lbl_loudness.setPreferredSize(new Dimension(140,18));
        txt_loudness.setPreferredSize(new Dimension(360,18));
        ploudness.add(lbl_loudness);
        ploudness.add(txt_loudness);
        ploudness.setMaximumSize(new Dimension(500,18));
        panel.add(ploudness);

        // Nombre de chansons
        JPanel pnbchansons = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JLabel lbl_nbchansons = new JLabel("Nombre de chansons ");
        lbl_nbchansons.setOpaque(true);
        lbl_nbchansons.setBackground(Color.black);
        lbl_nbchansons.setForeground(Color.white);
        JTextField txt_nbchansons = new JTextField(String.valueOf(listeChanson.size()));
        txt_nbchansons.setBackground(Color.GRAY);
        txt_nbchansons.setForeground(Color.white);
        txt_nbchansons.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_nbchansons.setEditable(false);
        lbl_nbchansons.setPreferredSize(new Dimension(140,18));
        txt_nbchansons.setPreferredSize(new Dimension(360,18));
        pnbchansons.add(lbl_nbchansons);
        pnbchansons.add(txt_nbchansons);
        pnbchansons.setMaximumSize(new Dimension(500,18));
        panel.add(pnbchansons);

        // Timer
        JPanel ptimer = new JPanel();
        JLabel lbl_timer = new JLabel("Temps restant");
        lbl_timer.setOpaque(true);
        lbl_timer.setBackground(Color.black);
        lbl_timer.setForeground(Color.white);
        JTextField txt_timer = new JTextField();
        txt_timer.setBackground(Color.GRAY);
        txt_timer.setForeground(Color.white);
        txt_timer.setBorder(BorderFactory.createLineBorder(Color.black));
        txt_timer.setEditable(false);
        txt_timer.setPreferredSize(new Dimension(140,18));
        txt_timer.setPreferredSize(new Dimension(360,18));
        ptimer.add(lbl_timer);
        ptimer.add(txt_timer);
        ptimer.setMaximumSize(new Dimension(500,18));
        panel.add(ptimer);

        // Boutons
        JPanel pboutons = new JPanel();
        JButton btn_précédent = new JButton("Précédent");
        btn_précédent.setEnabled(false);
        btn_précédent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton btn_suivant = new JButton("Suivant");
        btn_suivant.setEnabled(false);
        btn_suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_précédent.setEnabled(true);
            }
        });
        pboutons.add(btn_précédent);
        pboutons.add(btn_suivant);

        JButton btn_play = new JButton("Play/Pause");
        btn_play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(1000, new ActionListener() {
                    int time = currentChanson.getDuree();
                    public void actionPerformed(ActionEvent e) {
                        txt_timer.setText(String.valueOf(time));
                        time--;
                        if (txt_timer.getText().equals("0")){
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
        });
        pboutons.add(btn_play);
        panel.add(pboutons);

        songfilter();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        songThread.run(listeChanson, btn_suivant, currentChanson);
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
                //tempArray = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                // tempArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
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

    public static void songfilter(String s){
        List<Chanson> l;
        switch(s){
            case "Titre":
                l = listeChanson.stream()
                        .filter(c -> c.getTitre().equals(currentChanson.getTitre()))
                        .collect(Collectors.toList());
                break;
            case "Album":
               l = listeChanson.stream()
                        .filter(c -> c.getAlbum().equals(currentChanson.getAlbum()))
                        .collect(Collectors.toList());
               break;
            case "Artiste":
                l = listeChanson.stream()
                        .filter(c -> c.getArtiste().equals(currentChanson.getArtiste()))
                        .collect(Collectors.toList());
                break;
            case "Genres":
                break;
            case "Danceability":
                l = listeChanson.stream()
                        .filter(c -> c.getDanceability() >=currentChanson.getDanceability() - 0.05 && c.getDanceability() <= currentChanson.getDanceability() + 0.05)
                        .collect(Collectors.toList());
                break;
            case "Energy":
                l = listeChanson.stream()
                        .filter(c -> c.getEnergy() >=currentChanson.getEnergy() - 0.05 && c.getEnergy() <= currentChanson.getEnergy() + 0.05)
                        .collect(Collectors.toList());
                break;
            case "Loudness":
                l = listeChanson.stream()
                        .filter(c -> (int) c.getLoudness() == (int) currentChanson.getLoudness())
                        .collect(Collectors.toList());
                break;
        }
    }
}
