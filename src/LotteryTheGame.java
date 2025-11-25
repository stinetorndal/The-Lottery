import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LotteryTheGame {

    public static final int NUMBERS = 6;
    public static final int MAX_NUMBER = 40;
    public static final int PRIZE = 500;

    //------Vindertal genereres------
    public static Set<Integer> createWinningNumbers() {
        Set<Integer> winning = new TreeSet<>();
        Random r = new Random();
        while (winning.size() < NUMBERS) {
            int number = r.nextInt(MAX_NUMBER) + 1;
            winning.add(number);
        }
        return winning;
    }
    // ------Matcher tal------
    public static int calculateMatches(Set<Integer> ticket, Set<Integer> winning) {
        Set<Integer> matches = new TreeSet<>(ticket);
        matches.retainAll(winning);
        return matches.size();
    }
    //------Beregning af gevinst------
    public static int calculatePrize(int matches) {
        return (int) (PRIZE * Math.pow(2, matches));
    }

    //------Min lotteriseddel læses fra konsollen------
    public static Set<Integer> getTicket(Scanner console) {
        Set<Integer> ticket = new TreeSet<>();
        while (ticket.size() < NUMBERS) {
            int number = console.nextInt();
            ticket.add(number);
        }

        return ticket;
    }

}
