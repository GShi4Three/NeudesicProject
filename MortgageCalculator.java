import java.util.Scanner;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MortgageCalculator {

    private static void amortizationScheduleTable(String[][] data){
        // Initiallizing frame
        JFrame f = new JFrame("Amortization Schedule Calculator");
        
        // Creating column names
        String[] columnNames = { "Payment Month", "Starting Balance", "Remaining Balance", "Payment Amount", "Total Balance Paid",
        "Principle Amount", "Total Principle Paid",  "Interest Amount", "Total Interest Paid" };
 
        // Initializing table
        JTable t = new JTable(data, columnNames);

        // Initialling scroll pane
        JScrollPane sp = new JScrollPane(t);
        // Adding scroll pane to frame
        f.add(sp);
        // Frame Size
        f.setSize(1500, 1000);
        // Frame Visiblility
        f.setVisible(true);
        // Closing window ends program
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        double principleAmount, annualInterestRate;
        int loanLengthYears;
        // Prompting users for necessary values to calculate the monthly mortgage payment
        System.out.println("Input a value for the principle loan amount: ");
        principleAmount = scanner.nextDouble();

        System.out.println("Input a percentage for the annual interest rate: ");
        annualInterestRate = scanner.nextDouble();

        System.out.println("Input a value for the loan period in years: ");
        loanLengthYears = scanner.nextInt();
        // Calculating monthly mortgage payment
        int loanLengthMonths = loanLengthYears * 12;
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        double monthlyMortgagePayment = principleAmount * (monthlyInterestRate * Math.pow((1 + monthlyInterestRate), loanLengthMonths)) / (Math.pow((1 + monthlyInterestRate), loanLengthMonths) - 1);
        // Calculating other important values to output in the terminal
        double totalInterest = (monthlyMortgagePayment * loanLengthMonths) - principleAmount;
        double totalPayment = principleAmount + totalInterest;
        // Outputting values to terminal
        System.out.println("\nThe monthly mortgage payment is $" + Math.round(monthlyMortgagePayment * 100) / 100.0);
        System.out.println("The number of monthly payments is " + loanLengthMonths);
        System.out.println("The total interest amount is $" + Math.round(totalInterest * 100) / 100.0);
        System.out.println("The total payment amount is $" + Math.round(totalPayment * 100) / 100.0);
        /*  
        * Inititializing variables that will increment/decrement as the for loop runs. They must be initialized outside the loop
        * because their current values are needed to calculate their future values
        */
        double startingBalance = principleAmount;
        double cumulativePaymentAmount = 0;
        double cumulativePrincipleAmount = 0;
        double cumulativeInterestAmount = 0;
        // Initializing a 2D string array to store the calculated values into the table
        String[][] data = new String[loanLengthMonths][9];
        
        for(int i = 1; i <= loanLengthMonths; i++){
            // Initializing values that change each month
            double monthlyInterestAmmount = startingBalance * monthlyInterestRate;
            double monthlyPrincipleAmmount = monthlyMortgagePayment - monthlyInterestAmmount;
            double remainingBalance = startingBalance - (monthlyMortgagePayment - (startingBalance * monthlyInterestRate));
            // Incrementing cumulative amounts
            cumulativePaymentAmount += monthlyMortgagePayment;
            cumulativeInterestAmount += monthlyInterestAmmount;
            cumulativePrincipleAmount += monthlyPrincipleAmmount;
            // Storing appropriate values in each cell of the table
            data[i - 1][0] = "Month " + i;
            data[i - 1][1] = "$" + Math.round(startingBalance * 100) / 100.0;
            data[i - 1][2] = "$" + Math.round(remainingBalance * 100) / 100.0;
            data[i - 1][3] = "$" + Math.round((monthlyMortgagePayment) * 100) / 100.0;
            data[i - 1][4] = "$" + Math.round((cumulativePaymentAmount) * 100) / 100.0;
            data[i - 1][5] = "$" + Math.round((monthlyPrincipleAmmount) * 100) / 100.0;
            data[i - 1][6] = "$" + Math.round((cumulativePrincipleAmount) * 100) / 100.0;
            data[i - 1][7] = "$" + Math.round((monthlyInterestAmmount) * 100) / 100.0;
            data[i - 1][8] = "$" + Math.round((cumulativeInterestAmount) * 100) / 100.0;
            // Setting next month's starting balance to the current month's remaining balance
            startingBalance = remainingBalance;
        }
        // Opening the window and displaying the table
        amortizationScheduleTable(data);

        scanner.close();
    }
}
