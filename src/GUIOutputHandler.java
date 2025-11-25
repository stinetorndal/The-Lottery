import javax.swing.*;

public class GUIOutputHandler extends OutputHandler {
    private JTextArea textArea;

    public GUIOutputHandler(JTextArea textArea){
        this.textArea = textArea;
    }
    @Override
    public void displayMsg (String msg){
        textArea.append(msg + "\n");
    }
}
