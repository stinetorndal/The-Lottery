import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class LottoGUI {

    public static void createAndShowGUI()

    {
        JFrame frame = new JFrame("Lotto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel =  new JPanel(new FlowLayout());

        //------6 felter til indtastning af tal------
        JTextField[] numberFields = new JTextField[6];
        for (int i = 0;  i < 6; i++) {
            numberFields[i] = new JTextField(2);
            inputPanel.add(new JLabel("Tal " +  (i+1) + ": "));
            inputPanel.add(numberFields[i]);
        }
        //------panel til træk-knap så den er synlig------
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton drawButton = new JButton("Træk tal: ");
        buttonPanel.add(drawButton);

        //------Output area------
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        GUIOutputHandler guiOutput = new GUIOutputHandler(textArea);

        drawButton.addActionListener( e -> {
            Set<Integer>ticket = new TreeSet<>();
            try {
                for (JTextField field: numberFields) {
                    int num = Integer.parseInt(field.getText());
                    if (num < 1 || num > LotteryTheGame.MAX_NUMBER){
                        guiOutput.displayMsg("Alle tal skal være mellem 1 og " + LotteryTheGame.MAX_NUMBER);
                        return;
                    }
                    ticket.add(num);
                }
                if (ticket.size() < 6) {
                    guiOutput.displayMsg("Du må ikke skrive samme tal flere gange. Skam dig. ");

                }
                Set <Integer> winning = LotteryTheGame.createWinningNumbers();
                SaveNumbersInDB.saveWinningNumbers(winning);

                int matches = LotteryTheGame.calculateMatches(ticket, winning);
                int prize = LotteryTheGame.calculatePrize(matches);

                guiOutput.displayMsg("Din lotteriseddel: " + ticket);
                guiOutput.displayMsg("Vindertal: " + winning);
                guiOutput.displayMsg("Antal matches: " + matches);
                guiOutput.displayMsg("Gevinst: " + prize + " kr");

            } catch (NumberFormatException ex) {
                guiOutput.displayMsg("Indtast venligst gyldige tal i alle felter. Skam dig");
            }
        });
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
