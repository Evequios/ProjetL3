import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        String fileName = "names.txt";

        List<String> allLines;
        try {
            allLines = Files.readAllLines(Paths.get(fileName));
            Supplier<Stream<String>> supplier = () -> allLines.stream();

            //1
            System.out.println("1. Imprimez le nombre total de lignes du fichier names");
            System.out.println(supplier.get().count());
            System.out.println();

            //2
            System.out.println("2. Trouvez et imprimez tous les noms qui terminent par la lettre « a ».");
            supplier.get().filter(n -> n.endsWith("a")).forEach(System.out::println);
            System.out.println();

            //3
            System.out.println("3. Combien de nom terminent par « a » ?");
            System.out.println(supplier.get().filter(a -> a.endsWith("a")).count());
            System.out.println();

            //4
            System.out.println("4. Trouvez et imprimez une liste des noms uniques (sans duplication).");
            supplier.get().distinct().forEach(System.out::println);
            System.out.println();

            //5
            System.out.println("5. Combien de nom unique avez-vous trouvez en totale dans le fichier ?");
            System.out.println(supplier.get().distinct().count());
            System.out.println();


            //6
            System.out.println("6. Regroupez les noms par leurs fréquences, et imprimez chaque nom avec sa fréquence, dans un ordre décroissant de fréquences.");
            supplier.get()
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(System.out::println);
            System.out.println();

            //7
            System.out.println("7. A partir de la liste de noms sans duplication, remplacez chaque nom par sa taille (nombre de lettres), quelle est la taille minimale, maximale et moyenne d’un nom ?");
            System.out.println(supplier.get().distinct().collect(Collectors.summarizingInt(n -> n.length())).toString());
            System.out.println();


            //8
            System.out.println("8. A partir de la liste de noms sans duplication, regroupez les noms par leurs tailles (nombre de lettre par nom), imprimez le nombre de noms par taille (combien de nom se composent de 2 lettres, de 3 lettres, …), ordonnez par ordre croissant de taille.");
            supplier.get().distinct()
                    .map(n -> n.length())
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                    .forEach(System.out::println);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
