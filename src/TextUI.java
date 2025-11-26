import java.util.Scanner;
import java.util.Set;

public class TextUI {
    private OutputHandler output;

    public TextUI(Scanner scanner) {
        this.output = new OutputHandler();
    }

    public void play() {
        Scanner console = new Scanner(System.in);
        boolean continueGame = true;

        while (continueGame) {
            //------Her promptes bruger for 6 lottotal------
            output.displayMsg("Skriv " + LotteryTheGame.NUMBERS + " lottotal mellem 1 og " + LotteryTheGame.MAX_NUMBER + ": ");

           //------vindertal genereres-------
            Set<Integer> ticket = LotteryTheGame.getTicket(console);
            Set<Integer> winning = LotteryTheGame.createWinningNumbers();

           //------Tal gemmes i DB------
            SaveNumbersInDB.saveWinningNumbers(winning);
            int matches = LotteryTheGame.calculateMatches(ticket, winning);
            int prize = LotteryTheGame.calculatePrize(matches);

            output.displayMsg("Din lotteriseddel: " + ticket);
            output.displayMsg("Vindertal: " + winning);
            output.displayMsg("Antal matches: " + matches);
            output.displayMsg("Gevinst: " + prize + " kr");

            output.displayMsg("Vil du spille igen? [j/n]");
            String answer = console.nextLine().trim().toLowerCase();

            if (!answer.equals("j")) {
                continueGame = false;
                output.displayMsg("Tak for dit bidrag til kapitalismen");
            }
        }
    }
}