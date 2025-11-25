import java.util.Scanner;
import java.util.Set;

public class TextUI {
    private OutputHandler output;

    public TextUI(){
        this.output = new OutputHandler();
        }

    public void play() {
        Scanner console = new Scanner(System.in);

        output.displayMsg("Skriv" + LotteryTheGame.NUMBERS + " lottotal mellem 1 og " + LotteryTheGame.MAX_NUMBER + ": ");

        Set <Integer> ticket  = LotteryTheGame.getTicket(console);
        Set <Integer> winning= LotteryTheGame.createWinningNumbers();

        LottoDAO.saveWinningNumbers(winning);
        int matches =  LotteryTheGame.calculateMatches(ticket, winning);
        int prize = LotteryTheGame.calculatePrize(matches);

        output.displayMsg("Din lotteriseddel: " + ticket);
        output.displayMsg("Vindertal: " + winning);
        output.displayMsg("Antal matches: " + matches);
        output.displayMsg("Gevinst: " + prize + " kr");
    }
}
