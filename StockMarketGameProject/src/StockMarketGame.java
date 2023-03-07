import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class StockMarketGame {

    static double currentMoney = 1000.00; // Players current amount of money
    static int numOfStocks = 0; // How many stock the player currently has
    static double curPriceOfStock = 100.00; // How much 1 stock currently goes for
    static Random random = new Random();
    static final int PRICELIMIT = 20; // This is how much money can be gained or lost per tick on the stock
    static final int DELAYLIMIT = 500; // This is how many miliseconds need to pass per 1 tick

    public static void main(String[] args){
        // I am setting up all of my objects here so I can use them
        JFrame f =new JFrame("Stock Market Mini-game");
        JButton buy = new JButton("Buy");
        JButton sell = new JButton("Sell");
        JButton quit = new JButton("Quit the game");
        JTextArea currentStockPriceText = new JTextArea(String.format("Current Price: $%.2f", curPriceOfStock));
        JTextArea currentStockAmountText = new JTextArea("Current # of stocks held: " + numOfStocks);
        JTextArea currentMoneyText = new JTextArea(String.format("Money: $%.2f", currentMoney));

        // This makes it so I can actually print out the entire string
        currentStockPriceText.setLineWrap(true); currentStockAmountText.setLineWrap(true);
        currentMoneyText.setLineWrap(true);

        buy.setBounds(100,250,120,40);
        sell.setBounds(200,250,120,40);
        quit.setBounds(150,350,120,40);

        currentStockPriceText.setBounds(0,0,140,20);
        currentStockAmountText.setBounds(0,20,150,20);
        currentMoneyText.setBounds(0,40,120,20);

        // This is the code to handle when a player wants to buy a stock
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentMoney > curPriceOfStock){
                    numOfStocks++;
                    currentMoney-= curPriceOfStock;
                } else{
                    // TODO: Make a JLabel to do this instead
                    System.out.println("ERROR");
                }
            }
        });
        // THis is the code to handle when a player wants to see a stock
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numOfStocks > 0){
                    numOfStocks--;
                    currentMoney+= curPriceOfStock;
                } else{
                    // TODO: Make a JLabel to do this instead
                    System.out.println("ERROR!");
                }
            }
        });

        // This makes it so when the player clicks on quit game, it stops the code from running
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        f.add(buy); f.add(sell); f.add(quit); // Adding buttons
        f.add(currentStockPriceText); // Label for current stock price
        f.add(currentStockAmountText); // Label for current stock amount
        f.add(currentMoneyText); // Label for current amount of money

        f.setSize(400,500);
        f.setLayout(null);
        f.setVisible(true);

        // This makes it so the stock can go up or down every tick
        Timer currentStockPriceTimer = new Timer(DELAYLIMIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStockPriceText.setText(String.format("Current Price: $%.2f", curPriceOfStock));
                if(random.nextBoolean()){ // Theres a 50/50 chance that it goes up or down
                    curPriceOfStock = curPriceOfStock + (random.nextDouble() + PRICELIMIT);
                } else{
                    curPriceOfStock = curPriceOfStock + (random.nextDouble() - PRICELIMIT);
                }
                if(curPriceOfStock <= 0){ // This makes sure that the stock doesn't go to 0 or lower
                    curPriceOfStock = PRICELIMIT;
                }
            }
        });

        // Updates the text for currentStockAmountText
        Timer currentStockAmountTimer = new Timer(DELAYLIMIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStockAmountText.setText("Current # of stocks held: " + numOfStocks);
            }
        });
        // Updates the text for currentMoneyAmount
        Timer currentMoneyAmountTimer = new Timer(DELAYLIMIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMoneyText.setText(String.format("Money: $%.2f", currentMoney));
            }
        });

        currentStockPriceTimer.start();
        currentStockAmountTimer.start();
        currentMoneyAmountTimer.start();


    }
}
