import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] boardButtons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        currentPlayer = 'X';
        boardButtons = new JButton[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col] = new JButton();
                boardButtons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));

                int finalRow = row;
                int finalCol = col;

                boardButtons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (boardButtons[finalRow][finalCol].getText().isEmpty()) {
                            boardButtons[finalRow][finalCol].setText(Character.toString(currentPlayer));
                            if (checkWin()) {
                                statusLabel.setText(currentPlayer + " wins!");
                                disableButtons();
                            } else if (checkDraw()) {
                                statusLabel.setText("It's a draw!");
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                statusLabel.setText("Current player: " + currentPlayer);
                            }
                        }
                    }
                });

                add(boardButtons[row][col]);
            }
        }

        statusLabel = new JLabel("Current player: " + currentPlayer);
        add(statusLabel);

        setVisible(true);
    }

    private boolean checkWin() {
        String[] lines = new String[8];

        // Rows
        for (int i = 0; i < 3; i++) {
            lines[i] = boardButtons[i][0].getText() + boardButtons[i][1].getText() + boardButtons[i][2].getText();
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            lines[i + 3] = boardButtons[0][i].getText() + boardButtons[1][i].getText() + boardButtons[2][i].getText();
        }

        // Diagonals
        lines[6] = boardButtons[0][0].getText() + boardButtons[1][1].getText() + boardButtons[2][2].getText();
        lines[7] = boardButtons[0][2].getText() + boardButtons[1][1].getText() + boardButtons[2][0].getText();

        for (String line : lines) {
            if (line.equals("XXX") || line.equals("OOO")) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardButtons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardButtons[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGame();
            }
        });
    }
}