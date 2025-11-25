import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class LottoGUI {

    public static void createAndShowGIU()

    {
        JFrame frame = new JFrame("Lotto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel =  new JPanel();
        inputPanel.setLayout(new FlowLayout());

        //------6 felter til indtastning af tal------
        JTextField[] numberFields = new JTextField[6];
        for (int i = 0;  i < 6; i++) {
            numberFields[i] = new JTextField(2);
            inputPanel.add(new JLabel("Tal " +  (i+1) + ": "));
            inputPanel.add(numberFields[i]);
        }
        JButton drawButton = new JButton("Træk tal: ");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        GUIOutputHandler guiOutput = new GUIOutputHandler(textArea);

        drawButton.addActionListener( e -> {
            Set<Integer>ticket = new HashSet<>();
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
                LottoDAO.saveWinningNumbers(winning);

                int matches = LotteryTheGame.calculateMatches(ticket, winning);
                int prize = LotteryTheGame.calculatePrize(matches);

                guiOutput.displayMsg("Din lotteriseddel: " + ticket);
                guiOutput.displayMsg("Vindertal: " + winning);
                guiOutput.displayMsg("Antal matches" + matches);
                guiOutput.displayMsg("Gevinst: " + prize + " kr");

            } catch (NumberFormatException ex) {
                guiOutput.displayMsg("Indtast venligst gyldige tal i alle felter. Skam dig");
            }
        });
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
