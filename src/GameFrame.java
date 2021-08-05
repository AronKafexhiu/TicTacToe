import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private JPanel panel;
    private JLabel result;
    private JButton[][] buttons = new JButton[3][3];
    private boolean isXTurn = true;
    private boolean gameOver = false;
    private String lastLetter;
    private int oWins = 0, xWins = 0;
    private int ans;
    
    public GameFrame() {
        setProperties();
        init();
        setUI();
    }
    public void setProperties() {
        setSize(300,300);
        setLocationRelativeTo(null);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void init() {
        result = new JLabel("");
        panel = new JPanel(new GridLayout(3,3));
        for(int i = 0;i < buttons.length;i++) {
            for(int j = 0;j < buttons[i].length;j++) {
                buttons[i][j] = new JButton();
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(isXTurn) {
                            if(!buttons[finalI][finalJ].getText().equals("O")) {
                                buttons[finalI][finalJ].setText("X");
                                lastLetter = "X";
                                isXTurn = false;
                            }
                            if(shouldEndGame()) {
                                if(lastLetter.equals("X")) {
                                    JOptionPane.showMessageDialog(null,"X Won");
                                    xWins++;
                                    ans = JOptionPane.showConfirmDialog(null,"O has won " + oWins + " and X " + xWins + ". Would you like to continue","Continue",JOptionPane.YES_NO_OPTION);
                                    if(ans == JOptionPane.YES_OPTION)
                                        resetButtons();
                                    else {
                                        if(xWins > oWins) {
                                            JOptionPane.showMessageDialog(null,"X won the whole game.");
                                            disableButtons();
                                        }
                                        else if(oWins > xWins) {
                                            JOptionPane.showMessageDialog(null,"O won the whole game.");
                                            disableButtons();
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null,"Draw");
                                            disableButtons();
                                        }
                                    }
                                }
                            }
                        } else {
                            if(!buttons[finalI][finalJ].getText().equals("X")) {
                                buttons[finalI][finalJ].setText("O");
                                lastLetter = "O";
                                isXTurn = true;
                            }
                            if(shouldEndGame()) {
                                if(lastLetter.equals("O")) {
                                    JOptionPane.showMessageDialog(null,"O Won");
                                    oWins++;
                                    ans = JOptionPane.showConfirmDialog(null,"O has won " + oWins + " and X " + xWins + ". Would you like to continue","Continue",JOptionPane.YES_NO_OPTION);
                                    if(ans == JOptionPane.YES_OPTION)
                                        resetButtons();
                                    else {
                                        if(xWins > oWins) {
                                            JOptionPane.showMessageDialog(null,"X won the whole game.");
                                            disableButtons();
                                        }
                                        else if(oWins > xWins) {
                                            JOptionPane.showMessageDialog(null,"O won the whole game.");
                                            disableButtons();
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null,"Draw");
                                            disableButtons();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }
    public void setUI() {
        for(int i = 0;i < buttons.length;i++) {
            for(int j = 0;j < buttons[i].length;j++) {
                panel.add(buttons[i][j]);
            }
        }

        add(panel);
    }
    public boolean shouldEndGame() {
        if(!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][2].getText()) && buttons[1][2].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if(!buttons[0][1].getText().equals("") && buttons[0][1].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][1].getText())) {
            return true;
        }
        if(!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[0][1].getText()) && buttons[0][1].getText().equals(buttons[0][2].getText())) {
            return true;
        }
        if(!buttons[1][0].getText().equals("") && buttons[1][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[1][2].getText())) {
            return true;
        }
        if(!buttons[2][0].getText().equals("") && buttons[2][0].getText().equals(buttons[2][1].getText()) && buttons[2][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if(!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][0].getText()) && buttons[1][0].getText().equals(buttons[2][0].getText())) {
            return true;
        }
        if(!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if(!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }
        return false;
    }
    public void resetButtons() {
        for(int i = 0;i < buttons.length;i++) {
            for(int j = 0;j < buttons[i].length;j++) {
                buttons[i][j].setText("");
            }
        }
        isXTurn = true;
    }
    public void disableButtons() {
        for(int i = 0;i < buttons.length;i++) {
            for(int j = 0;j < buttons[i].length;j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}