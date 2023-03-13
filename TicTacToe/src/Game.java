import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This is a simple GUI Tic Tac Toe game that I made. I want to improve the code in the future since it doesn't feel
 * the best, but it works for now. This class has all the methods to make the game work and run. The only issue
 * that this code has right now is when someone wins or there are no more valid moves, it will just restart and not
 * alert the user that someone has won or the game ended in a draw.
 *
 * @author mattjone5
 * @version 1.0.0
 * @since 3/12/22
 */
public class Game {
    /**
     * This is the brains for how the computer will make its move. It will pick a random spot on the board, and it will
     * check to see if the spot is available or not. If it is, then it will make its move there and end the code. If the
     * spot is not valid, it will pick another set of numbers and try the process again until it picks a valid spot.
     *
     * @param buttons A JButton 2D array that represents the board
     */
    private static void computerMove(JButton[][] buttons){
        Random random = new Random();
        int x = random.nextInt(3); int y = random.nextInt(3);
        if(isThereValidMove(buttons)){
            while(!buttons[x][y].getText().equals("")){
                x = random.nextInt(3); y = random.nextInt(3);
            }
            buttons[x][y].setText("O");
            buttons[x][y].setEnabled(false);
        }
    }

    /**
     * This will check to see if there are any valid moves on the board. It'll do this by checking each spot one by one.
     * If it finds an empty spot, it'll return true. Otherwise, it will return false if the entire board is filled. An
     * empty spot is defined as a button that has the text of an empty String.
     *
     * @param board A JButton 2D array that represents the board
     * @return True if there is at least one empty spot. False otherwise
     */
    private static boolean isThereValidMove(JButton[][] board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j].getText().equals("")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This is to see if someone has won the game. I don't really like how I have it set up since it feels really
     * inefficient, but it works for now. This will run a for loop twice, first to check if the 'X' has a win, then to
     * check if the 'O' has a win. If a matching pair is found (An if/else-if returns true) then, it will flip the
     * variable isWin to true and will not reset the curCheck variable. If no one has won, then the variable curCheck
     * will be set to "". This will return whoever the winner is as a String, but if no one has won, then it'll return
     * a ""
     *
     * @param board A JButton 2D array that represents the board
     * @return "X" if the X's won the game, "O" if the O's won the game, "" if no one was won
     */
    private static String checkForWin(JButton[][] board){
        String curCheck = "X"; // This is to see who are trying to find a win for
        boolean isWin = false;
        for(int i = 0; i < 2; i++){
            if (board[0][0].getText().equals(curCheck) && board[0][1].getText().equals(curCheck) && board[0][2].getText().equals(curCheck)){
                isWin = true;
                break;
            } else if (board[1][0].getText().equals(curCheck) && board[1][1].getText().equals(curCheck) && board[1][2].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if (board[2][0].getText().equals(curCheck) && board[2][1].getText().equals(curCheck) && board[2][2].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if (board[0][0].getText().equals(curCheck) && board[1][0].getText().equals(curCheck) && board[2][0].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if (board[0][1].getText().equals(curCheck) && board[1][1].getText().equals(curCheck) && board[2][1].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if (board[0][2].getText().equals(curCheck) && board[1][2].getText().equals(curCheck) && board[2][2].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if(board[0][0].getText().equals(curCheck) && board[1][1].getText().equals(curCheck) && board[2][2].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else if (board[0][2].getText().equals(curCheck) && board[1][1].getText().equals(curCheck) && board[2][0].getText().equals(curCheck)) {
                isWin = true;
                break;
            } else{
                curCheck = "O"; // This runs if the X's didn't win
            }
        }
        if(!isWin){
            curCheck = ""; // We need to make sure that we set curCheck to "" if no one won, thus why this is here
        }
        return curCheck;
    }

    /**
     * This is just to reset the board so that no moves have been made
     *
     * @param buttons A JButton 2D array that represents the board
     */
    private static void resetBoard(JButton[][] buttons){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        // I didn't want to make these static and I wanted it to be in the main method, so I did it this way
        final int[] pScore = {0};
        final int[] cScore = {0};

        // Set up the Frame
        JFrame f = new JFrame("Tic Tac Toe");

        // Set up the Labels for Scores and other stuff
        JTextArea userScore = new JTextArea(String.format("User Score: %d", pScore[0]));
        JTextArea comScore = new JTextArea(String.format("Computer Score: %d", cScore[0]));
        JTextArea winMsg = new JTextArea();

        // Set up the buttons for input and other events
        JButton quitButton = new JButton("Quit Game");
        JButton restartButton = new JButton("Restart Game");
        JButton[][] buttons = new JButton[3][3];
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ActionListener() {
                    /**
                     * Basically each time a button is pressed, the game moves on with this code. I honestly think that
                     * there is a better way to do this, but I'll have to play around with this.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean hasChecked = false;
                        JButton button = (JButton) e.getSource();
                        button.setText("X");
                        button.setEnabled(false);
                        switch (checkForWin(buttons)) {
                            case "X" -> {
                                winMsg.setText("You Win!");
                                winMsg.setVisible(true);
                                pScore[0]++;
                                userScore.setText(String.format("User Score: %d", pScore[0]));
                                hasChecked = true;
                            }
                            case "O" -> {
                                winMsg.setText("You Lose!");
                                winMsg.setVisible(true);
                                cScore[0]++;
                                comScore.setText(String.format("Computer Score: %d", cScore[0]));
                                hasChecked = true;
                            }
                        }
                        // I want to make sure that the computer doesn't move if the play won the game
                        if(checkForWin(buttons).equals("")){
                            computerMove(buttons);
                        }
                        if(!hasChecked){
                            switch (checkForWin(buttons)) {
                                case "X" -> {
                                    winMsg.setText("You Win!");
                                    winMsg.setVisible(true);
                                    pScore[0]++;
                                    userScore.setText(String.format("User Score: %d", pScore[0]));
                                }
                                case "O" -> {
                                    winMsg.setText("You Lose!");
                                    winMsg.setVisible(true);
                                    cScore[0]++;
                                    comScore.setText(String.format("Computer Score: %d", cScore[0]));
                                }
                            }
                        }
                        if(winMsg.isVisible() || !isThereValidMove(buttons)){
                            resetBoard(buttons);
                            winMsg.setVisible(false);
                        }
                    }
                });
            }
        }

        // Set up the sizes of everything
        int curX = 200;
        int curY = 100;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setBounds(curX, curY, 100,100);
                curX += 100;
            }
            curX = 200;
            curY += 100;
        }
        userScore.setBounds(300,0,100,20);
        comScore.setBounds(300,20,110,20);
        winMsg.setBounds(300,50,100,20);
        quitButton.setBounds(400,450,100,20);
        restartButton.setBounds(200,450,112,20);

        // I need to hide the win message, so it's not shown until it need to be done
        winMsg.setVisible(false);

        // Now let's add some action listeners
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(buttons);
            }
        });

        // Add everything to the Frame
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                f.add(buttons[i][j]);
            }
        }
        f.add(userScore); f.add(comScore);
        f.add(quitButton); f.add(restartButton);
        f.add(winMsg);

        // Final touches to the Frame
        f.setSize(800,800);
        f.setLayout(null);
        f.setVisible(true);
    }
}