import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class StockMarketGame {

    static double currentMoney = 1000.00;
    static int numOfStocks = 0;
    static double curPriceOfStock = 100.00;
    static Random random = new Random();
    static final int PRICELIMIT = 20;
    static final int DELAYLIMIT = 500;

    public static void main(String[] args){
        JFrame f =new JFrame("Stock Market Mini-game");
        JButton buy = new JButton("Buy");
        JButton sell = new JButton("Sell");
        JButton quit = new JButton("Quit the game");
        JTextArea currentStockPriceText = new JTextArea(String.format("Current Price: $%.2f", curPriceOfStock));
        JTextArea currentStockAmountText = new JTextArea("Current # of stocks held: " + numOfStocks);
        JTextArea currentMoneyText = new JTextArea(String.format("Money: $%.2f", currentMoney));

        currentStockPriceText.setLineWrap(true); currentStockAmountText.setLineWrap(true);
        currentMoneyText.setLineWrap(true);

        buy.setBounds(100,250,120,40);
        sell.setBounds(200,250,120,40);
        quit.setBounds(150,350,120,40);

        currentStockPriceText.setBounds(0,0,140,20);
        currentStockAmountText.setBounds(0,20,150,20);
        currentMoneyText.setBounds(0,40,120,20);

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentMoney > curPriceOfStock){
                    numOfStocks++;
                    currentMoney-= curPriceOfStock;
                } else{
                    System.out.println("ERROR");
                }
            }
        });
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numOfStocks > 0){
                    numOfStocks--;
                    currentMoney+= curPriceOfStock;
                } else{
                    System.out.println("ERROR!");
                }
            }
        });

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

        Timer currentStockPriceTimer = new Timer(DELAYLIMIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStockPriceText.setText(String.format("Current Price: $%.2f", curPriceOfStock));
                if(random.nextBoolean()){
                    curPriceOfStock = curPriceOfStock + (random.nextDouble() + PRICELIMIT);
                } else{
                    curPriceOfStock = curPriceOfStock + (random.nextDouble() - PRICELIMIT);
                }
                if(curPriceOfStock <= 0){
                    curPriceOfStock = PRICELIMIT;
                }
            }
        });

        Timer currentStockAmountTimer = new Timer(DELAYLIMIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                currentStockAmountText.setText("Current # of stocks held: " + numOfStocks);
            }
        });
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
