import java.util.Map;
import java.util.Scanner;

public class Lottery {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
        System.out.println("******* Interface: *******");
        System.out.println("1. Konsol (TextUI)");
        System.out.println("2. GUI");
        System.out.println("3. Vis statistik");
        System.out.println("4. Afslut");
        System.out.println("Vælg en af mulighederne:");

        String line = scanner.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(line);
        } catch (NumberFormatException  e){
            System.out.println("Ugyldigt input, skriv et tal");
            continue;
        }

        switch (choice){
            case 1 -> new TextUI(scanner).play();
            case 2 -> LottoGUI.createAndShowGUI();
            case 3 -> showStatistics();
            case 4 -> {
                System.out.println("Farvel og tak for i dag!!");
                System.exit(0);
            }
            default -> System.out.println   ("Ugyldigt valg, du er retarderet og programmet afsluttes.");
        }
        }
}

    //------Statistikfunktionen------
    private static void showStatistics(){
        System.out.println("\n********* STATISTIK ********");

        Map<Integer, Integer> top4 =  LottoStats.getMostFrequent(Queries.TOP4_FREQUENT);
        int total = LottoStats.getSingleValue(Queries.TOTAL_DRAWS, "total");

        System.out.println("De fire hyppigste tal: ");
        top4.forEach((num, freq) ->
                System.out.println("Tal " + num + ": " + freq + " gange"));

        System.out.println("\nAntal trækning i alt: " + total);
    }
}